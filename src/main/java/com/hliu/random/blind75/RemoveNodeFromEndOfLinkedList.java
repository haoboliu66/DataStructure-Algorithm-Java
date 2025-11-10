package com.hliu.random.blind75;

import static com.hliu.random.blind75.entity.Utils.printLinkedList;

import com.hliu.random.blind75.entity.ListNode;

public class RemoveNodeFromEndOfLinkedList {

  public static void main(String[] args) {

    RemoveNodeFromEndOfLinkedList obj = new RemoveNodeFromEndOfLinkedList();

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

    obj.removeNthFromEnd(node2, 2);
  }

  public ListNode removeNthFromEnd(ListNode head, int n) {
    if (head == null) {
      return null;
    }
    int len = 0;
    ListNode curr = head;
    while (curr != null) {
      len++;
      curr = curr.next;
    }
    if (n == len) { // all gone
      return head.next;
    }

    // 2 -> 4 -> 6 -> 8 -> 10 -> 12    len: 6
    // n == 1, last
    // n = 2, second last
    curr = head;
    int targetIndex = len - n - 1;
    while (targetIndex != 0) {
      curr = curr.next;
      targetIndex--;
    }
    if (curr.next != null) {
      curr.next = curr.next.next;
    } else {
      curr.next = null;
    }
    return head;
  }

}
