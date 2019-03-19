package com.gp.pattern.adapter.service;

import com.gp.pattern.adapter.IPassportForThird;
import com.gp.pattern.adapter.adapters.LoginAdapter;
import com.gp.pattern.adapter.adapters.SinginForPhoneAdapter;
import com.gp.pattern.adapter.adapters.SinginForQQAdapter;
import com.gp.pattern.adapter.adapters.SinginForWetchatAdapter;

public class PassportForThirdAdapter extends SiginService implements IPassportForThird {

    public String loginString(String userName, String passport) {
        return super.loginString(userName, passport);
    }


    public String registered(String userName, String passport) {
        return super.registered(userName, passport);
    }

    @Override
    public String loginForQQ(String token) {
        return ProcessLogin(token, SinginForQQAdapter.class);
    }

    @Override
    public String loginForWetchat(String token) {
        return ProcessLogin(token, SinginForWetchatAdapter.class);
    }

    @Override
    public String loginForPhone(String phone, String code) {
        return ProcessLogin(phone, SinginForPhoneAdapter.class);
    }

    private String ProcessLogin(String key, Class<? extends LoginAdapter> clazz) {
        try {
            //适配器不一定要实现接口
            LoginAdapter adapter = clazz.newInstance();
            //判断传过来的适配器是否能处理指定的逻辑
            if (adapter.support(adapter)) {
                return adapter.login(key, adapter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

}
