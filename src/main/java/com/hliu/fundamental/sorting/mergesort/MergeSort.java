package com.hliu.fundamental.sorting.mergesort;


import java.util.Arrays;

public class MergeSort {

  /**
   * recursive
   */
  public static void mergeSort1(int[] arr) {
    if (arr == null || arr.length < 2) {
      return;
    }
    process(arr, 0, arr.length - 1);
  }

  public static void process(int[] arr, int left, int right) {
    if (left == right) {
      return;
    }
    int mid = left + ((right - left) >> 1);
    process(arr, left, mid);
    process(arr, mid + 1, right);
    merge(arr, left, mid, right);
  }

  private static void merge(int[] arr, int left, int mid, int right) {
    int[] buffer = new int[right - left + 1];
    int index = 0;
    int leftPointer = left; // left part scanner
    int rightPointer = mid + 1; // right part scanner
    while (leftPointer <= mid && rightPointer <= right) {
      buffer[index++] = arr[leftPointer] < arr[rightPointer] ? arr[leftPointer++] : arr[rightPointer++];
    }

    while (leftPointer <= mid) {
      buffer[index++] = arr[leftPointer++];
    }
    while (rightPointer <= right) {
      buffer[index++] = arr[rightPointer++];
    }

    for (int i = 0; i < buffer.length; i++) {
      arr[left + i] = buffer[i];
    }
  }

  /**
   * Iterative
   */
  public static void mergeSort2(int[] arr) {
    if (arr == null || arr.length < 2) {
      return;
    }
    int N = arr.length;
    int mergeSize = 1;   // merge group size = mergeSize * 2
    while (mergeSize < N) {
      int left = 0;
      while (left < N) {
                /*
                firstly locate mid and right of each group
                 */
        int mid = left + mergeSize - 1;  // mid of merge group
        int right = Math.min(left + 2 * mergeSize - 1, N - 1); // right of merge group
                /*
                 if mid reach the end of the array, meaning there is no right part,
                    and left part is already ordered
                 */
        if (mid > N - 1) {
          break;
        }
        // from left to N - 1, merge each group
        merge(arr, left, mid, right);
        // locate next group
        left = right + 1;
      }
      // Integer MAX_VALUE overflow
      if (mergeSize * 2 > N) {
        break;
      }
      mergeSize <<= 1;
    }
  }

  public static void main(String[] args) {
    int maxSize = 300;
    int maxValue = 100000;
    int times = 1000000;
    for (int i = 0; i < times; i++) {
      int[] arr = generateRandomArray(200, 50000);
      int[] copyArr = copyArray(arr);
      mergeSort2(arr);
      comparator(copyArr);
      boolean res = isEqual(arr, copyArr);
      if (!res) {
        System.out.println("Oops");
        break;
      }
    }
    System.out.println("Done");
  }

  // test
  public static boolean isEqual(int[] arr1, int[] arr2) {
    if (arr1 == null || arr2 == null) {
      return false;
    }
    if (arr1.length != arr2.length) {
      return false;
    }
    for (int i = 0; i < arr1.length; i++) {
      if (arr1[i] != arr2[i]) {
        return false;
      }
    }
    return true;
  }

  public static void printArray(int[] arr) {
    if (arr == null) {
      return;
    }
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }


  public static int[] generateRandomArray(int maxSize, int maxValue) {
    int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
    }
    return arr;
  }

  public static int[] copyArray(int[] arr) {
    if (arr == null) {
      throw new RuntimeException("Null value");
    }
    int[] replica = new int[arr.length];
    for (int i = 0; i < arr.length; i++) {
      replica[i] = arr[i];
    }
    return replica;
  }

  public static void comparator(int[] arr) {
    Arrays.sort(arr);
  }

}

