package com.gp.pattern.singleton.register;


public class EnumSingletonTest {

    public static void main(String[] args) throws Exception {
        Object object = new Object();
        EnumSingleton.INSTANCE.setObject(object);
        System.out.println(object);
        System.out.println(EnumSingleton.INSTANCE.getInstance());




    }
}
