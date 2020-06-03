package linear.linkedlist;

/**
 * @author andy-liu
 * @date 2020/5/18 - 8:38 PM
 */
public class DoublyLinkedList {

    private Node head;
    private Node tail;
    private int size;

    public DoublyLinkedList() {

    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void add(int val) {
        Node node = new Node(val);
        add(node);
    }

    public void add(Node node) {
        if (node == null) {
            throw new RuntimeException("Invalid");
        }
        if (isEmpty()) {
            head = node;
            tail = node;
            size++;
            return;
        }
        linkLast(node);
        size++;
    }

    private void linkLast(Node node) {
        node.prev = tail;
        tail.next = node;
        tail = node;
    }

    public void addFirst(int val) {
        Node node = new Node(val);
        addFirst(node);
    }

    public void addFirst(Node node) {
        if (node == null) {
            throw new RuntimeException("Invalid");
        }
        if (isEmpty()) {
            head = node;
            tail = node;
            size++;
            return;
        }
        linkFirst(node);
        size++;
    }

    private void linkFirst(Node node) {
        node.next = head;
        head.prev = node;
        head = node;
    }


    private class Node {
        int value;
        Node prev;
        Node next;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }
}

