package com.gp.pattern.strategy.pay;


public class PayResult {

    Integer code;

    String date;

    String msg;

    public PayResult(Integer code, String date, String msg) {
        this.code = code;
        this.date = date;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "{" +
                "支付状态" + code +
                ", '" + msg + '\'' +
                ", 交易详情：'" + date + '\'' +
                '}';
    }
}
