package advanced.top;

public class Problem_0098_ValidateBinarySearchTree {

    public static class TreeNode {
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

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    '}';
        }
    }


    public static boolean isValidBST(TreeNode head) {

        TreeNode mostRight = null;
        TreeNode pre = null;
        TreeNode cur = head;

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
                } else {
                    mostRight.right = null;
                }
            }
            if (pre != null && cur.val <= pre.val) return false;
            pre = cur;
            cur = cur.right;


        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        t2.left = t1;
        t2.right = t3;
        boolean res = isValidBST(t2);
        System.out.println(res);
    }
}
