package com.gp.pattern.factory.abstractfactory;

import com.gp.pattern.factory.ICard;
import com.gp.pattern.factory.TeslaCard;


public class TeslaCardFactory implements IAbstractCardFactory {

    public IEngine create() {
        return new TeslaEngine();
    }

    public ICard markUp() {
        return new TeslaCard();
    }
}
