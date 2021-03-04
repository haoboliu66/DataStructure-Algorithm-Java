package com.v2;

import com.v2.data.Trade;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class TradeTicker {

    private static final TradeTicker ticker = new TradeTicker();

    private BigDecimal volume = new BigDecimal(0);

    private List<Trade> queue = new LinkedList<>();

    private static final Logger LOGGER = Logger.getLogger(TradeTicker.class.toString());

    private TradeTicker() {
    }

    public static TradeTicker getInstance() {
        return ticker;
    }

    public void addRecord(Trade trade) {
        queue.add(trade);
        volume = volume.add(trade.getTransaction().multiply(trade.getFillprice()));
    }

    public void displayTrades() {
        Collections.sort(queue, new TradeComparator());
        queue.forEach(System.out::println);

        LOGGER.info("Transaction Volumn == " + volume);
    }

    private static class TradeComparator implements Comparator<Trade> {

        @Override
        public int compare(Trade t1, Trade t2) {
            if (t1.getTime() - t2.getTime() > 0) return 1;
            if (t1.getTime() - t2.getTime() < 0) return -1;
            return 0;
        }
    }

}
