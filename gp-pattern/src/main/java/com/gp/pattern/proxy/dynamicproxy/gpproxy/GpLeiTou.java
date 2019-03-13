package com.gp.pattern.proxy.dynamicproxy.gpproxy;

import java.lang.reflect.Method;

/**
 *    
 *   * 
 *   * Simple To Introduction 
 *   * 类描述:    [一句话描述该类的功能]
 *   * 创建人:    yuxb   
 *   * 创建时间:  [${date} ${time}]   
 *   * 修改人:    [${user}]   
 *   * 修改时间:  [${date} ${time}]   
 *   * 修改备注:  [说明本次修改内容]  
 *   * 版本:      [v1.0]   
 *   *    
 *  
 */
public class GpLeiTou implements GPInvocationHandler {

    private Object target;

    public Object getInstance(Object object) throws Exception {
        this.target = object;
        Class<?> clazz = object.getClass();
        return GPProxy.newProxyInstance(new GPClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object object = method.invoke(this.target, args);
        after();
        return object;
    }

    private void before() {
        System.out.println("根据简历技能，筛选适合的公司");
        System.out.println("开始安排面试");
    }

    private void after() {
        System.out.println("OK的话，明天报道！");
    }
}