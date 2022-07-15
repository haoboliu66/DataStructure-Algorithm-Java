package com.hliu.advanced.system._28_kmp;

import java.util.ArrayList;
import java.util.List;

public class TreeEqual {

  public static class Node {

    int val;
    Node left;
    Node right;

    public Node(int val) {
      this.val = val;
    }
  }

    /*
    二叉树T1头head1, 二叉树T2头head2, 判断T2树的结构是不是和T1的一部分结构相等
     */

  // 复杂度O(N * M)
  public static boolean containsTree1(Node head1, Node head2) {
    if (head2 == null) {// 任何树都包括空树
      return true;
    }
    if (head1 == null) { //空树不可能包括别的树
      return false;
    }
    if (isSameValueStructure(head1, head2)) {
      return true;
    }
    return containsTree1(head1.left, head2) || containsTree1(head1.right, head2);
  }

  private static boolean isSameValueStructure(Node head1, Node head2) {
    if (head1 == null && head2 != null) {
      return false;
    }
    if (head1 != null && head2 == null) {
      return false;
    }
    if (head1 == null && head2 == null) {
      return true;
    }
    if (head1.val != head2.val) {
      return false;
    }

    return isSameValueStructure(head1.left, head2.left) && isSameValueStructure(head1.right, head2.right);
  }

  public static boolean containsTree2(Node head1, Node head2) {
    if (head2 == null) {
      return true;
    }
    if (head1 == null) {
      return false;
    }
    ArrayList<String> l1 = preSerial(head1);
    ArrayList<String> l2 = preSerial(head2);
    String[] str = new String[l1.size()];
    for (int i = 0; i < str.length; i++) {
      str[i] = l1.get(i);
    }

    String[] match = new String[l2.size()];
    for (int i = 0; i < match.length; i++) {
      match[i] = l2.get(i);
    }

    return getIndexOf(str, match) != -1;
  }

  public static ArrayList<String> preSerial(Node head) {
    ArrayList<String> res = new ArrayList<>();
    if (head == null) {
      return null;
    }
    process2(head, res);
    return res;
  }

  public static void process2(Node node, List<String> res) {
    if (node == null) {
      res.add("null");
      return;
    }
    res.add(String.valueOf(node.val)); //头 左 右
    process2(node.left, res);
    process2(node.right, res);
  }


  public static int getIndexOf(String[] str, String[] match) {
    if (str.length < match.length) {
      return -1;
    }
    int x = 0;
    int y = 0;
    int[] next = getNextArray(match);
    while (x < str.length && y < match.length) {
      if (str[x].equals(match[y])) {
        x++;
        y++;
      } else if (y != 0) {
        y = next[y];
      } else {
        x++;
      }
    }
    return y == match.length ? x - y : -1;
  }

  public static int[] getNextArray(String[] match) {
    if (match.length == 1) {
      return new int[]{-1};
    }
    int[] next = new int[match.length];
    next[0] = -1;
    next[1] = 0;
    int i = 2;
    int cn = 0;
    while (i < match.length) {
      if (match[i - 1].equals(match[cn])) {
        next[i++] = ++cn;
      } else if (cn > 0) {
        cn = next[cn];
      } else {
        next[i++] = 0;
      }
    }
    return next;
  }

  // for test
  public static Node generateRandomBST(int maxLevel, int maxValue) {
    return generate(1, maxLevel, maxValue);
  }

  // for test
  public static Node generate(int level, int maxLevel, int maxValue) {
    if (level > maxLevel || Math.random() < 0.5) {
      return null;
    }
    Node head = new Node((int) (Math.random() * maxValue));
    head.left = generate(level + 1, maxLevel, maxValue);
    head.right = generate(level + 1, maxLevel, maxValue);
    return head;
  }

  public static void main(String[] args) {
    int bigTreeLevel = 7;
    int smallTreeLevel = 4;
    int nodeMaxValue = 5;
    int testTimes = 1000000;
    System.out.println("test begin");
    for (int i = 0; i < testTimes; i++) {
      Node big = generateRandomBST(bigTreeLevel, nodeMaxValue);
      Node small = generateRandomBST(smallTreeLevel, nodeMaxValue);
      boolean ans1 = containsTree1(big, small);
      boolean ans2 = containsTree2(big, small);
      if (ans1 != ans2) {
        System.out.println("Oops!");
      }
    }
    System.out.println("test finish!");

  }

}
