package com.gp.pattern.strategy.pay.payport;


public class AliPay extends Payment {
    @Override
    public String getName() {
        return "支付宝";
    }

    @Override
    public double qryBalance(String uid) {
        return 1000;
    }
}
