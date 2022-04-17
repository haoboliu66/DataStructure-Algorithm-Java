package src.main.java.advanced.top;


import java.util.HashMap;

public class Problem_0105_ConstructBinaryTreeFromPreorderAndInorderTraversal {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return process(preorder, 0, preorder.length - 1, inorder, 0, preorder.length - 1, map);
    }


    public TreeNode process(int[] pre, int L1, int R1, int[] in, int L2, int R2, HashMap<Integer, Integer> map) {
        if (L1 > R1) {
            return null;
        }
        TreeNode head = new TreeNode(pre[L1]);
        if (L1 == R1) {
            return head;
        }
        int index = map.get(pre[L1]);

        head.left = process(pre, L1 + 1, index - L2 + L1, in, L2, index - 1, map);
        head.right = process(pre, index - L2 + L1 + 1, R1, in, index + 1, R2, map);

        return head;
    }

}
