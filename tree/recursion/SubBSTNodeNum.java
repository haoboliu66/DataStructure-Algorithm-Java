package tree.recursion;

public class SubBSTNodeNum {

    /*
    给定一棵二叉树的头节点head, 返回这颗二叉树中最大的二叉搜索子树的大小
     */

    public static int getSubBSTNodeSize(TreeNode head) {
        if (head == null) {
            return 0;
        }

        return process(head).maxSubBSTSize;
    }

    public static NodeInfo process(TreeNode node) {
        if (node == null) {
            return null;
        }
        NodeInfo leftInfo = process(node.left);
        NodeInfo rightInfo = process(node.right);


        int max = node.val;
        int min = node.val;
        if (leftInfo != null) {
            max = Math.max(leftInfo.max, max);
            min = Math.min(leftInfo.min, min);
        }

        if (rightInfo != null) {
            min = Math.min(rightInfo.min, min);
            max = Math.max(rightInfo.max, max);
        }

        boolean isAllBST =
                (leftInfo == null || leftInfo.isAllBST) &&
                        (rightInfo == null || rightInfo.isAllBST) &&
                        (leftInfo == null || leftInfo.max < node.val) &&
                        (rightInfo == null || rightInfo.min > node.val);

        int maxSubBSTSize = !isAllBST ? Math.max(leftInfo != null ? leftInfo.maxSubBSTSize : 0, rightInfo != null ? rightInfo.maxSubBSTSize : 0)
                : (leftInfo != null ? leftInfo.maxSubBSTSize : 0) + (rightInfo != null ? rightInfo.maxSubBSTSize : 0) + 1;


        return new NodeInfo(maxSubBSTSize, isAllBST, min, max);
    }


    public static class NodeInfo {
        public int maxSubBSTSize;
        public boolean isAllBST;
        public int min;
        public int max;

        public NodeInfo(int maxSubBSTSize, boolean isAllBST, int min, int max) {
            this.maxSubBSTSize = maxSubBSTSize;
            this.isAllBST = isAllBST;
            this.min = min;
            this.max = max;
        }
    }
}
