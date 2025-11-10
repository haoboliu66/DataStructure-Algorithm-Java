package com.hliu.fundamental.linear.array.presum;


/*

Given an array of integers nums, calculate the pivot index of this array.

The pivot index is the index where the sum of all the numbers strictly to the left of the index is equal to the sum of all the numbers strictly to the index's right.

If the index is on the left edge of the array, then the left sum is 0 because there are no elements to the left. This also applies to the right edge of the array.

Return the leftmost pivot index. If no such index exists, return -1.

 */

public class Problem_0724_FindPivotIndex {

  // 相同的题 1991. Find the Middle Index in Array

  public int pivotIndex(int[] arr) {
    int leftSum = 0;
    int totalSum = 0;
    for (int x : arr) {
      totalSum += x;
    }
    for (int i = 0; i < arr.length; i++) {
      leftSum += (i == 0 ? 0 : arr[i - 1]);

      // leftSum] [i] [rightSum

      int rightSum = totalSum - leftSum - arr[i];
      if (leftSum == rightSum) {
        return i;
      }
    }
    return -1;
  }

}
