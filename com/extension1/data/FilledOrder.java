package com.extension1.data;

import com.extension1.OpCode;

import java.math.BigDecimal;

public class FilledOrder extends Order {

    BigDecimal fillPrice;
    BigDecimal remainingQuantity;

    public FilledOrder(long time, OpCode code, String orderId, BigDecimal fillPrice, BigDecimal quantity) {
        super(time, code, orderId);
        this.fillPrice = fillPrice;
        this.remainingQuantity = quantity;
    }

    public BigDecimal getFillPrice() {
        return fillPrice;
    }

    public BigDecimal getRemainingQuantity() {
        return remainingQuantity;
    }

    @Override
    public Trade updateOrder(Order currentOrder) {
        return null;
    }
}
