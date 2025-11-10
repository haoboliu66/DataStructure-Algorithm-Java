package com.hliu.random.leetcode;

import java.util.Arrays;

public class Problem_0435_NonOverlappingIntervals {

    /*
    435. Non-overlapping Intervals
    56 Merge Intervals <- very similar, i did it with just 3 lines different
    252 Meeting Rooms
    253 Meeting Rooms II
    452 Minimum Number of Arrows to Burst Balloons
     */

  public static int eraseOverlapIntervals(int[][] intervals) {
      if (intervals == null || intervals.length == 0) {
          return 0;
      }

    // Sort by the end of interval:
    Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
    int removed = 0;
    // The second value of the last valid interval.
    int prevEnd = intervals[0][1];

    // We need to traverse the sorted intervals, counting the number of invalid intervals, and
    // updating the prevEnd value when you find a valid interval.
    for (int i = 1; i < intervals.length; i++) {
      // We have an invalid interval if the  current start time is < prevEnd;
        if (intervals[i][0] < prevEnd) {
            removed++;
        } else {
            prevEnd = intervals[i][1];
        }
    }
    return removed;
  }

}
