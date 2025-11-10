package com.hliu.random.algoexpert.veryhard;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/max-points-on-a-line/
public class P05_LineThroughPoints {

  public int lineThroughPoints(int[][] points) {
    if (points == null || points.length == 0) {
      return 0;
    }
    int res = 1;
    for (int i = 0; i < points.length; i++) {
      int[] cur = points[i];
      int sameX = 0;
      int sameY = 0;
      int samePosition = 1;
      int sameSlope = 0;
      Map<String, Integer> map = new HashMap<>();
      for (int j = i + 1; j < points.length; j++) {
        int[] p = points[j];
        if (p[0] == cur[0] && p[1] == cur[1]) {
          samePosition++;
        } else if (p[0] == cur[0]) {
          sameX++;
        } else if (p[1] == cur[1]) {
          sameY++;
        } else {
          int xdiff = p[0] - cur[0];
          int ydiff = p[1] - cur[1];
          int gcd = gcd(xdiff, ydiff);
          String slope = (xdiff / gcd) + "_" + (ydiff / gcd);
          map.put(slope, map.getOrDefault(slope, 0) + 1);
          sameSlope = Math.max(sameSlope, map.get(slope));
        }
        int total = samePosition + Math.max(Math.max(sameX, sameY), sameSlope);
        res = Math.max(res, total);
      }
    }
    return res;
  }

  public static int gcd(int m, int n) {
    return n == 0 ? m : gcd(n, m % n);
  }

}
