package com.gp.pattern.observer.pool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Observable;
import java.util.Observer;
import java.util.Stack;

//继承Observable接口来实现观察者模式
public class PoolServer extends Observable {
    // 创建数据库连接池
    private Stack<ConnProxy> pool = new Stack<ConnProxy>();
    /*
     * threadlocal是一个数据结构，有点像HashMap，可以保存"key : value"键值对，
     * 但是一个ThreadLocal只能保存一个，并且各个线程的数据互不干扰。
     * 这里使用threadlocal将连接作为对象放到threadloacal里,实现只有该线程自己可以访问这个连接。
     */
    private ThreadLocal<ConnProxy> threadLocal = new ThreadLocal<ConnProxy>();

    // 在构造器初始化五个连接
    public PoolServer() {
        // 设置观察者实现Observer接口的唯一方法update
        this.addObserver(new Observer() {
            public void update(Observable o, Object arg) {
                try {
                    for (int i = 0; i < 5; i++) {
                        String driver = "com.mysql.jdbc.Driver";
                        Class.forName(driver);
                        Connection conn = DriverManager.getConnection("jdbc:mysql://xxx:3306/qq_test", "xxx",
                                "xx");
                        ConnProxy connProxy = new ConnProxy();
                        connProxy.setConn(conn);
                        pool.push(connProxy);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        this.setChanged();
        this.notifyObservers();
    }

    //代理连接类
    public class ConnProxy {
        // 连接属性
        private Connection conn;
        private int idle;// 时间

        public void setIdle(int idle) {
            this.idle = idle;
        }

        public void setConn(Connection conn) {
            this.conn = conn;
        }

        public Connection getConn() {
            return conn;
        }

        public ConnProxy() {
            // 设置空闲时间，如果空闲时间超过10秒，则回收
            new Thread(new Runnable() {
                public void run() {
                    try {
                        while (true) {
                            Thread.sleep(1000);
                            idle += 1000;
                            if (idle > 100000) {
                                synchronized (Object.class) {
                                    if (pool.size() > 5) {
                                        conn.close();
                                        pool.remove(ConnProxy.this);
                                        break;
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    // 代理连接方法
    public Connection getConn() {
        return (Connection) Proxy.newProxyInstance(Connection.class.getClassLoader(), new Class[]{Connection.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, final Method method, final Object[] args) throws Throwable {
                        // 判断threadlocal是否有连接对象
                        if (threadLocal.get() == null) {
                            Q:
                            while (true) {
                                // 监控连接池是不是空，如果不为空，则将连接放到threadlocal
                                synchronized (Object.class) {
                                    while (!pool.isEmpty()) {
                                        threadLocal.set(pool.pop());
                                        break Q;
                                    }
                                }
                                // 设置被观察者，监控到空时，则去通知观察者创建新的连接
                                while (pool.isEmpty()) {
                                    setChanged();
                                    notifyObservers();
                                    break;
                                }
                            }
                        }
                        // 获取代理连接
                        ConnProxy p = threadLocal.get();
                        if (method.getName().equals("close")) {
                            p.setIdle(0);// 被使用过的idle从0开始
                            pool.push(p);
                            return null;// 不让其调用真正的close方法
                        } else {
                            Connection conn = p.getConn();
                            return method.invoke(conn, args);// 调用其真正的connetion方法
                        }
                    }
                });
    }

    // 测试
    public static void main(String[] args) throws Exception {
        final PoolServer poolServer = new PoolServer();
        // Thread.sleep(1000);
        for (int i = 0; i < 20; i++) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        Connection conn = poolServer.getConn();
                        conn.prepareStatement("select * from jxf_merchant");
                        System.out.println(conn);
                        conn.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }
        System.in.read();
    }
}
