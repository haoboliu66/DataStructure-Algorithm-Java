package advanced.c1.morris;

public class MinHeight {

    public static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node(int x) {
            val = x;
        }
    }

    /*
    递归套路
     */
    public int minHeight1(Node head) {
        if (head == null) {
            return 0;
        }
        return process(head);
    }

    private int process(Node node) {
        if (node.left == null && node.right == null) {
            return 1;
        }
        int left = Integer.MAX_VALUE;
        if (node.left != null) {
            left = process(node.left);
        }

        int right = Integer.MAX_VALUE;
        if (node.right != null) {
            right = process(node.right);
        }

        return Math.min(left, right) + 1;
    }


    public static int minHeight2(Node head) {
        if (head == null) {
            return 0;
        }
        Node cur = head;
        Node mostRight;
        int curLevel = 0;
        int minHeight = Integer.MAX_VALUE;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                int rightEdgeSize = 1;
                while (mostRight.right != null && mostRight.right != cur) {
                    rightEdgeSize++;
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    curLevel++;
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    if (mostRight.left == null) {
                        minHeight = Math.min(minHeight, curLevel);
                    }
                    curLevel -= rightEdgeSize;
                    mostRight.right = null;
                }
            } else {
                curLevel++;
            }
            cur = cur.right;
        }
        int finalRight = 1;  // 检查头结点的整条右边界的高度
        cur = head;
        while (cur.right != null) {
            finalRight++;
            cur = cur.right;
        }
        if (cur.left == null) {  //头结点的整条右边界的最后一个节点如果是叶子节点, 更新minHeight
            minHeight = Math.min(minHeight, finalRight);
        }

        return minHeight;
    }


}
