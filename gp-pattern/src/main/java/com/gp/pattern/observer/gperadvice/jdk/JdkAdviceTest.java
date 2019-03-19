package com.gp.pattern.observer.gperadvice.jdk;

public class JdkAdviceTest {

    public static void main(String[] args) {
        GPer gPer = GPer.getInstance();
        Teacher teacher = new Teacher("tom");

        Question question = new Question();
        question.setUserName("张三");
        question.setContent("JDK 动态代理如何实现？");

        gPer.addObserver(teacher);
        gPer.publishQuestion(question);


    }
}
