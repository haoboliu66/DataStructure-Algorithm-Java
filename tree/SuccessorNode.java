package tree;

import java.util.Stack;

public class SuccessorNode {

    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node getSuccessorNode(Node node) {
        if (node == null) {
            return null;
        }
        if (node.right != null) { //右树不空, successor是右树上的最左节点
            return getLeftMost(node.right);
        }
        //右树空的情况下, 往上找
        Node parent = node.parent;
        while (parent != null && parent.left != node) { // 最右节点的后继节点为null
            node = parent;
            parent = node.parent;
        }
        return parent;
    }

    private static Node getLeftMost(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }


    // BST is way easier
    public static class Problem_285_InorderSuccessorInBST {

        public class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;

            TreeNode(int x) {
                val = x;
            }
        }

        public TreeNode inorderSuccessor1(TreeNode root, TreeNode p) {
            TreeNode succ = null;
            while (root != null) {
                if (p.val < root.val) {
                    succ = root;
                    root = root.left;
                } else
                    root = root.right;
            }
            return succ;
        }

        // another implementation with intuitive in-order traversal
        public TreeNode inorderSuccessor2(TreeNode head, TreeNode p) {

            Stack<TreeNode> stack = new Stack<>();

            boolean isFound = false;

            while (!stack.isEmpty() || head != null) {

                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    if (isFound) {
                        return head;
                    }
                    if (head.val == p.val) {
                        isFound = true;
                    }
                    head = head.right;
                }
            }
            return null;
        }

    }
}
