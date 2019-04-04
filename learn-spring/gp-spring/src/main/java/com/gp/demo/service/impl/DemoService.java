package com.gp.demo.service.impl;

import com.gp.demo.service.IDemoService;
import com.gp.mymvcframework.annotation.MyService;

/**
 * 核心业务逻辑
 */
@MyService
public class DemoService implements IDemoService{

	public String get(String name) {
		return "My name is " + name;
	}

}
