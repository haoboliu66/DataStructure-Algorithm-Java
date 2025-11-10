package com.hliu.fundamental.tree.traversal;

import java.util.Stack;

import com.hliu.fundamental.tree.TreeNode;

public class InOrder {

    public static void inOrderRecur(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrderRecur(node.left);
        System.out.println(node);
        inOrderRecur(node.right);
    }

    /* Iterative implementation of inOrder  */
    public static void inOrderIter(TreeNode head) {
        if (head == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;   //左树没走到头, 就一直进栈
            } else {  //左树走到头了
                head = stack.pop(); //左树走到头后, 将head移到父节点
                System.out.println(head);   //左树走到头, 回来应该打印父节点
                head = head.right;  //再走右树
            }
        }
    }

    public static void inOrderIter2(TreeNode head) {
        if (head == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || head != null) {
            while (head != null) {
                stack.push(head);
                head = head.left;
            }
            head = stack.pop();
            System.out.println(head);
            head = head.right;
        }
    }

}
