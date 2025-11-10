package com.hliu.fundamental.linear.slidingwindow;

public class Problem_0643_MaximumAverageSubarrayI {

  public double findMaxAverage(int[] arr, int k) {
    int max = Integer.MIN_VALUE;
    int left = 0, right = 0;
    int sum = 0;
    for (; right < arr.length; right++) {
      if (right - left + 1 < k) {
        sum += arr[right];
        continue;
      }
      sum += arr[right];
      max = Math.max(max, sum);
      sum -= arr[left++];
    }
    return max / (double) k;
  }

}
