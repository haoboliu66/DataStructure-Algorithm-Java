package sys.c5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class C01_ConstructTreeWithArray {

    /*
    1008. Construct Binary Search Tree from Preorder Traversal
     */

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static TreeNode constructTree(int[] pre) {
        if (pre == null || pre.length == 0) return null;
        if (pre.length == 1) return new TreeNode(pre[0]);
        return process(pre, 0, pre.length - 1);
    }

    public static TreeNode process(int[] pre, int left, int right) {
        if (left > right) return null;
        if (left == right) {
            return new TreeNode(pre[left]);
        }
        int headVal = pre[left];
        TreeNode head = new TreeNode(headVal);
        int rightSub = left;
        for (; rightSub <= right; rightSub++) {
            if (pre[rightSub] > headVal) {
                break;
            }
        }
        TreeNode leftChild = process(pre, left + 1, rightSub - 1);
        TreeNode rightChild = process(pre, rightSub, right);

        head.left = leftChild;
        head.right = rightChild;

        return head;
    }

    public static int[] checkTree(TreeNode head) {
        List<Integer> list = new ArrayList<>();
        TreeNode cur = head;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(cur);
        while (!stack.isEmpty()) {
            cur = stack.pop();
            list.add(cur.val);
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] pre = {8, 5, 1, 7, 10, 12};
        TreeNode treeNode = constructTree(pre);
        int[] test = checkTree(treeNode);
        System.out.println(Arrays.toString(test));
    }


}
