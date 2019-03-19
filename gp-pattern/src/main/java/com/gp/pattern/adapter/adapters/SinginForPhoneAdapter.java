package com.gp.pattern.adapter.adapters;

public class SinginForPhoneAdapter implements LoginAdapter {

    @Override
    public boolean support(Object adapter) {
        return adapter instanceof SinginForPhoneAdapter;
    }

    @Override
    public String login(String token, Object adapter) {
        if (support(adapter)) {
            return "手机号码登录成功token=" + token;
        }
        return "不支持该登录";
    }


}
