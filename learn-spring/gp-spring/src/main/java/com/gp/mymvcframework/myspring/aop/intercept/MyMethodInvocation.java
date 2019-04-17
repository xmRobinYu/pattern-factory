package com.gp.mymvcframework.myspring.aop.intercept;


import com.gp.mymvcframework.myspring.aop.aspect.MyJoinPoint;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyMethodInvocation implements MyJoinPoint {

    private Method method;
    private Object target;
    private Object [] arguments;
    private List<Object> interceptorsAndDynamicMethodMatchers;

    private Map<String, Object> userAttributes;


    //定义一个索引，从-1开始来记录当前拦截器执行的位置
    private int currentInterceptorIndex = -1;

    public MyMethodInvocation(Object target, Method method, Object[] arguments,
             List<Object> interceptorsAndDynamicMethodMatchers) {
        this.target = target;
        this.method = method;
        this.arguments = arguments;
        this.interceptorsAndDynamicMethodMatchers = interceptorsAndDynamicMethodMatchers;
    }

    public Object proceed() throws Throwable {
        //如果Interceptor执行完了，则执行joinPoint
        if (this.currentInterceptorIndex == this.interceptorsAndDynamicMethodMatchers.size() - 1) {
            return this.method.invoke(this.target,this.arguments);
        }

        Object interceptorOrInterceptionAdvice =
                this.interceptorsAndDynamicMethodMatchers.get(++this.currentInterceptorIndex);
        //如果要动态匹配joinPoint
        if (interceptorOrInterceptionAdvice instanceof MyMethodInterceptor) {
            MyMethodInterceptor mi =
                    (MyMethodInterceptor) interceptorOrInterceptionAdvice;
            return mi.invoke(this);
        } else {
            //动态匹配失败时,略过当前Intercetpor,调用下一个Interceptor
            return proceed();
        }
    }

    @Override
    public Object getThis() {
        return this.target;
    }

    @Override
    public Object[] getArguments() {
        return this.arguments;
    }

    @Override
    public Method getMethod() {
        return this.method;
    }

    @Override
    public void setUserAttribute(String key, Object value) {
        if (value != null) {
            if (this.userAttributes == null) {
                this.userAttributes = new HashMap<String,Object>();
            }
            this.userAttributes.put(key, value);
        }
        else {
            if (this.userAttributes != null) {
                this.userAttributes.remove(key);
            }
        }
    }


    @Override
    public Object getUserAttribute(String key) {
        return (this.userAttributes != null ? this.userAttributes.get(key) : null);
    }



}
