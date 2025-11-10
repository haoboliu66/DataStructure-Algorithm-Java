package com.hliu.fundamental.linear.linkedlist;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

import com.hliu.fundamental.linear.linkedlist.entity.ListNode;

public class RemoveDuplicate2 {

    /*
    Input: 1->2->3->3->4->4->5
    Output: 1->2->5
     */

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
        ListNode next = null;
        while (head != null) {
            next = head.next;
            head.next = null;

            if (!map.containsKey(head.val)) {
                map.put(head.val, 1);
            } else {
                map.put(head.val, map.get(head.val) + 1);
            }
            head = next;
        }
        ListNode h = null;
        Set<Integer> keys = map.keySet();
        for (Iterator<Integer> iterator = keys.iterator();
             iterator.hasNext(); ) {
            Integer key = iterator.next();
            if (map.get(key) > 1) {
                iterator.remove();
            } else {
                if (h == null) {
                    h = new ListNode(key);
                } else {
                    ListNode n = new ListNode(key);
                    h.next = n;
                    h = n;
                }
            }
        }

        return h;
    }
}
