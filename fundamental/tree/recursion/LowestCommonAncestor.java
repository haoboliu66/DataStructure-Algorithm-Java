package fundamental.tree.recursion;

public class LowestCommonAncestor {


    // https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/

    /*
    设置parentMap, 加Set, 从p,q向上找, Set中第一个重复节点就是LCA
     */
    // map思路可解: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iii/

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return process(root, p, q).ancestor;
    }

    public Info process(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) {
            return null;
        }

        Info leftInfo = process(node.left, p, q);
        Info rightInfo = process(node.right, p, q);
        boolean hasP;
        boolean hasQ;
        TreeNode ancestor;

        hasP = (node == p) || (leftInfo != null && leftInfo.hasP) || (rightInfo != null && rightInfo.hasP);
        hasQ = (node == q) || (leftInfo != null && leftInfo.hasQ) || (rightInfo != null && rightInfo.hasQ);

        ancestor = (leftInfo != null && leftInfo.hasP && leftInfo.hasQ) ? leftInfo.ancestor :
                // 左树上找到了p和q, 那么LCA一定在左树
                (rightInfo != null && rightInfo.hasP && rightInfo.hasQ) ? rightInfo.ancestor :
                // 右上找到了p和q, 那么LCA一定在左树
                        (hasP && hasQ) ? node : null;
                // 左树右树都没找到, 那么就当前的X节点找到没有

        return new Info(hasP, hasQ, ancestor);
    }

    private class Info {
        boolean hasP;
        boolean hasQ;
        TreeNode ancestor;

        public Info(boolean b1, boolean b2, TreeNode n) {
            hasP = b1;
            hasQ = b2;
            ancestor = n;
        }
    }


    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
