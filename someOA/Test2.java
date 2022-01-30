package someOA;

import java.util.*;

public class Test2 {

    // [ 1 2 3 ]

    //

    public static void main(String[] args) {
        List<String> operations = Arrays.asList("push", "push", "push", "pop");
        List<Integer> x = Arrays.asList(1, 2, 3, 1);
        List<Long> res = maxMin2(operations, x);
        System.out.println(res);
    }

    public static List<Long> maxMin3(List<String> operations, List<Integer> x) {
        List<Long> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        Deque<Integer> deque = new LinkedList<>();
        long min = 0, max = 0;
        int size = operations.size();
        for (int i = 0; i < size; i++) {
            String ops = operations.get(i);
            int num = x.get(i);
            if ("push".equals(ops)) {
                map.put(num, num);
            } else {
                // pop
                map.remove(num);
            }
            res.remove(2);
            res.add(max * min);
        }

        return res;
    }


                public static List<Long> maxMin2(List<String> operations, List<Integer> x) {
                    List<Long> res = new ArrayList<>();
                    Map<Integer, Integer> map = new HashMap<>();
                    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
                    PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
                    long min = 0, max = 0;
                    int size = operations.size();
                    for (int i = 0; i < size; i++) {
                        String ops = operations.get(i);
                        int num = x.get(i);
                        if ("push".equals(ops)) {
                            minHeap.offer(num);
                            maxHeap.offer(num);
                            map.put(num, num);
                        } else {
                            // pop
                map.remove(num);
            }
            while (!map.containsKey(minHeap.peek())) {
                minHeap.poll();
            }
            min = minHeap.peek();
            while (!map.containsKey(maxHeap.peek())) {
                maxHeap.poll();
            }
            max = maxHeap.peek();
            res.add(max * min);
        }

        return res;
    }


    public static List<Long> maxMin(List<String> operations, List<Integer> x) {
        List<Long> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        long min = 0, max = 0;
        int size = operations.size();
        for (int i = 0; i < size; i++) {
            String ops = operations.get(i);
            int num = x.get(i);
            if ("push".equals(ops)) {
                minHeap.offer(num);
                maxHeap.offer(num);
                // map.put(num, num);
            } else {
                // pop
                //map.remove(num);
                minHeap.remove(num);
                maxHeap.remove(num);
            }
            System.out.println("min: " + minHeap);
            System.out.println("max: " + maxHeap);
//            while (!map.containsKey(minHeap.peek())) {
//                System.out.println("----");
//                minHeap.poll();
//            }
            min = minHeap.peek();
//            while (!map.containsKey(maxHeap.peek())) {
//                System.out.println("???");
//                maxHeap.poll();
//            }
            max = maxHeap.peek();
            res.add(max * min);
        }

        return res;
    }



}
