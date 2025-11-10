package com.hliu.random.blind75;

import com.hliu.random.blind75.entity.TreeNode;

public class LowestCommonAncestor {

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    Info fullInfo = process(root, p, q);
    return fullInfo.lca;
  }

  private Info process(TreeNode node, TreeNode p, TreeNode q) {
    if (node == null) {
      return new Info(false, false, null);
    }

    Info leftInfo = process(node.left, p, q);
    Info rightInfo = process(node.right, p, q);

    boolean foundP = leftInfo.foundP || rightInfo.foundP || node.val == p.val;
    boolean foundQ = leftInfo.foundQ || rightInfo.foundQ || node.val == q.val;
    TreeNode lca = null;

    if (leftInfo.lca != null) {
      lca = leftInfo.lca;
    } else if (rightInfo.lca != null) {
      lca = rightInfo.lca;
    } else {
      if (foundP && foundQ) {
        lca = node;
      }
    }
    return new Info(foundP, foundQ, lca);
  }

  private static class Info {

    boolean foundP;
    boolean foundQ;
    TreeNode lca;

    public Info(boolean foundP, boolean foundQ, TreeNode lca) {
      this.foundP = foundP;
      this.foundQ = foundQ;
      this.lca = lca;
    }
  }

}
