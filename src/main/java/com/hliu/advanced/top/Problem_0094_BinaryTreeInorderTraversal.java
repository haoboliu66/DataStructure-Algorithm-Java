package src.main.java.advanced.top;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Problem_0094_BinaryTreeInorderTraversal {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

    }

    public List<Integer> inorderTraversal(TreeNode head) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                res.add(head.val);
                head = head.right;
            }
        }
        return res;
    }


    public List<Integer> inorderTraversal2(TreeNode head) {
        List<Integer> res = new ArrayList<>();
        if (head == null) {
            return res;
        }
        TreeNode cur = head;
        TreeNode mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {  // mostRight.right == cur
                    res.add(cur.val);
                    mostRight.right = null;
                }
            }
            cur = cur.right;
        }

        return res;
    }


}
