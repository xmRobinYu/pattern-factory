package com.gp.pattern.decorator;

//普通煎饼
public class BaseBattercake extends Battercake {

    private int num = 1;

    @Override
    protected String getMsg() {
        return "煎饼个" + num + "个";
    }

    @Override
    protected int getPrice() {
        return 5 * num;
    }

    @Override
    protected void addSomeThing(int num) {
        this.num = num;
    }

}
