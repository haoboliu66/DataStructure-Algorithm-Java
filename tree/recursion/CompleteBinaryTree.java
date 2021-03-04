package tree.recursion;

import java.util.LinkedList;
import java.util.Queue;

public class CompleteBinaryTree {

    /*
    lc 958. Check Completeness of a Binary Tree
     */
    public static boolean checkCBT(TreeNode head) {
        if (head == null) return true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        boolean isLeaf = false;

        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            // left=null,right=null
            // left!=null,right=null
            // left!=null,right!=null
            if (cur.left == null && cur.right != null || isLeaf && !(cur.left == null && cur.right == null)) {
                return false;
            }

            // left == null || (left!=null && right == null)
            if (cur.left == null || (cur.right == null)) {
                isLeaf = true;
            }

            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
        return true;
    }


    public static boolean isCBT1(TreeNode head) {
        if (head == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode leftChild = null;
        TreeNode rightChild = null;
        boolean leaf = false;
        queue.add(head);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            leftChild = cur.left;
            rightChild = cur.right;

            if ((leftChild == null && rightChild != null) || (leaf && !(leftChild == null && rightChild == null))) { //左树为空, 右树不为空, 违法CBT原则
                return false;
            }

            if (leftChild != null) {
                queue.add(leftChild);
            }
            if (rightChild != null) {
                queue.add(rightChild);
            }

            //只要有一个子为空
//            if (leftChild == null || rightChild == null) {
//                leaf = true;
//            }
            if (rightChild == null) {
                leaf = true;
            }
//            if (leftChild != null && rightChild == null) {
//                leaf = true;
//            }
        }
        return true;
    }

    public static boolean isCBT2(TreeNode head) {
        if (head == null) {
            return true;
        }
        return process(head).isCBT;
    }

    public static NodeInfo process(TreeNode node) {
        if (node == null) {
            return new NodeInfo(true, true, 0);
        }
        NodeInfo leftInfo = process(node.left);
        NodeInfo rightInfo = process(node.right);
        boolean isCBT = false;
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        //子树都是满, 而且两个子树高度相等
        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
        if (isFull) {
            isCBT = true;
        } else {
            //首先要子树都是BST,才有继续判断的必要
            if (leftInfo.isCBT && rightInfo.isCBT) {
                //1.左树是CBT,右树是满, 高度差1
                //2.左树是满,右树是满, 高度差1
                //3.左树是满, 右树是CBT, 高度相等
                isCBT = (leftInfo.isCBT && rightInfo.isFull) && (leftInfo.height - rightInfo.height == 1)
                        || (leftInfo.isFull && rightInfo.isFull) && (leftInfo.height - rightInfo.height == 1)
                        || (leftInfo.isFull && rightInfo.isCBT) && (leftInfo.height == rightInfo.height);
            }

        }

        return new NodeInfo(isFull, isCBT, height);
    }

    public static class NodeInfo {
        boolean isFull;
        boolean isCBT;
        int height;

        public NodeInfo(boolean isFull, boolean isBST, int height) {
            this.isFull = isFull;
            this.isCBT = isBST;
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
            if (isCBT1(head) != isCBT2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }


}
