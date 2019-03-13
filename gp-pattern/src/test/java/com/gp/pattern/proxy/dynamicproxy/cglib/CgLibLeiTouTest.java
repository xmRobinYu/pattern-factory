package com.gp.pattern.proxy.dynamicproxy.cglib;


import com.gp.pattern.proxy.staticproxy.Son;

public class CgLibLeiTouTest {

    public static void main(String[] args) {
        Son son = (Son) new CgLibLeiTou().getInstance(Son.class);
        son.findJob();
    }
}
