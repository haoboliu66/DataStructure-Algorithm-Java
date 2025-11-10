package com.hliu.fundamental.sorting.heap;

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
      if(node.next != null) {
        pq.add(node.next);
      }
      cur.next = node;
      cur = cur.next;
    }
    return dummy.next;
  }



}
