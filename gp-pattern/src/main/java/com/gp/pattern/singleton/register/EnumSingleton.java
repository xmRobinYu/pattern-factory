package com.gp.pattern.singleton.register;


public enum EnumSingleton {
    INSTANCE;

    private Object object = null;

    private EnumSingleton() {
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Object getInstance() {
        return object;
    }
}
