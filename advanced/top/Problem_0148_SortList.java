package advanced.top;

public class Problem_0148_SortList {

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode sortList(ListNode head) {
        if (head == null) {
            return null;
        }
        int len = 1;
        ListNode cur = head;
        while (cur != null) {
            len++;
//            cur = cur.next;
        }


        return null;
    }


}
