package linear.linkedlist;

import org.junit.Test;

import java.util.*;

public class LinkedListQuestion {

    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();
        Node node1 = new Node(4);

        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(6);
        Node node5 = new Node(7);
        Node node6 = new Node(1);
        list.insert(node1);
        list.insert(node2);
        list.insert(node3);
        list.insert(node4);
        list.insert(node5);
        list.insert(node6);

    }

    /* Move to Mid or preMid, postMid ===> slow,fast pointer */

    /* - - - - - - - - - - - - - - - - -- - - - - - - -- - - - - - - -- - - - - - - -- - - - - - - - */

    /*  if a linked list is palindrome */

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

    /*  List partition */

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

    /* Clone special linkedList with random

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
/**
 * Big Boss
 * */

    /**
     * 1.return the node that forms a loop in a looped linkedlist
     * 1.返回第一个入环节点
     */
    /*  Find the node that forms a loop   */
    public static Node getLoopNodeUseSet(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        HashSet<Node> set = new HashSet<>();
        Node cur = head;
        while (cur != null) {
            if (set.contains(cur)) {
                return cur;
            }
            set.add(cur);
            cur = cur.next;
        }
        return cur;
    }

    /*  implementation with extra space complexity O(1)  */
    public static Node getLoopNode(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    @Test
    public void testLoopNode() {
        SinglyLinkedList list = new SinglyLinkedList();
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        node6.next = node3;

        list.insert(node1);
        list.insert(node2);
        list.insert(node3);
        list.insert(node4);
        list.insert(node5);
        list.insert(node6);

        Node loop1 = getLoopNodeUseSet(list.getHead());
        Node loop2 = getLoopNode(list.getHead());
        System.out.println(loop1);
        System.out.println(loop2);
        System.out.println(loop1 == loop2);
    }

    /**
     * 2.two non-looped linked list intersect, find the first intersected SBTNode
     * 2.两个无环单链表相交, 返回第一个相交节点
     */
    public static Node getFirstIntersection(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        if (head1 == head2) {
            return head1;
        }

        Node cur1 = head1;
        int len1 = 0;
        while (cur1.next != null) {
            len1++;
            cur1 = cur1.next;
        }
        Node cur2 = head2;
        int len2 = 0;
        while (cur2.next != null) {
            len2++;
            cur2 = cur2.next;
        }

        // no intersection if the their last nodes not equal
        if (cur1 != cur2) {
            return null;
        }
        // find the initial step the longer linked list need to go
        int initialStep = Math.abs(len1 - len2);
        System.out.println(initialStep);
        Node cur = (len1 > len2) ? head1 : head2;
        while (initialStep != 0) {
            cur = cur.next;
            initialStep--;
        }
        // continue traversal neck and neck
        Node temp = (len1 > len2) ? head2 : head1;
        while (cur != temp) {
            cur = cur.next;
            temp = temp.next;
        }
        return cur;
    }

    @Test
    public void testIntersection() {
        SinglyLinkedList list1 = new SinglyLinkedList();
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);
        list1.insert(node1);
        list1.insert(node2);
        list1.insert(node3);
        list1.insert(node4);
        list1.insert(node5);
        list1.insert(node6);
        list1.insert(node7);
        list1.insert(node8);
        list1.insert(node9);
        list1.traversal();
        System.out.println("*******");
        SinglyLinkedList list2 = new SinglyLinkedList();
        Node node10 = new Node(10);
        Node node11 = new Node(11);
        Node node12 = new Node(12);
        list2.insert(node10);
        list2.insert(node11);
        list2.insert(node12);
        list2.traversal();
//        list2.insert(node5);
//        list2.insert(node6);
//        list2.insert(node7);
//        list2.insert(node8);
//        list2.insert(node9);
        Node inter = getFirstIntersection(list1.getHead(), list2.getHead());
        System.out.println(inter);
    }

    public static Node getFirstIntersectionTwoLoops(Node head1, Node head2) {
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        // 一个有环, 一个无环, 肯定不相交
        if ((loop1 == null && loop2 != null) || (loop1 != null && loop2 == null)) {
            return null;
        }
        // 两个链表都无环
        if (loop1 == null && loop2 == null) {
            return getFirstIntersection(head1, head2);
        }
        // 两个有环链表
        //1. 两个有环链表无交点 或 入环节点不是一个
        if (loop1 != loop2) {
            Node cur = loop1.next;
            while (cur != loop1) {
                if (cur == loop2) {
                    break;
                }
                cur = cur.next;
            }
            if (cur != loop2) {
                return null;
            }
            return loop1;
        }
        //2.两个有环链表入环节点是一个, 所以相交节点可能在入环节点或入环节点之前
        return getFirstIntersection(head1, head2);
    }

    @Test
    public void testTwoLoops() {
        SinglyLinkedList list1 = new SinglyLinkedList();
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        list1.insert(node1);
        list1.insert(node2);
        list1.insert(node3);
        list1.insert(node4);
        list1.insert(node5);
        list1.insert(node6);
        list1.insert(node7);
        list1.insert(node8);
        node8.next = node4;
        System.out.println("*******");
        SinglyLinkedList list2 = new SinglyLinkedList();
        Node node10 = new Node(10);
        Node node11 = new Node(11);
        Node node12 = new Node(12);
        list2.insert(node10);
        list2.insert(node11);
        list2.insert(node12);
        list2.insert(node4);
        list2.insert(node5);
        list2.insert(node6);
        list2.insert(node7);
        list2.insert(node8);
        getFirstIntersectionTwoLoops(list1.getHead(), list2.getHead());
//        list2.insert(node5);
//        list2.insert(node6);
//        list2.insert(node7);
//        list2.insert(node8);
//        list2.insert(node9);
//        SBTNode inter = getFirstIntersection(list1, list2);
//        System.out.println(inter);
    }

}

