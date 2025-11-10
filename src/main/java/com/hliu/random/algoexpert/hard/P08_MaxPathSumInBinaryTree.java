package com.hliu.random.algoexpert.hard;

public class P08_MaxPathSumInBinaryTree {

  static class BinaryTree {
    public int value;
    public BinaryTree left;
    public BinaryTree right;

    public BinaryTree(int value) {
      this.value = value;
    }
  }

  public static int maxPathSum(BinaryTree tree) {
    Info res = process(tree);
    return Math.max(res.leftSum, res.rightSum);
  }

  public static Info process(BinaryTree node){
    if(node == null) return null;

    Info leftInfo = process(node.left);
    Info rightInfo = process(node.right);

    int leftSum, rightSum;

    if(leftInfo == null && rightInfo == null){
      leftSum = node.value; rightSum = node.value;
    }else if(leftInfo != null && rightInfo != null){
      leftSum = Math.max(leftInfo.leftSum, leftInfo.rightSum);
      rightSum = Math.max(rightInfo.leftSum, rightInfo.rightSum);
    }else if(leftInfo != null){
      leftSum = Math.max(leftInfo.leftSum, leftInfo.rightSum);
      rightSum = 0;
    }else{
      leftSum = 0;
      rightSum = Math.max(rightInfo.leftSum, rightInfo.rightSum);
    }

    return new Info(leftSum + node.value, rightSum + node.value);
  }


  static class Info{
    int leftSum;
    int rightSum;

    public Info(int leftSum, int rightSum) {
      this.leftSum = leftSum;
      this.rightSum = rightSum;
    }
  }


}
