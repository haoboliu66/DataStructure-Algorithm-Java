package com.hliu.advanced.c3.class4;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/
public class C03_ConstructBinaryTreeFromPreorderAndPostorderTraversal {

  public static TreeNode constructFromPrePost(int[] pre, int[] post) {
    return process(pre, 0, pre.length - 1, post, 0, post.length - 1);
  }

  public static TreeNode process(int[] pre, int l1, int r1, int[] post, int l2, int r2) {
    if (l1 > r1 || l2 > r2) {
      return null;
    }

    if (l1 == r1) {
      return new TreeNode(pre[l1]);
    }
    int h1 = pre[l1];
    int right = post[r2 - 1];
    TreeNode head = new TreeNode(h1);
    int rightInPre = l1 + 1;
    for (; rightInPre <= r1; rightInPre++) {
      if (pre[rightInPre] == right) {
        break;
      }
    }
    // rightInPre...r1
    // r1-rightInPre + 1
    head.left = process(pre, l1 + 1, rightInPre - 1, post, l2, r2 - (r1 - rightInPre + 1) - 1);
    head.right = process(pre, rightInPre, r1, post, r2 - (r1 - rightInPre + 1), r2 - 1);
    return head;
  }

  // Optimize with Map
  public static TreeNode constructFromPrePost2(int[] pre, int[] post) {
    Map<Integer, Integer> preMap = new HashMap<>();
    for (int i = 0; i < pre.length; i++) {
      preMap.put(pre[i], i);
    }
    return process2(pre, 0, pre.length - 1, post, 0, post.length - 1, preMap);
  }

  public static TreeNode process2(int[] pre, int l1, int r1, int[] post, int l2, int r2, Map<Integer, Integer> preMap) {
    if (l1 > r1 || l2 > r2) {
      return null;
    }

    if (l1 == r1) {
      return new TreeNode(pre[l1]);
    }
    int h1 = pre[l1];
    int right = post[r2 - 1];
    TreeNode head = new TreeNode(h1);
    int rightInPre = preMap.get(right);

    // rightInPre...r1
    // r1-rightInPre + 1
    head.left = process2(pre, l1 + 1, rightInPre - 1, post, l2, r2 - (r1 - rightInPre + 1) - 1, preMap);
    head.right = process2(pre, rightInPre, r1, post, r2 - (r1 - rightInPre + 1), r2 - 1, preMap);
    return head;
  }


  public static void main(String[] args) {
    int[] pre = {2, 1};
    int[] post = {1, 2};
    TreeNode root = constructFromPrePost(pre, post);

  }


  private static class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
      this.val = val;
    }

    @Override
    public String toString() {
      return "TreeNode{" +
          "val=" + val +
          '}';
    }
  }

}
