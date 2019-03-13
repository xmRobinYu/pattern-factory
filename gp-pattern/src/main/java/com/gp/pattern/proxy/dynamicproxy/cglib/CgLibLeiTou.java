package com.gp.pattern.proxy.dynamicproxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CgLibLeiTou implements MethodInterceptor {

    public Object getInstance(Class<?> clazz) {
        //相当于Proxy，代理的工具类
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();

    }

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object object = methodProxy.invokeSuper(o, objects);
        after();
        return object;
    }

    private void before() {
        System.out.println("根据简历技能，筛选适合的公司");
        System.out.println("开始安排面试");
    }

    private void after() {
        System.out.println("OK的话，明天报道！");
    }
}
