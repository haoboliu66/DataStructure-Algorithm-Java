package fundamental.linear.linkedlist;

import org.junit.Test;

import java.util.HashMap;

public class CloneSpecialList {

    /**
     * clone特殊单链表结构
     * clone special linked list with nodes(class SpecialNode) having extra random pointer
     */
    public static Node cloneSpecialNodesWithHashMap(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        HashMap<Node, Node> map = new HashMap<>();
        /* Map:  SBTNode ---> cloned SBTNode  */
        Node cur = head;
        while (cur != null) {
            Node clonedNode = new Node(cur.value);
            map.put(cur, clonedNode);
            cur = cur.next;
        }
        cur = head;
        Node res = map.get(cur);
        while (cur != null) {
            map.get(cur).next = map.get(cur.next); // set next pointer
            map.get(cur).rand = map.get(cur.rand); // set rand pointer
            cur = cur.next;
        }
        return res;
    }


    public static Node cloneSpecialNodes(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node cur = head;
        while (cur != null) {
            Node clone = new Node(cur.value);
            /*
            insert cloned node between cur and cur.next
            1 ---> 1' ---> 2 ---> 2' ---> 3 ---> 3' ---> null
             */
            clone.next = cur.next;
            cur.next = clone;
            cur = cur.next.next;
        }

        // set rand pointer
        cur = head;
        while (cur != null) {
            cur.next.rand = cur.rand.next;
            cur = cur.next.next;
        }
        //split original nodes with cloned nodes
        Node clonedHead = head.next;
        cur = head;
//        SinglyLinkedList.traversal(head);
        Node cp = clonedHead;
        while (cp.next != null) {
            cur.next = cp.next;
            cp.next = cur.next.next;
            cur = cur.next;
            cp = cur.next;
        }
        cur.next = null;

        return clonedHead;
    }

    @Test
    public void testClone() {
        SinglyLinkedList list = new SinglyLinkedList();
        Node node1 = new Node(4);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(6);
        node1.rand = node3;
        node2.rand = node4;
        node3.rand = node1;
        node4.rand = node2;
        list.insert(node1);
        list.insert(node2);
        list.insert(node3);
        list.insert(node4);
        Node node = cloneSpecialNodesWithHashMap(list.getHead());
        Node nodewithout = cloneSpecialNodes(list.getHead());
        SinglyLinkedList.traversal(node);
        System.out.println("--------------");
        SinglyLinkedList.traversal(nodewithout);
    }
}
