package com.gp.pattern.observer.gperadvice.guava;


public class GPerQuestion {

    private GPer gPer;

    private Question question;

    public GPerQuestion(GPer gPer, Question question) {
        this.gPer = gPer;
        this.question = question;
    }

    public GPer getgPer() {
        return gPer;
    }

    public void setgPer(GPer gPer) {
        this.gPer = gPer;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
