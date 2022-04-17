package src.main.java.advanced.c3.class5;


public class C03_CompleteTreeNodeNumber {

    /*
    lc 222. Count Complete Tree Nodes
    https://leetcode.com/problems/count-complete-tree-nodes/
     */

    // 复杂度 O(h^2)
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        if (root.right == null) {
            return root.left != null ? 2 : 1;
        }
        int level = getLevel(root);
        int rightLevel = getLevel(root.right);

        if (rightLevel == level - 1) {
            // 左树是满树, 递归求右树的节点
            return 1 + countNodes(root.right) + (int) (Math.pow(2, level - 1) - 1);
        } else {
            // rightLevel == level - 2
            // 右树是满树, 递归求左树节点数
            return 1 + countNodes(root.left) + (int) (Math.pow(2, rightLevel) - 1);
        }
    }

    public static int getLevel(TreeNode root) {
        TreeNode cur = root;
        int level = 1;
        while (cur.left != null) {
            level++;
            cur = cur.left;
        }
        return level;
    }

    private static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }


}
