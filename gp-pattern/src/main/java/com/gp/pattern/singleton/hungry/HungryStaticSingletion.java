package com.gp.pattern.singleton.hungry;

//饿汉式静态单例
public class HungryStaticSingletion {

    private static HungryStaticSingletion hungrySingletion = null;

    static {
        hungrySingletion = new HungryStaticSingletion();

    }

    private HungryStaticSingletion() {

    }

    public static HungryStaticSingletion getInstance() {
        return hungrySingletion;
    }

}
