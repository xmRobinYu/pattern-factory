package com.gp.pattern.adapter;

/**
 * 在适配器里面，这个接口是可有可无，不要跟模板模式混淆
 * 模板模式一定是抽象类，而这里仅仅只是一个接口
 * Created by Tom on 2019/3/16.
 */
public interface IPassportForThird {


    public String loginForQQ(String token);


    public String loginForWetchat(String token);

    public String loginForPhone(String phone,String code);


}
