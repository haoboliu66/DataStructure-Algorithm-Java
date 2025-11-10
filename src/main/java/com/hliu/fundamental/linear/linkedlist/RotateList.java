package com.hliu.fundamental.linear.linkedlist;

import com.hliu.fundamental.linear.linkedlist.entity.ListNode;

public class RotateList {

  public ListNode rotateRight(ListNode head, int k) {
    if (head == null || head.next == null || k == 0) {
      return head;
    }
    int len = 0;
    ListNode cur = head;
    while (cur != null) {
      len++;
      cur = cur.next;
    }
    if (k % len == 0) {
      return head;
    }
    if (k > len) {
      k = k % len;
    }

    ListNode slow = head;
    ListNode fast = head;

    // 1->2->3->[4->5]
    while (k > 0) {
      fast = fast.next;
      k--;
    }
    // keep k distance between slow and fast, so when fast reaches the null, slow is the (k) position
    ListNode tmp = new ListNode(-1);
    tmp.next = slow;
    while (fast != null) {
      slow = slow.next;
      tmp = tmp.next;
      fast = fast.next;
    }
    // slow reaches the (k) position, with tmp as its previous node
    tmp.next = null;

    ListNode newHead = slow;
    while (slow.next != null) {
      slow = slow.next;
    }
    // slow reaches the end of the list, now we need connect everything back
    slow.next = head;
    return newHead;
  }

}
