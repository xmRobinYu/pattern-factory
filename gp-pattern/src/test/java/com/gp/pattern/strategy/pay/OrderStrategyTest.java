package com.gp.pattern.strategy.pay;


import com.gp.pattern.strategy.pay.payport.PayStrategy;

public class OrderStrategyTest {

    public static void main(String[] args) {
        Order order = new Order("abc", "121SDOFSDFSD", 10000);
        PayResult payResult = order.payOrder(PayStrategy.ALI_PAY);
        System.out.println(payResult.toString());
    }
}
