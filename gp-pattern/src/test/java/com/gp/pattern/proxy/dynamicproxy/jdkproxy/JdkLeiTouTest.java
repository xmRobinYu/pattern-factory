package com.gp.pattern.proxy.dynamicproxy.jdkproxy;


import com.gp.pattern.proxy.staticproxy.Son;

import java.lang.reflect.Method;

public class JdkLeiTouTest {
    public static void main(String[] args) throws Exception {
        Object object = new JdkLeiTou().getInstance(new Son());
        Method menthod = object.getClass().getMethod("findJob", null);
        menthod.invoke(object);
    }
}
