package com.hliu.advanced.c1.monotoneStack;

import java.util.Stack;

// https://leetcode.com/problems/number-of-smooth-descent-periods-of-a-stock/
public class C10_NumberOfSmoothDescentPeriodsOfStock {

  public static void main(String[] args) {
    C10_NumberOfSmoothDescentPeriodsOfStock obj = new C10_NumberOfSmoothDescentPeriodsOfStock();

    int[] arr = new int[]{12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 4, 3, 10, 9, 8, 7};
    System.out.println(1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 + 10);
    System.out.println(1 + 2);
    System.out.println(4 + 3 + 2 + 1);
    System.out.println(obj.getDescentPeriods(arr));
  }

  // monotonic stack
  public long getDescentPeriods(int[] arr) {
    int res = 0;
    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < arr.length; i++) {
      if (!stack.isEmpty() && arr[i] != arr[stack.peek()] - 1) {
        while (!stack.isEmpty()) {
          int poppedIndex = stack.pop();
          res += (i - poppedIndex);
        }
      }
      stack.push(i);
    }
    while (!stack.isEmpty()) {
      res += (arr.length - stack.pop());
    }
    return res;
  }



}
