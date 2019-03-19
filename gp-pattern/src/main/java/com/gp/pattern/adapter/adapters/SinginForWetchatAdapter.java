package com.gp.pattern.adapter.adapters;

public class SinginForWetchatAdapter implements LoginAdapter {

    @Override
    public boolean support(Object adapter) {
        return adapter instanceof SinginForWetchatAdapter;
    }

    @Override
    public String login(String token, Object adapter) {
        if (support(adapter)) {
            return "微信登录成功token=" + token;
        }
        return "不支持该登录";
    }


}
