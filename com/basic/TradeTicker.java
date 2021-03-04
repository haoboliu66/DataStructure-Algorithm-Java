package com.basic;

import com.basic.data.Trade;

import java.util.*;

public class TradeTicker {

    private static final TradeTicker ticker = new TradeTicker();

//    PriorityQueue<Trade> minHeap = new PriorityQueue<>((o1, o2) -> {
//        if (o1.getTime() - o2.getTime() > 0) return 1;
//        if (o1.getTime() - o2.getTime() < 0) return -1;
//        return 0;
//    });
    List<Trade> queue = new LinkedList<>();

    private TradeTicker() {
    }

    public static TradeTicker getInstance() {
        return ticker;
    }

    public void addRecord(Trade trade) {
        queue.add(trade);
    }

    public void updateAndDisplay() {
        Collections.sort(queue, new TradeComparator());
        queue.forEach(System.out::println);
    }

    private static class TradeComparator implements Comparator<Trade>{

        @Override
        public int compare(Trade t1, Trade t2) {
            if (t1.getTime() - t2.getTime() > 0) return 1;
            if (t1.getTime() - t2.getTime() < 0) return -1;
            return 0;
        }
    }


    public static void main(String[] args) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.add(1);
        minHeap.add(3);
        minHeap.add(2);
        System.out.println(minHeap);
    }
}
