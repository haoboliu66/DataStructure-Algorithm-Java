package com.basic;

import com.basic.data.CreatedOrder;
import com.basic.data.FilledOrder;
import com.basic.data.Order;
import com.basic.data.Trade;

import java.math.BigDecimal;
import java.util.*;

public class Desk {

    private TradeTicker ticker = TradeTicker.getInstance();

    private final Map<String, Order> orderMap = new HashMap<>();

    public void run() {
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\\n");
        while (scanner.hasNext()) {
            String line = scanner.next();
            String[] input = line.split("\\t");
            Order order = handle(input);
            if (order != null) {
                String id = order.getOrderId();
                orderMap.put(id, order);
            }
        }
    }


    public Order handle(String[] input) {
        String code = input[1];
        String id = input[2];
        // CANCELLED update
        if ("CANCELLED".equals(code)) {
            if (!orderMap.containsKey(id)) {
                throw new RuntimeException("No such order with ID: " + id);
            }
            // cancel - remove an order from orderMap
            orderMap.remove(id);
            return null;
        }
        // CREATED update
        if ("CREATED".equals(code)) {
            // duplicate orderID
            if (orderMap.containsKey(id)) {
                throw new RuntimeException("Duplicate order with ID: " + id);
            }
            String param = input[5];
            Side side = "BUY".equals(param) ? Side.BUY : Side.SELL;
            return new CreatedOrder(Long.parseLong(input[0]), OpCode.CREATED, input[2], new BigDecimal(input[3]), new BigDecimal(input[4]), side, input[6]);
        }

        // FILLED update
        if (!orderMap.containsKey(id)) {
            throw new RuntimeException("No such order with ID: " + id);
        }
        Order oldOrder = orderMap.get(id);  // get current order (CREATED/FILLED)
        Order currentOrder = new FilledOrder(Long.parseLong(input[0]), OpCode.FILLED, id, new BigDecimal(input[3]), new BigDecimal(input[4]));

        Trade trade = oldOrder.updateOrder(currentOrder);
        ticker.addRecord(trade);
        ticker.updateAndDisplay();
        CreatedOrder order = (CreatedOrder) oldOrder;

        // no remaining quantity
        if (BigDecimal.ZERO.compareTo(order.getQuantity()) == 0) orderMap.remove(order.getOrderId());

        return null;
    }

}
