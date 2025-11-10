package com.hliu.random.leetcode.linkedlist;

public class M_Problem_0369_PlusOneLinkedList {

    /*
    Find the last node whose val != 9, (意味着这个node后面要么没东西了, 要么后面是9)

    这个node后面的节点全部置为0, node.val++;
     */
    public static ListNode plusOne2(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        ListNode lastNotNine = dummy;
        while (cur != null) {
            if (cur.val != 9) {
                lastNotNine = cur;
            }
            cur = cur.next;
        }
        lastNotNine.val++;
        cur = lastNotNine.next;
        while (cur != null) {
            cur.val = 0;
            cur = cur.next;
        }

        return lastNotNine == dummy ? dummy : dummy.next;
    }

    /*
    Reverse list and add one, reverse again
     */
    public static ListNode plusOne1(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode newHead = reverse(head);
        ListNode pre = null;
        ListNode cur = newHead;
        int carry = 1;

        while (cur != null) {
            pre = cur;
            if (cur.val != 9) {
                cur.val += carry;
                carry = 0;
            } else {
                if (carry == 1) {
                    cur.val = 0;
                    carry = 1;
                }
            }
            cur = cur.next;
        }

        if (carry == 1) {
            pre.next = new ListNode(1);
            pre = pre.next;
        }
        return reverse(newHead);
    }

    public static ListNode reverse(ListNode head) {
        ListNode next = null;
        ListNode pre = null;
        ListNode cur = head;

        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    // test
    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        n1.next = n2;
        n2.next = n3;
        plusOne1(n1);
    }


    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    '}';
        }
    }

    public static void printList(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            if (cur.next != null) {
                System.out.print("node:" + cur.val + " -> ");
            } else {
                System.out.print("node:" + cur.val);
            }

            cur = cur.next;
        }
        System.out.println();
    }


}
