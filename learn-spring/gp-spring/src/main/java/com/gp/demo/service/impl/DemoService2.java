package com.gp.demo.service.impl;

import com.gp.demo.service.IDemoService2;
import com.gp.mymvcframework.annotation.MyService;

/**
 * 核心业务逻辑
 */
@MyService
public class DemoService2 implements IDemoService2 {

	public String get(String name) {
		return "My name is " + name;
	}

}
