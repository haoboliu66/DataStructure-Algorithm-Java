package com.extension2;

import com.extension2.data.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.*;
import java.util.logging.Logger;

public class Desk {

    private TradeTicker ticker = TradeTicker.getInstance();

    private Timer timer = new Timer();

    private static final long EXPIRY_PERIOD = 60000L;

    // store updates in a chronological order
    PriorityQueue<Order> minHeap;

    // store initial order id and its object
    private final Map<String, Order> orderMap;

    private final OrderFactory factory;

    private static final Logger LOGGER = Logger.getLogger(Desk.class.toString());

    public Desk() {
        orderMap = new HashMap<>();
        factory = new OrderFactory(orderMap);
        minHeap = new PriorityQueue<>(new OrderComparator());
    }

    public void run() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            String line = null;
            LOGGER.info("Application Started");
            while ((line = br.readLine()) != null) {
                if ("done".equalsIgnoreCase(line)) {  // enter "done" to terminate this round of input
                    getTrade();
                    continue;
                }
                String[] input = line.split("\\t");
                // check validity of the input
                if (!validate(input)) {
                    LOGGER.warning("Invalid Input");
                    continue;
                }
                Order order = getOrder(input); // get an order according to the input format
                handleOrder(order);
            }
        } catch (Exception e) {
            LOGGER.severe(e.toString());
            System.exit(1);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                LOGGER.severe(e.toString());
            }
        }
    }

    private boolean validate(String[] input) {
        if (input.length < 3) return false;
        String code = input[1];
        if ("CREATED".equalsIgnoreCase(code)) return input.length == 7;
        if ("FILLED".equalsIgnoreCase(code)) return input.length == 5;
        return input.length == 3;
    }

    public Order getOrder(String[] input) {
        return factory.createOrder(input);
    }

    public void handleOrder(Order order) {
        if (order instanceof CreatedOrder) {
            String id = order.getOrderId();
            orderMap.put(id, order);
            runTimerByOrderId(id);
        } else {
            // add FILLED & CANCELLED as a chronological order
            minHeap.add(order);
        }
    }

    public void runTimerByOrderId(String id) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                CreatedOrder checkOrder = (CreatedOrder) orderMap.get(id);
                if (checkOrder != null && BigDecimal.ZERO.compareTo(checkOrder.getQuantity()) != 0) {
                    orderMap.remove(id);
                    LOGGER.warning("Order with Id: " + checkOrder.getOrderId() + " is expired");
                }
            }
        }, EXPIRY_PERIOD);
    }

    public void getTrade() {
        while (!minHeap.isEmpty()) {
            Order cur = minHeap.poll();
            String id = cur.getOrderId();
            if (cur instanceof CancelledOrder) {
                orderMap.remove(id);
                continue;
            }
            CreatedOrder order = (CreatedOrder) orderMap.get(id);  // get corresponding CreatedOrder object
            if (order != null) {
                Trade trade = order.updateOrder(cur);  // update data in the order
                ticker.addRecord(trade);  // collect the generated trade
                // check no remaining quantity
                if (BigDecimal.ZERO.compareTo(order.getQuantity()) == 0) {
                    LOGGER.info("Order with Id: " + order.getOrderId() + " has zero remaining quantity");
                    orderMap.remove(order.getOrderId());
                }
            }
        }
        LOGGER.info("Derived Trade Info: ");
        ticker.displayTrades();
    }


    private static class OrderComparator implements Comparator<Order> {

        @Override
        public int compare(Order o1, Order o2) {
            if (o1.getTime() - o2.getTime() > 0) return 1;
            if (o1.getTime() - o2.getTime() < 0) return -1;
            return 0;
        }
    }

}
