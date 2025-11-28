package com.hliu.fundamental.linear.stackqueue.stack.monotonic;

import java.util.Stack;

// https://leetcode.com/problems/largest-rectangle-in-histogram/
public class C03_LargestRectangleHistogram {

  // 相等的值可以允许前面的算错, 后续的会纠正
  public int largestRectangleArea0(int[] arr) {
    Stack<Integer> stack = new Stack<>();
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < arr.length; i++) {

      while (!stack.isEmpty() && arr[i] <= arr[stack.peek()]) {
        int popIndex = stack.pop();
        int leftIndex = !stack.isEmpty() ? stack.peek() : -1;
        max = Math.max(max, arr[popIndex] * (i - leftIndex - 1));
      }
      stack.push(i);

    }
    while (!stack.isEmpty()) {
      int popIndex = stack.pop();
      int leftIndex = !stack.isEmpty() ? stack.peek() : -1;
      max = Math.max(max, arr[popIndex] * (arr.length - leftIndex - 1));
    }

    return max;
  }


  public static int largestRectangleArea(int[] arr) {
    if (arr == null || arr.length == 0) {
      return 0;
    }
    Stack<Integer> incrStack = new Stack<>();
    int N = arr.length;
    int res = 0;

    for (int i = 0; i < N; i++) {
      while (!incrStack.isEmpty() && arr[incrStack.peek()] >= arr[i]) {
        int popIndex = incrStack.pop();
        int leftIndex = incrStack.isEmpty() ? 0 : incrStack.peek() + 1;
        int rightIndex = i - 1;
        res = Math.max(arr[popIndex] * (rightIndex - leftIndex + 1), res);
      }
      incrStack.push(i);
    }

    while (!incrStack.isEmpty()) {
      int popIndex = incrStack.pop();
      int leftIndex = incrStack.isEmpty() ? 0 : incrStack.peek() + 1;
      int rightIndex = N - 1;
      res = Math.max(arr[popIndex] * (rightIndex - leftIndex + 1), res);
    }

    return res;
  }

}
