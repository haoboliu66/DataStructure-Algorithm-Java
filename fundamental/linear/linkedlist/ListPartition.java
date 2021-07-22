package fundamental.linear.linkedlist;

import org.junit.Test;

import java.util.Comparator;

public class ListPartition {

    /**
     * partition linked list by a pivot num
     */

    /* netherlandFlag + swap + comparator   */
    public static Node linkedListPartitionWithArray(Node head, int num) {
        if (head == null || head.next == null) {
            return head;
        }
        Node cur = head;
        int count = 0;
        // get the size of linkedlist
        while (cur != null) {
            count++;
            cur = cur.next;
        }
        Node[] nodes = new Node[count];
        // put all nodes into the array
        cur = head;
        int index = 0;
        while (cur != null) {
            Node dup = new Node(cur.value);
            nodes[index++] = dup;
            cur = cur.next;
        }

        //  netherlandsFlag partition
        netherlandsFlag(nodes, 0, nodes.length - 1, num);
        // link all the nodes together
        Node newHead = nodes[0];
        Node tail = nodes[0];
        for (int i = 1; i < nodes.length; i++) {
            tail.next = nodes[i];
            tail = tail.next;
        }

        return newHead;
    }

    /*  nodes array partition   */
    private static void netherlandsFlag(Node[] nodes, int left, int right, int num) {
        if (nodes == null || nodes.length < 2) {
            return;
        }
        NodeComparator comparator = new NodeComparator();
        Node pivot = new Node(num);
        int less = left - 1;
        int more = right + 1;
        int index = 0;
        while (index < more) {
            if (comparator.compare(nodes[index], pivot) == 0) {
                index++;
            } else if (comparator.compare(nodes[index], pivot) < 0) {
                swap(nodes, index++, ++less);
            } else {
                swap(nodes, index, --more);
            }
        }
    }

    private static void swap(Node[] nodes, int left, int right) {
        Node cp = nodes[left];
        nodes[left] = nodes[right];
        nodes[right] = cp;
    }

    private static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.value - o2.value;
        }
    }

    /* implementation with extra space complexity O(1)  */
    public static Node linkedListPartition(Node head, int num) {
        if (head == null || head.next == null) {
            return head;
        }
        /* need three extra linked lists  */
        Node sh = null; // smaller head
        Node st = null; // smaller tail
        Node eh = null; // equal head
        Node et = null; // equal tail
        Node lh = null; // larger head
        Node lt = null; // larger tail
        Node post = null;
        while (head != null) {
            post = head.next; // post always keep the SBTNode after the one being visited
            if (head.value < num) {
                head.next = null;
                if (sh != null) {
                    st.next = head;
                    st = head;
                } else {
                    sh = head;
                    st = head;
                }
            } else if (head.value == num) {
                head.next = null;
                if (eh != null) {
                    et.next = head;
                    et = head;
                } else {
                    eh = head;
                    et = head;
                }
            } else {
                head.next = null;
                if (lh != null) {
                    lt.next = head;
                    lt = head;
                } else {
                    lh = head;
                    lt = head;
                }
            }
            head = post;
        }
        // link three lists together
        Node resHead = sh != null ? sh : (eh != null ? eh : lh);
        if (st != null) {
            st.next = (et != null) ? eh : lh;
        }
        if (et != null) {
            et.next = lh;
        }

        return resHead;
    }


    @Test
    public void testPartition() {
        SinglyLinkedList list = new SinglyLinkedList();
        Node node1 = new Node(4);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(6);
        Node node5 = new Node(7);
        Node node6 = new Node(1);
        Node node7 = new Node(4);
        list.insert(node1);
        list.insert(node2);
        list.insert(node3);
        list.insert(node4);
        list.insert(node5);
        list.insert(node6);
        list.insert(node7);
        Node head = linkedListPartitionWithArray(list.getHead(), 4);
        SinglyLinkedList.traversal(head);
    }
}
