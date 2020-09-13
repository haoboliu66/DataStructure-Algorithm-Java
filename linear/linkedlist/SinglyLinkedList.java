package linear.linkedlist;

/**
 * @author andy-liu
 * @date 2020/5/11 - 5:43 PM
 */
public class SinglyLinkedList {

    private int size;
    private Node head;
    private Node tail;

    public SinglyLinkedList() {
    }

    public SinglyLinkedList(Node head) {
        this.head = head;
        this.tail = head;
        size = 0;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public void insert(int value) {
        Node node = new Node(value);
        if (isEmpty()) {
            this.head = node;
            this.tail = node;
            size++;
            return;
        }
        tail.next = node;
        tail = node;
        size++;
    }

    public void insert(Node node) {
        if (node == null) {
            return;
        }
        if (isEmpty()) {
            this.head = node;
            this.tail = node;
            size++;
            return;
        }
        tail.next = node;
        tail = node;
        size++;
    }

    public Node search(int value) {
        Node cur = head;
        while (cur != null) {
            if (cur.value == value) {
                return cur;
            }
            cur = cur.next;
        }
        return cur;
    }

    public void traversal() {
        Node cur = head;
        while (cur != null) {
            System.out.println(cur);
            cur = cur.next;
        }
    }

    public static void traversal(Node head) {
        Node cur = head;
        while (cur != null) {
            System.out.println(cur);
            cur = cur.next;
        }
    }

    /**
     * reverse a list, return new head
     */
    public Node reverse() {
        if (this.head == null || this.head.next == null) {
            return head;
        }
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }


    public static Node reverse(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getTail() {
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }

}

class Node {
    public Integer value;
    public Node next;
    public Node rand;

    public Node() {
    }

    public Node(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SBTNode{" +
                "value=" + value +
                '}';
    }

}