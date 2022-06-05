package com.hliu.advanced.c1.monotoneStack;

import java.util.Arrays;
import java.util.Stack;

// https://leetcode.com/problems/sum-of-subarray-ranges/
public class C07_SumOfSubarrayRanges {

  // 1 2 2 2 3

  public static void main(String[] args) {
    C07_SumOfSubarrayRanges obj = new C07_SumOfSubarrayRanges();

    int[] arr = {1, 2, 3};
    obj.subArrayRanges(arr);
  }

  public long subArrayRanges(int[] arr) {

    long sum = 0;

    int[] leftLessThan = getLeftMoreThan(arr);
    int[] rightLessThanOrEqual = getRightMoreThanOrEqual(arr);

    System.out.println(Arrays.toString(leftLessThan));
    System.out.println(Arrays.toString(rightLessThanOrEqual));

    for (int i = 0; i < arr.length; i++) {

      int leftLessIndex = leftLessThan[i];
      int rightLessOrEqualIndex = rightLessThanOrEqual[i];

      int larger = arr[i];
      if (leftLessIndex == -1 && rightLessOrEqualIndex == arr.length) {
        continue;
      } else if (leftLessIndex != -1 && rightLessOrEqualIndex != arr.length) {
        larger = Math.max(arr[leftLessIndex], arr[rightLessOrEqualIndex]);
      } else if (leftLessIndex != -1) {
        larger = arr[leftLessIndex];
      } else {
        larger = arr[rightLessOrEqualIndex];
      }
      System.out.println("larger number: " + larger);
      sum += (larger - arr[i]);
    }

    return sum;
  }

  public int[] getLeftMoreThan(int[] arr) {
    int[][] res = new int[2][arr.length];
    int[] maxValues = new int[arr.length];
    Arrays.fill(maxValues, Integer.MIN_VALUE);

    int[] leftMoreThan = new int[arr.length];
    Stack<Integer> stack = new Stack<>();

    for (int i = arr.length - 1; i >= 0; i--) {
      while (!stack.isEmpty() && arr[i] > arr[stack.peek()]) {
        leftMoreThan[stack.pop()] = i;
      }
      stack.push(i);
    }
    while (!stack.isEmpty()) {
      leftMoreThan[stack.pop()] = -1;
    }

    return leftMoreThan;
  }


  public int[] getRightMoreThanOrEqual(int[] arr) {
    int[][] res = new int[2][arr.length];

    int[] maxValues = new int[arr.length];
    Arrays.fill(maxValues, Integer.MIN_VALUE);

    int[] rightMoreThanOrEqual = new int[arr.length];
    Stack<Integer> stack = new Stack<>();

    for (int i = 0; i < arr.length; i++) {
      while (!stack.isEmpty() && arr[i] <= arr[stack.peek()]) {
        rightMoreThanOrEqual[stack.pop()] = i;



      }
      stack.push(i);
    }
    while (!stack.isEmpty()) {
      rightMoreThanOrEqual[stack.pop()] = arr.length;
    }
    return rightMoreThanOrEqual;
  }

}
