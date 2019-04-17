package com.My.mymvcframework.myspring.aop.aspect;

import com.gp.mymvcframework.myspring.aop.aspect.MyAbstractAspectAdvice;
import com.gp.mymvcframework.myspring.aop.aspect.MyAdvice;
import com.gp.mymvcframework.myspring.aop.aspect.MyJoinPoint;
import com.gp.mymvcframework.myspring.aop.intercept.MyMethodInterceptor;
import com.gp.mymvcframework.myspring.aop.intercept.MyMethodInvocation;

import java.lang.reflect.Method;

/**
 * Created by Tom on 2019/4/15.
 */
public class MyAroundAdviceInterceptor extends MyAbstractAspectAdvice implements MyAdvice, MyMethodInterceptor {


    private MyJoinPoint joinPoint;

    public MyAroundAdviceInterceptor(Method aspectMethod, Object aspectTarget) {
        super(aspectMethod, aspectTarget);
    }

    private void before(Method method, Object[] args, Object target) throws Throwable {
        //传送了给织入参数
        super.invokeAdviceMethod(this.joinPoint, null, null);
    }

    private void after(Method method, Object[] args, Object target) throws Throwable {
        //传送了给织入参数
        super.invokeAdviceMethod(this.joinPoint, null, null);
    }

    @Override
    public Object invoke(MyMethodInvocation mi) throws Throwable {
        //从被织入的代码中才能拿到，JoinPoint
        this.joinPoint = mi;
        before(mi.getMethod(), mi.getArguments(), mi.getThis());
        Object object = mi.proceed();
        after(mi.getMethod(), mi.getArguments(), mi.getThis());
        return object;
    }
}

