package com.hliu.fundamental.linear.stackqueue.monotonic;

import java.util.Stack;

// https://leetcode.com/problems/number-of-valid-subarrays/
public class C09_NumberOfValidSubarrays {

  //  x <= [......]
  public int validSubarrays(int[] arr) {
    if (arr == null || arr.length == 0) {
      return 0;
    }
    int count = 0;
    int[] rightLess = getRightLess(arr);

    for (int i = 0; i < arr.length; i++) {
      int rightLessIndex = rightLess[i];
      count += rightLessIndex - i;
    }
    return count;
  }

  public int[] getRightLess(int[] arr) {
    int[] rightLess = new int[arr.length];
    Stack<Integer> stack = new Stack<>();

    for (int i = 0; i < arr.length; i++) {
      while (!stack.isEmpty() && arr[i] < arr[stack.peek()]) {
        rightLess[stack.pop()] = i;
      }
      stack.push(i);
    }
    while (!stack.isEmpty()) {
      rightLess[stack.pop()] = arr.length;
    }
    return rightLess;
  }

}
