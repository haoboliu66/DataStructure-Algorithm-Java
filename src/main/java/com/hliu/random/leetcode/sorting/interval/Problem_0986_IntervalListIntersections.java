package com.hliu.random.leetcode.sorting.interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem_0986_IntervalListIntersections {

  /*
  [0,2],[5,10],[13,23],[24,25]      [1,5],[8,12],[15,24],[25,26]

  [0,2] [1,5] [2,10] [8,12] [13,23] [15,24] [24,25] [25,26]
   */
  public int[][] intervalIntersection2(int[][] first, int[][] second) {
    List<int[]> res = new ArrayList<>();
    int i = 0, j = 0;
    for (; i < first.length && j < second.length; ) {
      int[] a = first[i];
      int[] b = second[j];
      if (a[1] >= b[0] && a[0] <= b[1]) {
        res.add(new int[]{Math.max(a[0], b[0]), Math.min(a[1], b[1])});
      }
      if (b[1] > a[1]) {
        i++;
      } else {
        j++;
      }
    }
    return res.toArray(new int[0][]);
  }

  public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
    int[][] intervals = new int[firstList.length + secondList.length][2];
    int index = 0;
    for (int i = 0; i < firstList.length; i++) {
      intervals[index++] = firstList[i];
    }
    for (int i = 0; i < secondList.length; i++) {
      intervals[index++] = secondList[i];
    }

    return merge(intervals);
  }

  public int[][] merge(int[][] intervals) {

    List<int[]> res = new ArrayList<>();
    Arrays.sort(intervals, (int[] a, int[] b) -> (a[0] - b[0]));
    int start = intervals[0][0];
    int end = intervals[0][1];

    for (int i = 1; i < intervals.length; i++) {

      if (intervals[i][0] <= end) {
        // [start1 end1]  [start2    end2]
        res.add(new int[]{intervals[i][0], Math.min(end, intervals[i][1])});
      }
      //  [start1  [start2   end1]    end2]
      start = intervals[i][0];
      end = Math.max(end, intervals[i][1]);
    }
    // res.add(new int[]{start, end});

    int[][] ans = new int[res.size()][2];
    for (int i = 0; i < ans.length; i++) {
      ans[i] = res.get(i);
    }

    return ans;

  }
}
