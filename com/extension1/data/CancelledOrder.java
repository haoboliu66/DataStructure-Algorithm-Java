package com.extension1.data;

import com.extension1.OpCode;

public class CancelledOrder extends Order {

    public CancelledOrder(long time, OpCode code, String orderId) {
        super(time, code, orderId);
    }

    @Override
    public Trade updateOrder(Order currentOrder) {
        return null;
    }
}
