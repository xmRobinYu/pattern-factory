package com.gp.pattern.factory.menthod;

import com.gp.pattern.factory.BmwCard;
import com.gp.pattern.factory.ICard;

/**
 *    
 *   * 
 *   * 类描述:    宝马工厂
 *   *    
 *  
 */
public class BmwCardFactory implements ICardFactory {

    public ICard buyCard() {
        return new BmwCard();
    }
}
