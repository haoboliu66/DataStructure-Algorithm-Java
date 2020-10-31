package tree.recursion;

import java.util.ArrayList;

public class IsBST {

    /*
    lc 98.Validate Binary Search Tree
     */

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }


    public static boolean isBSTMorris(TreeNode head) {
        if (head == null) return true;

        TreeNode cur = head;
        TreeNode mostRight;
        TreeNode pre = null;
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
            if (pre != null && pre.val >= cur.val) return false;
            pre = cur;
            cur = cur.right;
        }
        
        return true;
    }


    public static boolean isBST1(TreeNode head) {
        if (head == null) {
            return true;
        }

        return process1(head);
    }

    public static boolean process1(TreeNode node) {
        if (node == null) {
            return true;
        }
        boolean left = false;
        boolean right = false;
        if (node.left != null && node.left.val <= node.val) {
            left = process1(node.left);
        }
        if (node.right != null && node.right.val >= node.val) {
            right = process1(node.right);
        }
        return left && right;
    }


    static class Info {
        boolean isBST;
        int min;
        int max;

        public Info(boolean isBST, int min, int max) {
            this.isBST = isBST;
            this.min = min;
            this.max = max;
        }
    }

    public static boolean isBST2(TreeNode head) {
        if (head == null) return true;
        return process(head).isBST;
    }

    public static Info process2(TreeNode cur) {
        if (cur.left == null && cur.right == null) {
            return new Info(true, cur.val, cur.val);
        }

        int min = cur.val, max = cur.val;

        Info left = null;
        if (cur.left != null) {
            left = process(cur.left);
            min = Math.min(left.min, min);
            max = Math.max(left.max, max);
        }

        Info right = null;
        if (cur.right != null) {
            right = process(cur.right);
            min = Math.min(min, right.min);
            max = Math.max(max, right.max);
        }

        boolean isBST = (left != null ? left.isBST && cur.val > left.max : true)

                && (right != null ? right.isBST && cur.val < right.min : true);

        return new Info(isBST, min, max);
    }


    public static boolean isBST3(TreeNode head) {
        if (head == null) {
            return true;
        }
        ArrayList<TreeNode> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).val <= arr.get(i - 1).val) {
                return false;
            }
        }
        return true;
    }

    public static void in(TreeNode head, ArrayList<TreeNode> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    public static boolean isBST4(TreeNode head) {
        if (head == null) {
            return true;
        }
        return process(head).isBST;
    }

    public static Info process(TreeNode head) {
        if (head == null) {
            return null;
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        int min = head.val;
        int max = head.val;
        if (leftInfo != null) {
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
        }
        if (rightInfo != null) {
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
        }
        boolean isBST = false;
        if (
                (leftInfo == null ? true : (leftInfo.isBST && leftInfo.max < head.val))
                        &&
                        (rightInfo == null ? true : (rightInfo.isBST && rightInfo.min > head.val))
        ) {
            isBST = true;
        }
        return new Info(isBST, min, max);
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
            if (isBST1(head) != isBST2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
