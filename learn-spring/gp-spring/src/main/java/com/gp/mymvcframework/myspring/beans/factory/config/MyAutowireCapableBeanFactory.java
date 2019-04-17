package com.gp.mymvcframework.myspring.beans.factory.config;


public interface MyAutowireCapableBeanFactory {
    //没有依赖注入
    int AUTOWIRE_NO = 0;

    //根据类名依赖注入
    int AUTOWIRE_BY_NAME = 1;

    //根据类型注入
    int AUTOWIRE_BY_TYPE = 2;

    //根据构造注入
    int AUTOWIRE_CONSTRUCTOR = 3;


    int AUTOWIRE_AUTODETECT = 4;
}
