package com.gp.pattern.strategy.pay.payport;


public class WchatPay extends Payment {
    @Override
    public String getName() {
        return "微信支付";
    }

    @Override
    public double qryBalance(String uid) {
        return 200;
    }
}
