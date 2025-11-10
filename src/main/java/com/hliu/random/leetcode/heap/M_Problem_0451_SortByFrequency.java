package com.hliu.random.leetcode.heap;

import java.util.PriorityQueue;

public class M_Problem_0451_SortByFrequency {

  public static String frequencySort(String s) {
    if (s == null || s.length() == 0) {
      return s;
    }
    int max = 0;
    int[] map = new int[128];
    for (char c : s.toCharArray()) {  // O(N)
      map[c]++;
      max = Math.max(max, map[c]);
    }
    if (max == 1) {
      return s;
    }
    PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[1] - a[1]);
    for (int i = 0; i < 128; i++) {  // O(1)
      maxHeap.add(new int[]{i, map[i]});
    }

    StringBuilder res = new StringBuilder();
    while (!maxHeap.isEmpty()) {   // O(K)
      int[] cur = maxHeap.poll();
      res.append(getString(cur));
    }
    return res.toString();
  }

  public static String getString(int[] cur) {
    StringBuilder sb = new StringBuilder();
    char c = (char) cur[0];
    int count = cur[1];
    for (int i = 1; i <= count; i++) {
      sb.append(c);
    }
    return sb.toString();
  }

}
