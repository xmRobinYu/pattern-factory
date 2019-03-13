package com.gp.pattern.singleton.lazy;

import junit.framework.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

//懒汉式单例
//在外部需要使用的时候才进行实例化
public class LazySimpleSingleTonTest {

    private static final int CONCURRENT_THREAD_NUMBER = 200;

    @Test
    public void concurrentGetSingletonWithCyclicBarrierTest() throws Exception {
        final CyclicBarrier barrier = new CyclicBarrier(CONCURRENT_THREAD_NUMBER);
        final CountDownLatch latch = new CountDownLatch(CONCURRENT_THREAD_NUMBER);

        // 统计并发获取到的单例对象引用地址，用于验证获取到的单例是否是同一个
        final List<String> instanceUrl = new Vector<String>();

        for (int i = 0; i < CONCURRENT_THREAD_NUMBER; i++) {
            new Thread(() -> {
                try {
                    barrier.await();
                    LazySimpleSingleTon instance = LazySimpleSingleTon.getInstance();
                    System.out.println("当前时间: {" + System.currentTimeMillis() + "}, 单例: {" + instance.toString() + "}");
                    instanceUrl.add(instance.toString());
                    latch.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }

        latch.await();

        System.out.println("模拟并发线程数：{" + CONCURRENT_THREAD_NUMBER + "}, 实际并发线程数：{" + instanceUrl.size() + "}");
     //   Assert.assertEquals("线程数：" + CONCURRENT_THREAD_NUMBER, "线程数：" + "   " + instanceUrl.size());

        // 验证并发获取到的单例是否是同一个实例
        this.verifySingletonsIsSameOrNotWithAssert(instanceUrl);
    }

    @Test
    public void concurrentGetSingletonWithCountDownLatchTest() throws Exception {
        final CountDownLatch workThreadLatch = new CountDownLatch(CONCURRENT_THREAD_NUMBER);
        final CountDownLatch mainThreadLatch = new CountDownLatch(CONCURRENT_THREAD_NUMBER);

        // 统计并发获取到的单例对象引用地址，用于验证获取到的单例是否是同一个
        final List<String> instanceUrl = new Vector<String>();

        for (int i = 0; i < CONCURRENT_THREAD_NUMBER; i++) {
            new Thread(() -> {
                try {
                    workThreadLatch.await();

                    LazySimpleSingleTon instance = LazySimpleSingleTon.getInstance();

                    System.out.println("当前时间: {" + System.currentTimeMillis() + "}, 单例: {" + instance.toString() + "}");
                    instanceUrl.add(instance.toString());

                    mainThreadLatch.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("线程 {} 异常: {}" + Thread.currentThread().getName() + "   " + e.getMessage());
                }
            }).start();

            workThreadLatch.countDown();
        }

        mainThreadLatch.await();

        System.out.println("模拟并发线程数：{" + CONCURRENT_THREAD_NUMBER + "}, 实际并发线程数：{" + instanceUrl.size() + "}");
      //  Assert.assertEquals("线程数：" + CONCURRENT_THREAD_NUMBER, "线程数：" + instanceUrl.size());

        // 验证并发获取到的单例是否是同一个实例
        this.verifySingletonsIsSameOrNotWithAssert(instanceUrl);
    }

    //发射破坏单例测试
    @Test
    public void breakSingletonByReflectionTest() throws Exception {
        LazySimpleSingleTon singleton = LazySimpleSingleTon.getInstance();

        Constructor<LazyInnerClassSingleton> constructor = LazyInnerClassSingleton.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        LazyInnerClassSingleton singletonFromReflection = constructor.newInstance();

        System.out.println("正常单例：{" + singleton.toString() + "}");
        System.out.println("反射单例：{" + singletonFromReflection.toString() + "}");
        Assert.assertEquals(singleton.toString(), singletonFromReflection.toString());
    }


    //字节码破坏单例测试

    //序列化就是说把内存中的状态通过转换成字节码的形式
    //从而转换一个IO流，写入到其他地方(可以是磁盘、网络IO)
    //内存中状态给永久保存下来了

    //反序列化
    //讲已经持久化的字节码内容，转换为IO流
    //通过IO流的读取，进而将读取的内容转换为Java对象
    //在转换过程中会重新创建对象new
    @Test
    public void breakSingletonBySeriableTest() throws Exception {
        try {
            LazySimpleSingleTon  s1 = (LazySimpleSingleTon ) LazySimpleSingleTon .getInstance();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(s1);
            oos.flush();
            oos.close();

            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);

            LazySimpleSingleTon s2 = (LazySimpleSingleTon)ois.readObject();
            ois.close();

            System.out.println(s1);
            System.out.println(s2);
            System.out.println(s1 == s2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 验证并发获取到的单例是否是同一个实例
     */
    private void verifySingletonsIsSameOrNotWithAssert(final List<String> instanceUrl) {
        for (int i = 0; i < instanceUrl.size(); i++) {
            if (i == instanceUrl.size() - 1) {
                Assert.assertEquals(instanceUrl.get(i - 1), instanceUrl.get(i));
                break;
            }

            Assert.assertEquals(instanceUrl.get(i), instanceUrl.get(i + 1));
        }
    }


}
