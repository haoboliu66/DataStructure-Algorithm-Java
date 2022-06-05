package com.hliu.sys.c17;

public class Problem_1351_CountNegativeNumbersInSortedMatrix {

  //https://leetcode.com/problems/count-negative-numbers-in-a-sorted-matrix/

  // 直接利用单调性, 可有最优解 O(M+n)


  // O(M*logN)
  public static int countNegatives0(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    int count = 0;
    for (int i = 0; i < m; i++) {
      int[] arr = grid[i];
      int index = findMostLeftLessThanZero(arr, 0, n - 1);
      count += (index != -1 ? n - index : 0);
    }
    return count;
  }

  // L....R non-increasing
  public static int findMostLeftLessThanZero(int[] arr, int L, int R) {
    int res = -1;
    int mid;
    while (L <= R) {
      mid = L + (R - L) / 2;
      if (arr[mid] < 0) {
        res = mid;
        R = mid - 1;
      } else {
        L = mid + 1;
      }
    }
    return res;
  }
}
