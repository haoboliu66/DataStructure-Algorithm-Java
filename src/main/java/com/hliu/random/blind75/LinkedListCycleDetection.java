package com.hliu.random.blind75;

import com.hliu.random.blind75.entity.ListNode;

public class LinkedListCycleDetection {

  public boolean hasCycle(ListNode head) {
    if (head == null) {
      return false;
    }
    ListNode slow = head;
    ListNode fast = head;

    while (slow != fast) {
      if (slow == null || fast == null || fast.next == null) {
        return false;
      }
      slow = slow.next;
      fast = fast.next.next;
    }
    return true;
  }

}
