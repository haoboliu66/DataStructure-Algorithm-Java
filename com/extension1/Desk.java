package com.extension1;

import com.extension1.data.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.*;

public class Desk {

    static TradeTicker ticker = TradeTicker.getInstance();

    static Timer timer = new Timer();

    static final Map<String, Order> orderMap = new HashMap<>();

    // store updates in a chronological order
    static PriorityQueue<Order> minHeap = new PriorityQueue<>((o1, o2) -> {
        if (o1.getTime() - o2.getTime() > 0) return 1;
        if (o1.getTime() - o2.getTime() < 0) return -1;
        return 0;
    });

    static List<Order> orderList = new LinkedList<>();

    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src/order.txt")));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null) {

            String[] input = line.split("\\t");
//            System.out.println(Arrays.toString(input));
            Order order = getOrder(input);
            if (order instanceof CreatedOrder) {
                String id = order.getOrderId();
                orderMap.put(id, order);
            } else {
                // add as a chronological order
                minHeap.add(order);
            }

        }
        handle();
    }

    public static Order getOrder(String[] input) {
        String time = input[0];
        String code = input[1];
        String id = input[2];
        // CANCELLED update
        if ("CANCELLED".equals(code)) {
            if (!orderMap.containsKey(id)) {
                throw new RuntimeException("No such order with ID: " + id);
            }
            return new CancelledOrder(Long.parseLong(time), OpCode.CANCELLED, id);
        }

        // CREATED update
        if ("CREATED".equals(code)) {
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

    public static void handle() {
        while (!minHeap.isEmpty()) {
            Order cur = minHeap.poll();
            String id = cur.getOrderId();
            if (cur instanceof CancelledOrder) {
                orderMap.remove(id);
                continue;
            }
            Order createdOrder = orderMap.get(id); // 获取对应的createdOrder
            Trade trade = createdOrder.updateOrder(cur);//更新数据
            ticker.addRecord(trade);
        }
        ticker.updateAndDisplay();
    }


//    public static Order handle(String[] input) {
//        String time = input[0];
//        String code = input[1];
//        String id = input[2];
//        // CANCELLED update
//        if ("CANCELLED".equals(code)) {
//            if (!orderMap.containsKey(id)) {
//                throw new RuntimeException("No such order with ID: " + id);
//            }
//            // cancel - remove an order from orderMap
////            orderMap.remove(id);
//            return new CancelledOrder(Long.parseLong(time), OpCode.CANCELLED, id);
//        }
//
//        // CREATED update
//        if ("CREATED".equals(code)) {
//            // duplicate orderID
//            if (orderMap.containsKey(id)) {
//                throw new RuntimeException("Duplicate order with ID: " + id);
//            }
//            String param = input[5];
//            Side side = "BUY".equals(param) ? Side.BUY : Side.SELL;
//            CreatedOrder createdOrder = new CreatedOrder(Long.parseLong(time), OpCode.CREATED, id, new BigDecimal(input[3]), new BigDecimal(input[4]), side, input[6]);
//            return createdOrder;
//        }
//
//        // FILLED update
//        if (!orderMap.containsKey(id)) {
//            throw new RuntimeException("No such order with ID: " + id);
//        }
//
//        Order oldOrder = orderMap.get(id);  // get current order (CREATED/FILLED)
//        Order currentOrder = new FilledOrder(Long.parseLong(input[0]), OpCode.FILLED, id, new BigDecimal(input[3]), new BigDecimal(input[4]));
//
//        Trade trade = oldOrder.updateOrder(currentOrder);
//        ticker.addRecord(trade);
//        ticker.updateAndDisplay();
//        CreatedOrder order = (CreatedOrder) oldOrder;
//
//        // no remaining quantity
//        if (BigDecimal.ZERO.compareTo(order.getQuantity()) == 0) orderMap.remove(order.getOrderId());
//
//
//        return null;
//    }


}
