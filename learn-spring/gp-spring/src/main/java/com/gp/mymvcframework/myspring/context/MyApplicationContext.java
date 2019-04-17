package com.gp.mymvcframework.myspring.context;

import com.gp.mymvcframework.annotation.MyAutowired;
import com.gp.mymvcframework.annotation.MyController;
import com.gp.mymvcframework.annotation.MyService;
import com.gp.mymvcframework.myspring.aop.MyAopProxy;
import com.gp.mymvcframework.myspring.aop.MyCglibAopProxy;
import com.gp.mymvcframework.myspring.aop.MyJdkDynamicAopProxy;
import com.gp.mymvcframework.myspring.aop.config.MyAopConfig;
import com.gp.mymvcframework.myspring.aop.framework.MyAbstractAdvisingBeanPostProcessor;
import com.gp.mymvcframework.myspring.aop.support.MyAdvisedSupport;
import com.gp.mymvcframework.myspring.beans.MyBeanWrapper;
import com.gp.mymvcframework.myspring.beans.MyBeanWrapperImpl;
import com.gp.mymvcframework.myspring.beans.factory.MyBeanFactory;
import com.gp.mymvcframework.myspring.beans.factory.config.MyBeanDefinition;
import com.gp.mymvcframework.myspring.beans.factory.config.MyBeanPostProcessor;
import com.gp.mymvcframework.myspring.beans.factory.support.AbstractBeanDefinitionReader;
import com.gp.mymvcframework.myspring.beans.factory.support.MyAbstractBeanDefinition;
import com.gp.mymvcframework.myspring.beans.factory.support.MyDefaultListableBeanFactory;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 按之前源码分析的套路，IOC、DI、MVC、AOP
 * <p>
 * Created by Tom.
 */
public class MyApplicationContext extends MyDefaultListableBeanFactory implements MyBeanFactory {


    private String[] configLoactions;
    private AbstractBeanDefinitionReader reader;

    //单例的IOC容器缓存
    private Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<String, Object>();
    //通用的IOC容器
    private Map<String, MyBeanWrapper> factoryBeanInstanceCache = new ConcurrentHashMap<String, MyBeanWrapper>();

