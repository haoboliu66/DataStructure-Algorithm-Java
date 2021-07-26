package advanced.top;

public class Problem_0019_RemoveNthNodeFromEndOfList {

    public static void main(String[] args) {
    }

    private static class ListNode {
        int val;
        ListNode next;

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }
    }

    /* Two Pass */
    public ListNode removeNthFromEnd2(ListNode head, int n) {

        if (head == null) return null;
        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            cur = cur.next;
            len++;
        }
        if(len == n) return head.next;
        cur = head;
        n = len - n - 1;
        while (n != 0) {
            cur = cur.next;
            n--;
        }
        cur.next = cur.next.next;
        return head;
    }

    /* One Pass */
    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode cur = head;
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode pre = dummy;
        int j = n - 1;
        while(n != 0){
            cur = cur.next;
            n--;
        }
        if(cur == null) return head.next;

        while(cur != null){
            cur = cur.next;
            pre = pre.next;
        }

        pre.next = pre.next.next;
        return head;

    }
}
