package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class Problem_0426_ConvertBinarySearchTreeToSortedDoublyLinkedList {

    private static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    '}';
        }
    }

    /*
    Morris
     */
    public static Node treeToDoublyListMorris(Node root) {
        Node cur = root;
        Node mostRight = null;
        Node pre = null;
        Node head = null;
        while (cur != null) {
            if (cur.left != null) {
                mostRight = cur.left;
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                    pre = mostRight;
                }
            }
            if (pre != null) {
                pre.right = cur;
                cur.left = pre;
                pre = cur;
            } else {
                pre = cur;
                head = cur;
            }
            cur = cur.right;
        }
        // connect head and last node
        // need to consider pre == null when loop is done (no left subtree)
        head.left = pre;
        if(pre == null){
            pre = cur;
            pre.right = cur;
            cur.left = pre;
        }
        pre.right = head;
        return head;
    }

    @Test
    public void testMorris(){
        Node root = new Node(2);
        Node n3 = new Node(3);
        root.right = n3;
        Node head = treeToDoublyListMorris(root);
        System.out.println(head);
    }

    /*
    Recursive Solution
     */
    public static class Info {
        Node start;
        Node end;

        public Info(Node start, Node end) {
            this.start = start;
            this.end = end;
        }
    }

    public static Node treeToDoublyListRecursive(Node root) {
        if (root == null) return null;
        Info info = process(root);
        Node head = info.start;
        Node tail = info.end;
        System.out.println("tail: "  + tail);
        head.left = tail;
        tail.right = head;
        return head;
    }

    public static Info process(Node X) {
        if (X == null) {
            return null;
        }
        Info leftInfo = process(X.left);
        Info rightInfo = process(X.right);
        Node start, end;
        if(leftInfo != null){
            leftInfo.end.right = X;
            X.left = leftInfo.end;
            start = leftInfo.start;
        }else{
            start = X;
        }
        if(rightInfo != null){
            rightInfo.start.left = X;
            X.right = rightInfo.start;
            end = rightInfo.end;
        }else{
            end = X;
        }
        return new Info(start, end);
    }

    @Test
    public void testRecursion(){
        Node root = new Node(2);
        Node n1  = new Node(1);
        root.left = n1;
        Node head = treeToDoublyListRecursive(root);

    }

    /*
    inOrder with Queue Solution
     */
    public static Node treeToDoublyListInOrder(Node root) {
        if (root == null) return null;
        Queue<Node> queue = new LinkedList<>();
        inOrder(root, queue);
        Node head = queue.poll();
        Node cur = head;
        while (!queue.isEmpty()) {
            Node tmp = queue.poll();
            cur.right = tmp;
            tmp.left = cur;
            cur = cur.right;
        }
        head.left = cur;
        cur.right = head;
        return head;
    }

    public static void inOrder(Node node, Queue<Node> queue) {
        if (node == null) return;
        inOrder(node.left, queue);
        queue.offer(node);
        inOrder(node.right, queue);
    }


    /* ---------------------------------------------------------------------- */

    // for test
    public static Node generateRandomBST(int level, int max) {
        if (level == 0) return null;
        int rand = (int) (Math.random() * max);
        Node root = new Node(rand);
        root.left = generateRandomBST(--level, max);
        root.right = generateRandomBST(level, max);
        return root;
    }

    // for test
    public static void main(String[] args) {
        Node n4 = new Node(4);
        Node n2 = new Node(2);
        Node n5 = new Node(5);
        Node n1 = new Node(1);
        Node n3 = new Node(3);
        n4.left = n2;
        n4.right = n5;
        n2.left = n1;
        n2.right = n3;
        Node node = treeToDoublyListInOrder(n4);
//        System.out.println(node);
        int level = 5, max = 1000;
        Node root = generateRandomBST(level, max);
        Queue<Integer> queue1 = new LinkedList<>();
        inOrder2(root, queue1);
        List<Integer> list = new ArrayList<>(queue1);
        List<Integer> filtered = list.stream().filter((a) -> a != null).collect(Collectors.toList());
        filtered.add(0, null);
        filtered.add(filtered.size(), null);
        queue1.retainAll(filtered);
        System.out.println("-----------------------");
        Node res = treeToDoublyListRecursive(root);
        Queue<Integer> queue2 = new LinkedList<>();
        printConverted(res, queue2);

        boolean isEqual = compareTwoQueue(new LinkedList<>(filtered), queue2);
        System.out.println(isEqual);
    }

    public static boolean compareTwoQueue(Queue<Integer> q1, Queue<Integer> q2) {
        System.out.println("q1: " + q1);
        System.out.println("q2: " + q2);
        if (q1 == q2) return true;
        if (q1 == null || q2 == null || q1.size() != q2.size()) return false;
        while (!q1.isEmpty()) {
            Integer i1 = q1.poll();
            Integer i2 = q2.poll();
            if (i1 == null) {
                if (i2 != null) return false;
                continue;
            }
            if (!i1.equals(i2)) return false;
        }
        return true;
    }

    public static void printConverted(Node head, Queue<Integer> queue) {
        queue.offer(null);
        Node cur = head;
        while (cur != null) {
//            System.out.println(cur);
            queue.offer(cur.val);
            cur = cur.right;
        }
        queue.offer(null);
    }


    public static void inOrder2(Node node, Queue<Integer> queue) {
        if (node == null) {
            queue.offer(null);
            return;
        }
        inOrder2(node.left, queue);
//        System.out.println(node);
        queue.offer(node.val);
        inOrder2(node.right, queue);
    }


}
