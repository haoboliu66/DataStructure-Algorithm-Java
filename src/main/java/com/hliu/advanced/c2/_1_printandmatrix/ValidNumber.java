package com.hliu.advanced.c2._1_printandmatrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ValidNumber {

  public static void main(String[] args) {
    int[] arr = {1, 2, 3, 4, 5};
    int[] res = Arrays.copyOf(arr, arr.length);
    System.out.println(Arrays.toString(res));
  }

  public static boolean isSum(int n) {
    for (int i = 1; i <= n; i++) {
      int sum = i;
      for (int j = i + 1; j <= n; j++) {
        if (sum + j > n) {
          break;
        }
        if (sum + j == n) {
          return true;
        }
        sum += j;
      }
    }

    return false;
  }

  public static boolean isValid(int n) {
    List<Integer> list = new ArrayList<>();
      if (n <= 2) {
          return false;
      }
    int tmp;
    for (int i = 1; i <= n / 2 + 1; i++) {
      // 12   -1-2-3-4-5
      tmp = n;
      for (int j = i; j <= n / 2 + 1; ) {
        tmp -= j;
        list.add(j);
        j++;
        if (tmp < 0) {
          list.clear();
          break;
        }
        if (tmp == 0) {
          return true;
        }
      }
    }
    return false;
  }
}
