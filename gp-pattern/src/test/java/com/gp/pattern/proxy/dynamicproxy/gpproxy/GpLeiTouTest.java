package com.gp.pattern.proxy.dynamicproxy.gpproxy;


import com.gp.pattern.proxy.staticproxy.Son;

import java.lang.reflect.Method;

public class GpLeiTouTest {
    public static void main(String[] args) throws Exception {
        Object object = new GpLeiTou().getInstance(new Son());
        Method menthod = object.getClass().getMethod("findJob", null);
        menthod.invoke(object);
    }
}
