package src.main.java.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class E_Problem_0346_MovingAverageFromDataStream {

    public class MovingAverage {
        Queue<Integer> q;
        int size;
        int cap;
        int sum;

        public MovingAverage(int l) {
            q = new LinkedList<>();
            cap = l;
            size = 0;
            sum = 0;
        }

        public double next(int val) {
            q.offer(val);
            if (size < cap) {
                size++;
            } else {
                int out = q.poll();
                sum -= out;
            }
            sum += val;
            return sum * 1.0 / size;
        }
    }

}
