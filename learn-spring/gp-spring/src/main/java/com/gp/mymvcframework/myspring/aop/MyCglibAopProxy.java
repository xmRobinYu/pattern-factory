package com.gp.mymvcframework.myspring.aop;

import com.gp.mymvcframework.myspring.aop.intercept.MyMethodInvocation;
import com.gp.mymvcframework.myspring.aop.support.MyAdvisedSupport;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by Tom on 2019/4/14.
 */
public class MyCglibAopProxy implements MyAopProxy, MethodInterceptor {

    private MyAdvisedSupport advised;

    public MyCglibAopProxy(MyAdvisedSupport config) {
        advised = config;
    }

    @Override
    public Object getProxy() {
        return getProxy(advised.getTargetClass());
    }

    @Override
    public Object getProxy(Class clazz) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        // 设置回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        List<Object> interceptorsAndDynamicMethodMatchers = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, this.advised.getTargetClass());
        MyMethodInvocation invocation = new MyMethodInvocation(this.advised.getTarget(), method, args, interceptorsAndDynamicMethodMatchers);
        return invocation.proceed();
    }


}
