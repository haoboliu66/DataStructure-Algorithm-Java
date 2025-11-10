package com.hliu.fundamental.linear.linkedlist;

import java.util.Comparator;

import com.hliu.fundamental.linear.linkedlist.entity.ListNode;

public class ListPartition {

    /**
     * partition linked list by a pivot num
     */
    /* netherlandFlag + swap + comparator   */
    public static ListNode linkedListPartitionWithArray(ListNode head, int num) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        int count = 0;
        // get the size of linkedlist
        while (cur != null) {
            count++;
            cur = cur.next;
        }
        ListNode[] nodes = new ListNode[count];
        // put all nodes into the array
        cur = head;
        int index = 0;
        while (cur != null) {
            ListNode dup = new ListNode(cur.val);
            nodes[index++] = dup;
            cur = cur.next;
        }

        //  netherlandsFlag partition
        netherlandsFlag(nodes, 0, nodes.length - 1, num);
        // link all the nodes together
        ListNode newHead = nodes[0];
        ListNode tail = nodes[0];
        for (int i = 1; i < nodes.length; i++) {
            tail.next = nodes[i];
            tail = tail.next;
        }

        return newHead;
    }

    /*  nodes array partition   */
    private static void netherlandsFlag(ListNode[] nodes, int left, int right, int num) {
        if (nodes == null || nodes.length < 2) {
            return;
        }
        NodeComparator comparator = new NodeComparator();
        ListNode pivot = new ListNode(num);
        int less = left - 1;
        int more = right + 1;
        int index = 0;
        while (index < more) {
            if (comparator.compare(nodes[index], pivot) == 0) {
                index++;
            } else if (comparator.compare(nodes[index], pivot) < 0) {
                swap(nodes, index++, ++less);
            } else {
                swap(nodes, index, --more);
            }
        }
    }

    private static void swap(ListNode[] nodes, int left, int right) {
        ListNode cp = nodes[left];
        nodes[left] = nodes[right];
        nodes[right] = cp;
    }

    private static class NodeComparator implements Comparator<ListNode> {
        @Override
        public int compare(ListNode o1, ListNode o2) {
            return o1.val - o2.val;
        }
    }

    /* implementation space complexity O(1)  */
    public static ListNode linkedListPartition(ListNode head, int num) {
        if (head == null || head.next == null) {
            return head;
        }
        /* need three extra linked lists  */
        ListNode sh = null; // smaller head
        ListNode st = null; // smaller tail
        ListNode eh = null; // equal head
        ListNode et = null; // equal tail
        ListNode lh = null; // larger head
        ListNode lt = null; // larger tail
        ListNode post = null;
        while (head != null) {
            post = head.next; // post always keep the SBTNode after the one being visited
            if (head.val < num) {
                head.next = null;
                if (sh != null) {
                    st.next = head;
                    st = head;
                } else {
                    sh = head;
                    st = head;
                }
            } else if (head.val == num) {
                head.next = null;
                if (eh != null) {
                    et.next = head;
                    et = head;
                } else {
                    eh = head;
                    et = head;
                }
            } else {
                head.next = null;
                if (lh != null) {
                    lt.next = head;
                    lt = head;
                } else {
                    lh = head;
                    lt = head;
                }
            }
            head = post;
        }
        // link three lists together
        ListNode resHead = sh != null ? sh : (eh != null ? eh : lh);
        if (st != null) {
            st.next = (et != null) ? eh : lh;
        }
        if (et != null) {
            et.next = lh;
        }

        return resHead;
    }
}
