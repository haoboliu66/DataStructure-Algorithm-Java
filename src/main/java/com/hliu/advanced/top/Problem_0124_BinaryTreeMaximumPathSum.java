package src.main.java.advanced.top;

public class Problem_0124_BinaryTreeMaximumPathSum {

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

    public static int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return process(root).maxSum;
    }

    public static Info process(TreeNode node) {
        if (node == null) {
            return null;
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        int maxSum, maxSumWithHead;

        // only node itself
        int p1 = node.val;

        // max in left subtree with node not included
        int p2 = leftInfo != null ? leftInfo.maxSum : Integer.MIN_VALUE;
        // max in right subtree with node not included
        int p3 = rightInfo != null ? rightInfo.maxSum : Integer.MIN_VALUE;
        //
        int p4 = leftInfo != null ? leftInfo.maxSumWithHead + node.val : Integer.MIN_VALUE;

        int p5 = rightInfo != null ? rightInfo.maxSumWithHead + node.val : Integer.MIN_VALUE;

        int p6 = leftInfo != null && rightInfo != null ? leftInfo.maxSumWithHead + rightInfo.maxSumWithHead + node.val : Integer.MIN_VALUE;

        maxSum = Math.max(Math.max(Math.max(p1, p2), Math.max(p3, p4)), Math.max(p5, p6));
        maxSumWithHead = Math.max(p1, Math.max(p4, p5)); // 不能包含p6, 如果两侧都走, 就不是从X出发了

        return new Info(maxSum, maxSumWithHead);
    }

    public static class Info {
        int maxSum;
        int maxSumWithHead;

        public Info(int maxSum, int maxSumWithHead) {
            this.maxSum = maxSum;
            this.maxSumWithHead = maxSumWithHead;
        }

    }

}
