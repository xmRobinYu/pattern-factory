package com.gp.pattern.singleton.register;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//Spring中的做法，就是用这种注册式单例
public class ContanierSingleton {
    private ContanierSingleton() {

    }

    private static Map<String, Object> iocMap = new ConcurrentHashMap<String, Object>();

    public static Object getInstance(String className) {
        synchronized (iocMap) {
            if (!iocMap.containsKey(className)) {
                Object object = null;
                try {
                    object = Class.forName(className).newInstance();
                    iocMap.put(className, object);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return object;
            }else {
                return iocMap.get(className);
            }
        }
    }


}
