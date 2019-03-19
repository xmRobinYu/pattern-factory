package com.gp.pattern.adapter.service;

//旧的方法
@Deprecated
public class SiginService {

    public String loginString(String userName,String passport){
        System.out.println("老业务系统实现登录功能！");
        return "登录成功！";
    }


    public String registered(String userName,String passport){
        System.out.println("老业务系统实现注册功能！");
        return "登录成功！";
    }
}
