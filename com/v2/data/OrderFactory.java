package com.v2.data;

import com.v2.OpCode;
import com.v2.Side;

import java.math.BigDecimal;
import java.util.Map;

public class OrderFactory {

    Map<String, Order> orderMap;

    public OrderFactory(Map<String, Order> orderMap) {
        this.orderMap = orderMap;
    }

    public Order createOrder(String[] input) {
        String time = input[0];
        String code = input[1];
        String id = input[2];
        // CANCELLED update
        if ("CANCELLED".equalsIgnoreCase(code)) {
            if (!orderMap.containsKey(id)) {
                throw new RuntimeException("No such order with ID: " + id);
            }
            return new CancelledOrder(Long.parseLong(time), OpCode.CANCELLED, id);
        }

        // CREATED update
        if ("CREATED".equalsIgnoreCase(code)) {
            // duplicate orderID
            if (orderMap.containsKey(id)) {
                throw new RuntimeException("Duplicate order with ID: " + id);
            }
            String param = input[5];
            Side side = "BUY".equals(param) ? Side.BUY : Side.SELL;
            return new CreatedOrder(Long.parseLong(time), OpCode.CREATED, id, new BigDecimal(input[3]), new BigDecimal(input[4]), side, input[6]);
        }

        // FILLED update
        if (!orderMap.containsKey(id)) {
            throw new RuntimeException("No such order with ID: " + id);
        }
        return new FilledOrder(Long.parseLong(time), OpCode.FILLED, id, new BigDecimal(input[3]), new BigDecimal(input[4]));


    }


}
