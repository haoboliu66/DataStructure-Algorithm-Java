package advanced.c3.class5;


/**
 * @author andy-liu
 * @date 2020/7/4 - 9:09 PM
 */
public class C03_CompleteTreeNodeNumber {

    /*
    LeetCode 222. Count Complete Tree Nodes
     */

    public static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }


//    public static int nodeNum(SBTNode head) {
//
//
//    }
//
//    public static int process(SBTNode node) {
//
//
//    }

    public static int getLevel(Node node) {
        if (node == null) {
            return 0;
        }
        Node cur = node;
        int level = 0;
        while (cur != null) {
            level++;
            cur = cur.left;
        }
        return level + 1;
    }





}
