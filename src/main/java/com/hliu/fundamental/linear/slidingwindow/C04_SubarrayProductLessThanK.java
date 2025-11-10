package com.hliu.fundamental.linear.slidingwindow;

// https://leetcode.com/problems/subarray-product-less-than-k/
// not done yet
public class C04_SubarrayProductLessThanK {

  public static void main(String[] args) {

    int[] arr = {10, 5, 2, 6};

    System.out.println(numSubarrayProductLessThanK(arr, 100));
  }

  public static int numSubarrayProductLessThanK(int[] arr, int k) {
    int left = 0, right = 0;
    int p = 1;
    int count = 0;
    while (right < arr.length) {
      while (p < k) {
        p *= arr[right];
        right++;
        if (right == arr.length) {
          break;
        }
      }
      count += (right - left);
      while (p >= k) {
        p /= arr[left++];
        if (left == right) {
          break;
        }
      }
    }
    return count;
  }
}
