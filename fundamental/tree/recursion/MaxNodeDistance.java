package fundamental.tree.recursion;

public class MaxNodeDistance {

    /**
     * 一个二叉树, 每两个节点之间都有距离, 求整棵树的最大距离
     * <p>
     * https://leetcode.com/problems/diameter-of-binary-tree/
     */

    public static int maxDistance(TreeNode head) {
        return 0;
    }

    public static NodeInfo process(TreeNode head) {
        if (head == null) {
            return new NodeInfo(0, 0);
        }
        NodeInfo leftInfo = process(head.left);
        NodeInfo rightInfo = process(head.right);

        //不包括X的最大距离: 左树的最大距离和右树的最大距离取max
        int maxDistance1 = Math.max(leftInfo.maxDistance, rightInfo.maxDistance);
        // 包括X的最大距离: 左树的最远节点到X的距离(左树的高) + 右树的最远节点到X的距离(右树的高) + 1
        int maxDistance2 = leftInfo.height + rightInfo.height + 1;
        int maxDistance = Math.max(maxDistance1, maxDistance2);

        int height = Math.max(leftInfo.height, rightInfo.height);

        return new NodeInfo(maxDistance, height);
    }


    public static class NodeInfo {
        int maxDistance;
        int height;

        public NodeInfo(int maxDistance, int height) {
            this.maxDistance = maxDistance;
            this.height = height;
        }
    }


    private static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int value) {
            this.val = value;
        }

        @Override
        public String toString() {
            return "SBTNode{" +
                    "value='" + val + '\'' +
                    '}';
        }
    }

}
