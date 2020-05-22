package tree;

import java.util.Stack;

/**
 * @author andy-liu
 * @date 2020/5/23 - 12:09 AM
 */
public class PostOrder {

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

    public static void postOrder2(Node h) {
        if (h == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(h);
        Node c = null;
        while (!stack.isEmpty()) {
            c = stack.peek();
            if (c.left != null && h != c.left && h != c.right) {
                stack.push(c.left);
            } else if (c.right != null && h != c.right) {
                stack.push(c.right);
            } else {
                System.out.print(stack.pop() + " ");
                h = c;
            }
        }
    }
}
