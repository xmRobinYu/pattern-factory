package com.gp.pattern.factory.abstractfactory;

import com.gp.pattern.factory.BmwCard;
import com.gp.pattern.factory.ICard;

/**
 *    
 *   * 
 *   * Simple To Introduction 
 *   * 类描述:    [一句话描述该类的功能]
 *   * 创建人:    yuxb   
 *   * 创建时间:  [${date} ${time}]   
 *   * 修改人:    [${user}]   
 *   * 修改时间:  [${date} ${time}]   
 *   * 修改备注:  [说明本次修改内容]  
 *   * 版本:      [v1.0]   
 *   *    
 *  
 */
public class BmwCardFactory implements IAbstractCardFactory {
    public IEngine create() {
        return new BmwEngine();
    }

    public ICard markUp() {
        return new BmwCard();
    }
}
