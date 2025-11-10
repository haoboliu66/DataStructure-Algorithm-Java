package com.hliu.fundamental.linear.stackqueue.monotonic;

import java.util.Stack;

// https://leetcode.com/problems/maximal-rectangle/
public class C04_MaximalRectangle {

  public static int maximalRectangle(char[][] matrix) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
      return 0;
    }
    int[] height = new int[matrix[0].length];
    int maxArea = Integer.MIN_VALUE;
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        height[j] = i == 0 ? matrix[i][j] - '0' : matrix[i][j] == '0' ? 0 : height[j] + 1;
      }
      maxArea = Math.max(maxArea, getMaxRectangleArea(height));
    }
    return maxArea;
  }

  public static int getMaxRectangleArea(int[] arr) {
    Stack<Integer> stack = new Stack<>();
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < arr.length; i++) {

      while (!stack.isEmpty() && arr[i] <= arr[stack.peek()]) {
        int popIndex = stack.pop();
        int leftIndex = !stack.isEmpty() ? stack.peek() : -1;
        max = Math.max(max, arr[popIndex] * (i - leftIndex - 1));
      }
      stack.push(i);

    }
    while (!stack.isEmpty()) {
      int popIndex = stack.pop();
      int leftIndex = !stack.isEmpty() ? stack.peek() : -1;
      max = Math.max(max, arr[popIndex] * (arr.length - leftIndex - 1));
    }
    return max;
  }


  public static void main(String[] args) {
    char[][] matrix = {
        {'1', '1', '1', '1', '1'},
        {'1', '1', '1', '0', '1'},
        {'1', '1', '0', '0', '0'},
        {'1', '1', '1', '0', '0'},
        {'0', '0', '1', '1', '1'}};
    System.out.println(maximalRectangle(matrix));
  }


}
