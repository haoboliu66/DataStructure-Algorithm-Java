package com.hliu.random.leetcode.tree.bfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Problem_0515_FindLargestValueInEachTreeRow {

  /*
   ["aba","bcb","ece","aa","e"]

   [ 1, 1, 2, 3, 3 ]


   */




  // DFS Solution
  public List<Integer> largestValues2(TreeNode root) {
    List<Integer> result = new ArrayList<>();

    return result;
  }

  // BFS
  public List<Integer> largestValues1(TreeNode root) {
    if (root == null) {
      return new ArrayList<>(0);
    }
    List<Integer> result = new ArrayList<>();
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
      int size = queue.size();
      int max = Integer.MIN_VALUE;
      for (int i = 0; i < size; i++) {
        TreeNode curr = queue.poll();
        max = Math.max(max, curr.val);
        if (curr.left != null) {
          queue.offer(curr.left);
        }
        if (curr.right != null) {
          queue.offer(curr.right);
        }
      }
      result.add(max);
    }
    return result;
  }

  // BFS
  public List<Integer> largestValues(TreeNode root) {
    if (root == null) {
      return new ArrayList<>(0);
    }
    List<Integer> result = new ArrayList<>();
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    Map<TreeNode, Integer> levelMap = new HashMap<>();
    levelMap.put(root, 0);
    int currLevel = 0;
    int max = Integer.MIN_VALUE;

    while (!queue.isEmpty()) {
      TreeNode curr = queue.poll();
      int currNodeLevel = levelMap.get(curr);

      if (currNodeLevel > currLevel) { // curr is at the new level
        result.add(max);  // 结算上一层的最大值, 加入结果集
        max = curr.val; // 更新新的level的最大值
        currLevel++;
      } else {
        max = Math.max(max, curr.val); // curr 还是在当前的level, 一直更新max
      }

      if (curr.left != null) {
        queue.offer(curr.left);
        levelMap.put(curr.left, currNodeLevel + 1);
      }
      if (curr.right != null) {
        queue.offer(curr.right);
        levelMap.put(curr.right, currNodeLevel + 1);
      }
    }
    result.add(max); // 最后一层的节点没有下一层来触发结算, 所以在循环结束后, 结算最后一层的最大值
    return result;
  }

  static class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
      this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }
}
