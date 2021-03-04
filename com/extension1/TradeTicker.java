package com.extension1;

import com.extension1.data.Trade;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class TradeTicker {

    private static final TradeTicker ticker = new TradeTicker();

    PriorityQueue<Trade> minHeap = new PriorityQueue<>((t1, t2) -> (int) (t1.getTime() - t2.getTime()));

    Deque<Trade> queue = new LinkedList<>();

    private TradeTicker() {
    }


    public static TradeTicker getInstance() {
        return ticker;
    }

    public void addRecord(Trade trade) {
        queue.offerLast(trade);
        minHeap.add(trade);
    }

    public void updateAndDisplay() {
        minHeap.forEach(System.out::println);
    }
}
