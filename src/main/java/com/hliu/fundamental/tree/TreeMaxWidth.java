package com.hliu.fundamental.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class TreeMaxWidth {

  public static int maxWidthUseMap(TreeNode head) {
    if (head == null) {
      return 0;
    }
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(head);
    HashMap<TreeNode, Integer> levelMap = new HashMap<>();
    levelMap.put(head, 1);
    int curLevel = 1;
    int curLevelNodes = 0;
    int max = 0;
    while (!queue.isEmpty()) {
      TreeNode cur = queue.poll();
      int curNodeLevel = levelMap.get(cur);
      if (cur.left != null) {
        levelMap.put(cur.left, curNodeLevel + 1);
        queue.add(cur.left);
      }
      if (cur.right != null) {
        levelMap.put(cur.right, curNodeLevel + 1);
        queue.add(cur.right);
      }
      if (curNodeLevel == curLevel) {
        curLevelNodes++;
      } else {
        max = Math.max(max, curLevelNodes);
        curLevel++;
        curLevelNodes = 1;
      }
    }
    max = Math.max(max, curLevelNodes);
    return max;
  }

  public static int getTreeMaxWidth(TreeNode head) {
    if (head == null) {
      return 0;
    }
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(head);
    TreeNode curEnd = head;
    TreeNode nextEnd = null;
    int curLevelNodes = 0;
    int max = 0;
    while (!queue.isEmpty()) {
      TreeNode cur = queue.poll();
      curLevelNodes++;
      if (cur.left != null) {
        queue.add(cur.left);
        nextEnd = cur.left;
      }
      if (cur.right != null) {
        queue.add(cur.right);
        nextEnd = cur.right;
      }

      if (cur == curEnd) {
        max = Math.max(max, curLevelNodes);
        curLevelNodes = 0;
        curEnd = nextEnd;
      }

    }
    return max;
  }


  public static int maxWidth(TreeNode head) {
      if (head == null) {
          return 0;
      }
    Queue<TreeNode> queue = new LinkedList<>();
    int max = 1;
    queue.offer(head);
    while (!queue.isEmpty()) {
      int size = queue.size();
      max = Math.max(size, max);
      for (int i = 0; i < size; i++) {
        TreeNode cur = queue.poll();
        if (cur.left != null) {
          queue.offer(cur.left);
        }
        if (cur.right != null) {
          queue.offer(cur.right);
        }
      }
    }
    return max;
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
    int maxLevel = 20;
    int maxValue = 100;
    int testTimes = 1000000;
    System.out.println("Started!");
    for (int i = 0; i < testTimes; i++) {
      TreeNode head = generateRandomBST(maxLevel, maxValue);
      if (maxWidthUseMap(head) != getTreeMaxWidth(head) || getTreeMaxWidth(head) != maxWidth(head)) {
        System.out.println("Oops!");
      }
    }
    System.out.println("Finish!");
  }


}
