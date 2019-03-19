package com.gp.pattern.observer.gperadvice.guava;

import com.google.common.eventbus.EventBus;

public class GuavaAdviceTest {

    public static void main(String[] args) {
        GPer gPer = GPer.getInstance();


        Question question = new Question();
        question.setUserName("张三");
        question.setContent("JDK 动态代理如何实现？");

        gPer.publishQuestion(question);

        EventBus eventBus = new EventBus();
        Teacher teacher = new Teacher("tom");
        eventBus.register(teacher);

        GPerQuestion gPerQuestion = new GPerQuestion(gPer, question);
        eventBus.post(gPerQuestion);


    }
}
