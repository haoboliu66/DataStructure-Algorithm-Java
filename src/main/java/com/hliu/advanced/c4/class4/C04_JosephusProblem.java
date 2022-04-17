package src.main.java.advanced.c4.class4;

public class C04_JosephusProblem {

    private static class Node {
        int val;
        Node next;

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


    public static Node josephusKill(Node head, int m) {
        if (head == null || head.next == null || m < 1) {
            return head;
        }
        Node cur = head.next;
        int size = 1;
        while (cur != head) {
            size++;
            cur = cur.next;
        }
        int live = getLive(size, m);
        while (--live != 0) {
            head = head.next;
        }
        return head;
    }


    /*
    有size个节点, 数到count就杀掉, 返回活下来的节点 在有size个节点时编号
     */
    // 旧 = (新 + m-1) % i + 1
    public static int getLive(int size, int count) {
        if (size == 1) {
            return 1;
        }
        //  f(10, 8) => f(9,8) => f(8,8) ..... => f(1, 8)
        // 杀前 = (杀后 + m - 1) % i + 1
        return (getLive(size - 1, count) + count - 1) % size + 1;
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n1;

        Node node = josephusKill(n1, 8);
        System.out.println(node);
    }
}
