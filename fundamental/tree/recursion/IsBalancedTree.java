package fundamental.tree.recursion;

public class IsBalancedTree {

    /*
    lc 110
    不能直接在head处收集高度判断, 可能存在整体高度平衡, 但是子树不平衡的情况
    https://leetcode.com/problems/balanced-binary-tree/
     */

    public static boolean isBalanced1(TreeNode head) {
        boolean[] ans = new boolean[1];
        ans[0] = true;
        process1(head, ans);
        return ans[0];
    }

    public static int process1(TreeNode head, boolean[] ans) {
        if (!ans[0] || head == null) {
            return -1;
        }
        int leftHeight = process1(head.left, ans);
        int rightHeight = process1(head.right, ans);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            ans[0] = false;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }


    // Recursion
    public static boolean isBalanced2(TreeNode head) {
        if (head == null) {
            return true;
        }
        return process(head).isBalanced;
    }

    public static NodeInfo process(TreeNode head) {
        if (head == null) {
            return new NodeInfo(true, 0);
        }
        NodeInfo leftInfo = process(head.left);
        NodeInfo rightInfo = process(head.right);

        boolean isBalanced = leftInfo.isBalanced
                && rightInfo.isBalanced
                && Math.abs(leftInfo.height - rightInfo.height) <= 1;

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;

        return new NodeInfo(isBalanced, height);
    }

    public static class NodeInfo {
        public boolean isBalanced;
        public int height;

        public NodeInfo(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }


    // for test
    public static TreeNode generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static TreeNode generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        TreeNode head = new TreeNode((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            TreeNode head = generateRandomBST(maxLevel, maxValue);
            if (isBalanced1(head) != isBalanced2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
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

