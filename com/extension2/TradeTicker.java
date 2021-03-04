package com.extension2;

import com.extension2.data.Trade;

import java.math.BigDecimal;
import java.util.*;
import java.util.logging.Logger;

public class TradeTicker {

    private static final TradeTicker ticker = new TradeTicker();

    /*  total volume */
    private BigDecimal volume = new BigDecimal(0);

    private List<Trade> queue = new LinkedList<>();

    private static final Logger LOGGER = Logger.getLogger(TradeTicker.class.toString());

    private TradeTicker() {
    }

    public static TradeTicker getInstance() {
        return ticker;
    }

    /**
     * add a trade and calculate the volume of this trade
     * @param trade
     */
    public void addRecord(Trade trade) {
        queue.add(trade);
        volume = volume.add(trade.getTransaction().multiply(trade.getFillprice()));
    }

    /**
     * display total volume and recent trades in chronological order
     */
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
