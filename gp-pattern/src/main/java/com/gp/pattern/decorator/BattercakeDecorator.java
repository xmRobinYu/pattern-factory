package com.gp.pattern.decorator;


public abstract class BattercakeDecorator extends Battercake {

    private Battercake battercake;

    public BattercakeDecorator(Battercake battercake){
        this.battercake = battercake;
    }

    @Override
    public abstract void addSomeThing(int num);

    @Override
    protected String getMsg() {
        return this.battercake.getMsg();
    }

    @Override
    protected int getPrice() {
        return this.battercake.getPrice();
    }


}
