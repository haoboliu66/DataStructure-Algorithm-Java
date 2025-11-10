package com.hliu.random.algoexpert.veryhard;

import java.util.ArrayList;
import java.util.List;

public class P13_ {

  private static class BinaryTree {

    public int value;
    public BinaryTree left = null;
    public BinaryTree right = null;

    public BinaryTree(int value) {
      this.value = value;
    }
  }

  public boolean compareLeafTraversal(BinaryTree tree1, BinaryTree tree2) {

    List<Integer> res1 = new ArrayList<>();
    List<Integer> res2 = new ArrayList<>();
    process(tree1, res1);
    process(tree2, res2);
    return res1.equals(res2);
  }

  public void process(BinaryTree node, List<Integer> res) {
    if (node == null) {
      return;
    }
    if (node.left == null && node.right == null) {
      res.add(node.value);
      return;
    }
    process(node.left, res);
    process(node.right, res);
  }

}
