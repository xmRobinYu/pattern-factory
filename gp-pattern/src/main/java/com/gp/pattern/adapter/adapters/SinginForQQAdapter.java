package com.gp.pattern.adapter.adapters;

public class SinginForQQAdapter implements LoginAdapter {

    @Override
    public boolean support(Object adapter) {
        return adapter instanceof SinginForQQAdapter;
    }

    @Override
    public String login(String token, Object adapter) {
        if (support(adapter)) {
            return "QQ登录成功token=" + token;
        }
        return "不支持该登录";
    }


}
