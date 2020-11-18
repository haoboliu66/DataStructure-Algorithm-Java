package advanced.c1.morris;

public class IsBSTUseMorris {
    /*
    Morris算法判断是不是BST
     */
    public static class Node {
        public int val;
        Node left;
        Node right;

        public Node(int data) {
            this.val = data;
        }
    }

    public static boolean isBST(Node head) {
        if (head == null) {
            return true;
        }
        Node cur = head;
        Node mostRight;
        Integer pre = null;
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
            //当遇到没有左树的节点时, 更新pre  第二次回到自己的时候进行, 进行比较
            if (pre != null && pre >= cur.val) {//一直都是顺时针方法比较, 比较左和头 或 比较头和右
                return false;
            }
            pre = cur.val;
            cur = cur.right;
        }
        return true;
    }
}
