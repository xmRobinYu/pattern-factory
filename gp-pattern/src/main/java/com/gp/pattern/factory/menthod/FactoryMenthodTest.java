package com.gp.pattern.factory.menthod;

import com.gp.pattern.factory.ICard;

/**
 *    
 *   * 
 *   * Simple To Introduction 
 *   * 类描述:   汽车工厂
 *   *    
 *  
 */
public class FactoryMenthodTest {

    public static void main(String[] args) {
        BmwCardFactory bmwCardFactory = new BmwCardFactory();
        ICard card = bmwCardFactory.buyCard();
        card.buildUp();

        TeslaCardFactory teslaCardFactory = new TeslaCardFactory();
        ICard card2 = teslaCardFactory.buyCard();
        card2.buildUp();
    }
}
