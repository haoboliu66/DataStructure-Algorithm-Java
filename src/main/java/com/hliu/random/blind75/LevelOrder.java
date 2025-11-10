package com.hliu.random.blind75;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.hliu.random.blind75.entity.TreeNode;

public class LevelOrder {

  public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null) {
      return res;
    }
    Queue<TreeNode> queue = new LinkedList<>();
    TreeNode curr = root;
    queue.offer(curr);
    while (!queue.isEmpty()) {

      int currLevelSize = queue.size();

      List<Integer> levelNode = new ArrayList<>();
      for (int i = 0; i < currLevelSize; i++) {
        TreeNode node = queue.poll();
        levelNode.add(node.val);
        if (node.left != null) {
          queue.offer(node.left);
        }
        if (node.right != null) {
          queue.offer(node.right);
        }
      }
      res.add(levelNode);
    }
    return res;
  }

}
