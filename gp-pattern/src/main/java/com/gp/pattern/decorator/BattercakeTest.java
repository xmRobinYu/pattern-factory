package com.gp.pattern.decorator;

public class BattercakeTest {

    public static void main(String[] args) {
        Battercake battercake = new BaseBattercake();
        System.out.println(battercake.getMsg() + " 总价格：" + battercake.getPrice());

        battercake = new  EggDecorator(battercake);
        battercake.addSomeThing(500);
        System.out.println(battercake.getMsg() + " 总价格：" + battercake.getPrice());

        battercake = new SauageDecorator(battercake);
        battercake.addSomeThing(500);
        System.out.println(battercake.getMsg() + " 总价格：" + battercake.getPrice());
    }

}
