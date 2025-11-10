package com.hliu.random.blind75;

import com.hliu.random.blind75.entity.TreeNode;

public class InvertBinaryTree {

  public TreeNode invertTree(TreeNode root) {
    return invertSubTree(root);
  }

  private TreeNode invertSubTree(TreeNode node) {
    if (node == null) {
      return null;
    }
    TreeNode tmp = node.left;
    node.left = node.right;
    node.right = tmp;
    invertTree(node.left);
    invertTree(node.right);
    return node;
  }
}
