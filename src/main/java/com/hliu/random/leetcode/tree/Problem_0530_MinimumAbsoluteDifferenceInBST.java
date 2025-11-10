package com.hliu.random.leetcode.tree;

public class Problem_0530_MinimumAbsoluteDifferenceInBST {


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


  public int getMinimumDifference(TreeNode root) {
    if (root == null) {
      return 0;
    }
    return process(root).maxDiff;
  }

  public Info process(TreeNode node) {
      if(node == null) {
          return new Info(Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
      }
    Info leftInfo = process(node.left);
    Info rightInfo = process(node.right);

    int min = Math.min(node.val, leftInfo.min);
    int max = Math.max(node.val, rightInfo.max);
    int maxDiff = Math.max(
        Math.max(leftInfo.maxDiff, rightInfo.maxDiff),
        Math.max(Math.abs(node.val - leftInfo.min), Math.abs(node.val - rightInfo.max))
    );
    maxDiff = Math.max((rightInfo.max - leftInfo.min), maxDiff);
    return new Info(min, max, maxDiff);
  }

  static class Info {
      int min;
      int max;
      int maxDiff;

    public Info(int min, int max, int maxDiff) {
      this.min = min;
      this.max = max;
      this.maxDiff = maxDiff;
    }
  }

}
