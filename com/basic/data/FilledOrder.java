package com.basic.data;

import com.basic.OpCode;

import java.math.BigDecimal;

public class FilledOrder extends Order{

    BigDecimal fillPrice;
    BigDecimal fillQuantity;

    public FilledOrder(long time, OpCode code, String orderId, BigDecimal fillPrice, BigDecimal quantity) {
        super(time, code, orderId);
        this.fillPrice = fillPrice;
        this.fillQuantity = quantity;
    }

    public void setFillQuantity(BigDecimal fillQuantity) {
        this.fillQuantity = fillQuantity;
    }

    public BigDecimal getFillPrice() {
        return fillPrice;
    }

    public BigDecimal getFillQuantity() {
        return fillQuantity;
    }

    @Override
    public Trade updateOrder(Order currentOrder) {
        return null;
    }
}
