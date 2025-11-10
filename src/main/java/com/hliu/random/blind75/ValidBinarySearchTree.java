package com.hliu.random.blind75;


import com.hliu.random.blind75.entity.TreeNode;

public class ValidBinarySearchTree {

  public boolean isValidBST(TreeNode root) {
    return check(root).valid;
  }

  private Info check(TreeNode node) {
    if (node == null) {
      return new Info(Integer.MAX_VALUE, Integer.MIN_VALUE, true);
    }
    int val = node.val;
    Info leftInfo = check(node.left);
    Info rightInfo = check(node.right);

    int min = Math.min(leftInfo.min, val);
    int max = Math.max(rightInfo.max, val);
    boolean valid = leftInfo.valid && rightInfo.valid && leftInfo.max < val && rightInfo.min > val;
    return new Info(min, max, valid);
  }

  private static class Info {

    int min;
    int max;
    boolean valid;

    public Info(int min, int max, boolean valid) {
      this.min = min;
      this.max = max;
      this.valid = valid;
    }
  }
}
