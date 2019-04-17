package com.gp.mymvcframework.myspring.aop.framework;


import com.gp.mymvcframework.myspring.beans.factory.config.MyBeanPostProcessor;

public class MyAbstractAdvisingBeanPostProcessor implements MyBeanPostProcessor {


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }
}
