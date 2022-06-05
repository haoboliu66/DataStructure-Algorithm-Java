package com.hliu.sys.c12;

import java.util.Arrays;

import org.junit.Test;

public class C02_MedianOfTwoSortedArray {

  // https://leetcode.com/problems/median-of-two-sorted-arrays/

  public double findMedianSortedArrays(int[] arr1, int[] arr2) {
    return 0;
  }


  public int findMedian(int[] arr1, int[] arr2, int L1, int R1, int L2, int R2) {
    int mid1, mid2;
    while (L1 < R1) {
      mid1 = L1 + (R1 - L1) / 2;
      mid2 = L2 + (R2 - L2) / 2;

      // even length
      if (((R1 - L1 + 1) & 1) == 0) {
        if (arr1[mid1] == arr2[mid2]) {
          return arr1[mid1];
        } else if (arr1[mid1] > arr2[mid2]) {
          R1 = mid1;
          L2 = mid2 + 1;
        } else {
          R2 = mid2;
          L1 = mid1 + 1;
        }

      } else {
        // odd length
        if (arr1[mid1] == arr2[mid2]) {
          return arr1[mid1];
        } else if (arr1[mid1] > arr2[mid2]) {
          if (arr2[mid2] > arr1[mid1 - 1]) {
            return arr2[mid2];
          }
          R1 = mid1 - 1;
          L2 = mid2 + 1;
        } else {
          if (arr1[mid1] > arr2[mid2 - 1]) {
            return arr1[mid1];
          }
          L1 = mid1 + 1;
          R2 = mid2 - 1;
        }
      }
    }

    return Math.min(arr1[L1], arr2[L2]);
  }

  // for test
  // O(N)
  public int rightFindMedian(int[] arr1, int[] arr2, int L1, int R1, int L2, int R2) {
    int N = R1 - L1 + 1;
    // 10 => 5 -> index: 4
    int[] arr = new int[N * 2];
    // 0 1 2 3 4 5 6
    for (int i = 0; i < N; i++) {
      if (arr1[L1] < arr2[L2]) {
        arr[i] = arr1[L1++];
      } else {
        arr[i] = arr2[L2++];
      }
    }
    return arr[N - 1];
  }


  // for test
  private int[] generateRandomSortedArray(int len, int val) {
    int[] res = new int[len];
    for (int i = 0; i != len; i++) {
      int cur = (int) (Math.random() * val) + 1;
      res[i] = cur;
    }
    Arrays.sort(res);
    return res;
  }

  @Test
  public void test_findMedian() {
    int len = 50, max = 300, times = 200000;
    int[] arr1, arr2;
    System.out.println("Started");
    for (int i = 0; i < times; i++) {
      arr1 = generateRandomSortedArray(len, max);
      arr2 = generateRandomSortedArray(len, max);
      int res1 = findMedian(arr1, arr2, 0, arr1.length - 1, 0, arr2.length - 1);
      int res2 = rightFindMedian(arr1, arr2, 0, arr1.length - 1, 0, arr2.length - 1);
      if (res1 != res2) {
        System.out.println("Oops");
        System.out.println("res1: " + res1);
        System.out.println("res2: " + res2);
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
        break;
      }
    }
    System.out.println("Done");
  }


}
