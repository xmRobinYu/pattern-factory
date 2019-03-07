package com.gp.pattern.factory.simple;

import com.gp.pattern.factory.ICard;


public class CardSimpleFactory {


    ICard buyCard(Class<? extends ICard> clazz) {
        ICard card = null;
        if (null != clazz) {
            try {
                card = clazz.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return card;
    }
}
