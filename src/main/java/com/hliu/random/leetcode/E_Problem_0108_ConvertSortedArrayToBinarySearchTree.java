package com.hliu.random.leetcode;

public class E_Problem_0108_ConvertSortedArrayToBinarySearchTree {

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
    /*
    Recursive Solution
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        int n = nums.length;
        TreeNode root = process(nums, 0, n - 1);
        return root;
    }

    // 把arr数组left到right的数组成bst,返回头部
    public TreeNode process(int[] arr, int left, int right) {
        if (right < left) {
            return null;
        }
        if (left == right) {
            return new TreeNode(arr[left]);
        }
        int mid = (right + left) / 2;
        TreeNode subRoot = new TreeNode(arr[mid]);
        subRoot.left = process(arr, left, mid - 1);
        subRoot.right = process(arr, mid + 1, right);
        return subRoot;
    }
}
