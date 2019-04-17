package com.gp.mymvcframework.myspring.beans.factory.support;


import com.gp.mymvcframework.myspring.beans.factory.config.MyBeanDefinition;
import com.gp.mymvcframework.myspring.context.MyAbstractApplicationContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MyDefaultListableBeanFactory extends MyAbstractApplicationContext {

    public final Map<String, MyBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);



}
