package com.gp.mymvcframework.myspring.aop;

import com.gp.mymvcframework.myspring.aop.intercept.MyMethodInvocation;
import com.gp.mymvcframework.myspring.aop.support.MyAdvisedSupport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * Created by Tom on 2019/4/14.
 */
public class MyJdkDynamicAopProxy implements  MyAopProxy,InvocationHandler{

    private MyAdvisedSupport advised;

    public MyJdkDynamicAopProxy(MyAdvisedSupport config){
        this.advised = config;
    }

    @Override
    public Object getProxy() {
        return getProxy(this.advised.getTargetClass());
    }

    @Override
    public Object getProxy(Class<?> clzss) {
        return Proxy.newProxyInstance(clzss.getClassLoader(),this.advised.getTargetClass().getInterfaces(),this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        List<Object> interceptorsAndDynamicMethodMatchers = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method,this.advised.getTargetClass());
        MyMethodInvocation invocation = new MyMethodInvocation(this.advised.getTarget(),method,args,interceptorsAndDynamicMethodMatchers);
        return invocation.proceed();
    }
}
