package com.hliu.random.algoexpert.hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/*
You're given a list of time intervals during
which students at a school need a laptop.
These time intervals are represented by pairs
of integers [start, end] , where
0 <= start < end . However, start
and end don't represent real times;
therefore, they may be greater than 24 .
No two students can use a laptop at the
same time, but immediately after a student is
done using a laptop, another student can use
that same laptop. For example, if one
student rents a laptop during the time
interval [0, 2] , another student can rent
the same laptop during any time interval
starting with 2 .
Write a function that returns the minimum
number of laptops that the school needs to
rent such that all students will always have
access to a laptop when they need one.

times =
          [
           [0, 2],
           [1, 4],
           [4, 6],
           [0, 4],
           [7, 8],
           [9, 11],
           [3, 10],
          ]

Output: 3
 */
public class P24_LaptopRentals {

  public static void main(String[] args) {
    List<List<Integer>> times = new ArrayList<>(
        List.of(List.of(0, 2), List.of(1, 4), List.of(4, 6), List.of(0, 4), List.of(7, 8),
            List.of(9, 11), List.of(3, 10)));

    int res = laptopRentals(times);
    System.out.println(res);
  }

  /*
      先按start排序
       [0, 2], [0, 4], [1, 4], [3, 10], [4, 6], [7, 8], [9, 11],
       第一个放进minHeap(以end排序), 然后遍历后续
       遍历时拿到每一个time[], 此时检查minHeap的root, 这个root表示我已经处理过的time中最早结束的
       如果当前的time的 start 比这个最早的end还要早, 那么意味着我必须count++
       然后必须把当前的time也加到minHeap里, 因为后续遍历要查看前面所有看过time中最早结束的
   */
  public static int laptopRentals(List<List<Integer>> times) {
    if (times == null || times.isEmpty()) {
      return 0;
    }
    int count = 0;
    Collections.sort(times, (a, b) -> (a.get(0) - b.get(0)));

    PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
    minHeap.add(new int[]{times.get(0).get(0), times.get(0).get(1)});
    count++;

    for (int i = 1; i < times.size(); i++) {
      int[] cur = new int[]{times.get(i).get(0), times.get(i).get(1)};
      int start = cur[0], end = cur[1];
      int[] earliest = minHeap.poll();
      if (start < earliest[1]) {
        count++;
        minHeap.add(earliest);
        minHeap.add(cur);
      } else {
        minHeap.add(cur);
      }
    }

    return count;
  }

}
