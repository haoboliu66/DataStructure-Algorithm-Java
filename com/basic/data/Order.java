package com.basic.data;

import com.basic.OpCode;

public abstract class Order {

    long time;
    OpCode code;
    String orderId;

    protected Order(long time, OpCode code, String orderId) {
        this.time = time;
        this.code = code;
        this.orderId = orderId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public OpCode getCode() {
        return code;
    }

    public void setCode(OpCode code) {
        this.code = code;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public abstract Trade updateOrder(Order currentOrder);
}