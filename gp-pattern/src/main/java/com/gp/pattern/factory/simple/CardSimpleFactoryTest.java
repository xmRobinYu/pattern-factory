package com.gp.pattern.factory.simple;

import com.gp.pattern.factory.BmwCard;
import com.gp.pattern.factory.ICard;
import com.gp.pattern.factory.TeslaCard;


public class CardSimpleFactoryTest {


    public static void main(String[] args) {
        CardSimpleFactory cardSimpleFactory = new CardSimpleFactory();
        ICard card = cardSimpleFactory.buyCard(BmwCard.class);
        card.buildUp();
        ICard car2 =cardSimpleFactory.buyCard(TeslaCard.class);
        car2.buildUp();

    }
}
