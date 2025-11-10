package com.hliu.fundamental.linear;

public class GetMaxInArray {

  public static int getMax(int[] arr, int l, int r) {
    if (arr.length < 1) {
      return -1;
    }
    if (l == r) {
      return arr[l];
    }
    int mid = l + (r - l) / 2;

    int maxLeft = getMax(arr, l, mid);
    int rightLeft = getMax(arr, mid + 1, r);

    return Math.max(maxLeft, rightLeft);
  }


  public static int right(int[] arr) {
    if (arr.length < 1) {
      return -1;
    }
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < arr.length; i++) {
      max = Math.max(max, arr[i]);
    }
    return max;
  }


  public static int[] generateRandomArray(int maxSize, int maxValue) {
    int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
    }
    return arr;
  }

  public static void main(String[] args) {

    int maxSize = 50;
    int maxValue = 50000;
    int time = 1000000;
    System.out.println("started");
    for (int i = 0; i < time; i++) {
      int[] arr = generateRandomArray(maxSize, maxValue);
//            if (arr.length < 1) {
//                continue;
//            }
      if (right(arr) != getMax(arr, 0, arr.length - 1)) {
        System.out.println("Oops");
        break;
      }
//            System.out.println(right(arr));
//            System.out.println(getMax(arr, 0, arr.length - 1));
    }
    System.out.println("end");
  }
}