    public MyApplicationContext(String... configLoactions) {
        this.configLoactions = configLoactions;
        try {
            refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void refresh() throws Exception {
        //1、定位，定位配置文件
        reader = new AbstractBeanDefinitionReader(this.configLoactions);

        //2、加载配置文件，扫描相关的类，把它们封装成BeanDefinition
        List<MyBeanDefinition> beanDefinitions = reader.loadBeanDefinitions();

        //3、注册，把配置信息放到容器里面(伪IOC容器)
        doRegisterBeanDefinition(beanDefinitions);

        //4、把不是延时加载的类，有提前初始化
        doAutowrited();
    }

    public Properties getConfig() {
        return reader.getConfig();
    }


    private void doAutowrited() {
        for (Map.Entry<String, MyBeanDefinition> beanDefinitionEntry : super.beanDefinitionMap.entrySet()) {
            String beanName = beanDefinitionEntry.getKey();
            MyBeanDefinition mydefinition = beanDefinitionEntry.getValue();
            if (!mydefinition.isLazyInit()) {
                try {
                    getBean(beanName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    //到这里为止，容器初始化完毕
    private void doRegisterBeanDefinition(List<MyBeanDefinition> beanDefinitions) throws Exception {
        for (MyBeanDefinition definition : beanDefinitions) {
            if (super.beanDefinitionMap.containsKey(definition.getFactoryBeanName())) {
                throw new Exception(" The \"" + definition.getFactoryBeanName() + "\" is exists!");
            }
            super.beanDefinitionMap.put(definition.getFactoryBeanName(), definition);
        }
    }


    //依赖注入，从这里开始，通过读取BeanDefinition中的信息
    //然后，通过反射机制创建一个实例并返回
    //Spring做法是，不会把最原始的对象放出去，会用一个BeanWrapper来进行一次包装
    //装饰器模式：
    //1、保留原来的OOP关系
    //2、我需要对它进行扩展，增强（为了以后AOP打基础）
    @Override
    public Object getBean(String beanName) throws Exception {
        MyBeanDefinition myBeanDefinition = super.beanDefinitionMap.get(beanName);
        Object instance = null;

        MyBeanPostProcessor postProcessor = new MyAbstractAdvisingBeanPostProcessor();

        postProcessor.postProcessAfterInitialization(instance, beanName);

        instance = instantiateBean(beanName, myBeanDefinition);
        if (instance == null) {
            throw new Exception(" The \"" + beanName + "\" is null!");
        }


        //3、把这个对象封装到BeanWrapper中
        MyBeanWrapper beanWrapper = new MyBeanWrapperImpl(instance);

        //4、把BeanWrapper存到IOC容器里面
//        //1、初始化
//        //class A{ B b;}
//        //class B{ A a;}
//        //先有鸡还是先有蛋的问题，一个方法是搞不定的，要分两次

        //2、拿到BeanWraoper之后，把BeanWrapper保存到IOC容器中去
        this.factoryBeanInstanceCache.put(beanName, beanWrapper);

        postProcessor.postProcessAfterInitialization(instance, beanName);

//        //3、注入
        populateBean(beanName, new MyAbstractBeanDefinition(), beanWrapper);

        return this.factoryBeanInstanceCache.get(beanName).getWrappedInstance();

    }

    private void populateBean(String beanName, MyAbstractBeanDefinition myAbstractBeanDefinition, MyBeanWrapper beanWrapper) {
        Object instance = beanWrapper.getWrappedInstance();
        Class<?> clazz = beanWrapper.getWrappedClass();
        //判断只有加了注解的类，才执行依赖注入
        if (!(clazz.isAnnotationPresent(MyController.class) || clazz.isAnnotationPresent(MyService.class))) {
            return;
        }
        //获得所有的fields
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(MyAutowired.class)) {
                continue;
            }
            MyAutowired autowired = field.getAnnotation(MyAutowired.class);

            String autowiredBeanName = autowired.value().trim();
            if ("".equals(autowiredBeanName)) {
                autowiredBeanName = field.getType().getName();
            }

            //强制访问
            field.setAccessible(true);
            try {
                //为什么会为NULL，先留个坑
                if (this.factoryBeanInstanceCache.get(autowiredBeanName) == null) {
                    continue;
                }
//                if(instance == null){
//                    continue;
//                }
                field.set(instance, this.factoryBeanInstanceCache.get(autowiredBeanName).getWrappedInstance());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }

    private Object instantiateBean(String beanName, MyBeanDefinition myBeanDefinition) {
        //1、拿到要实例化的对象的类名
        String className = myBeanDefinition.getBeanClass();
        Object instance = null;
        try {
            //2、反射实例化，得到一个对象

            if (this.factoryBeanInstanceCache.containsKey(className)) {
                instance = this.factoryBeanInstanceCache.get(className);
            } else {
                Class<?> clazz = Class.forName(className);
                instance = clazz.newInstance();

                MyAdvisedSupport config = instantionAopConfig(myBeanDefinition);
                config.setTargetClass(clazz);
                config.setTarget(instance);

                //符合PointCut的规则的话，代理对象
                if (config.pointCutMatch()) {
                    instance = createProxy(config).getProxy();
                }

                this.factoryBeanObjectCache.put(className, instance);
                this.factoryBeanObjectCache.put(myBeanDefinition.getFactoryBeanName(), instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return instance;
    }

    private MyAopProxy createProxy(MyAdvisedSupport config) {
        Class clazz = config.getTargetClass();
        if (clazz.getInterfaces().length > 0) {
            return new MyJdkDynamicAopProxy(config);
        }
        return new MyCglibAopProxy(config);
    }


    private MyAdvisedSupport instantionAopConfig(MyBeanDefinition myBeanDefinition) {
        MyAopConfig config = new MyAopConfig();
        config.setPointCut(this.reader.getConfig().getProperty("pointCut"));
        config.setAspectClass(this.reader.getConfig().getProperty("aspectClass"));
        config.setAspectBefore(this.reader.getConfig().getProperty("aspectBefore"));
        config.setAspectAfter(this.reader.getConfig().getProperty("aspectAfter"));
        config.setAspectAfterThrow(this.reader.getConfig().getProperty("aspectAfterThrow"));
        config.setAspectAfterThrowingName(this.reader.getConfig().getProperty("aspectAfterThrowingName"));
        config.setAspectAround(this.reader.getConfig().getProperty("aspectArround"));
        return new MyAdvisedSupport(config);
    }

    public String[] getBeanDefinitionNames() {
        return this.beanDefinitionMap.keySet().toArray(new String[this.beanDefinitionMap.size()]);
    }
}
