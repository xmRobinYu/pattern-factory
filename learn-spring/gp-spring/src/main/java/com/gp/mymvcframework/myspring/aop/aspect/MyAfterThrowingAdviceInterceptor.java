package com.gp.mymvcframework.myspring.aop.aspect;

import com.gp.mymvcframework.myspring.aop.intercept.MyMethodInterceptor;
import com.gp.mymvcframework.myspring.aop.intercept.MyMethodInvocation;

import java.lang.reflect.Method;


public class MyAfterThrowingAdviceInterceptor extends MyAbstractAspectAdvice implements MyAdvice, MyMethodInterceptor {

    private String throwingName;

    public MyAfterThrowingAdviceInterceptor(Method method, Object aspectTarget) {
        super(method, aspectTarget);
    }

    @Override
    public Object invoke(MyMethodInvocation mi) throws Throwable {
        //从被织入的代码中才能拿到，JoinPoint
        Object object = null;
        try {
            object = mi.proceed();
        } catch (Throwable e) {
            invokeAdviceMethod(mi, null, e.getCause());
            setThrowName(e.getMessage());
            throw e;
        }
        return object;
    }

    public void setThrowName(String throwName) {
        this.throwingName = throwName;
    }
}



