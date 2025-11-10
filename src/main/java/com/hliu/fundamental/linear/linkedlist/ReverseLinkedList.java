package com.hliu.fundamental.linear.linkedlist;


import com.hliu.fundamental.linear.linkedlist.entity.ListNode;

public class ReverseLinkedList {

  public static ListNode reverse(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode pre = null;
    ListNode post = null;
    while (head != null) {
      post = head.next;
      head.next = pre;
      pre = head;
      head = post;
    }
    return pre;
  }

}
