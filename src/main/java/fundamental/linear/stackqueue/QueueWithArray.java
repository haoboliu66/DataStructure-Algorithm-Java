package src.main.java.fundamental.linear.stackqueue;

import java.util.LinkedList;
import java.util.Queue;

public class QueueWithArray {

    private int[] arr;
    private int size;
    private int pushIndex;
    private int pollIndex;
    private int limit;

    public QueueWithArray(int limit) {
        this.limit = limit;
        arr = new int[limit];
        size = 0;
        pollIndex = 0;
        pushIndex = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void add(int ele) {
        if (size == limit) {
            throw new RuntimeException("Queue is full");
        }
        size++;
        arr[pushIndex] = ele;
        pushIndex = nextIndex(pushIndex);
    }

    public int poll() {
        if (size == 0) {
            throw new RuntimeException("Queue Empty");
        }
        size--;
        int res = arr[pollIndex];
        pollIndex = nextIndex(pollIndex);
        return res;
    }

    private int nextIndex(int i) {
        return i < limit - 1 ? i + 1 : 0;
    }

    public static boolean isEqual(QueueWithArray myQueue, Queue<Integer> queue) {
        if (myQueue.size() != queue.size()) {
            return false;
        }
        while (!queue.isEmpty()) {
            if (queue.poll() != myQueue.poll()) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int limit = 50;
        Queue<Integer> queue = new LinkedList<>();
        QueueWithArray myQueue = new QueueWithArray(limit);
        int testTime = 100000;
        for (int i = 0; i < testTime; i++) {
            for (int j = 0; j < limit; j++) {
                int ran = (int) (Math.random() * 100);
                queue.add(ran);
                myQueue.add(ran);
            }
            for (int k = 0; k < 20; k++) {
                if (queue.poll() != myQueue.poll()) {
                    System.out.println("Fuck");
                    break;
                }
            }
            if (!isEqual(myQueue, queue)) {
                System.out.println("Oops");
                break;
            }
        }
        System.out.println("Finished");

    }

}
