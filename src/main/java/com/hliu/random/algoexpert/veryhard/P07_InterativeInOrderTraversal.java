package com.hliu.random.algoexpert.veryhard;

import java.util.Stack;
import java.util.function.Function;


/*
https://leetcode.com/problems/count-of-smaller-numbers-after-self/
 */
public class P07_InterativeInOrderTraversal {

  public static void iterativeInOrderTraversal(
      BinaryTree root, Function<BinaryTree, Void> callback) {
    if(root == null) return;
    if(root.left == null && root.right == null){
      callback.apply(root);
      return;
    }

    Stack<BinaryTree> stack = new Stack<>();

    while(root != null || !stack.isEmpty()){
      while(root != null){
        stack.push(root);
        root = root.left;
      }
      root = stack.pop();
      callback.apply(root);
      root = root.right;
    }
  }

  static class BinaryTree {
    public int value;
    public BinaryTree left;
    public BinaryTree right;
    public BinaryTree parent;

    public BinaryTree(int value) {
      this.value = value;
    }

    public BinaryTree(int value, BinaryTree parent) {
      this.value = value;
      this.parent = parent;
    }
  }

}
