package com.hliu.random.blind75.entity;

public class Utils {

  public static void printLinkedList(ListNode node) {
    StringBuilder sb = new StringBuilder();
    while (node != null) {
      sb.append(node.val)
        .append("->");
      node = node.next;
    }
    sb.append("null");
    System.out.println(sb);
  }

}

