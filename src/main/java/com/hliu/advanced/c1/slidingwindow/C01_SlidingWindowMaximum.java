package com.hliu.advanced.c1.slidingwindow;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

// https://leetcode.com/problems/sliding-window-maximum/
public class C01_SlidingWindowMaximum {

  /**
   * 给定一个数组arr, 一个固定大小为W的滑动窗口依次划过arr, 返回每次滑动后窗口内的最大值 返回值是int[]
   */

  public int[] maxSlidingWindow(int[] arr, int k) {
    if (arr == null || arr.length == 0 || k < 1) {
      return null;
    }
    int[] res = new int[arr.length - k + 1];// 8  k = 3
    // (1 3 -1) -3 5 3 6 7
    int L = 0, R = 0;
    LinkedList<Integer> deque = new LinkedList<>();

    int index = 0;
    for (; R < arr.length; ) {
      // 队列要保持头->尾递减
      while (!deque.isEmpty() && arr[R] >= arr[deque.peekLast()]) {
        deque.pollLast();
      }
      deque.addLast(R);

      if (R - L + 1 == k) {
        res[index++] = arr[deque.peekFirst()];

          if (index == res.length) {
              break;
          }

        // 在窗口向前移动之前, 检查L位置是不是最大值
        if (L == deque.peekFirst()) {
          deque.pollFirst();
        }
        L++;
        R++;

      } else {  // 窗口还未形成,需要R向右走
        R++;
      }
    }
    return res;
  }


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
