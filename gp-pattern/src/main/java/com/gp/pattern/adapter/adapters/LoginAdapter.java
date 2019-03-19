package com.gp.pattern.adapter.adapters;

public interface LoginAdapter {

    boolean support(Object adapter);

    String login(String id, Object adapter);


}
