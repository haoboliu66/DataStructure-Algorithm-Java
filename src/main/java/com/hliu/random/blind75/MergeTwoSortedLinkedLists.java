package com.hliu.random.blind75;

import com.hliu.random.blind75.entity.ListNode;

public class MergeTwoSortedLinkedLists {

  public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
    if (list1 == null) {
      return list2;
    }
    if (list2 == null) {
      return list1;
    }
    ListNode head1 = list1;
    ListNode head2 = list2;
    ListNode curList1 = head1;
    ListNode curList2 = head2;
    ListNode cur = null;
    boolean init = false;
    while (curList1 != null && curList2 != null) {
      if (cur == null && !init) {
        if (curList1.val < curList2.val) {
          cur = curList1;
          curList1 = curList1.next;
        } else {
          cur = curList2;
          curList2 = curList2.next;
        }
        init = true;
      }
      if(curList2 == null || curList1 == null) break;

      if (curList1.val < curList2.val) {
        cur.next = curList1;
        curList1 = curList1.next;
        cur = cur.next;
      } else {
        cur.next = curList2;
        curList2 = curList2.next;
        cur = cur.next;
      }
    }

    while(curList1 != null) {
      cur.next = curList1;
      curList1 = curList1.next;
      cur = cur.next;
    }
    while(curList2 != null) {
      cur.next = curList2;
      curList2 = curList2.next;
      cur = cur.next;
    }

    return head1.val < head2.val ? head1 : head2;
  }

}
