package com.gp.mymvcframework.myspring.beans.factory.support;


import com.gp.mymvcframework.myspring.beans.factory.config.MyAutowireCapableBeanFactory;
import com.gp.mymvcframework.myspring.beans.factory.config.MyBeanDefinition;

public class MyAbstractBeanDefinition implements MyBeanDefinition {

    //beanClassName
    // 一旦一个共享变量（类的成员变量、类的静态成员变量）被volatile修饰之后，那么就具备了两层语义：
    //1）保证了不同线程对这个变量进行操作时的可见性，即一个线程修改了某个变量的值，这新值对其他线程来说是立即可见的。
    //2）禁止进行指令重排序。
    private volatile String beanClass;

    //是否开启开启懒汉式调用方式（类加载时自动初始化），默认等类调用的时候再初始化
    private boolean lazyInit = false;

    //注入方式
    private int autowireMode = MyAutowireCapableBeanFactory.AUTOWIRE_NO;

    //工厂类名称
    private String factoryBeanName;

    @Override
    public String getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(String beanClass) {
        this.beanClass = beanClass;
    }

    @Override
    public boolean isLazyInit() {
        return lazyInit;
    }

    public void setLazyInit(boolean lazyInit) {
        this.lazyInit = lazyInit;
    }

    @Override
    public int getAutowireMode() {
        return autowireMode;
    }

    public void setAutowireMode(int autowireMode) {
        this.autowireMode = autowireMode;
    }

    @Override
    public String getFactoryBeanName() {
        return factoryBeanName;
    }

    public void setFactoryBeanName(String factoryBeanName) {
        this.factoryBeanName = factoryBeanName;
    }
}
