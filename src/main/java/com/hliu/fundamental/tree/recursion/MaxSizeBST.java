package com.hliu.fundamental.tree.recursion;

import java.util.ArrayList;

public class MaxSizeBST {

  // https://leetcode.com/problems/largest-bst-subtree/

  private static class Info {

    int maxBSTSize;
    int min;
    int max;
    boolean isBST;

    public Info(int maxBSTSize, int min, int max, boolean bst) {
      this.maxBSTSize = maxBSTSize;
      this.min = min;
      this.max = max;
      isBST = bst;
    }
  }

  public static int maxSizeBST(TreeNode root) {
    if (root == null) {
      return 0;
    }
    return process(root).maxBSTSize;
  }

  public static Info process(TreeNode node) {
    if (node == null) {
      return null;
    }
    int maxBSTSize, min, max;
    boolean isBST;
    Info leftInfo = process(node.left);
    Info rightInfo = process(node.right);

    if (leftInfo == null && rightInfo == null) {
      // 叶节点
      maxBSTSize = 1;
      min = node.val;
      max = node.val;
      isBST = true;
    } else {
      if (leftInfo != null && rightInfo != null) {
        // 只有当左右都是BST, 且满足val大小关系, 才能是BST
        isBST = leftInfo.isBST && rightInfo.isBST &&
            node.val > leftInfo.max && node.val < rightInfo.min;
        min = isBST ? leftInfo.min : Math.min(node.val, Math.min(leftInfo.min, rightInfo.min));
        max = isBST ? rightInfo.max : Math.max(node.val, Math.max(leftInfo.max, rightInfo.max));
        maxBSTSize = isBST ? 1 + leftInfo.maxBSTSize + rightInfo.maxBSTSize
            : Math.max(leftInfo.maxBSTSize, rightInfo.maxBSTSize);
      } else if (leftInfo == null) {
        isBST = rightInfo.isBST && node.val < rightInfo.min;
        min = Math.min(node.val, rightInfo.min);
        max = Math.max(node.val, rightInfo.max);
        // 如果总体是BST, 那么就加上右树的size
        // 如果总体不是BST, 那么结果就是右树上已经找到的maxBSTSize
        maxBSTSize = isBST ? 1 + rightInfo.maxBSTSize : rightInfo.maxBSTSize;
      } else {
        // rightInfo == null
        isBST = leftInfo.isBST && node.val > leftInfo.max;
        min = Math.min(node.val, leftInfo.min);
        max = Math.max(node.val, leftInfo.max);
        // 如果总体是BST, 那么就加上左树的size
        // 如果总体不是BST, 那么结果就来自左树某个子树形成的maxBSTSize
        maxBSTSize = isBST ? 1 + leftInfo.maxBSTSize : leftInfo.maxBSTSize;
      }
    }
    return new Info(maxBSTSize, min, max, isBST);
  }

  /* ----------------------*/
  // 对数器 for test
  public static int getBSTSize(TreeNode head) {
    if (head == null) {
      return 0;
    }
    ArrayList<TreeNode> arr = new ArrayList<>();
    in(head, arr);
    for (int i = 1; i < arr.size(); i++) {
      if (arr.get(i).val <= arr.get(i - 1).val) {
        return 0;
      }
    }
    return arr.size();
  }

  public static void in(TreeNode head, ArrayList<TreeNode> arr) {
    if (head == null) {
      return;
    }
    in(head.left, arr);
    arr.add(head);
    in(head.right, arr);
  }

  public static int maxSubBSTSize1(TreeNode head) {
    if (head == null) {
      return 0;
    }
    int h = getBSTSize(head);
    if (h != 0) {
      return h;
    }
    return Math.max(maxSubBSTSize1(head.left), maxSubBSTSize1(head.right));
  }


  public static int maxSubBSTSize2(TreeNode head) {
    if (head == null) {
      return 0;
    }
    return process2(head).maxBSTSubtreeSize;
  }

  public static class InfoZuo {

    public int maxBSTSubtreeSize;
    public int allSize;
    public int max;
    public int min;

    public InfoZuo(int m, int a, int ma, int mi) {
      maxBSTSubtreeSize = m;
      allSize = a;
      max = ma;
      min = mi;
    }
  }

