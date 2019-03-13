package com.gp.pattern.proxy.staticproxy;


import com.gp.pattern.proxy.Person;

public class Son implements Person {
    @Override
    public int findJob() {
        System.out.println("儿子要求：钱多事少，按时上下班！");
        return 0;
    }
}
