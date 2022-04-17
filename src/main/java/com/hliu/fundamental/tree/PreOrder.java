package src.main.java.fundamental.tree;

import java.util.Stack;

public class PreOrder {

    /**
     * PreOrder traversal
     */
    /* Recursive implementation  */
    public static void preOrderRecur(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.println(node);
        preOrderRecur(node.left);
        preOrderRecur(node.right);
    }

    /* Iterative implementation of preOrder  */
    public static void preOrderIter(TreeNode node) {
        if (node == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.add(node);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
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
