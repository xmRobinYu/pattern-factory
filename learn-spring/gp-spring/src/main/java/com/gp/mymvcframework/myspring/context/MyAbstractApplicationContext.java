package com.gp.mymvcframework.myspring.context;

/**
 * IOC容器实现的顶层设计
 * Created by Tom.
 */
public abstract class MyAbstractApplicationContext {

    //受保护，只提供给子类重写
    protected void refresh() throws Exception {}

}
