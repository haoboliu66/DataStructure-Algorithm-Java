package com.hliu.advanced.c2.sortedMap;

import java.util.HashSet;

public class C01_CountOfRangeSum {
    /*
    lc 327
     */
  public static class SBTNode {

    long key;
    SBTNode left;
    SBTNode right;
    long size; //不同的key的size
    long all;

    public SBTNode(long k) {
      this.key = k;
      size = 1;
      all = 1;
    }

  }

  public static class SizeBalanceTree {

    private SBTNode root;
    private HashSet<Long> set = new HashSet<>();

    private SBTNode rightRotate(SBTNode cur) {
      long same = cur.all - (cur.left != null ? cur.left.all : 0) - (cur.right != null ? cur.right.all : 0);
      SBTNode leftNode = cur.left;
      cur.left = leftNode.right;
      leftNode.right = cur;
      leftNode.size = cur.size;
      cur.size = 1 + (cur.left != null ? cur.left.size : 0) + (cur.right != null ? cur.right.size : 0);

      leftNode.all = cur.all;
      cur.all = same + (cur.left != null ? cur.left.all : 0) + (cur.right != null ? cur.right.all : 0);

      return leftNode;
    }


    private SBTNode leftRotate(SBTNode cur) {
      long same = cur.all - (cur.left != null ? cur.left.all : 0) - (cur.right != null ? cur.right.all : 0);
      SBTNode rightNode = cur.right;
      cur.right = rightNode.left;
      rightNode.left = cur;
      rightNode.size = cur.size;
      cur.size = 1 + (cur.left != null ? cur.left.size : 0) + (cur.right != null ? cur.right.size : 0);

      rightNode.all = cur.all;
      cur.all = same + (cur.left != null ? cur.left.all : 0) + (cur.right != null ? cur.right.all : 0);

      return rightNode;
    }

    private SBTNode maintain(SBTNode cur) {
      if (cur == null) {
        return null;
      }
      if (cur.left != null && cur.left.left != null && cur.right != null && cur.left.left.size > cur.right.size) {
        // LL
        cur = rightRotate(cur);
        cur.right = maintain(cur.right);
        cur = maintain(cur);

      } else if (cur.left != null && cur.left.right != null && cur.right != null
          && cur.left.right.size > cur.right.size) {
        //LR
        cur.left = leftRotate(cur.left);
        cur = rightRotate(cur);
        cur.left = maintain(cur.left);
        cur.right = maintain(cur.right);
        cur = maintain(cur);

      } else if (cur.right != null && cur.right.right != null && cur.left != null
          && cur.right.right.size > cur.left.size) {
        //RR
        cur = leftRotate(cur);
        cur.left = maintain(cur.left);
        cur = maintain(cur);

      } else if (cur.right != null && cur.right.left != null && cur.left != null
          && cur.right.left.size > cur.left.size) {
        //RL
        cur.right = rightRotate(cur.right);
        cur = leftRotate(cur);
        cur.left = maintain(cur.left);
        cur.right = maintain(cur.right);
        cur = maintain(cur);
      }

      return cur;
    }

    private SBTNode add(SBTNode cur, long key, boolean contains) {
      if (cur == null) {
        return new SBTNode(key);
      }
      cur.all++;
      if (key == cur.key) {
        return cur;
      } else {
        if (!contains) {
          cur.size++;
        }
        if (key < cur.key) {
          cur.left = add(cur.left, key, contains);
        } else {
          cur.right = add(cur.right, key, contains);
        }
      }
      cur = maintain(cur);
      return cur;
    }

    public void add(long sum) {
      boolean contains = set.contains(sum);
      root = add(root, sum, contains);
      set.add(sum);
    }

    // 小于key这个值的有多少个(根据all信息累加得来)
    public long lessKeySize(long key) {
      SBTNode cur = root;
      long ans = 0;
      while (cur != null) {
        if (key == cur.key) {
          return ans += (cur.left != null ? cur.left.all : 0);
        } else if (key < cur.key) {
          cur = cur.left;
        } else {
          ans += cur.all - (cur.right != null ? cur.right.all : 0);
          cur = cur.right;
        }
      }
      return ans;
    }

  }

  public static int countRangeSum(int[] nums, int lower, int upper) {
    SizeBalanceTree sortedMap = new SizeBalanceTree();
    long sum = 0;
    int ans = 0;
    sortedMap.add(0); //一个数都没有时候, 前缀和是0
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];

      long a = sortedMap.lessKeySize(sum - lower + 1);
      long b = sortedMap.lessKeySize(sum - upper);
      ans += a - b;

      sortedMap.add(sum);
    }
    return ans;
  }


}
