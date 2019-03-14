package com.gp.pattern.delegate.simple;


public class MemberA implements IMember {

    public void dojob(String commond) {
        System.out.print("我是张三，我是DBA，我开始做" + commond + "工作了！");
    }
}
