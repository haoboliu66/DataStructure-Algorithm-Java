package com.hliu.random.algoexpert.medium;

/*

given a circular array with integers, arr[i] = 2: a jump of 2 indices forward in the array, arr[i] = -3, a jump of 3
indices backward in the array

check whether the jumps in the array form a single cycle, A single cycle occurs if, starting at any index in the
array and following the jumps, every element in the array is visited exactly once before landing back on the starting
 index
 */

public class P25_SingleCycleCheck {

  public static boolean hasSingleCycle(int[] arr) {
    int N = arr.length;
    int count = N;
    int start = (int) (Math.random() * N);
    int cur = start;
    while (count != 0) {
      count--;
      int next = nextIndex(cur, arr[cur], N);
      cur = next;
      if (count > 0 && cur == start) {
        return false;
      }
    }

    return cur == start;
  }

  private static int nextIndex(int i, int len, int N) {
    int next = (i + len) % N;
    return next >= 0 ? next : next + N;

  }

}
