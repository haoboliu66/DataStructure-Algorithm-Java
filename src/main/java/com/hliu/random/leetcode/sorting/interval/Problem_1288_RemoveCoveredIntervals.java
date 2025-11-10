package com.hliu.random.leetcode.sorting.interval;

import java.util.Arrays;

// https://leetcode.com/problems/remove-covered-intervals/
public class Problem_1288_RemoveCoveredIntervals {

  public int removeCoveredIntervals(int[][] intervals) {
    if (intervals == null || intervals.length == 0) {
      return 0;
    }
    int count = 1;
    Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
    int start = intervals[0][0];
    int end = intervals[0][1];

    // (1,2)  (1,4)  (3,4)
    for (int i = 1; i < intervals.length; i++) {
      int curStart = intervals[i][0];
      int curEnd = intervals[i][1];

      if (curStart >= end) {
        count++;
        start = curStart;
        end = curEnd;

      } else if (curStart == start) {
        end = Math.max(end, curEnd);

      } else if (curEnd > end) {
        //  curStart > start && curStart < end
        count++;
        start = curStart;
        end = curEnd;
      }
    }
    return count;
  }
}
