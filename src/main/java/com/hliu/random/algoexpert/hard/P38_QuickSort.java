package com.hliu.random.algoexpert.hard;

import java.util.Arrays;

public class P38_QuickSort {


  public static void main(String[] args) {
    int[] arr = new int[]{2, 3, 1, 7, 9, 3, 50, 0, -1, -7 - 4, -9};
    quickSort(arr);
    System.out.println(Arrays.toString(arr));
  }

  public static int[] quickSort(int[] arr) {
    process(arr, 0, arr.length - 1);
    return arr;
  }

  public static void process(int[] arr, int L, int R) {
    if (L >= R) {
      return;
    }
    // 7,8,9,10,11,12

    //   [7,13)
//    int index = L + (int) (Math.random() * (R - L + 1));
//    int pivot = arr[index];
    int[] indices = netherlandFlagSplit(arr, L, R);

    process(arr, L, indices[0] - 1);
    process(arr, indices[1] + 1, R);

  }

  // [1,1,1,1,1,1,1]
  public static int[] netherlandFlagSplit(int[] arr, int L, int R) {
    if (L > R) {
      return new int[]{-1, -1};
    }
    if (L == R) {
      return new int[]{L, R};
    }
    int target = arr[R];
    int index = L;
    int less = L - 1, more = R + 1;

    while (index != more) {

      if (arr[index] < target) {
        swap(arr, index++, ++less);
      } else if (arr[index] > target) {
        swap(arr, index, --more);
      } else {
        index++;
      }
    }
    return new int[]{less + 1, more - 1};
  }

  public static void swap(int[] arr, int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }
}
