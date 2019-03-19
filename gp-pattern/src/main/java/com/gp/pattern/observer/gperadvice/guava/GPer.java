package com.gp.pattern.observer.gperadvice.guava;

//被观察者
public class GPer {

    private String name = "咕泡学院";

    private static GPer gper = null;

    public static GPer getInstance(){
        if(null == gper){
            gper = new GPer();
        }
        return gper;
    }

    public String getName() {
        return name;
    }


    public void publishQuestion(Question question) {
        System.out.println(question.getUserName() + "" + getName() + " 上发起了提问");

    }
}
