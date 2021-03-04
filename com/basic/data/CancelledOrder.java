package com.basic.data;

import com.basic.OpCode;

public class CancelledOrder extends Order{

    public CancelledOrder(long time, OpCode code, String orderId) {
        super(time, code, orderId);
    }

    @Override
    public Trade updateOrder(Order currentOrder) {
        return null;
    }
}
