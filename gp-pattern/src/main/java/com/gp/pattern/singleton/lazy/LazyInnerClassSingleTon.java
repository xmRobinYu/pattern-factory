package com.gp.pattern.singleton.lazy;

//这种形式兼顾饿汉式的内存浪费，也兼顾synchronized性能问题
//完美地屏蔽了这两个缺点
//史上最牛B的单例模式的实现方式
public class LazyInnerClassSingleton {
    //默认使用LazyInnerClassGeneral的时候，会先初始化内部类
    //如果没使用的话，内部类是不加载的
    private LazyInnerClassSingleton() {
        if (LazyInnerHolder.lazy != null) {
            throw new RuntimeException("不允许创建多个实例");
        }
    }
    public final static LazyInnerClassSingleton getInstance() {
        return LazyInnerHolder.lazy;
    }
    //默认不加载
    private static class LazyInnerHolder {
        private static final LazyInnerClassSingleton lazy = new LazyInnerClassSingleton();
    }
}
