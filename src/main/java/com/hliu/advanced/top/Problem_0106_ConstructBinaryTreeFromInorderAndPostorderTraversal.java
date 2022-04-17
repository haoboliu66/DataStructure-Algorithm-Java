package src.main.java.advanced.top;

import java.util.HashMap;

public class Problem_0106_ConstructBinaryTreeFromInorderAndPostorderTraversal {

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

    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return process(inorder, 0, inorder.length - 1, postorder, 0, inorder.length - 1, map);
    }

    public static TreeNode process(int[] in, int L1, int R1, int[] post, int L2, int R2, HashMap<Integer, Integer> map) {
        if (L2 > R2) {
            return null;
        }

        TreeNode head = new TreeNode(post[R2]);
        if (L2 == R2) {
            return head;
        }

        int index = map.get(post[R2]);

        head.right = process(in, index + 1, R1, post, R2 - (R1 - index), R2 - 1, map);
        head.left = process(in, L1, index - 1, post, L2, R2 - (R1 - index) - 1, map);

        return head;
    }

    public static void main(String[] args) {
        int[] in = {9, 3, 15, 20, 7};
        int[] post = {9, 15, 7, 20, 3};
        TreeNode node = buildTree(in, post);
    }


}