  public static InfoZuo process2(TreeNode x) {
    if (x == null) {
      return null;
    }
    InfoZuo leftInfo = process2(x.left);
    InfoZuo rightInfo = process2(x.right);
    int max = x.val;
    int min = x.val;
    int allSize = 1;
    if (leftInfo != null) {
      max = Math.max(leftInfo.max, max);
      min = Math.min(leftInfo.min, min);
      allSize += leftInfo.allSize;
    }
    if (rightInfo != null) {
      max = Math.max(rightInfo.max, max);
      min = Math.min(rightInfo.min, min);
      allSize += rightInfo.allSize;
    }
    int p1 = -1;
    if (leftInfo != null) {
      p1 = leftInfo.maxBSTSubtreeSize;
    }
    int p2 = -1;
    if (rightInfo != null) {
      p2 = rightInfo.maxBSTSubtreeSize;
    }
    int p3 = -1;
    boolean leftBST = leftInfo == null ? true : (leftInfo.maxBSTSubtreeSize == leftInfo.allSize);
    boolean rightBST = rightInfo == null ? true : (rightInfo.maxBSTSubtreeSize == rightInfo.allSize);
    if (leftBST && rightBST) {
      boolean leftMaxLessX = leftInfo == null ? true : (leftInfo.max < x.val);
      boolean rightMinMoreX = rightInfo == null ? true : (x.val < rightInfo.min);
      if (leftMaxLessX && rightMinMoreX) {
        int leftSize = leftInfo == null ? 0 : leftInfo.allSize;
        int rightSize = rightInfo == null ? 0 : rightInfo.allSize;
        p3 = leftSize + rightSize + 1;
      }
    }
    return new InfoZuo(Math.max(p1, Math.max(p2, p3)), allSize, max, min);
  }


     /*
    给定一棵二叉树的头节点head, 返回这颗二叉树中最大的二叉搜索子树的大小
     */

  public static int getSubBSTNodeSize(TreeNode head) {
    if (head == null) {
      return 0;
    }

    return process0(head).maxSubBSTSize;
  }

  public static NodeInfo process0(TreeNode node) {
    if (node == null) {
      return null;
    }
    NodeInfo leftInfo = process0(node.left);
    NodeInfo rightInfo = process0(node.right);

    int max = node.val;
    int min = node.val;
    if (leftInfo != null) {
      max = Math.max(leftInfo.max, max);
      min = Math.min(leftInfo.min, min);
    }

    if (rightInfo != null) {
      min = Math.min(rightInfo.min, min);
      max = Math.max(rightInfo.max, max);
    }

    boolean isAllBST =
        (leftInfo == null || leftInfo.isAllBST) &&
            (rightInfo == null || rightInfo.isAllBST) &&
            (leftInfo == null || leftInfo.max < node.val) &&
            (rightInfo == null || rightInfo.min > node.val);

    int maxSubBSTSize = !isAllBST ? Math.max(leftInfo != null ? leftInfo.maxSubBSTSize : 0,
        rightInfo != null ? rightInfo.maxSubBSTSize : 0)
        : (leftInfo != null ? leftInfo.maxSubBSTSize : 0) + (rightInfo != null ? rightInfo.maxSubBSTSize : 0) + 1;

    return new NodeInfo(maxSubBSTSize, isAllBST, min, max);
  }


  public static class NodeInfo {

    public int maxSubBSTSize;
    public boolean isAllBST;
    public int min;
    public int max;

    public NodeInfo(int maxSubBSTSize, boolean isAllBST, int min, int max) {
      this.maxSubBSTSize = maxSubBSTSize;
      this.isAllBST = isAllBST;
      this.min = min;
      this.max = max;
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
    int maxLevel = 50;
    int maxValue = 3000;
    int testTimes = 1000000;
    System.out.println("Started");
    for (int i = 0; i < testTimes; i++) {
      TreeNode head = generateRandomBST(maxLevel, maxValue);
      if (maxSubBSTSize1(head) != maxSizeBST(head) ||
          maxSubBSTSize2(head) != maxSizeBST(head) ||
          getSubBSTNodeSize(head) != maxSubBSTSize2(head)) {
        System.out.println("Oops!");
      }
    }
    System.out.println("Done!");
  }


  private static class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
      this.val = val;
    }
  }

}
