package com.extension2.data;

import com.extension2.OpCode;

public class CancelledOrder extends Order {

    public CancelledOrder(long time, OpCode code, String orderId) {
        super(time, code, orderId);
    }

}
