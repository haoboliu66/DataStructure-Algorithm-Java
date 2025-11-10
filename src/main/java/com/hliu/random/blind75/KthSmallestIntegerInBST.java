package com.hliu.random.blind75;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.hliu.random.blind75.entity.TreeNode;

public class KthSmallestIntegerInBST {

  public int kthSmallest(TreeNode root, int k) {
    List<Integer> nodeValueList = new ArrayList<>();
    inOrderTraversal(root, nodeValueList);
    return nodeValueList.get(k - 1);
  }


  private void inOrderTraversal0(TreeNode node, List<Integer> nodes) {
    Stack<TreeNode> stack = new Stack<>();
    stack.push(node);
    while (!stack.isEmpty() || node != null) {
      while (node != null) {
        stack.push(node);
        node = node.left;
      }
      node = stack.pop(); // head of this subtree
      nodes.add(node.val);
      node = node.right;
    }
  }

  private void inOrderTraversal(TreeNode node, List<Integer> nodes) {
    if (node == null) {
      return;
    }
    inOrderTraversal(node.left, nodes);
    nodes.add(node.val);
    inOrderTraversal(node.right, nodes);
  }


}
