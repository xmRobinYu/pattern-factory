package com.gp.pattern.proxy.staticproxy;


import com.gp.pattern.proxy.Person;

public class Father implements Person {

    private Son person;

    public Father(Son person) {
        this.person = person;
    }

    @Override
    public int findJob() {
        System.out.println("父亲开始找关系！");
        this.person.findJob();
        System.out.println("儿子满意，明天开始去上班！");
        return 0;
    }
}
