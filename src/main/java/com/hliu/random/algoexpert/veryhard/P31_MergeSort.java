package com.hliu.random.algoexpert.veryhard;

import java.util.Arrays;

public class P31_MergeSort {

  public static void main(String[] args) {
    int[] arr = {8,5,2,9,5,6,3};
    System.out.println(Arrays.toString(mergeSort(arr)));
  }

  public static int[] mergeSort(int[] arr) {

    process(arr, 0, arr.length - 1);

    return arr;
  }

  public static void process(int[] arr, int left, int right) {
    if (left >= right) {
      return;
    }
    int mid = left + (right - left) / 2;
    process(arr, left, mid);
    process(arr, mid + 1, right);
    merge(arr, left, mid, right);
  }

  public static void merge(int[] arr, int left, int mid, int right) {
    int[] buffer = new int[right - left + 1];
    int p1 = left, p2 = mid + 1;
    int index = 0;
    for (; p1 <= mid && p2 <= right; ) {
      if (arr[p1] < arr[p2]) {
        buffer[index++] = arr[p1];
        p1++;
      } else {
        buffer[index++] = arr[p2];
        p2++;
      }
    }
    for (; p1 <= mid; p1++) {
      buffer[index++] = arr[p1];
    }
    for (; p2 <= right; p2++) {
      buffer[index++] = arr[p2];
    }

    for (int i = 0; i < buffer.length; i++) {
      arr[i + left] = buffer[i];
    }
  }
}
