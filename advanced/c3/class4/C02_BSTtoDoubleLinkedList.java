package advanced.c3.class4;

import java.util.LinkedList;
import java.util.Queue;

public class C02_BSTtoDoubleLinkedList {

    /*
    426. Convert Binary Search Tree to Sorted Doubly Linked List
     */

    public static class Node {
        int val;
        Node left;
        Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    //方法一: 递归套路
    public static Node BSTToLinkedList1(Node head) {
        if (head == null) {
            return null;
        }
        //返回头结点
        return process(head).start;
    }

    // 以x为头的整棵搜索二叉树，请全部以有序双向链表的方式，连好
    // 并且返回，整个有序双向链表的头节点和尾节点
    public static Info process(Node X) {
        if (X == null) {
            return null;
        }
        Info leftInfo = process(X.left);
        Info rightInfo = process(X.right);
        Node start;
        Node end;
        if (leftInfo != null) {
            leftInfo.end.right = X;
            X.left = leftInfo.end;
            start = leftInfo.start;
        } else {
            start = X;
        }

        if (rightInfo != null) {
            rightInfo.start.left = X;
            X.right = rightInfo.start;
            end = rightInfo.end;
        } else {
            end = X;
        }

        return new Info(start, end);
    }

    // 整棵树，串成双向链表，返回头、尾
    public static class Info {
        public Node start;
        public Node end;

        public Info(Node start, Node end) {
            this.start = start;
            this.end = end;
        }
    }

    /*
    方法二: 中序遍历Queue收集, 取出依次连接  => leetcode跑不过
     */
    public static Node BSTToLinkedList2(Node head) {
        Queue<Node> queue = new LinkedList<>();
        inOrder(head, queue);
        if (queue.isEmpty()) {
            return head;
        }
        head = queue.poll();
        Node pre = head;
        pre.left = null;
        Node cur = null;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            pre.right = cur;
            cur.left = pre;
            pre = cur;
        }
        pre.right = null;

        return head;
    }

    public static void inOrder(Node node, Queue<Node> queue) {
        if (node == null) {
            return;
        }
        inOrder(node.left, queue);
        queue.offer(node);
        inOrder(node.right, queue);
    }


}
