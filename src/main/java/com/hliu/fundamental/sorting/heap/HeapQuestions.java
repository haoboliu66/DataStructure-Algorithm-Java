package com.hliu.fundamental.sorting.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

import com.hliu.fundamental.linear.linkedlist.entity.ListNode;

public class HeapQuestions {

  // https://leetcode.com/problems/merge-k-sorted-lists/
  public ListNode mergeKLists(ListNode[] lists) {
    if (lists == null || lists.length == 0) {
      return null;
    }
    PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
    for (int i = 0; i < lists.length; i++) {
      if (lists[i] != null) {
        pq.add(lists[i]);  // maintain heap size as k (number of lists), so time complexity is O(N log k)
      }
    }
    ListNode dummy = new ListNode(-1);
    ListNode cur = dummy;
    if (pq.isEmpty()) {
      return null;
    }
    while (!pq.isEmpty()) {
      ListNode node = pq.poll();
      if (node.next != null) {
        pq.add(node.next);
      }
      cur.next = node;
      cur = cur.next;
    }
    return dummy.next;
  }

  private class C01_SortArrayDistanceLessK {

    public void sortedArrayDistanceLessK(int[] arr, int k) {
      PriorityQueue<Integer> heap = new PriorityQueue<>();
      int index = 0;
      // 先把数组中前k + 1个数(下标是到k)组织成小根堆, 因为第k + 1往前移动k步都满足要求
      for (; index <= Math.min(k, arr.length); index++) {
        heap.add(arr[index]);
      }
      int i = 0;
      // index继续向后走, 开始从小根堆中弹出值并赋值给arr, 弹出的永远是剩下的值中最小的
      for (; index < arr.length; i++, index++) {
        heap.add(arr[index]);
        arr[i] = heap.poll();
      }
      //剩下的值依次弹出
      while (!heap.isEmpty()) {
        arr[i++] = heap.poll();
      }
    }
  }

  /*
  给定很多线段，每个线段都有一个区间 [start, end]， 表示线段开始位置和结束位置，左右都是闭区间
  规定：
  1）线段的开始和结束位置一定都是整数值
  2）线段重合区域的长度必须 >= 1    e.g. [1,3] [3,6] => 这2个线段不算重合
  返回线段最多重合区域中，包含了几条线段
 */
  private class C02_LinesOverlapProblem {

    /*
    由于线段重合的部分必须大于0, 所以先找到这些线段的最小开头和最大结尾, 然后取每一个0.5的位置,
    看每一个0.5的位置有多少个线段覆盖
    复杂度 O( (max-min) * N )
     */
    public int maxCover(int[][] lines) {
      int minStart = Integer.MAX_VALUE;
      int maxEnd = Integer.MIN_VALUE;

      for (int[] line : lines) {
        minStart = Math.min(minStart, line[0]);
        maxEnd = Math.min(maxEnd, line[1]);
      }
      int maxCover = 0;
      for (float i = minStart + 0.5f; i < maxEnd; i++) {
        int count = 0;
        for (int[] line : lines) {
          if (line[0] < i && line[1] > i) {
            count++;
          }
          maxCover = Math.max(maxCover, count);
        }
      }
      return maxCover;
    }

    /*
      遍历线段, 针对每一个线段, 查看当前的start,
      1.把minHeap中小于等于start的值都弹出 (这个操作意味着,前面的线段的结尾都 <= 当前线段的start, 所以没有重合)
      2.把当前的end加入minHeap
      3.此时堆的size, 就是与当前线段有重合的线段数量 (表示: 以当前线段的start为左边界, 有多少线段可以穿过start i.e 有多少线段的结尾在当前start的右侧)
     */
    public int maxCover1(int[][] lines) {
      int minStart = Integer.MAX_VALUE;
      int maxEnd = Integer.MIN_VALUE;

      Arrays.sort(lines, (a, b) -> a[0] - b[1]);
      PriorityQueue<Integer> minHeap = new PriorityQueue<>();
      int maxCover = 0;
      for (int[] line : lines) {
        while (!minHeap.isEmpty() && minHeap.peek() <= line[0]) {
          minHeap.poll();
        }
        minHeap.offer(line[1]);
        maxCover = Math.max(maxCover, minHeap.size());
      }
      return maxCover;
    }
  }


}
