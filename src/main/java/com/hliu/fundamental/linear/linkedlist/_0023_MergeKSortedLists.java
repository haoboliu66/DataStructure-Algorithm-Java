package com.hliu.fundamental.linear.linkedlist;


import java.util.PriorityQueue;

import com.hliu.fundamental.linear.linkedlist.entity.ListNode;

public class _0023_MergeKSortedLists {

  /*
  多个链表内部有序, 外部无序, 整合成一个有序链表
   */
  public static ListNode mergeKLists(ListNode[] lists) {
    if (lists == null || lists.length == 0) {
      return null;
    }
    PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);
    for (ListNode head : lists) {
      if (head != null) {
        minHeap.add(head);
      }
    }
    if (minHeap.isEmpty()) {
      return null;
    }

    ListNode cur = minHeap.poll();
    if (cur.next != null) {
      minHeap.add(cur.next);
    }
    ListNode res = cur;
    while (!minHeap.isEmpty()) {
      ListNode node = minHeap.poll();
      if (node.next != null) {
        minHeap.add(node.next);
        node.next = null;
      }
      cur.next = node;
      cur = node;
    }
    return res;
  }

}
