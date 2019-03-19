package com.gp.pattern.observer.gperadvice.jdk;

import java.util.Observable;

//被观察者
public class GPer extends Observable {

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
        setChanged();
        notifyObservers(question);
    }
}
