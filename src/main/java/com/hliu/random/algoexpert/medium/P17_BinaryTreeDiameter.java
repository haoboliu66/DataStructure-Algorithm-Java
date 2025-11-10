package com.hliu.random.algoexpert.medium;

public class P17_BinaryTreeDiameter {

  static class BinaryTree {
    public int value;
    public BinaryTree left = null;
    public BinaryTree right = null;

    public BinaryTree(int value) {
      this.value = value;
    }
  }

  public int binaryTreeDiameter(BinaryTree tree) {
    return process(tree).maxDistance - 1;
  }

  public Info process(BinaryTree node){
    if(node == null) return new Info(0,0);

    Info leftInfo = process(node.left);
    Info rightInfo = process(node.right);

    int m = Math.max(leftInfo.maxDistance, rightInfo.maxDistance);
    m = Math.max(1 + leftInfo.height + rightInfo.height, m);

    int h = Math.max(leftInfo.height, rightInfo.height) + 1;
    return new Info(m, h);
  }


  class Info{
    int maxDistance;
    int height;

    public Info(int h1, int m1){
      maxDistance = h1;
      height = m1;
    }
  }

}
