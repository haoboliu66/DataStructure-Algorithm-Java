package com.hliu.random.blind75;

import static com.hliu.random.blind75.entity.Utils.printLinkedList;

import com.hliu.random.blind75.entity.ListNode;

public class ReorderLinkedList {

  public static void main(String[] args) {

    ReorderLinkedList obj = new ReorderLinkedList();

    ListNode node2 = new ListNode(2);
    ListNode node4 = new ListNode(4);
    ListNode node6 = new ListNode(6);
    ListNode node8 = new ListNode(8);
    ListNode node10 = new ListNode(10);

    node2.next = node4;
    node4.next = node6;
    node6.next = node8;
    node8.next = node10;

    printLinkedList(node2);

    obj.reorderList(node2);
  }



  public void reorderList(ListNode head) {
    if (head == null || head.next == null || head.next.next == null) {
      return;
    }
    ListNode curr = head;
    int len = 0;
    while (curr != null) {
      len++;
      curr = curr.next;
    }
    ListNode tmp = null;
    curr = head;
    int index = 0;
    while (index < len / 2) {
      index++;
      tmp = curr;
      curr = curr.next;
    }
    tmp.next = null;  // must disconnect 2 lists

    // curr now is the start of second half of the list
    printLinkedList(head);
    printLinkedList(curr);

    ListNode prev = null;
    ListNode post = null;
    while (curr != null) {
      post = curr.next;
      curr.next = prev;
      prev = curr;
      curr = post;
    }
    // the second half list is reversed
    // the new head of it now is [prev]

    // now we can merge two lists
    ListNode dummy = new ListNode(-1);
    curr = head;
    ListNode curr1 = prev;
    while (curr != null && curr1 != null) {
      dummy.next = curr;
      dummy = curr;
      curr = curr.next;

      dummy.next = curr1;
      dummy = curr1;
      curr1 = curr1.next;
    }
    if (curr1 != null) {
      dummy.next = curr1;
    }

    printLinkedList(head);
  }

}
