package src.main.java.advanced.c4.class7;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class C02_RecoverBST {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static void recoverTree(TreeNode root) {
        if (root == null) {
            return;
        }

        List<TreeNode> list = new ArrayList<>();

        process(root, list);

        TreeNode first = null;
        TreeNode second = null;

        int t = 1;

        for (int i = 0; i < list.size() - 1; i++) {

            if (t == 1 && list.get(i).val > list.get(i + 1).val) {
                first = list.get(i);
                second = list.get(i + 1);
                t++;
            }

            if (t == 2 && list.get(i).val > list.get(i + 1).val) {
                second = list.get(i + 1);
            }
        }

        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;

    }

    public static void inOrder(TreeNode head, List<TreeNode> list) {

        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || head != null) {
            while (head != null) {
                stack.push(head);
                head = head.left;
            }
            head = stack.pop();
            list.add(head);
            head = head.right;
        }
    }


    public static void process(TreeNode node, List<TreeNode> list) {
        if (node == null) {
            return;
        }
        process(node.left, list);
        list.add(node);
        process(node.right, list);
    }

}
