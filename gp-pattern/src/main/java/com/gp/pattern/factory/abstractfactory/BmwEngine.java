package com.gp.pattern.factory.abstractfactory;

/**
 *    
 *   * 
 *   * 类描述:   宝马发动机
 *   *    
 *  
 */
public class BmwEngine implements IEngine {

    /**
     * 生产宝马发动机
     *
     * @return
     */
    public void make() {
        System.out.println("生产宝马发动机");
    }
}
