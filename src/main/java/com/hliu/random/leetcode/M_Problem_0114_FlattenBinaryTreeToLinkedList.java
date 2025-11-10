package com.hliu.random.leetcode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class M_Problem_0114_FlattenBinaryTreeToLinkedList {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    '}';
        }
    }

    // Brute force
    public static void flatten(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();

        process(root, queue);
//        System.out.println(queue);
        TreeNode cur = queue.poll();
        while (!queue.isEmpty()) {
            cur.left = null;
            cur.right = queue.poll();
            cur = cur.right;
        }
    }

    public static void process(TreeNode root, Queue<TreeNode> queue) {
        TreeNode cur;
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            cur = stack.pop();
            queue.add(cur);
            if (cur.right != null) {
                stack.add(cur.right);
            }
            if (cur.left != null) {
                stack.add(cur.left);
            }
        }
    }

    /*  O(1) Space   */
    // Morris Pre
    public static void flatten2(TreeNode root) {
        if (root == null) return;
        TreeNode cur = root;
        TreeNode mostRight;
        while (cur != null) {
            if (cur.left != null) {
                mostRight = cur.left;
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                mostRight.right = cur.right;
                cur.right = cur.left;
                cur.left = null;
            }
            cur = cur.right;
        }
    }

    // From Discuss   Morris-Like Solution
    public static void flatten4(TreeNode root) {
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left != null) {
                TreeNode last = cur.left;
                while (last.right != null) last = last.right;
                last.right = cur.right;
                cur.right = cur.left;
                cur.left = null;
            }
            cur = cur.right;
        }
    }

    // Iterative pre-order with stack
    public static void flatten3(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode cur;
        while (!stack.isEmpty()) {
            cur = stack.pop();
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
            if (!stack.isEmpty()) {
                cur.right = stack.peek();
            }
            cur.left = null;
        }
    }


    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        n1.left = n2;
        n2.left = n3;
        n2.right = n4;
        n1.right = n5;
        n5.right = n6;
        flatten2(n1);
    }

}
