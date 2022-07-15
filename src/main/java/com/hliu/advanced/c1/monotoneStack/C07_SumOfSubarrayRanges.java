package com.hliu.advanced.c1.monotoneStack;

import java.util.Stack;

// https://leetcode.com/problems/sum-of-subarray-ranges/
/*
假设数组arr有4个subarray,a1,b1,c1,d1是每个subarray的max, a2,b2,c2,d2是每个subarray的min
要求的是 a1-a2+b1-b2+c1-c2+d1-d2
subA, subB, subC, subD
a1,a2 b1,b2 c1,c2 d1,d2
===> a1-a2+b1-b2+c1-c2+d1-d2 = (a1+b1+c1+d1) - (a2+b2+c2+d2)

找每个subarray的max - min 然后求sum, 转化为 sum(max(b)) - sum(min(b)), b是所有子数组
即 => 每个子数组的最大值的累加和 - 每个子数组的最小值的累加和

复用 https://leetcode.com/problems/sum-of-subarray-minimums/ 的答案

 */
public class C07_SumOfSubarrayRanges {

  public long subArrayRanges(int[] arr) {
    long minSum = 0;
    long maxSum = 0;

    int[] leftLess = getLeftLess(arr);
    int[] rightLessOrEqual = getRightLessOrEqual(arr);

    int[] leftGreater = getLeftGreater(arr);
    int[] rightGreaterOrEqual = getRightGreaterOrEqual(arr);

    for (int i = 0; i < arr.length; i++) {
      long leftLessIndex = leftLess[i];
      long rightLessOrEqualIndex = rightLessOrEqual[i];

      long leftGreaterIndex = leftGreater[i];
      long rightGreaterOrEqualIndex = rightGreaterOrEqual[i];

      minSum += (i - leftLessIndex) * (rightLessOrEqualIndex - i) * arr[i];
      maxSum += (i - leftGreaterIndex) * (rightGreaterOrEqualIndex - i) * arr[i];
    }
    return maxSum - minSum;
  }

  public int[] getLeftLess(int[] arr) {
    int[] leftLess = new int[arr.length];
    Stack<Integer> stack = new Stack<>();

    for (int i = arr.length - 1; i >= 0; i--) {
      while (!stack.isEmpty() && arr[i] < arr[stack.peek()]) {
        leftLess[stack.pop()] = i;
      }
      stack.push(i);
    }
    while (!stack.isEmpty()) {
      leftLess[stack.pop()] = -1;
    }
    return leftLess;
  }


  public int[] getRightLessOrEqual(int[] arr) {
    int[] rightLessOrEqual = new int[arr.length];
    Stack<Integer> stack = new Stack<>();

    for (int i = 0; i < arr.length; i++) {
      while (!stack.isEmpty() && arr[i] <= arr[stack.peek()]) {
        rightLessOrEqual[stack.pop()] = i;
      }
      stack.push(i);
    }
    while (!stack.isEmpty()) {
      rightLessOrEqual[stack.pop()] = arr.length;
    }
    return rightLessOrEqual;
  }

  public int[] getLeftGreater(int[] arr) {
    int[] leftMore = new int[arr.length];
    Stack<Integer> stack = new Stack<>();

    for (int i = arr.length - 1; i >= 0; i--) {
      while (!stack.isEmpty() && arr[i] > arr[stack.peek()]) {
        leftMore[stack.pop()] = i;
      }
      stack.push(i);
    }
    while (!stack.isEmpty()) {
      leftMore[stack.pop()] = -1;
    }

    return leftMore;
  }


  public int[] getRightGreaterOrEqual(int[] arr) {
    int[] rightMoreOrEqual = new int[arr.length];
    Stack<Integer> stack = new Stack<>();

    for (int i = 0; i < arr.length; i++) {
      while (!stack.isEmpty() && arr[i] >= arr[stack.peek()]) {
        rightMoreOrEqual[stack.pop()] = i;
      }
      stack.push(i);
    }
    while (!stack.isEmpty()) {
      rightMoreOrEqual[stack.pop()] = arr.length;
    }
    return rightMoreOrEqual;
  }


}
