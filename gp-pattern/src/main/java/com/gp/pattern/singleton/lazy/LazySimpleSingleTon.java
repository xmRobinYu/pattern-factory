package com.gp.pattern.singleton.lazy;

import java.io.Serializable;

//懒汉式单例
//在外部需要使用的时候才进行实例化

public class LazySimpleSingleTon implements Serializable {

    private static LazySimpleSingleTon lazy = null;

    private LazySimpleSingleTon() {
        if (lazy != null) {
            throw new RuntimeException("不允许创建多个实例");
        }

    }

    public synchronized static LazySimpleSingleTon getInstance() {
        if (null == lazy) {
            lazy = new LazySimpleSingleTon();
        }
        return lazy;
    }


}
