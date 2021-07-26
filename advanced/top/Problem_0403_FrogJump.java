package advanced.top;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Problem_0403_FrogJump {

    //The first stone's position is always 0.

    public boolean canCross(int[] stones) {

        if (stones.length > 1 && stones[1] != 1) {
            return false;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < stones.length; i++) {
            map.put(stones[i], i);
        }

        return process(stones, 1, 1, map) || process(stones, 1, 1, map);
    }

    // 1 - 1/2 - 1,2/1,2,3 -

    // 已经跳到了i位置, 当前可跳的距离是cur
    public boolean process(int[] stones, int i, int cur, HashMap<Integer, Integer> map) {
        if (i == stones.length - 1) {
            return true;
        }
        if (cur <= 0) {
            return false;
        }

        if (map.containsKey(i + cur)) { // 当前位置 + cur步数, 到达的位置是index
            Integer index = map.get(i + cur);
            // 从index开始, 可以走的步数是cur - 1, cur , cur + 1
            return process(stones, index, cur, map) || process(stones, index, cur - 1, map) || process(stones, index, cur + 1, map);
        }

        if (map.containsKey(i + cur - 1)) {
            Integer index = map.get(i + cur - 1);
            // 从index开始, 可以走的步数是cur - 1, cur , cur + 1
            return process(stones, index, cur - 1, map) || process(stones, index, cur - 2, map) || process(stones, index, cur, map);
        }

        if (map.containsKey(i + cur + 1)) {
            Integer index = map.get(i + cur + 1);
            // 从index开始, 可以走的步数是cur - 1, cur , cur + 1
            return process(stones, index, cur + 1, map) || process(stones, index, cur, map) || process(stones, index, cur + 2, map);
        }

        return false;
    }


    static char[] number = "123456".toCharArray();
    static char[] letter = "ABCDEF".toCharArray();
    static int indexNum = 0;
    static int indexLetter = 0;

    static ReentrantLock lock = new ReentrantLock();
    static Condition numLock = lock.newCondition();
    static Condition letterLock = lock.newCondition();


    public static void print() {

        new Thread(() -> {
            try {
                lock.lock();
                for (char i : number) {
                    System.out.println(i);
                    letterLock.signal();
                    numLock.await();
                }
                letterLock.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }).start();


        new Thread(() -> {
            try {
                lock.lock();
                for (char i : letter) {

                    System.out.println(i);
                    numLock.signal();
                    letterLock.await();
                }
                numLock.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }).start();

    }


    public static int[] window(int[] arr, int W) {

        int[] res = new int[arr.length - W + 1];
        int L = 0;
        int R;
        Deque<Integer> deque = new LinkedList<>();
        for (R = 0; R < arr.length; R++) {
            while (!deque.isEmpty() && arr[deque.peekFirst()] <= arr[R]) {
                deque.pollFirst();
            }
            deque.addLast(R);

            if (R - W + 1 >= 0) {
                res[L++] = arr[deque.peekFirst()];
            }

            if (deque.peekFirst() < R - W + 1) {
                deque.pollFirst();
            }

        }

        return res;
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        print();

        ExecutorService service = Executors.newCachedThreadPool();

        Future<String> future = service.submit(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello world";
        });
        int i = 0;
        while (!future.isDone()) {
            System.out.println(i++);
            Thread.sleep(1000);
        }
        System.out.println(future.get());
        service.shutdown();

    }

}
