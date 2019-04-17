package com.gp.mymvcframework.myspring.beans;


public class MyBeanWrapperImpl implements MyBeanWrapper {

    Object wrappedObject = null;

    String nestedPath = "";

    Object rootObject = null;

    public MyBeanWrapperImpl(Object rootObject) {
        this.rootObject = rootObject;
    }

    @Override
    public Object getWrappedInstance() {
        return rootObject;
    }

    @Override
    public Class<?> getWrappedClass() {
        return rootObject.getClass();
    }
}
