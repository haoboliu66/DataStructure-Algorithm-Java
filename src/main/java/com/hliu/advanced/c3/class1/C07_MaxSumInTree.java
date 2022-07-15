package com.hliu.advanced.c3.class1;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class C07_MaxSumInTree {

    /*
    112. Path Sum
    113. Path Sum II
    129. Sum Root to Leaf Numbers
    437. Path Sum III
     */

  private static class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
      this.val = val;
    }

    @Override
    public String toString() {
      return "TreeNode{" +
          "val=" + val +
          '}';
    }
  }

  public static void main(String[] args) {
    TreeNode n1 = new TreeNode(10);
    TreeNode n2 = new TreeNode(5);
    TreeNode n3 = new TreeNode(-3);
    TreeNode n4 = new TreeNode(3);
    TreeNode n5 = new TreeNode(2);
    TreeNode n6 = new TreeNode(11);
    TreeNode n7 = new TreeNode(3);
    TreeNode n8 = new TreeNode(-2);
    TreeNode n9 = new TreeNode(1);
    n1.left = n2;
    n1.right = n3;
    n2.left = n4;
    n2.right = n5;
    n3.right = n6;
    n4.left = n7;
    n4.right = n8;
    n5.right = n9;
    System.out.println(pathSum(n1, 8));
  }

  // https://leetcode.com/problems/path-sum-iii/   not done yet
  public static int pathSum(TreeNode root, int targetSum) {
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();
    System.out.println(res);
    return process(root, targetSum, res, path);
  }

  public static int process(TreeNode node, int rest, List<List<Integer>> res, LinkedList<Integer> path) {
    if (node == null) {
      return 0;
    }

    if (node.left == null && node.right == null) {
      if (node.val == rest) {
        path.add(node.val);
        List<Integer> tmp = new ArrayList<>(path);
        res.add(tmp);
        path.pollLast();
        return 1;
      }
    }

    int count = 0;
//        if (node.val == rest) {
//            count++;
//        }

    count += process(node.left, rest, res, path);
    count += process(node.right, rest, res, path);

    path.add(node.val);
    count += process(node.left, rest - node.val, res, path);
    count += process(node.right, rest - node.val, res, path);
    path.pollLast();

    return count;
  }


  /*
  Q1: 路径是从头到叶, 返回最大路径和
   */
  public static int maxSum1(TreeNode head) {
    if (head == null) {
      return 0;
    }
    return process1(head);
  }

  public static int process1(TreeNode treeNode) {
    if (treeNode.left == null && treeNode.right == null) {
      return treeNode.val;
    }
    int next = Integer.MIN_VALUE;
    if (treeNode.left != null) {
      next = process1(treeNode.left);
    }
    if (treeNode.right != null) {
      next = Math.max(next, process1(treeNode.right));
    }
    return next + treeNode.val;
  }


  /*
  Q2:任意节点出发, 只能向下走, 返回最大路径和
   */
  public static int maxSum2(TreeNode head) {
    if (head == null) {
      return 0;
    }
    return process2(head).max;
  }

  public static Info process2(TreeNode X) {
    if (X == null) {
      return null;
    }
    int maxWithHead;
    int max = X.val;
    Info leftInfo = process2(X.left);
    Info rightInfo = process2(X.right);
    if (leftInfo != null) {

    }
    maxWithHead = X.val + Math.max(leftInfo.maxWithHead, rightInfo.maxWithHead);
    max = Math.max(leftInfo.max, rightInfo.max);

    return new Info(maxWithHead, max);
  }

  public static class Info {

    int maxWithHead;
    int max;

    public Info(int maxWithHead, int max) {
      this.maxWithHead = maxWithHead;
      this.max = max;
    }
  }

  /*
  Q3:任意节点出发, 到任意节点, 返回最大路径和
   */
  public static int maxSum3(TreeNode head) {
    if (head == null) {
      return 0;
    }
    return process3(head).allTreeMaxSum;
  }

  private static Info3 process3(TreeNode X) {
    if (X == null) {
      return null;
    }
    Info3 leftInfo = process3(X.left);
    Info3 rightInfo = process3(X.right);
    int p1 = Integer.MIN_VALUE;
    if (leftInfo != null) {
      p1 = leftInfo.allTreeMaxSum;
    }
    int p2 = Integer.MIN_VALUE;
    if (rightInfo != null) {
      p2 = rightInfo.allTreeMaxSum;
    }
    int p3 = X.val;
    int p4 = Integer.MIN_VALUE;
    if (leftInfo != null) {
      p4 = X.val + leftInfo.sumFromHead;
    }
    int p5 = Integer.MIN_VALUE;
    if (rightInfo != null) {
      p5 = X.val + rightInfo.sumFromHead;
    }
    int p6 = Integer.MIN_VALUE;
    if (leftInfo != null && rightInfo != null) {
      p6 = X.val + leftInfo.sumFromHead + rightInfo.sumFromHead;
    }

    int allTreeMaxSum = Math.max(p1, Math.max(p2, Math.max(p3, Math.max(p4, Math.max(p5, p6)))));
    int sumFromHead = Math.max(p3, Math.max(p4, p5)); // 不能算p6

    return new Info3(sumFromHead, allTreeMaxSum);
  }

  private static class Info3 {

    int sumFromHead;
    int allTreeMaxSum;

    public Info3(int sumFromHead, int allTreeMaxSum) {
      this.sumFromHead = sumFromHead;
      this.allTreeMaxSum = allTreeMaxSum;
    }
  }

//    public static void main(String[] args) {
//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(1);
//        list.add(2);
//        list.add(2);
//        list.add(3);
//        list.add(3);
//        System.out.println(list);
//        List<Integer> list1 = new ArrayList<>(new LinkedHashSet<>(list));
//        System.out.println(list1);
//    }

}

