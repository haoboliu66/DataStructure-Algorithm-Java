package advanced.c3.class1;

import java.util.TreeMap;

/**
 * @author andy-liu
 * @date 2020/6/25 - 9:41 PM
 */
public class C07_MaxSumInTree {

    /*
    LeetCode 112,113, 437
     */

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int val) {
            value = val;
        }
    }

    /*
    Q1: 路径是从头到叶, 返回最大路径和
     */
    public static int maxSum1(Node head) {
        if (head == null) {
            return 0;
        }
        return process1(head);
    }

    public static int process1(Node node) {
        if (node.left == null && node.right == null) {
            return node.value;
        }
        int next = Integer.MIN_VALUE;
        if (node.left != null) {
            next = process1(node.left);
        }
        if (node.right != null) {
            next = Math.max(next, process1(node.right));
        }

        return next + node.value;
    }


    /*
    Q2:任意节点出发, 只能向下走, 返回最大路径和
     */
//    public static int maxSum2(SBTNode head) {
//        if (head == null) {
//            return 0;
//        }
//
//
//    }

//    public static

    /*
    Q3:任意节点出发, 到任意节点, 返回最大路径和
     */

}

