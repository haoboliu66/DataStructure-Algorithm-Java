package com.extension1.data;

import com.extension1.OpCode;
import com.extension1.Side;

import java.math.BigDecimal;

public class CreatedOrder extends Order {

    BigDecimal price;
    BigDecimal quantity;
    Side side;
    String listing;

    public CreatedOrder(long time, OpCode code, String orderId, BigDecimal price, BigDecimal quantity, Side side, String listing) {
        super(time, code, orderId);
        this.price = price;
        this.quantity = quantity;
        this.side = side;
        this.listing = listing;
    }

    /**
     * merge currentOrder with this object and status changed to FILLED
     *
     * @param currentOrder
     * @return
     */
    @Override
    public Trade updateOrder(Order currentOrder) {
        FilledOrder filledOrder = (FilledOrder) currentOrder;
        long time = filledOrder.getTime();
        OpCode code = OpCode.FILLED;
        String id = getOrderId();
        BigDecimal price = filledOrder.getFillPrice();
        BigDecimal transactionQuantity = quantity.subtract(filledOrder.getRemainingQuantity());
        setQuantity(filledOrder.getRemainingQuantity());
        String listing = getListing();
        Side side = getSide();
        this.setCode(OpCode.FILLED);

        return new Trade(time, listing, side, transactionQuantity, price);
    }


    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public Side getSide() {
        return side;
    }

    public String getListing() {
        return listing;
    }

    @Override
    public String toString() {
        return "CreatedOrder{" +
                "price=" + price +
                ", quantity=" + quantity +
                ", side=" + side +
                ", listing='" + listing + '\'' +
                ", time=" + time +
                ", code=" + code +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}
