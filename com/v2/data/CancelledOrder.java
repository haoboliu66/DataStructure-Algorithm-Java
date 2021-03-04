package com.v2.data;

import com.v2.OpCode;

public class CancelledOrder extends Order {

    public CancelledOrder(long time, OpCode code, String orderId) {
        super(time, code, orderId);
    }

}
