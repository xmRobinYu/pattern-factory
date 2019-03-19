package com.gp.pattern.decorator;

public class SauageDecorator extends BattercakeDecorator {

    int num;

    public SauageDecorator(Battercake battercake) {
        super(battercake);
    }


    @Override
    public void addSomeThing(int num) {
        this.num = num;
    }

    @Override
    protected String getMsg() {
        return super.getMsg() + "+" + num + "个香肠";
    }

    @Override
    protected int getPrice() {
        return super.getPrice() + num * 2;
    }
}
