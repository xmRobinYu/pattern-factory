package com.gp.pattern.observer.gperadvice.guava;

import com.google.common.eventbus.Subscribe;

//观察者
public class Teacher{

    private String name;
    public Teacher(String name){
        this.name = name;
    }

    @Subscribe
    private void update(GPerQuestion gPerQuestion) throws InterruptedException {
        System.out.println("===============================");
        System.out.println(name + "老师，你好！\n" +
                "您收到了一个来自“" + gPerQuestion.getgPer().getName() + "”的提问，希望您解答，问题内容如下：\n" +
                gPerQuestion.getQuestion().getContent() + "\n" +
                "提问者：" + gPerQuestion.getQuestion().getUserName());
    }

    @Subscribe
    private void update2(GPerQuestion gPerQuestion) throws InterruptedException {
        System.out.println("===============================2");
        System.out.println(name + "老师，你好！\n" +
                "您收到了一个来自“" + gPerQuestion.getgPer().getName() + "”的提问，希望您解答，问题内容如下：\n" +
                gPerQuestion.getQuestion().getContent() + "\n" +
                "提问者：" + gPerQuestion.getQuestion().getUserName());
    }

    @Subscribe
    private void update3(GPerQuestion gPerQuestion) throws InterruptedException {
        System.out.println("===============================3");
        System.out.println(name + "老师，你好！\n" +
                "您收到了一个来自“" + gPerQuestion.getgPer().getName() + "”的提问，希望您解答，问题内容如下：\n" +
                gPerQuestion.getQuestion().getContent() + "\n" +
                "提问者：" + gPerQuestion.getQuestion().getUserName());
    }

    @Subscribe
    private void update4(GPerQuestion gPerQuestion) throws InterruptedException {
        System.out.println("===============================4");
        System.out.println(name + "老师，你好！\n" +
                "您收到了一个来自“" + gPerQuestion.getgPer().getName() + "”的提问，希望您解答，问题内容如下：\n" +
                gPerQuestion.getQuestion().getContent() + "\n" +
                "提问者：" + gPerQuestion.getQuestion().getUserName());
    }
}
