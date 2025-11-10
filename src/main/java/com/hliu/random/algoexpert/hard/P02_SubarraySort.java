package com.hliu.random.algoexpert.hard;


/*
Write a function that takes in an array of at
least two integers and that returns an array of
the starting and ending indices of the smallest
subarray in the input array that needs to be
sorted in place in order for the entire input
array to be sorted (in ascending order).
If the input array is already sorted, the
function should return [-1, -1].
Sample Input
 [1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19]
Sample Output
[3, 9]

 */
public class P02_SubarraySort {

  public static int[] subarraySort(int[] arr) {
    int max = arr[0];
    int rightIndex = -1, leftIndex = -1;
    for (int i = 1; i < arr.length; i++) {
      if (arr[i] < max) {
        rightIndex = i;
      }
      max = Math.max(max, arr[i]);

    }
    int min = arr[arr.length - 1];
    for (int i = arr.length - 2; i >= 0; i--) {
      if (arr[i] > min) {
        leftIndex = i;
      }
      min = Math.min(min, arr[i]);

    }
    return new int[]{leftIndex, rightIndex};
  }
}
