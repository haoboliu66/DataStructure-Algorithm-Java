package com.hliu.fundamental.linear.linkedlist;

import java.util.Stack;

import com.hliu.fundamental.linear.linkedlist.entity.ListNode;

/*
https://leetcode.com/problems/palindrome-linked-list/
 */
public class PalindromeLinkedList {

  public static boolean isPalindromeWithStack1(ListNode head) {
    if (head == null || head.next == null) {
      return true;
    }
    // two nodes and equal value
    if (head.next.next == null) {
      return head.next.val == head.val;
    }

    Stack<ListNode> stack = new Stack<>();
    ListNode cur = head;
    while (cur != null) {
      stack.add(cur);
      cur = cur.next;
    }
    cur = head;
    while (cur != null) {
      if (cur.val != stack.pop().val) {
        return false;
      }
      cur = cur.next;
    }
    return true;
  }

  /**
   * stack only stores half of the linked list
   */
  public static boolean isPalindromeWithStack2(ListNode head) {
    if (head == null || head.next == null) {
      return true;
    }
    if (head.next.next == null) {
      return head.next.val == head.val;
    }
    ListNode slow = head;
    ListNode fast = head;
    while (fast.next != null && fast.next.next != null) {
      fast = fast.next.next;
      slow = slow.next;
    }

    Stack<ListNode> stack = new Stack<>();
    slow = slow.next;
    while (slow != null) {
      stack.add(slow);
      slow = slow.next;
    }
    slow = head;
    while (!stack.isEmpty()) {
      if (slow.val != stack.pop().val) {
        return false;
      }
      slow = slow.next;
    }
    return true;
  }

  /**
   * extra space complexity O(1)
   */
  public static boolean isPalindrome(ListNode head) {
    if (head == null || head.next == null) {
      return true;
    }
    if (head.next.next == null) {
      return head.next.val == head.val;
    }
    ListNode slow = head;
    ListNode fast = head;
    while (fast.next != null && fast.next.next != null) {
      fast = fast.next.next;
      slow = slow.next;
    }

    /* reverse the second half */
    ListNode pre = null;
    ListNode post = null;
    while (slow != null) {
      post = slow.next;
      slow.next = pre;
      pre = slow;
      slow = post;
    }
    ListNode help = pre;  // keep a record of the head of the second half
    ListNode cur = head;
    // check isPalindrome
    boolean res = true;
    while (cur != null && pre != null) {
      if (cur.val != pre.val) {
        res = false;
      }
      cur = cur.next;
      pre = pre.next;
    }

    /* restore the second half  */
    pre = null;
    post = null;
    while (help != null) {
      post = help.next;
      help.next = pre;
      pre = help;
      help = post;
    }
    cur = head;
    while (cur.next != null) {
      cur = cur.next;
    }
    cur.next = pre;

    return res;
  }

}
