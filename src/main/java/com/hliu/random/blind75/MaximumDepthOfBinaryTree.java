package com.hliu.random.blind75;

import com.hliu.random.blind75.entity.TreeNode;

public class MaximumDepthOfBinaryTree {

  public int maxDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int leftHeight = maxDepth(root.left);
    int rightHeight = maxDepth(root.right);

    return Math.max(leftHeight, rightHeight) + 1;
  }

  public boolean isSameTree(TreeNode p, TreeNode q) {
    if (p == null || q == null) {
      return p == q;
    }
    boolean leftSame = isSameTree(p.left, q.left);
    boolean rightSame = isSameTree(p.right, q.right);
    return p.val == q.val && leftSame && rightSame;
  }

  public boolean isSubtree(TreeNode root, TreeNode subRoot) {
    if (root == null || subRoot == null) {
      return root == subRoot;
    }
    boolean leftResult = isSubtree(root.left, subRoot);
    boolean rightResult = isSubtree(root.right, subRoot);
    boolean currResult = isSameTreeHelper(root, subRoot);
    boolean currLeftResult = isSameTreeHelper(root.left, subRoot);
    boolean currRightResult = isSameTreeHelper(root.right, subRoot);
    return currResult || currLeftResult || currRightResult || leftResult || rightResult;
  }

  public boolean isSameTreeHelper(TreeNode p, TreeNode q) {
    if (p == null || q == null) {
      return p == q;
    }
    boolean leftSame = isSameTreeHelper(p.left, q.left);
    boolean rightSame = isSameTreeHelper(p.right, q.right);
    return p.val == q.val && leftSame && rightSame;
  }

  public boolean isSubtree0(TreeNode root, TreeNode subRoot) {
    return check(root, subRoot);
  }

  // if subtree with node as head matches subRoot
  private boolean check(TreeNode node, TreeNode subRoot) {
    if(node == null || subRoot == null) {
      return node == subRoot;
    }
    boolean currResult = isSameTreeHelper(node, subRoot); // current tree matches?
    boolean leftResult = check(node.left, subRoot); // check left subtree
    boolean rightResult = check(node.right, subRoot); // check right subtree
    return currResult || leftResult || rightResult;
  }
}
