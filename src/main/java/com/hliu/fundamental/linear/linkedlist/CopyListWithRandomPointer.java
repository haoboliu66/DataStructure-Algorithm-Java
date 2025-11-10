package com.hliu.fundamental.linear.linkedlist;

import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/copy-list-with-random-pointer/
 */
public class CopyListWithRandomPointer {

    /**
     * clone特殊单链表结构
     * clone special linked list with nodes(class SpecialNode) having extra random pointer
     */
    public static ListNode copyRandomListWithHashMap(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        Map<ListNode, ListNode> map = new HashMap<>();
        /* Map:  SBTNode ---> cloned SBTNode  */
        ListNode cur = head;
        while (cur != null) {
            ListNode clonedNode = new ListNode(cur.val);
            map.put(cur, clonedNode);
            cur = cur.next;
        }
        cur = head;
        ListNode res = map.get(cur);
        while (cur != null) {
            map.get(cur).next = map.get(cur.next); // set next pointer
            map.get(cur).random = map.get(cur.random); // set rand pointer
            cur = cur.next;
        }
        return res;
    }


    public static ListNode copyRandomList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        while (cur != null) {
            ListNode clone = new ListNode(cur.val);
            /*
            insert cloned node between cur and cur.next
            1 ---> 1' ---> 2 ---> 2' ---> 3 ---> 3' ---> null
             */
            clone.next = cur.next;
            cur.next = clone;
            cur = cur.next.next;
        }

        // set rand pointer
        cur = head;
        while (cur != null) {
            cur.next.random = cur.random.next;
            cur = cur.next.next;
        }
        //split original nodes with cloned nodes
        ListNode clonedHead = head.next;
        cur = head;
//        SinglyLinkedList.traversal(head);
        ListNode cp = clonedHead;
        while (cp.next != null) {
            cur.next = cp.next;
            cp.next = cur.next.next;
            cur = cur.next;
            cp = cur.next;
        }
        cur.next = null;

        return clonedHead;
    }

    private static class ListNode {
        public int val;
        public ListNode next;
        public ListNode random;

        public ListNode(int value) {
            this.val = value;
        }

        @Override
        public String toString() {
            return "SBTNode{" +
                "value=" + val +
                '}';
        }
    }
}
