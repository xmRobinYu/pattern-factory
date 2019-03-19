package com.gp.pattern.template.jdbc;


import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.util.List;

public class SysLoginUserDaoTest {


    public static void main(String[] args) {
        try {
            //创建连接池实例
            ComboPooledDataSource ds = new ComboPooledDataSource();
            //设置连接池连接数据库所需的驱动
            ds.setDriverClass("com.mysql.jdbc.Driver");
            //设置链接数据库的URL
            ds.setJdbcUrl("jdbc:mysql://XXXX:3306/qq_test");
            //设置连接数据库的用户名
            ds.setUser("xxx");
            //设置连接数据库的密码
            ds.setPassword("xxxxx");
            SysLoginUserDao sysLoginUserDao = new SysLoginUserDao(ds);
            List<SysLoginUser> sysLoginUsers = (List<SysLoginUser>) sysLoginUserDao.selectAll();
            for (int i = 0; i < sysLoginUsers.size(); ++i) {
                SysLoginUser sysLoginUser = sysLoginUsers.get(i);
                System.out.println("id:" + sysLoginUser.getSluId() + "  " + " name:" + sysLoginUser.getSluName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
