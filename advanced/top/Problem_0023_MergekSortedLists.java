package advanced.top;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Problem_0023_MergekSortedLists {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(new NodeComparator());

        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                minHeap.add(lists[i]);
            }
        }

        //每条链表都是空,检验
        if (minHeap.isEmpty()) {
            return null;
        }

        ListNode head = minHeap.poll();
        ListNode pre = head;
        if (pre.next != null) {
            minHeap.add(pre.next);
        }
        ListNode cur;

        while (!minHeap.isEmpty()) {
            cur = minHeap.poll();
            pre.next = cur;
            pre = cur;
            if (cur.next != null) {
                minHeap.add(cur.next);
            }

        }

        return head;
    }


    public class NodeComparator implements Comparator<ListNode> {

        public int compare(ListNode n1, ListNode n2) {
            return n1.val - n2.val;
        }
    }

}
