package advanced.c3.class1;


import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class C07_MaxSumInTree {

    /*
    LeetCode
    112. Path Sum
    113. Path Sum II
    129. Sum Root to Leaf Numbers
    437. Path Sum III
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
    public static int maxSum2(Node head) {
        if (head == null) {
            return 0;
        }

        return process2(head).max;
    }

    public static Info process2(Node X) {
        if (X == null) {
            return null;
        }
        int maxWithHead;
        int max = X.value;
        Info leftInfo = process2(X.left);
        Info rightInfo = process2(X.right);
        if (leftInfo != null) {

        }
        maxWithHead = X.value + Math.max(leftInfo.maxWithHead, rightInfo.maxWithHead);
        max = Math.max(leftInfo.max, rightInfo.max);

        return new Info(maxWithHead, max);
    }

    public static class Info {
        int maxWithHead;
        int max;

        public Info(int maxWithHead, int max) {
            this.maxWithHead = maxWithHead;
            this.max = max;
        }
    }

    /*
    Q3:任意节点出发, 到任意节点, 返回最大路径和
     */
    public static int maxSum3(Node head) {
        if (head == null) {
            return 0;
        }
        return process3(head).allTreeMaxSum;
    }

    private static Info3 process3(Node X) {
        if (X == null) {
            return null;
        }
        Info3 leftInfo = process3(X.left);
        Info3 rightInfo = process3(X.right);
        int p1 = Integer.MIN_VALUE;
        if (leftInfo != null) {
            p1 = leftInfo.allTreeMaxSum;
        }
        int p2 = Integer.MIN_VALUE;
        if (rightInfo != null) {
            p2 = rightInfo.allTreeMaxSum;
        }
        int p3 = X.value;
        int p4 = Integer.MIN_VALUE;
        if (leftInfo != null) {
            p4 = X.value + leftInfo.sumFromHead;
        }
        int p5 = Integer.MIN_VALUE;
        if (rightInfo != null) {
            p5 = X.value + rightInfo.sumFromHead;
        }
        int p6 = Integer.MIN_VALUE;
        if (leftInfo != null && rightInfo != null) {
            p6 = X.value + leftInfo.sumFromHead + rightInfo.sumFromHead;
        }

        int allTreeMaxSum = Math.max(p1, Math.max(p2, Math.max(p3, Math.max(p4, Math.max(p5, p6)))));
        int sumFromHead = Math.max(p3, Math.max(p4, p5)); // 不能算p6

        return new Info3(sumFromHead, allTreeMaxSum);
    }

    private static class Info3 {
        int sumFromHead;
        int allTreeMaxSum;

        public Info3(int sumFromHead, int allTreeMaxSum) {
            this.sumFromHead = sumFromHead;
            this.allTreeMaxSum = allTreeMaxSum;
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(3);
        System.out.println(list);
        List<Integer> list1 = new ArrayList<>(new LinkedHashSet<>(list));
        System.out.println(list1);
    }

}

