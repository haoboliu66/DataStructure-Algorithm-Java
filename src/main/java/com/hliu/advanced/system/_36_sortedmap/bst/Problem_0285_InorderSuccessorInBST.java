package com.hliu.advanced.system._36_sortedmap.bst;

public class Problem_0285_InorderSuccessorInBST {

  private static class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }


  public static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
    TreeNode successor = null;
    while (root != null) {
      if (p.val >= root.val) {
        // successor must exist in the right tree
        root = root.right;
      } else { // p < cur.val
        // search p in the left tree, current root can be the answer
        successor = root;
        root = root.left;
      }
    }
    return successor;
  }
}
