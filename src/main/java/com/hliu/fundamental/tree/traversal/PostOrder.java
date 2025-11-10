package com.hliu.fundamental.tree.traversal;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

import com.hliu.fundamental.tree.TreeNode;

public class PostOrder {

    public static void postOrderRecur(TreeNode node) {
        if (node == null) {
            return;
        }
        postOrderRecur(node.left);
        postOrderRecur(node.right);
        System.out.print(node + " ");
    }

    /* Iterative implementation of postOrder  */
    // N L R(preOrder) ---> N R L ---> L R N(postOrder)
    public static void postOrderIter(TreeNode head) {
        if (head == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> help = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            help.push(cur);
            if (cur.left != null) {
                stack.push(cur.left);
            }
            if (cur.right != null) {
                stack.push(cur.right);
            }
        }
        while (!help.isEmpty()) {
            System.out.print(help.pop() + " ");
        }
        System.out.println();
    }

    public static void postOrderIter2(TreeNode h) {
        if (h == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(h);
        TreeNode c = null;
        while (!stack.isEmpty()) {
            c = stack.peek();
            if (c.left != null && h != c.left && h != c.right) {
                stack.push(c.left);
            } else if (c.right != null && h != c.right) {
                stack.push(c.right);
            } else {
                System.out.print(stack.pop() + " ");
                h = c;
            }
        }
        System.out.println();
    }

    public static void postOrderIter3(TreeNode head) {  // with LinkedList
        if (head == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        LinkedList<TreeNode> list = new LinkedList<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            list.addFirst(cur);
            if (cur.left != null) {
                stack.push(cur.left);
            }
            if (cur.right != null) {
                stack.push(cur.right);
            }
        }
        for(Iterator<TreeNode> iterator = list.iterator(); iterator.hasNext();){
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        TreeNode node1 = new TreeNode("A");
        TreeNode node2 = new TreeNode("B");
        TreeNode node3 = new TreeNode("C");
        TreeNode node4 = new TreeNode("D");
        TreeNode node5 = new TreeNode("E");
        TreeNode node6 = new TreeNode("F");
        TreeNode node7 = new TreeNode("G");
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        postOrderRecur(node1);
        System.out.println();
        postOrderIter(node1);
        postOrderIter2(node1);
        postOrderIter3(node1);
    }

}
