package com.hliu.fundamental.tree.recursion;

import org.junit.Test;

public class IsFullTree {

  @Test
  public void myTest() {
    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(3);
    TreeNode node4 = new TreeNode(4);
    TreeNode node5 = new TreeNode(5);
    TreeNode node6 = new TreeNode(6);
    TreeNode node7 = new TreeNode(7);
    node1.left = node2;
    node1.right = node3;
    node2.left = node4;
    node2.right = node5;
    node3.left = node6;
    node3.right = node7;
    System.out.println(isFull1(node1));
    System.out.println(isFull2(node1));
    System.out.println(fullTree(node1));
  }

  public static boolean fullTree(TreeNode head) {
      if (head == null) {
          return true;
      }
    int h = height(head);
    int n = nodes(head);
    return n == (1 << h) - 1;
  }

  public static int height(TreeNode node) {
      if (node == null) {
          return 0;
      }
    return Math.max(height(node.left), height(node.right)) + 1;
  }


  public static int nodes(TreeNode node) {
      if (node == null) {
          return 0;
      }
    return nodes(node.left) + nodes(node.right) + 1;
  }


  // nodes == 2 ^ h - 1
  public static boolean isFull1(TreeNode head) {
    int height = getHeight(head);
    int nodes = getNodes(head);
    return nodes == (1 << height) - 1;
  }

  public static int getHeight(TreeNode node) {
    if (node == null) {
      return 0;
    }
    return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
  }

  public static int getNodes(TreeNode node) {
    if (node == null) {
      return 0;
    }
    return getNodes(node.left) + getNodes(node.right) + 1;
  }


  public static boolean isFull2(TreeNode head) {
    if (head == null) {
      return true;
    }
    NodeInfo res = process(head);
    return (1 << res.height) - 1 == res.nodes;
  }

  public static NodeInfo process(TreeNode node) {
    if (node == null) {
      return new NodeInfo(0, 0);
    }

    NodeInfo left = process(node.left);
    NodeInfo right = process(node.right);

    int height = Math.max(left.height, right.height) + 1;
    int nodes = left.nodes + right.nodes + 1;

    return new NodeInfo(height, nodes);
  }

  public static class NodeInfo {

    int height;
    int nodes;

    public NodeInfo(int height, int node) {
      this.height = height;
      this.nodes = node;
    }
  }


  // for test
  public static TreeNode generateRandomBST(int maxLevel, int maxValue) {
    return generate(1, maxLevel, maxValue);
  }

  // for test
  public static TreeNode generate(int level, int maxLevel, int maxValue) {
    if (level > maxLevel || Math.random() < 0.5) {
      return null;
    }
    TreeNode head = new TreeNode((int) (Math.random() * maxValue));
    head.left = generate(level + 1, maxLevel, maxValue);
    head.right = generate(level + 1, maxLevel, maxValue);
    return head;
  }

  public static void main(String[] args) {
    int maxLevel = 5;
    int maxValue = 100;
    int testTimes = 1000000;
    for (int i = 0; i < testTimes; i++) {
      TreeNode head = generateRandomBST(maxLevel, maxValue);
      if (isFull1(head) != isFull2(head)) {
        System.out.println("Oops!");
      }
    }
    System.out.println("finish!");
  }


  private static class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int value) {
      this.val = value;
    }

    @Override
    public String toString() {
      return "SBTNode{" +
          "value='" + val + '\'' +
          '}';
    }
  }


}