package com.gp.pattern.strategy.pay.payport;


import com.gp.pattern.strategy.pay.PayResult;

public abstract class Payment {

    //支付类型名称
    public abstract String getName();

    //查询余额
    public abstract double qryBalance(String uid);

    //扣款支付
    public PayResult pay(String uid, double amount) {
        double balance = qryBalance(uid);
        if (balance >= amount) {
            return new PayResult(200, "支付金额：" + amount, "交易成功");
        }
        return new PayResult(500, "余额：" + balance, "交易失败");
    }
}
