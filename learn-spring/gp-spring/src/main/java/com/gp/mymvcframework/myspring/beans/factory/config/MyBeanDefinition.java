package com.gp.mymvcframework.myspring.beans.factory.config;


public interface MyBeanDefinition {

    public String getFactoryBeanName();

    public boolean isLazyInit();

    public int getAutowireMode();

    public String getBeanClass();


}
