package com.hliu.random.leetcode.tree;

public class Problem_0109_ConvertSortedListToBinarySearchTree {

  public class ListNode {

    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }

  public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
      this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

  /*
  Given the head of a singly linked list where elements are sorted in ascending order, convert it to a
   height-balanced BST.
   */
  public TreeNode sortedListToBST(ListNode head) {
    if (head == null || head.next == null) {
      return new TreeNode(head.val);
    }
    int len = 0;
    ListNode cur = head;
    while (cur != null) {
      len++;
      cur = cur.next;
    }
    cur = head;
    int target = len / 2;
    while (target > 0) {
      cur = cur.next;
      target--;
    }
    TreeNode root = new TreeNode(cur.val);

    return root;
  }

}
