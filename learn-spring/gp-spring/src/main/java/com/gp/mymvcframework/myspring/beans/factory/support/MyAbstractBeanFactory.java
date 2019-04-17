package com.gp.mymvcframework.myspring.beans.factory.support;


import com.gp.mymvcframework.myspring.beans.factory.MyBeanFactory;

public abstract class MyAbstractBeanFactory implements MyBeanFactory {

    @Override
    public Object getBean(String name) {
        Object bean = null;
        try {
            return doGetBean(name, null, null, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    protected <T> T doGetBean(String name, final Class<T> requiredType, final Object[] args, boolean typeCheckOnly) throws Exception {
        return (T) createBean(name, args);
    }

    protected abstract Object createBean(String beanName, Object[] args)
            throws Exception;

}
