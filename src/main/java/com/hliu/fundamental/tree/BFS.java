package com.hliu.fundamental.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeMap;

public class BFS {


  public static void main(String[] args) {

    int[] nums = {9, 8, 1, 0, 1, 9, 4, 0, 4, 1};
    int res = maxWidthRamp(nums);
    System.out.println(res);
  }

  public static int maxWidthRamp(int[] nums) {
    if (nums == null || nums.length < 2) {
      return 0;
    }
    TreeMap<Integer, Integer> indexMap = new TreeMap<>();
    int ramp = 0;
    indexMap.put(nums[0], 0);
    for (int i = 1; i < nums.length; i++) {
      int cur = nums[i];
      if (!indexMap.containsKey(cur)) {
        indexMap.put(cur, i);
      }
      Integer lessIndex = indexMap.firstEntry()
                                  .getValue();
      if (lessIndex != null) {
        ramp = Math.max(ramp, i - lessIndex);
      }
      Integer equalIndex = indexMap.get(cur);
      if (equalIndex != null) {
        ramp = Math.max(ramp, i - equalIndex);
      }
      if (!indexMap.containsKey(cur)) {
        indexMap.put(cur, i);
      }
    }
    return ramp;
  }

  // 普通bfs
  public void bfs(TreeNode root) {
    if (root == null) {
      return;
    }
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      TreeNode cur = queue.poll();
      System.out.println(cur);
      if (cur.left != null) {
        queue.add(cur.left);
      }
      if (cur.right != null) {
        queue.add(cur.right);
      }
    }
  }

  // https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null) {
      return res;
    }
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    boolean linkToTail = true;
    while (!queue.isEmpty()) {
      int size = queue.size();
      LinkedList<Integer> curLevel = new LinkedList<>();
      for (int i = 0; i < size; i++) {
        TreeNode node = queue.poll();
        if (linkToTail) {
          curLevel.addLast(node.val);
        } else {
          curLevel.addFirst(node.val);
        }
        if (node.left != null) {
          queue.offer(node.left);
        }
        if (node.right != null) {
          queue.offer(node.right);
        }
      }
      res.add(curLevel);
      linkToTail = !linkToTail;
    }
    return res;
  }

  // https://leetcode.com/problems/maximum-width-of-binary-tree/
  public int widthOfBinaryTree(TreeNode root) {
    if (root == null) {
      return 0;
    }
    Queue<TreeNode> queue = new LinkedList<>();
    List<Integer> nodeIds = new ArrayList<>(); // [0,1,2,3,4,5,6,7,8,9,10]
    queue.offer(root);
    nodeIds.add(1); // assign root node as id 1
    int pass = 0;
    int maxWidth = 1;
    while (!queue.isEmpty()) {
      int size = queue.size(); // current level node count
      int idCount = nodeIds.size(); // total node count, we need remove passed node, tracked with pass
      int mostRightIndex = idCount - 1; // index of most right node in current level
      maxWidth = Math.max(maxWidth, nodeIds.get(mostRightIndex) - nodeIds.get(pass) + 1);
      for (int i = 0; i < size; i++) {
        TreeNode node = queue.poll();

        if (node.left != null) {
          queue.offer(node.left);
          nodeIds.add(nodeIds.get(pass) * 2);
        }
        if (node.right != null) {
          queue.offer(node.right);
          nodeIds.add(nodeIds.get(pass) * 2 + 1);
        }
        pass++;
      }
    }
    return maxWidth;
  }

  // 在已经node总数有限制的情况下, 使用数组优化当前层节点的追踪
  public int widthOfBinaryTree0(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int maxWidth = 1;
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    int left = 0, right = 0;
    int[] nodeIndex = new int[3001]; // ids[i] => get i-th node index
    // [left ... right) track the valid ids in current level => right - left = current level node count
    // left和right只是追踪有效节点的个数, 具体节点的index要用left和right作为下标访问数据获得
    nodeIndex[right++] = 1; // assign root node as index 1

    /*
    the idea is to assign index to each node:
    parent node index = i;
     - left child index = 2 * i;
     - right child index = 2 * i + 1;
     */
    while (!queue.isEmpty()) {
      int curLevelSize = right - left; // total node at current level
      maxWidth = Math.max(maxWidth, nodeIndex[right - 1] - nodeIndex[left] + 1);
      for (int i = 0; i < curLevelSize; i++) {
        TreeNode node = queue.poll();
        int index = nodeIndex[left];
        if (node.left != null) {
          queue.offer(node.left);
          nodeIndex[right++] = index * 2;
        }
        if (node.right != null) {
          queue.offer(node.right);
          nodeIndex[right++] = index * 2 + 1;
        }
        left++;
      }
    }
    return maxWidth;
  }

  // https://leetcode.com/problems/check-completeness-of-a-binary-tree/
  /*
  rule1: 某个node有右无左, 违反CBT规则
  rule2: 某个node不包含两个子节点, 那么后续的所有节点都必须是leaf node
   */
  public boolean isCompleteTree(TreeNode root) {
    if (root == null) {
      return true;
    }
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    boolean mustBeLeaf = false;
    while (!queue.isEmpty()) {
      TreeNode node = queue.poll();
      if (node.right != null && node.left == null) {
        return false;
      }
      if (mustBeLeaf) {
        if (node.left != null || node.right != null) {
          return false;
        }
      }

      // 一旦发现当前node不包含两个子节点, 那么后续的所有节点都必须是leaf node, 设置mustBeLeaf flag
      if (node.left != null) {
        queue.offer(node.left);
      } else {
        mustBeLeaf = true;
      }
      if (node.right != null) {
        queue.offer(node.right);
      } else {
        mustBeLeaf = true;
      }
    }
    return true;
  }

  public static int maxWidthUsingMap(TreeNode head) {
    if (head == null) {
      return 0;
    }
    HashMap<TreeNode, Integer> levelMap = new HashMap<>();
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(head);
    levelMap.put(head, 1);
    int curLevel = 1; //当前层
    int curLevelNodes = 0; //一个节点出队列的时候再加到宽度上
    int max = 0;
    while (!queue.isEmpty()) {
      TreeNode cur = queue.poll();
      int curNodeLevel = levelMap.get(cur);
      if (cur.left != null) {
        queue.add(cur.left);
        levelMap.put(cur.left, curLevelNodes + 1);
      }
      if (cur.right != null) {
        queue.add(cur.right);
        levelMap.put(cur.right, curLevelNodes + 1);
      }
      if (curNodeLevel == curLevel) { // 如果当前出队列的Node所在层数 == 当前在统计的层数
        curLevelNodes++; // 说明当前出队列的Node就是统计层数上的节点, 所以应该节点数++
      } else { // 如果当前出队列的Node所在层数 != 当前在统计的层数(应该是当前Node的层数是统计的层数的下一层)
        max = Math.max(curLevelNodes, max); //结算上一层的节点个数, 找出最大
        curLevel++; // 应该去统计下一层了, 所以curLevel++
        curLevelNodes = 1; //节点数置位1(也就是统计了刚出队列的这一个Node)
      }
    }
    //最后一层节点没有再下一层给他结算, 所以最后还要再算一次
    max = Math.max(curLevelNodes, max);
    return max;
  }

  public static int maxWidthNoMap(TreeNode head) {
    if (head == null) {
      return 0;
    }
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(head);
    TreeNode curEnd = head;  // 当前层的最右节点
    TreeNode nextEnd = null;    //如果有下一层的话, 下一层的最右节点
    int curLevelNodes = 0;
    int max = 0;
    while (!queue.isEmpty()) {
      TreeNode cur = queue.poll();
      if (cur.left != null) {
        queue.add(cur.left);
        nextEnd = cur.left;
      }
      if (cur.right != null) {
        queue.add(cur.right);
        nextEnd = cur.right;
      }
      curLevelNodes++;  //因为队列刚弹出一个Node, 所以当前层节点要++
      if (cur == curEnd) { //当队列弹出的节点等于curEnd的时, 说明这个节点已经是到了该层的最后一个
        max = Math.max(max, curLevelNodes);
        curLevelNodes = 0; // 还没统计下一层,所以是0
        curEnd = nextEnd;  //到了下一层, 下一层的最后一个节点curEnd就是上一层统计的nextEnd
      }
    }
    return max;
  }

}
