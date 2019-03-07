package com.gp.pattern.factory.abstractfactory;

import com.gp.pattern.factory.ICard;

/**
 *    
 *   * 
 *   * Simple To Introduction 
 *   * 类描述:    汽车工厂类
 *   *    
 *  
 */
public interface IAbstractCardFactory {

    /**
     * 生产发动机
     *
     * @return
     */
    IEngine create();

    /**
     * 组装汽车
     *
     * @return
     */
    ICard markUp();


}
