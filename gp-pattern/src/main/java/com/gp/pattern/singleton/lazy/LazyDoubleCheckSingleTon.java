package com.gp.pattern.singleton.lazy;

import java.io.Serializable;

//双重检查锁的单例模式
public class LazyDoubleCheckSingleTon implements Serializable {

    private static LazyDoubleCheckSingleTon lazy = null;

    private LazyDoubleCheckSingleTon() {
        if (lazy != null) {
            throw new RuntimeException("不允许创建多个实例");
        }

    }

    public synchronized static LazyDoubleCheckSingleTon getInstance() {
        if (null == lazy) {
            synchronized (LazyDoubleCheckSingleTon.class) {
                if (null == lazy) {
                    lazy = new LazyDoubleCheckSingleTon();
                }
            }
        }
        return lazy;
    }


}
