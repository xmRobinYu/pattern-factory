package com.gp.pattern.factory.abstractfactory;

/**
 *    
 *   * 
 *   * 类描述:    抽象工厂测试类
 *   *    
 *  
 */
public class AbstractCardFactoryTest {

    public static void main(String[] args) {
        BmwCardFactory bmwCardFactory = new BmwCardFactory();
        bmwCardFactory.create().make();
        bmwCardFactory.markUp().buildUp();


        TeslaCardFactory teslaCardFactory = new TeslaCardFactory();
        teslaCardFactory.markUp().buildUp();
        teslaCardFactory.create().make();

    }
}
