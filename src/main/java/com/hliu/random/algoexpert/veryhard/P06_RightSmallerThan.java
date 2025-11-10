package com.hliu.random.algoexpert.veryhard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/*
https://leetcode.com/problems/count-of-smaller-numbers-after-self/
 */
public class P06_RightSmallerThan {

  public static void main(String[] args) {

    int[] arr = {8, 5, 11, -1, 3, 4, 2};
    List<Integer> list = Arrays.stream(arr)
                               .boxed()
                               .collect(Collectors.toList());
    rightSmallerThan(list);
  }

  public static List<Integer> rightSmallerThan(List<Integer> list) {

    int[] res = new int[list.size()];
    int[] arr = list.stream()
                    .mapToInt((a) -> a.intValue())
                    .toArray();
    process(arr, 0, arr.length - 1, res);
    System.out.println(Arrays.toString(res));

    return new ArrayList<Integer>();
  }

  public static void process(int[] arr, int left, int right, int[] res) {
    if (left >= right) {
      return;
    }
    int mid = left + (right - left) / 2;
    process(arr, left, mid, res);
    process(arr, mid + 1, right, res);

    merge(arr, left, mid, right, res);
  }

  public static void merge(int[] arr, int left, int mid, int right, int[] res) {
    int[] buffer = new int[right - left + 1];
    int p1 = mid, p2 = right;
    // [5,6,7,8]  [4,6,7,8]
    for (; p1 >= left && p2 >= mid + 1; ) {
      if (arr[p1] > arr[p2]) {
        res[p1] += p2 - (mid + 1) + 1;
        p1--;
      } else if(arr[p2] > arr[p1]){
        p2--;
      } else {
        p2--;
      }
    }
    // merge
    p1 = left;
    p2 = mid + 1;
    int index = 0;
    for (; p1 <= mid && p2 <= right; ) {
      if (arr[p1] < arr[p2]) {
        buffer[index++] = arr[p1++];
      } else {
        buffer[index++] = arr[p2++];
      }
    }

    for (; p1 <= mid; ) {
      buffer[index++] = arr[p1++];
    }
    for (; p2 <= right; ) {
      buffer[index++] = arr[p2++];
    }
    for (int i = 0; i < buffer.length; i++) {
      arr[i + left] = buffer[i];
    }
  }

}
