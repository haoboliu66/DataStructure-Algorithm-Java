package com.hliu.random.algoexpert.veryhard;


/*

 */
public class P10_AllKindsOfNodeDepths {

  private static class BinaryTree {

    int value;
    BinaryTree left = null;
    BinaryTree right = null;

    public BinaryTree(int value) {
      this.value = value;
    }
  }

  public static BinaryTree flattenBinaryTree(BinaryTree root) {
    // Write your code here.
    return root;
  }

  public static Info process(BinaryTree node) {
    if (node == null) {
      return null;
    }
    Info leftInfo = process(node.left);
    Info rightInfo = process(node.right);
    BinaryTree head = node;
    BinaryTree tail = node;
    if (leftInfo == null && rightInfo == null) {
      return new Info(head, tail);
    }
    if (leftInfo != null && rightInfo != null) {
      leftInfo.tail.right = node;
      node.left = leftInfo.tail;
      head = leftInfo.head;
      node.right = rightInfo.head;
      rightInfo.head.left = node;
      tail = rightInfo.tail;
    } else if (leftInfo != null) {
      leftInfo.tail.right = node;
      node.left = leftInfo.tail;
      head = leftInfo.head;
    } else if (rightInfo != null) {
      node.right = rightInfo.head;
      rightInfo.head.left = node;
      tail = rightInfo.tail;
    }

    return new Info(head, tail);
  }

  private static class Info {

    BinaryTree head;
    BinaryTree tail;

    public Info(BinaryTree prev, BinaryTree next) {
      this.head = prev;
      this.tail = next;
    }
  }

}
