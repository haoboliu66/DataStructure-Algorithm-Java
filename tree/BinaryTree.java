package tree;

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
    public static void preOrderRecur(Node node){
        if(node == null){
            return;
        }
        System.out.println(node);
        preOrderRecur(node.left);
        preOrderRecur(node.right);
    }

    /* Iterative implementation  */
    public static void preOrderIter(Node node){
        if(node == null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.add(node);
        while(!stack.isEmpty()){
            Node cur = stack.pop();
            System.out.println(cur);
            stack.add(node.right);
            stack.add(node.left);
        }
    }

    public static void inOrderRecur(Node node){
        if(node == null){
            return;
        }
        inOrderRecur(node.left);
        System.out.println(node);
        inOrderRecur(node.right);
    }

    public static void inOrderIter(Node node){

    }

    public static void postOrderRecur(Node node){
        if(node == null){
            return;
        }
        postOrderRecur(node.left);
        postOrderRecur(node.right);
        System.out.println(node);
    }

    public static void postOrderIter(Node node){

    }

}


class Node{
    public int value;
    public Node left;
    public Node right;

    public Node(int value) {
        this.value = value;
    }

}
