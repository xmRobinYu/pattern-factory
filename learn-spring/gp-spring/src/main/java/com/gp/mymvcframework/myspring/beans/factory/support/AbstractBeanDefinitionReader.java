package com.gp.mymvcframework.myspring.beans.factory.support;


import com.gp.mymvcframework.myspring.beans.factory.config.MyBeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class AbstractBeanDefinitionReader implements MyBeanDefinitionReader {

    public AbstractBeanDefinitionReader(String... locations) {
        //通过URL定位找到其所对应的文件，然后转换为文件流
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(locations[0].replace("classpath:", ""));
        try {
            config.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        doScannerFile(config.getProperty(SCAN_PACKAGE));
    }

    private void doScannerFile(String scanPackage) {
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.", "/") + "/");
        File file = new File(url.getFile());
        for (File file1 : file.listFiles()) {
            if (file1.isDirectory()) {//如果是目录，递归往下继续找
                doScannerFile(scanPackage + "." + file1.getName());
            } else if (file1.getName().endsWith(".class")) {
                registyBeanClasses.add((scanPackage + "." + file1.getName().replaceAll(".class", "").trim()));
            } else {
                continue;
            }
        }
    }


    @Override
    //把配置文件中扫描到的所有的配置信息转换为MyBeanDefinition对象，以便于之后IOC操作方便
    public List<MyBeanDefinition> loadBeanDefinitions() {
        List<MyBeanDefinition> result = new ArrayList<MyBeanDefinition>();
        try {
            for (String className : registyBeanClasses) {
                Class<?> beanClass = Class.forName(className);
                //如果是一个接口，是不能实例化的
                //用它实现类来实例化
                if (beanClass.isInterface()) {
                    continue;
                }

                //beanName有三种情况:
                //1、默认是类名首字母小写
                //2、自定义名字
                //3、接口注入
                result.add(doCreateBeanDefinition(toLowerFirstCase(beanClass.getSimpleName()), beanClass.getName()));

                Class<?>[] interfaces = beanClass.getInterfaces();
                for (Class<?> i : interfaces) {
                    //如果是多个实现类，只能覆盖
                    //这个时候，可以自定义名字
                    result.add(doCreateBeanDefinition(i.getName(), beanClass.getName()));
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //把每一个配信息解析成一个MyAbstractBeanDefinition
    private MyAbstractBeanDefinition doCreateBeanDefinition(String factoryBeanName, String beanClassName) {
        MyAbstractBeanDefinition myBeanDefinition = new MyAbstractBeanDefinition();
        myBeanDefinition.setFactoryBeanName(factoryBeanName);
        myBeanDefinition.setBeanClass(beanClassName);
        return myBeanDefinition;
    }

    private String toLowerFirstCase(String className) {
        char[] clssCharArray = className.toCharArray();
        if (clssCharArray.length == 0) {
            return className;
        }
        if (clssCharArray[0] <= 90 && clssCharArray[0] >= 65) {
            clssCharArray[0] += 32;
            return String.valueOf(clssCharArray);
        }
        return className;
    }

    public Properties getConfig(){
        return config;
    }


}
