package com.gp.demo.service.impl;

import com.gp.demo.service.IDemoService;
import com.gp.mymvcframework.annotation.MyService;

/**
 * 核心业务逻辑
 */
@MyService
public class DemoService implements IDemoService{

	@Override
    public String get(String name) throws Exception {
		System.out.println("进入方法内部执行！");
		//return "My name is " + name;
        throw new Exception("故意抛出异常！");
    }

}
