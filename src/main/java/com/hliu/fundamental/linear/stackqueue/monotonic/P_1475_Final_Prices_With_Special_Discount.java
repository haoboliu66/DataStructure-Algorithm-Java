package com.hliu.fundamental.linear.stackqueue.monotonic;

import java.util.Arrays;
import java.util.Stack;


/*

Find the Most Competitive Subsequence
Minimum Number of Removals to Make Mountain Array
Final Prices With a Special Discount in a Shop
Constrained Subsequence Sum
Minimum Cost Tree From Leaf Values
Sum of Subarray Minimums
Online Stock Span
Score of Parentheses
Next Greater Element II
Next Greater Element I
Largest Rectangle in Histogram
Trapping Rain Water
 */

public class P_1475_Final_Prices_With_Special_Discount {

  public int[] finalPrices(int[] prices) {

    int[] rightSmaller = getRightSmaller(prices);
    int[] res = new int[prices.length];
    for (int i = 0; i < prices.length; i++) {
      res[i] = prices[i];
      if (rightSmaller[i] != -1) {
        res[i] -= prices[rightSmaller[i]];
      }

    }
    return res;
  }

  public int[] getRightSmaller(int[] arr) {
    int[] res = new int[arr.length];
    Arrays.fill(res, -1);

    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < arr.length; i++) {
      while (!stack.isEmpty() && arr[i] <= arr[stack.peek()]) {
        res[stack.pop()] = i;
      }
      stack.push(i);
    }

    while (!stack.isEmpty()) {
      res[stack.pop()] = -1;
    }
    return res;
  }
}
