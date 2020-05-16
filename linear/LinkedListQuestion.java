package linear;

import org.junit.Test;

import java.util.*;

/**
 * @author andy-liu
 * @date 2020/5/12 - 10:24 PM
 */
public class LinkedListQuestion {

    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();
        Node node1 = new Node(4);

        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(6);
        Node node5 = new Node(7);
        Node node6 = new Node(1);
        list.insert(node1);
        list.insert(node2);
        list.insert(node3);
        list.insert(node4);
        list.insert(node5);
        list.insert(node6);
//        Node head = linkedListPartition(list, 4);
////        System.out.println(head);
//        SinglyLinkedList.traversal(head);
        Node node = cloneSpecialNodesWithHashMap(list);

    }

    /**
     * 1.return mid if odd length, return preMid if even length
     * 1.输入链表头结点, 奇数长度返回中点, 偶数长度返回上中点
     */
    public static Node midOrPreMid(SinglyLinkedList list){
        Node head = list.getHead();
        if(head == null || head.next == null || head.next.next == null){
            return head;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while(fast.next != null && fast.next.next !=null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * implementation 1 with ArrayList<Node>, return (size - 1) / 2
     */
    public static Node midOrPreMidWithArray(SinglyLinkedList list){
        Node head = list.getHead();
        if(head == null || head.next == null || head.next.next == null){
            return head;
        }
        Node cur = head;
        ArrayList<Node> nodes = new ArrayList<>();
        while(cur != null){
            nodes.add(cur);
            cur = cur.next;
        }
        return nodes.get((nodes.size() - 1)/ 2);
    }

    /**
     * 2.return mid if odd length, return postMid if even length
     * 2.输入链表头结点, 奇数长度返回中点, 偶数长度返下中点
     */
    public static Node midOrPostMid(SinglyLinkedList list){
        Node head = list.getHead();
        if(head == null || head.next == null || head.next.next == null){
            return head;
        }
        Node slow = head.next;
        Node fast = head.next;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * implementation 2 with ArrayList<Node>
     */
    public static Node midOrPostMidWithArray(SinglyLinkedList list){
        Node head = list.getHead();
        if(head == null || head.next == null || head.next.next == null){
            return head;
        }
        Node cur = head;
        ArrayList<Node> nodes = new ArrayList<>();
        while(cur != null){
            nodes.add(cur);
            cur = cur.next;
        }

        return nodes.get(nodes.size() / 2);
    }

    /**
     * 3.return the one before mid if odd length, return the one before preMid if even length
     * 3.输入链表头结点, 奇数长度返回中点前一个, 偶数长度返上中点前一个
     */
    public static Node preMidOrprePreMid(SinglyLinkedList list){
        Node head = list.getHead();
        if(head == null || head.next == null || head.next.next == null){
            return head;
        }
        Node slow = head;
        Node fast = head.next.next;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**  implementation 2 with ArrayList<Node>  */
    public static Node preMidOrprePreMidWithArray(SinglyLinkedList list){
        Node head = list.getHead();
        if(head == null || head.next == null || head.next.next == null){
            return head;
        }
        Node cur = head;
        ArrayList<Node> nodes = new ArrayList<>();
        while(cur != null){
            nodes.add(cur);
            cur = cur.next;
        }

        return nodes.get(nodes.size() / 2 - 1);
    }

    /**
     * 4.输入链表头结点, 奇数长度返回中点下一个, 偶数长度返下中点前一个
     * 4.return the one after mid if odd length, return the one before postMid if even length
     */
    public static Node postMidOrprePostMid(SinglyLinkedList list){
        Node head = list.getHead();
        if(head == null || head.next == null || head.next.next == null){
            return head;
        }
        Node slow =  null;


        return slow;
    }

/* - - - - - - - - - - - - - - - - -- - - - - - - -- - - - - - - -- - - - - - - -- - - - - - - - */

    public static boolean isPalindromeWithStack1(SinglyLinkedList list){
        if(list == null){
            throw new RuntimeException("Invalid Parameter");
        }
        Node head = list.getHead();
        // no node or only one node
        if(head == null || head.next == null){
            return true;
        }
        // two nodes and equal value
        if(head.next.next == null && head.next.value == head.value){
            return true;
        }

        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while(cur != null){
            stack.add(cur);
            cur = cur.next;
        }
        cur = head;
        while(cur != null){
            if(cur.value != stack.pop().value){
                return false;
            }
            cur = cur.next;
        }
        return true;
    }

    /**
     * stack only stores half of the linked list
     */
    public static boolean isPalindromeWithStack2(SinglyLinkedList list){
        if(list == null){
            throw new RuntimeException("Invalid Parameter");
        }
        Node head = list.getHead();
        // no node or only one node
        if(head == null || head.next == null){
            return true;
        }
        // two nodes and not equal value
        if(head.next.next == null && head.next.value == head.value){
            return true;
        }
        Node slow = head;
        Node fast = head;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }

        Stack<Node> stack = new Stack<>();
        slow = slow.next;
        while(slow != null){
            stack.add(slow);
            slow = slow.next;
        }
        slow = head;
        while(!stack.isEmpty()){
            if(slow.value != stack.pop().value){
                return false;
            }
            slow = slow.next;
        }
        return true;
    }

    /**
     *
     * extra space complexity O(1)
     */
    public static boolean isPalindrome(SinglyLinkedList list){
        if(list == null){
            throw new RuntimeException("Invalid Parameter");
        }
        Node head = list.getHead();
        // no node or only one node
        if(head == null || head.next == null){
            return true;
        }
        // two nodes and not equal value
        if(head.next.next == null && head.next.value == head.value){
            return true;
        }
        Node slow = head;
        Node fast = head;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }

        /* reverse the second half */
        Node pre = null;
        Node post = null;
        while(slow != null){
            post = slow.next;
            slow.next = pre;
            pre = slow;
            slow = post;
        }
        Node help = pre;  // keep a record of the head of the second half
        Node cur = head;
        // check isPalindrome
        while(cur != null && pre != null){
            if(cur.value != pre.value){
                return false;
            }
            cur = cur.next;
            pre = pre.next;
        }

        /* restore the second half  */
        pre = null;
        post = null;
        while(help != null){
            post = help.next;
            help.next = pre;
            pre = help;
            help = post;
        }
        cur = head;
        while(cur.next != null){
            cur = cur.next;
        }
        cur.next = pre;

        return true;
    }


    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

    /**
     * partition linked list by a pivot num
     */

    /* netherlandFlag + swap + comparator   */
    public static Node linkedListPartitionWithArray(SinglyLinkedList list, int num){
        if(list == null){
            throw new RuntimeException("Invalid Parameter");
        }
        Node head = list.getHead();
        if(head == null || head.next == null){
            return head;
        }
        Node cur = head;
        int count = 0;
        // get the size of linkedlist
        while(cur != null){
            count++;
            cur = cur.next;
        }
        Node[] nodes = new Node[count];
        // put all nodes into the array
        cur = head;
        int index = 0;
        while(cur != null){
            Node dup = new Node(cur.value);
            nodes[index++] = dup;
            cur = cur.next;
        }

        //  netherlandsFlag partition
        netherlandsFlag(nodes, 0, nodes.length - 1, num);
        // link all the nodes together
        Node newHead = nodes[0];
        Node tail = nodes[0];
        for(int i = 1; i < nodes.length; i++){
            tail.next = nodes[i];
            tail = tail.next;
        }

        return newHead;
    }

    /*  nodes array partition   */
    private static void netherlandsFlag(Node[] nodes, int left, int right, int num) {
        if(nodes == null || nodes.length < 2){
            return;
        }
        NodeComparator comparator = new NodeComparator();
        Node pivot = new Node(num);
        int less = left - 1;
        int more = right + 1;
        int index = 0;
        while(index < more){
            if(comparator.compare(nodes[index], pivot) == 0){
                index++;
            }
            else if(comparator.compare(nodes[index], pivot) < 0){
                swap(nodes,index++, ++less);
            }else {
                swap(nodes, index, --more);
            }
        }
    }

    private static void swap(Node[] nodes, int left, int right){
        Node cp = nodes[left];
        nodes[left] = nodes[right];
        nodes[right] = cp;
    }

    private static class NodeComparator implements Comparator<Node>{

        @Override
        public int compare(Node o1, Node o2) {
            return o1.value - o2.value;
        }
    }

    /* implementation with extra space complexity O(1)  */
    public static Node linkedListPartition(SinglyLinkedList list, int num){
        if(list == null){
            throw new RuntimeException("Invalid Parameter");
        }
        Node head = list.getHead();
        if(head == null || head.next == null){
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
        while(head != null){
            post = head.next; // post always keep the Node after the one being visited
            if(head.value < num){
                head.next = null;
                if(sh != null){
                    st.next = head;
                    st = head;
                }else {
                    sh = head;
                    st = head;
                }
            }else if(head.value == num){
                head.next = null;
                if(eh != null){
                    et.next = head;
                    et = head;
                }else{
                    eh = head;
                    et = head;
                }
            }else{
                head.next = null;
                if(lh != null){
                    lt.next = head;
                    lt = head;
                }else {
                    lh = head;
                    lt = head;
                }
            }
            head = post;
        }
        // link three lists together
        Node resHead = sh != null? sh: (eh != null? eh: lh);
        if(st != null){
            st.next = (et != null)? eh: lh;
        }
        if(et != null){
            et.next = lh;
        }

        return resHead;
    }


    @Test
    public void testPartition(){
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
        Node head = linkedListPartitionWithArray(list, 4);
        SinglyLinkedList.traversal(head);

    }

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

    /**
     *  clone特殊单链表结构
     *  clone special linked list with nodes(class SpecialNode) having extra random pointer
     */
    public static Node cloneSpecialNodesWithHashMap(SinglyLinkedList list){
        if(list == null){
            throw new RuntimeException("Invalid Parameter");
        }
        Node head = list.getHead();
        if(head == null || head.next == null){
            return head;
        }
        HashMap<Node, Node> map = new HashMap<>();
        /* Map:  Node ---> cloned Node  */
        Node cur = head;
        while(cur != null){
            Node clonedNode = new Node(cur.value);
            map.put(cur,clonedNode);
            cur = cur.next;
        }
        cur = head;
        Node res = map.get(cur);
        while(cur != null){
            map.get(cur).next = map.get(cur.next); // set next pointer
            map.get(cur).rand = map.get(cur.rand); // set rand pointer
            cur = cur.next;
        }
        return res;
    }


    public static Node cloneSpecialNodes(SinglyLinkedList list){
        if(list == null){
            throw new RuntimeException("Invalid Parameter");
        }
        Node head = list.getHead();
        if(head == null || head.next == null){
            return head;
        }
        Node cur = head;
        while(cur != null){
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
        while(cur != null){
            cur.next.rand = cur.rand.next;
            cur = cur.next.next;
        }
        //split original nodes with cloned nodes
        Node clonedHead = head.next;
        cur = head;
//        SinglyLinkedList.traversal(head);
        Node cp = clonedHead;
        while(cp.next != null){
                cur.next = cp.next;
                cp.next = cur.next.next;
                cur = cur.next;
                cp = cur.next;
        }
        cur.next = null;

        return clonedHead;
    }

    @Test
    public void testClone(){
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
        Node node = cloneSpecialNodesWithHashMap(list);
        Node nodewithout = cloneSpecialNodes(list);
        SinglyLinkedList.traversal(node);
        System.out.println("--------------");
        SinglyLinkedList.traversal(nodewithout);

    }

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
/**
 * Big Boss
 * */

    /**
     * 1.return the node that forms a loop in a looped linkedlist
     * 1.返回第一个入环节点
     */
    /*  Find the node that forms a loop   */
    public static Node getLoopNodeUseSet(SinglyLinkedList list){
        if(list == null){
            throw new RuntimeException("Invalid Parameter");
        }
        Node head = list.getHead();
        if(head == null || head.next == null){
            return head;
        }
        HashSet<Node> set = new HashSet<>();
        Node cur = head;
        while(cur != null){
            if(set.contains(cur)){
                return cur;
            }
            set.add(cur);
            cur = cur.next;
        }
        return cur;
    }

    /*  implementation with extra space complexity O(1)  */
    public static Node getLoopNode(SinglyLinkedList list){
        if(list == null){
            throw new RuntimeException("Invalid Parameter");
        }
        Node head = list.getHead();
        if(head == null || head.next == null){
            return head;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while(slow != fast){
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = head;
        while(slow != fast){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
    @Test
    public void testLoopNode(){
        SinglyLinkedList list = new SinglyLinkedList();
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        node6.next = node3;

        list.insert(node1);
        list.insert(node2);
        list.insert(node3);
        list.insert(node4);
        list.insert(node5);
        list.insert(node6);

        Node loop1 = getLoopNodeUseSet(list);
        Node loop2 = getLoopNode(list);
        System.out.println(loop1);
        System.out.println(loop2);
        System.out.println(loop1 == loop2);
    }

    /**
     * 2.two non-looped linked list intersect, find the first intersected Node
     * 2.两个无环单链表相交, 返回第一个相交节点
     */
    public static Node getFirstIntersection(SinglyLinkedList list1, SinglyLinkedList list2){
        if(list1 == null || list2 == null){
            throw new RuntimeException("Invalid Parameter");
        }
        Node head1 = list1.getHead();
        Node head2 = list2.getHead();
        if(head1 == null || head2 == null){
            return null;
        }
        if(head1 == head2){
            return head1;
        }

        Node cur1 = head1;
        int len1 = 0;
        while(cur1.next  != null){
            len1++;
            cur1 = cur1.next;
        }
        Node cur2 = head2;
        int len2 = 0;
        while(cur2.next != null){
            len2++;
            cur2 = cur2.next;
        }

        // no intersection if the their last nodes not equal
        if(cur1 != cur2){
            return null;
        }
        // find the initial step the longer linked list need to go
        int initialStep = Math.abs(len1 - len2);
        System.out.println(initialStep);
        Node cur = (len1 > len2)? head1: head2;
        while(initialStep != 0){
            cur = cur.next;
            initialStep--;
        }
        // continue traversal neck and neck
        Node temp = (len1 > len2)? head2: head1;
        while(cur != temp){
            cur = cur.next;
            temp = temp.next;
        }
        return cur;
    }

    @Test
    public void testIntersection(){
        SinglyLinkedList list1 = new SinglyLinkedList();
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);
        list1.insert(node1);
        list1.insert(node2);
        list1.insert(node3);
        list1.insert(node4);
        list1.insert(node5);
        list1.insert(node6);
        list1.insert(node7);
        list1.insert(node8);
        list1.insert(node9);
        list1.traversal();
        System.out.println("*******");
        SinglyLinkedList list2 = new SinglyLinkedList();
        Node node10 = new Node(10);
        Node node11 = new Node(11);
        Node node12 = new Node(12);
        list2.insert(node10);
        list2.insert(node11);
        list2.insert(node12);
        list2.traversal();
//        list2.insert(node5);
//        list2.insert(node6);
//        list2.insert(node7);
//        list2.insert(node8);
//        list2.insert(node9);
        Node inter = getFirstIntersection(list1, list2);
        System.out.println(inter);
    }







}

