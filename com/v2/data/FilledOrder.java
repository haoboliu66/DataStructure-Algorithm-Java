package com.v2.data;

import com.v2.OpCode;

import java.math.BigDecimal;

public class FilledOrder extends Order {

    private BigDecimal fillPrice;
    private BigDecimal remainingQuantity;

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

}
