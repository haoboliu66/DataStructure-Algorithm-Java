package com.hliu.fundamental.sorting.mergesort;

import java.util.Arrays;

/*
https://leetcode.com/problems/reverse-pairs/

Note: To handle Integer overflow
 */
public class C03_ReversePairs {

  public static int reversePairs(int[] arr) {
    if (arr == null || arr.length < 2) {
      return 0;
    }
    return process(arr, 0, arr.length - 1);
  }

  public static int process(int[] arr, int l, int r) {
    if (l == r) {
      return 0;
    }
    int mid = l + ((r - l) >>> 1);
    return process(arr, l, mid) +
        process(arr, mid + 1, r) +
        merge(arr, l, mid, r);
  }

  public static int merge(int[] arr, int l, int m, int r) {
    int pairs = 0;
    int rp = m + 1;
    for (int i = l; i <= m; i++) {
      while (rp <= r && arr[i] - arr[rp] > arr[rp]) {
        rp++;
      }
      pairs += rp - (m + 1);
    }

    int[] help = new int[r - l + 1];
    int p1 = l, p2 = m + 1, index = 0;
    while (p1 <= m && p2 <= r) {
      help[index++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
    }
    while (p1 <= m) {
      help[index++] = arr[p1++];
    }
    while (p2 <= r) {
      help[index++] = arr[p2++];
    }
    for (int i = 0; i < help.length; i++) {
      arr[l + i] = help[i];
    }
    return pairs;
  }

  // for test
  public static int rightReversePair(int[] arr) {
    if (arr == null || arr.length < 2) {
      return 0;
    }
    int pairs = 0;
    for (int i = 0; i < arr.length; i++) {
      for (int j = i + 1; j < arr.length; j++) {
        // (arr[i] > arr[j] * 2) 可能会overflow
        if (arr[i] - arr[j] > arr[j]) {
          pairs++;
        }
      }
    }
    return pairs;
  }

  public static int[] generateRandomArray(int maxSize, int maxValue) {
    int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
    }
    return arr;
  }

  public static void main(String[] args) {
    int maxSize = 200;
    int maxValue = 10000;
    int times = 200000;
    System.out.println("Started");
    for (int i = 0; i < times; i++) {
      int[] arr = generateRandomArray(maxSize, maxValue);
      int[] copy = Arrays.copyOfRange(arr, 0, arr.length);
      int res1 = reversePairs(arr);
      int res2 = rightReversePair(copy);
      if (res1 != res2) {
        System.out.println("Oops");
        System.out.println(Arrays.toString(arr));
        System.out.println("res1: " + res1);
        System.out.println("res2: " + res2);
        break;
      }
    }
    System.out.println("Done");
  }

}
