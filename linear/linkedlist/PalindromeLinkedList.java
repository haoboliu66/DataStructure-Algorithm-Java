package linear.linkedlist;


import java.util.*;

/**
 * @author andy-liu
 * @date 2020/5/17 - 11:33 AM
 */
public class PalindromeLinkedList {

    public static boolean isPalindromeWithStack1(Node head) {
        // no node or only one node
        if (head == null || head.next == null) {
            return true;
        }
        // two nodes and equal value
        if (head.next.next == null && head.next.value == head.value) {
            return true;
        }

        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while (cur != null) {
            stack.add(cur);
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            if (cur.value != stack.pop().value) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }

    /**
     * stack only stores half of the linked list
     */
    public static boolean isPalindromeWithStack2(Node head) {
        // no node or only one node
        if (head == null || head.next == null) {
            return true;
        }
        // two nodes and not equal value
        if (head.next.next == null) {
            return head.next.value == head.value;
        }
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        Stack<Node> stack = new Stack<>();
        slow = slow.next;
        while (slow != null) {
            stack.add(slow);
            slow = slow.next;
        }
        slow = head;
        while (!stack.isEmpty()) {
            if (slow.value != stack.pop().value) {
                return false;
            }
            slow = slow.next;
        }
        return true;
    }

    /**
     * extra space complexity O(1)
     */
    public static boolean isPalindrome(Node head) {
        // no node or only one node
        if (head == null || head.next == null) {
            return true;
        }
        // two nodes and not equal value
        if (head.next.next == null) {
            return head.next.value == head.value;
        }
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        /* reverse the second half */
        Node pre = null;
        Node post = null;
        while (slow != null) {
            post = slow.next;
            slow.next = pre;
            pre = slow;
            slow = post;
        }
        Node help = pre;  // keep a record of the head of the second half
        Node cur = head;
        // check isPalindrome
        boolean res = true;
        while (cur != null && pre != null) {
            if (cur.value != pre.value) {
                res = false;
            }
            cur = cur.next;
            pre = pre.next;
        }

        /* restore the second half  */
        pre = null;
        post = null;
        while (help != null) {
            post = help.next;
            help.next = pre;
            pre = help;
            help = post;
        }
        cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = pre;

        return res;
    }

}
