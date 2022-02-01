package src.main.java.fundamental.linear.linkedlist;

import java.util.ArrayList;

public class GetToMid {


    /**
     * 1.return mid if odd length, return preMid if even length
     * 1.输入链表头结点, 奇数长度返回中点, 偶数长度返回上中点
     */
    public static Node midOrPreMidNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * implementation 1 with ArrayList<SBTNode>, return (size - 1) / 2
     */
    public static Node midOrPreMidWithArray(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        Node cur = head;
        ArrayList<Node> nodes = new ArrayList<>();
        while (cur != null) {
            nodes.add(cur);
            cur = cur.next;
        }
        return nodes.get((nodes.size() - 1) / 2);
    }

    /**
     * 2.return mid if odd length, return postMid if even length
     * 2.输入链表头结点, 奇数长度返回中点, 偶数长度返下中点
     */
    public static Node midOrPostMidNode(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node slow = head.next;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * implementation 2 with ArrayList<SBTNode>
     */
    public static Node midOrPostMidWithArray(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node cur = head;
        ArrayList<Node> nodes = new ArrayList<>();
        while (cur != null) {
            nodes.add(cur);
            cur = cur.next;
        }

        return nodes.get(nodes.size() / 2);
    }

    /**
     * 3.return the one before mid if odd length, return the one before preMid if even length
     * 3.输入链表头结点, 奇数长度返回中点前一个, 偶数长度返上中点前一个
     */
    public static Node preMidOrprePreMid(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            //如果只有2个Node, 上中点没有前一个Node, 所以是null
            return null;
        }
        Node slow = head;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * implementation 2 with ArrayList<SBTNode>
     */
    public static Node preMidOrprePreMidWithArray(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node cur = head;
        ArrayList<Node> nodes = new ArrayList<>();
        while (cur != null) {
            nodes.add(cur);
            cur = cur.next;
        }

        return nodes.get(nodes.size() / 2 - 1);
    }

    /**
     * 4.输入链表头结点, 奇数长度返回中点前一个, 偶数长度返下中点前一个
     * 4.return the one after mid if odd length, return the one before postMid if even length
     */
    public static Node postMidOrprePostMid(Node head) {
        if (head == null || head.next == null) {
            return null;
        }
        if (head.next.next == null) {
            return head;
        }
        Node slow = head;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

}
