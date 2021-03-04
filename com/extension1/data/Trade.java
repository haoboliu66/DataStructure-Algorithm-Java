package com.extension1.data;

import com.extension1.Side;

import java.math.BigDecimal;

public class Trade {

    private long time;
    private String listing;
    private Side side;
    private BigDecimal transaction;
    private BigDecimal fillprice;

    public Trade(long time, String listing, Side side, BigDecimal transaction, BigDecimal fillprice) {
        this.time = time;
        this.listing = listing;
        this.side = side;
        this.transaction = transaction;
        this.fillprice = fillprice;
    }

    public long getTime() {
        return time;
    }

    public String getListing() {
        return listing;
    }

    public Side getSide() {
        return side;
    }

    public BigDecimal getTransaction() {
        return transaction;
    }

    public BigDecimal getFillprice() {
        return fillprice;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "time=" + time +
                ", listing='" + listing + '\'' +
                ", side=" + side +
                ", transaction='" + transaction + '\'' +
                ", fillprice=" + fillprice +
                '}';
    }
}
