package advanced.c1.slidingwindow;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;


public class SlidingWindowMaximum {
    /**
     * 给定一个数组arr, 一个固定大小为W的滑动窗口依次划过arr, 返回每次滑动后窗口内的最大值
     * 返回值是int[]
     */
    /*
    leetcode 239
     */
    public static int[] getMax(int[] arr, int W) {
        if (arr == null || W > arr.length || W < 1) {
            return null;
        }
        Deque<Integer> qmax = new LinkedList<>();//双向队列
        int L = 0;
        int[] res = new int[arr.length - W + 1];

        for (int R = 0; R < arr.length; R++) {
            // 如果队列末尾的数字 <= 当前的数字, 就一直弹出
            while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[R]) {
                qmax.pollLast();
            }
            qmax.addLast(R);
            if (qmax.peekFirst() == R - W) { //窗口左边界外的数字过期了,且该数字是最大值
                qmax.pollFirst(); //移除过期数字
            }

            if (R >= W - 1) { // R = W - 1 第一次形成窗口, 开始收集答案
                res[L++] = arr[qmax.peekFirst()];
            }
        }
        return res;
    }


    public static int[] getMin(int[] arr, int W) {
        if (arr == null || W > arr.length || W < 1) {
            return null;
        }
        Deque<Integer> qmax = new LinkedList<>();//双向队列
        int L = 0;
        int[] res = new int[arr.length - W + 1];

        for (int R = 0; R < arr.length; R++) {
            // 如果队列末尾的数字 >= 当前的数字, 就一直弹出
            while (!qmax.isEmpty() && arr[qmax.peekLast()] >= arr[R]) {
                qmax.pollLast();
            }
            qmax.addLast(R);
            if (qmax.peekFirst() == R - W) { //窗口左边界外的数字过期了,且该数字是最大值
                qmax.pollFirst(); //移除过期数字
            }

            if (R >= W - 1) { // R = W - 1 第一次形成窗口, 开始收集答案
                res[L++] = arr[qmax.peekFirst()];
            }
        }
        return res;
    }


    public static void main(String[] args) {
        int[] arr = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] arr1 = {1, -1};
        int W = 3;
        System.out.println(Arrays.toString(getMin(arr, W)));
    }
}
