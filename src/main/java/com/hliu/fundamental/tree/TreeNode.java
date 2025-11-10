package com.hliu.fundamental.tree;

public class TreeNode {

  public String value;
  public int val;
  public TreeNode left;
  public TreeNode right;

  public TreeNode(String value) {
    this.value = value;
  }

  public TreeNode(int val) {
    this.val = val;
  }

  @Override
  public String toString() {
    return "TreeNode{" +
        "value='" + val + '\'' +
        '}';
  }
}
