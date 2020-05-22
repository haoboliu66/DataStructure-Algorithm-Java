package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author andy-liu
 * @date 2020/5/22 - 5:47 PM
 */
public class Serialization {

    /*  preorder serialization(preSerial + preProcess) and deserialization(preDeserial)  */
    public static Queue<String> preSerial(Node head) {
        if (head == null) {
            return null;
        }
        Queue<String> ans = new LinkedList<>();
        preProcess(head, ans);
        return ans;
    }

    public static void preProcess(Node node, Queue<String> ans) {
        if (node == null) {
            ans.add(null);
            return;
        }
        String nodeString = String.valueOf(node.value);
        ans.add(nodeString);
        preProcess(node.left, ans);
        preProcess(node.right, ans);
    }

    public static Node preDeserial(Queue<String> preList) {
        String first = preList.poll();
        if (first == null) {
            return null;
        }
        Node head = new Node(first);
        head.left = preDeserial(preList);
        head.right = preDeserial(preList);
        return head;
    }

    /*  inorder serialization(inSerial + inProcess) and deserialization(inDeserial)  */
    public static Queue<String> inSerial(Node head) {
        if (head == null) {
            return null;
        }
        Queue<String> ans = new LinkedList<>();
        inProcess(head, ans);
        return ans;
    }

    public static void inProcess(Node node, Queue<String> ans) {
        if (node == null) {
            ans.add(null);
            return;
        }
        inProcess(node.left, ans);
        ans.add(String.valueOf(node.value));
        inProcess(node.right, ans);
    }

    public static Node inDeserial(Queue<String> inList) {
        Node head = null;

        return head;
    }

    /*  postorder serialization(postSerial + postProcess) and deserialization(postDeserial)  */
    public static Queue<String> postSerial(Node head) {
        if (head == null) {
            return null;
        }
        Queue<String> ans = new LinkedList<>();
        postProcess(head, ans);
        return ans;
    }

    public static void postProcess(Node node, Queue<String> ans) {
        if (node == null) {
            ans.add(null);
            return;
        }
        postProcess(node.left, ans);
        postProcess(node.right, ans);
        ans.add(String.valueOf(node.value));
    }

    public static Node postDeserial(Queue<String> postList) {
        String left = postList.poll();

        Node leftNode = null;
        if (left != null) {
            leftNode = postDeserial(postList);
        }
        String right = postList.poll();
        Node rightNode = null;
        if (right != null) {
            rightNode = postDeserial(postList);
        }
        String first = postList.poll();
        Node head = new Node(first);
        head.left = leftNode;
        head.right = rightNode;

        return head;
    }


    /* Serialization by Level  */
    public static Queue<String> levelSerial(Node head) {
        Queue<String> ans = new LinkedList<>();
        if (head == null) {
            return null;
        }
        ans.add(String.valueOf(head.value));
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            Node cur = queue.poll(); //实质就是BFS, 然后把节点都加入Queue, null也要加
            if (cur.left != null) {
                queue.add(cur.left);
                ans.add(String.valueOf(cur.left.value));
            } else {
                ans.add(null);
            }

            if (cur.right != null) {
                queue.add(cur.right);
                ans.add(String.valueOf(cur.right.value));
            } else {
                ans.add(null);
            }
        }
        return ans;
    }

    public static Node levelDeserial(Queue<String> levelList) {
        if(levelList == null || levelList.size() == 0){
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        Node head = generateNode(levelList.poll());
        if(head != null){
            queue.add(head);
        }
        Node node = null;
        while (!queue.isEmpty()) {
            node = queue.poll();
            String left = levelList.poll();
            Node leftNode = generateNode(left);
            if(leftNode != null){
                queue.add(leftNode);
            }
            String right = levelList.poll();
            Node rightNode = generateNode(right);
            if(rightNode != null){
                queue.add(rightNode);
            }
            node.left = leftNode;
            node.right = rightNode;
        }
        return head;
    }

    private static Node generateNode(String left) {
        if(left == null){
            return null;
        }
        return new Node(left);
    }


    public static void main(String[] args) {
        Node node1 = new Node("A");
        Node node2 = new Node("B");
        Node node3 = new Node("C");
        Node node4 = new Node("D");
        Node node5 = new Node("E");
        Node node6 = new Node("F");
        Node node7 = new Node("G");
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        Queue<String> queue = levelSerial(node1);
        System.out.println(queue);
        System.out.println("**************");
        Node head = levelDeserial(queue);
        BinaryTree.preOrderIter(head);
    }

}
