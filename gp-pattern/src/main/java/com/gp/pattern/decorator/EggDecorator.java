package com.gp.pattern.decorator;


public class EggDecorator extends BattercakeDecorator {

    int num;

    public EggDecorator(Battercake battercake) {
        super(battercake);
    }


    @Override
    public void addSomeThing(int num) {
        this.num = num;
    }

    @Override
    protected String getMsg() {
        return super.getMsg() + "+" + num + "个鸡蛋";
    }

    @Override
    protected int getPrice() {
        return super.getPrice() + num;
    }


}
