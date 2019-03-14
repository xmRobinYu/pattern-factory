package com.gp.pattern.delegate.simple;


public class MemberB implements IMember {

    public void dojob(String commond) {
        System.out.print("我是李四，我是系统架构师，我开始做" + commond + "工作了！");
    }
}
