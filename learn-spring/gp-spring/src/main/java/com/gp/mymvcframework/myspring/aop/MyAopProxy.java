package com.gp.mymvcframework.myspring.aop;

/**
 * Created by Tom.
 */
public interface MyAopProxy {


    Object getProxy();


    Object getProxy(Class<?> clazz);
}
