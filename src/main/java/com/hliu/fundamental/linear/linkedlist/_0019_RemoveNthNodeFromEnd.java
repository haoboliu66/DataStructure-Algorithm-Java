package com.hliu.fundamental.linear.linkedlist;


import com.hliu.fundamental.linear.linkedlist.entity.ListNode;

public class _0019_RemoveNthNodeFromEnd {

  public ListNode removeNthFromEnd(ListNode head, int n) {
    int len = 0;

    ListNode cur = head;
    while (cur != null) {
      len++;
      cur = cur.next;
    }
    cur = head;
    if (n >= len) {
      cur = cur.next;
      head.next = null;
      return cur;
    }
    int steps = len - n - 1;
    while (steps != 0) {
      steps--;
      cur = cur.next;
    }
    cur.next = cur.next != null ? cur.next.next : null;
    return head;
  }

}
