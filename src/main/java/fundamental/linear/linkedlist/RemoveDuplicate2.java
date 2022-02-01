package src.main.java.fundamental.linear.linkedlist;

import java.util.*;

public class RemoveDuplicate2 {

    /*
    Input: 1->2->3->3->4->4->5
    Output: 1->2->5
     */

    public static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    public Node deleteDuplicates(Node head) {

        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }

        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
        Node next = null;
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
        Node h = null;
        Set<Integer> keys = map.keySet();
        for (Iterator<Integer> iterator = keys.iterator();
             iterator.hasNext(); ) {
            Integer key = iterator.next();
            if (map.get(key) > 1) {
                iterator.remove();
            } else {
                if (h == null) {
                    h = new Node(key);
                } else {
                    Node n = new Node(key);
                    h.next = n;
                    h = n;
                }
            }
        }

        return h;
    }
}
