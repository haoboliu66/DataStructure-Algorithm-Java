package src.main.java.fundamental.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class SymmetricTree {

    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        Queue<String> queue1 = new LinkedList<>();
        Queue<String> queue2 = new LinkedList<>();
        stack1.push(root);
        stack2.push(root);
        while (!stack1.isEmpty() && !stack2.isEmpty()) {

            TreeNode cur1 = stack1.pop();
            queue1.add(cur1.value.toString());

            if (cur1.right != null) { // stack1是正常前序  头左右
                stack1.push(cur1.right);
            }
            if (cur1.left != null) {
                stack1.push(cur1.left);
            }

            TreeNode cur2 = stack2.pop();
            queue2.add(cur2.value.toString());

            if (cur2.left != null) { //stack2是变化的前序  头右左
                stack2.push(cur2.left);
            }
            if (cur2.right != null) {
                stack2.push(cur2.right);
            }

        }
        return isEquals(queue1, queue2);
    }

    public static boolean isEquals(Queue<String> q1, Queue<String> q2) {
        System.out.println(q1);
        System.out.println(q2);
        if (q1.size() != q2.size()) {
            return false;
        }

        while (!q1.isEmpty()) {
            if (!q1.poll().equals(q2.poll())) {
                return false;
            }
        }
        return true;
    }


    public static boolean isSymmetric1(TreeNode root) {
        if (root == null) {
            return true;
        }
        return process(root.left, root.right);
    }

    public static boolean process(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left != null && right != null) {
            return left.val == right.val
                    && process(left.left, right.right)
                    && process(left.right, right.left);
        }

        return false;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode("1");

        TreeNode node2 = new TreeNode("2");

        TreeNode node3 = new TreeNode("3");
        TreeNode node4 = new TreeNode("4");

        TreeNode node5 = new TreeNode("2");

        TreeNode node6 = new TreeNode("3");
        TreeNode node7 = new TreeNode("4");
        node1.left = node2;
        node1.right = node5;

        node2.left = node3;
        node2.right = node4;

        node5.left = node7;
        node5.right = node6;

        boolean s = isSymmetric(node1);
        boolean s1 = isSymmetric1(node1);
        System.out.println(s);
        System.out.println(s1);

    }

}
