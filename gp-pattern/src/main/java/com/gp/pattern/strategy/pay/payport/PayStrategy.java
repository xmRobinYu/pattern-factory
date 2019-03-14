package com.gp.pattern.strategy.pay.payport;

import java.util.HashMap;

//支付策略
public class PayStrategy {

    public static final String ALI_PAY = "AliPay";
    public static final String WeChant_PAY = "WeChant_PAY";

    public static final String DEFAULT_PAY = "AliPay";

    private static HashMap payMap = new HashMap();

    static {
        payMap.put(ALI_PAY, new AliPay());
        payMap.put(WeChant_PAY, new WchatPay());
    }

    public static Payment getPayment(String payType) {
        if (!payMap.containsKey(payType)) {
            return (Payment) payMap.get(DEFAULT_PAY);
        }
        return (Payment) payMap.get(payType);
    }

}
