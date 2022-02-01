package src.main.java.advanced.top;

public class Problem_0098_ValidateBinarySearchTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    '}';
        }
    }


    public static boolean isValidBST(TreeNode root) {

        TreeNode mostRight = null;
        TreeNode pre = null;
        TreeNode cur = root;

        while (cur != null) {
            if (cur.left != null) {
                mostRight = cur.left;
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
//            if (pre != null && cur.val <= pre.val) return false;
//            pre = cur;
//            cur = cur.right;

            pre = cur;
            cur = cur.right;
            System.out.println("pre: " + pre);
            System.out.println("cur: " + cur);
            if(pre.val >= cur.val) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(5);
        TreeNode t2 = new TreeNode(1);
        TreeNode t3 = new TreeNode(4);
        TreeNode t4 = new TreeNode(3);
        TreeNode t5 = new TreeNode(6);
        t1.left = t2;
        t1.right = t3;
        t3.left = t4;
        t4.right = t5;
        boolean res = isValidBST(t1);
        System.out.println(res);
    }
}
