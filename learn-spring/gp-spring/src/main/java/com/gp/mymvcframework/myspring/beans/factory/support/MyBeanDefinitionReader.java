package com.gp.mymvcframework.myspring.beans.factory.support;


import com.gp.mymvcframework.myspring.beans.factory.config.MyBeanDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public interface MyBeanDefinitionReader {

    List<String> registyBeanClasses = new ArrayList<String>();

    Properties config = new Properties();

    //固定配置文件中的key，相对于xml的规范
    String SCAN_PACKAGE = "scanPackage";

    List<MyBeanDefinition> loadBeanDefinitions();




}
