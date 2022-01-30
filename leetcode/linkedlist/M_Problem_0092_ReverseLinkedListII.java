package leetcode.linkedlist;

public class M_Problem_0092_ReverseLinkedListII {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }
    }

    /*
    1.找到left和right所在的node
    2.使用pre和post分别抓取left的前一个和right的后一个, 并断连链表
    3.reverse从left到right的部分
    4.重新连接 => corner case: left可能是原链表的head节点, 这时pre == null,
        最后还是需要返回原链表的head
     */

    public static ListNode reverseBetween(ListNode head, int left, int right) {

        int diff = right - left;
        if (diff == 0) return head;

        ListNode res = head;
        ListNode pre = null;

        ListNode leftNode = head;
        while (--left != 0) {
            pre = leftNode;
            leftNode = leftNode.next;
        }

        ListNode rightNode = leftNode;
        while (diff != 0) {
            rightNode = rightNode.next;
            diff--;
        }

        ListNode post = rightNode.next;
        rightNode.next = null; // disconnect the rest part of the list

        ListNode[] reverse = reverse(leftNode); // reverse the list with null at the end

        if (pre != null) { // left is 1 => leftNode is head
            pre.next = reverse[0];
        } else {
            res = reverse[0];
        }
        reverse[1].next = post;

        return res;
    }

    public static ListNode[] reverse(ListNode leftNode) {
        ListNode cur = leftNode;
        ListNode pre = null;
        ListNode next;
        ListNode tail = leftNode;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return new ListNode[]{pre, tail};
    }
}
