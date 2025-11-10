package com.hliu.fundamental.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class SymmetricTree {

  public static void main(String[] args) {

    String s = "vvvlo";
    SymmetricTree solution = new SymmetricTree();
    String x = solution.reorganizeString(s);
    System.out.println(x);
  }



  public String reorganizeString(String s) {
    if (s == null || s.length() == 0) {
      return "";
    }
    char[] str = s.toCharArray();
    int[] countMap = new int[26];
    for (char c : str) {
      countMap[c - 'a']++;
      if (countMap[c - 'a'] > (str.length + 1) / 2) {
        return "";
      }
    }
    char[] res = new char[str.length];
    res[0] = str[0];
    for (int i = 1; i < res.length; i++) {
      res[i] = findChar(countMap, res[i - 1]);
    }
    return new String(res);
  }

  private char findChar(int[] map, char exclude) {
    int appear = 0;
    int index = 0;
    for (int i = 0; i < map.length; i++) {

      if ((char) (i + 'a') == exclude) {
        continue;
      }
      if (map[i] > appear) {
        appear = map[i];
        index = i;
      }
    }
    map[index]--;
    return (char) (index + 'a');
  }

  public static boolean isSymmetric(TreeNode root) {
    if (root == null) {
      return true;
    }
    Stack<TreeNode> stack1 = new Stack<>();
    Stack<TreeNode> stack2 = new Stack<>();
    Queue<String> queue1 = new LinkedList<>();
    Queue<String> queue2 = new LinkedList<>();
    stack1.push(root);
    stack2.push(root);
    while (!stack1.isEmpty() && !stack2.isEmpty()) {

      TreeNode cur1 = stack1.pop();
      queue1.add(cur1.value.toString());

      if (cur1.right != null) { // stack1是正常前序  头左右
        stack1.push(cur1.right);
      }
      if (cur1.left != null) {
        stack1.push(cur1.left);
      }

      TreeNode cur2 = stack2.pop();
      queue2.add(cur2.value.toString());

      if (cur2.left != null) { //stack2是变化的前序  头右左
        stack2.push(cur2.left);
      }
      if (cur2.right != null) {
        stack2.push(cur2.right);
      }

    }
    return isEquals(queue1, queue2);
  }

  public static boolean isEquals(Queue<String> q1, Queue<String> q2) {
    System.out.println(q1);
    System.out.println(q2);
    if (q1.size() != q2.size()) {
      return false;
    }

    while (!q1.isEmpty()) {
      if (!q1.poll()
             .equals(q2.poll())) {
        return false;
      }
    }
    return true;
  }


  public static boolean isSymmetric1(TreeNode root) {
    if (root == null) {
      return true;
    }
    return process(root.left, root.right);
  }

  public static boolean process(TreeNode left, TreeNode right) {
    if (left == null && right == null) {
      return true;
    }
    if (left != null && right != null) {
      return left.val == right.val
          && process(left.left, right.right)
          && process(left.right, right.left);
    }

    return false;
  }

}
