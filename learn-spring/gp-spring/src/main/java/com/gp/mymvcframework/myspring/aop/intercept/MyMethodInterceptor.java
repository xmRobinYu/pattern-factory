package com.gp.mymvcframework.myspring.aop.intercept;

/**
 * Created by Tom on 2019/4/14.
 */
public interface MyMethodInterceptor {
    Object invoke(MyMethodInvocation invocation) throws Throwable;


}
