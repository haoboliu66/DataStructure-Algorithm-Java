package com.hliu.random.algoexpert.hard;

/*
Write a function that takes in an array of
integers and returns the largest possible value
for the expression
array[a] - array[b] + array[c] - array
, where a , b , c , and d are indices of the
array and a < b < c < d .
If the input array has fewer than 4 elements,
your function should return 0

Input: array = [3, 6, 1, -3, 2, 7]
Output: 4

 */

public class P18_MaximizeExpression {

  public static void main(String[] args) {

    int[] arr = {3, 6, 1, -3, 2, 7};
    System.out.println(maximizeExpression(arr));

  }

  public static int maximizeExpression(int[] arr) {
    if (arr == null || arr.length < 4) {
      return 0;
    }
    return process(arr, 0, 4);
  }

  public static int process(int[] arr, int index, int rest) {
    if (rest == 0 || index == arr.length) {
      return 0;
    }

    int max = Integer.MIN_VALUE;
    // 从index...末尾, 选择一个数字

    // n - 1, n - 2, n - 3
    for (int i = index; i <= arr.length - rest; i++) {
      int cur = (rest == 1 || rest == 3) ? -arr[i] : arr[i];

      int subRes = process(arr, i + 1, rest - 1);

      max = Math.max(max, cur + subRes);
    }

    return max;
  }


  public static int process2(int[] arr, int index, int rest, int[][] map) {
    if (map[index][rest] != Integer.MIN_VALUE) {
      return map[index][rest];
    }
    if (rest == 0 || index == arr.length) {
      return 0;
    }

    int max = Integer.MIN_VALUE;
    // 从index...末尾, 选择一个数字

    // n - 1, n - 2, n - 3
    for (int i = index; i <= arr.length - rest; i++) {
      int cur = (rest == 1 || rest == 3) ? -arr[i] : arr[i];

      int subRes = process2(arr, i + 1, rest - 1, map);

      max = Math.max(max, cur + subRes);
    }

    map[index][rest] = max;
    return max;
  }
}
