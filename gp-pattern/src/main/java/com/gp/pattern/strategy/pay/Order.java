package com.gp.pattern.strategy.pay;

import com.gp.pattern.strategy.pay.payport.PayStrategy;
import com.gp.pattern.strategy.pay.payport.Payment;

public class Order {

    private String uid;

    private String orderId;

    private float amount;


    public Order(String uid, String orderId, float amount) {
        this.uid = uid;
        this.orderId = orderId;
        this.amount = amount;
    }

    PayResult payOrder() {
        return payOrder(PayStrategy.DEFAULT_PAY);
    }

    PayResult payOrder(String payKey) {
        Payment payment = PayStrategy.getPayment(payKey);

        System.out.println("欢迎使用" + payment.getName());
        System.out.println("本次交易金额为" + amount + ",开始扣款！");
        return payment.pay(uid, amount);

    }
}
