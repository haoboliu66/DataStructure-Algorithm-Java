package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author andy-liu
 * @date 2020/5/9 - 9:39 AM
 */
public class BinaryTree {

    Node root;

    public BinaryTree() {
        System.out.println();
    }

    public BinaryTree(Node root) {
        this.root = root;
    }

    /**
     * PreOrder traversal
     */
    /* Recursive implementation  */
    public static void preOrderRecur(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node);
        preOrderRecur(node.left);
        preOrderRecur(node.right);
    }

    /* Iterative implementation of preOrder  */
    public static void preOrderIter(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.add(node);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur);
            if (cur.right != null) {
                stack.add(cur.right);
            }
            if (cur.left != null) {
                stack.add(cur.left);
            }
        }
    }

    public static void inOrderRecur(Node node) {
        if (node == null) {
            return;
        }
        inOrderRecur(node.left);
        System.out.println(node);
        inOrderRecur(node.right);
    }

    /* Iterative implementation of inOrder  */
    public static void inOrderIter(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || head != null) {
            if(head != null){
                stack.push(head);
                head = head.left;   //左树没走到头, 就一直进栈
            }else{  //左树走到头了
                head = stack.pop(); //左树走到头后, 将head移到父节点
                System.out.println(head);   //左树走到头, 回来应该打印父节点
                head = head.right;  //再走右树
            }
        }
    }

    public static void postOrderRecur(Node node) {
        if (node == null) {
            return;
        }
        postOrderRecur(node.left);
        postOrderRecur(node.right);
        System.out.println(node);
    }

    /* Iterative implementation of postOrder  */
    // N L R(preOrder) ---> N R L ---> L R N(postOrder)
    public static void postOrderIter(Node head) {
        if (head == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        Stack<Node> help = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            help.push(cur);
            if (cur.left != null) {
                stack.push(cur.left);
            }
            if (cur.right != null) {
                stack.push(cur.right);
            }
        }
        while (!help.isEmpty()) {
            System.out.print(help.pop() + " ");
        }
    }

    public static void postOrder2(Node h){
        if(h == null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(h);
        Node c = null;
        while(!stack.isEmpty()){
            c = stack.peek();
            if(c.left != null && h != c.left && h != c.right){
                stack.push(c.left);
            }else if(c.right != null && h != c.right){
                stack.push(c.right);
            }else{
                System.out.print(stack.pop() + " ");
                h = c;
            }
        }
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
        inOrderRecur(node1);
        System.out.println("*******");
        inOrderIter(node1);

    }
}


class Node {
    public String value;
    public Node left;
    public Node right;


    public Node(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value='" + value + '\'' +
                '}';
    }

}
