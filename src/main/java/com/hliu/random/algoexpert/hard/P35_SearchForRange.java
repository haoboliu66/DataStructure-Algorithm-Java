package com.hliu.random.algoexpert.hard;


import java.util.Arrays;

/*
Write a function that takes in a sorted array of
integers as well as a target integer. The
function should use a variation of the Binary
Search algorithm to find a range of indices in
between which the target number is
contained in the array and should return this
range in the form of an array.
The first number in the output array should
represent the first index at which the target
number is located, while the second number
should represent the last index at which the
target number is located. The function should
return [-1, -1] if the integer isn't
contained in the array.
If you're unfamiliar with Binary Search, we
recommend watching the Conceptual
Overview section of the Binary Search
question's video explanation before starting
to code.

Input:  [0, 1, 21, 33, 45, 45, 45, 45, 45, 45, 61, 71, 73],  45
Output: [4,9]

 */
public class P35_SearchForRange {

  public static void main(String[] args) {
    int[] arr = {0, 1, 21, 33, 45, 45, 45, 45, 45, 45, 61, 71, 73};
    int t = 45;

    System.out.println(Arrays.toString(searchForRange(arr, t)));
  }

  public static int[] searchForRange(int[] arr, int target) {

    int i = leftMostGreaterOrEqual(arr, target);
    int j = rightMostSmallerOrEqual(arr, target);

    if (i > arr.length - 1 || j < 0 || arr[i] != target || arr[j] != target) {
      return new int[]{-1, -1};
    }

    return new int[]{j, i};
  }

  // [5,7,7,8,8,10] , 5
  public static int leftMostGreaterOrEqual(int[] arr, int target) {
    int left = 0, right = arr.length - 1, mid;
    int index = arr.length;  // corner case: 如果arr最后一个值都比target小, 意味着没有答案
    while (left <= right) {
      mid = left + ((right - left) >> 1);

      if (arr[mid] <= target) {
        index = mid;
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return index;
  }

  public static int rightMostSmallerOrEqual(int[] arr, int target) {
    int left = 0, right = arr.length - 1, mid;
    int index = -1; // corner case: 如果arr第一个值都比target大, 意味着没有答案
    while (left <= right) {
      mid = left + ((right - left) >> 1);
      if (arr[mid] >= target) {
        index = mid;
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    return index;
  }

}
