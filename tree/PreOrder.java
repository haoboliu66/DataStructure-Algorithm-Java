package tree;

import java.util.Stack;

/**
 * @author andy-liu
 * @date 2020/5/23 - 12:09 AM
 */
public class PreOrder {

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
}
