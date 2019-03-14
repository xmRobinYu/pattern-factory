package com.gp.pattern.delegate.simple;


import java.util.HashMap;

public class Manager {

    private static HashMap map = new HashMap();

    static {
        map.put("数据库设计", new MemberA());
        map.put("系统架构", new MemberB());
    }

    public void doing(String command) {
        IMember iMember = (IMember) map.get(command);
        System.out.println("我是项目经理，我分配工作！" );
        iMember.dojob(command);

    }


}
