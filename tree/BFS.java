package tree;

import java.util.*;

public class BFS {

//    public static void breadthFirstSearchRecur(SBTNode head, Queue<SBTNode> queue){
//        if (head == null) {
//            return;
//        }
//        if(!queue.contains(head)){
//            queue.add(head);
//        }
//        if(head.left != null){
//            queue.add(head.left);
//        }
//        if(head.right != null){
//            queue.add(head.right);
//        }
//        SBTNode cur = queue.poll();
//        System.out.println(cur);
//        breadthFirstSearchRecur(cur.left,queue);
//    }

    public static void breadthFirstSearch(TreeNode head) {
        if (head == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            System.out.println(cur);
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }


    public static int maxWidthUsingMap(TreeNode head) {
        if (head == null) {
            return 0;
        }
        HashMap<TreeNode, Integer> levelMap = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        levelMap.put(head, 1);
        int curLevel = 1; //当前层
        int curLevelNodes = 0; //一个节点出队列的时候再加到宽度上
        int max = 0;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            int curNodeLevel = levelMap.get(cur);
            if (cur.left != null) {
                queue.add(cur.left);
                levelMap.put(cur.left, curLevelNodes + 1);
            }
            if (cur.right != null) {
                queue.add(cur.right);
                levelMap.put(cur.right, curLevelNodes + 1);
            }
            if (curNodeLevel == curLevel) { // 如果当前出队列的Node所在层数 == 当前在统计的层数
                curLevelNodes++; // 说明当前出队列的Node就是统计层数上的节点, 所以应该节点数++
            } else { // 如果当前出队列的Node所在层数 != 当前在统计的层数(应该是当前Node的层数是统计的层数的下一层)
                max = Math.max(curLevelNodes, max); //结算上一层的节点个数, 找出最大
                curLevel++; // 应该去统计下一层了, 所以curLevel++
                curLevelNodes = 1; //节点数置位1(也就是统计了刚出队列的这一个Node)
            }
        }
        //最后一层节点没有再下一层给他结算, 所以最后还要再算一次
        max = Math.max(curLevelNodes, max);
        return max;
    }

    public static int maxWidthNoMap(TreeNode head) {
        if (head == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        TreeNode curEnd = head;  // 当前层的最右节点
        TreeNode nextEnd = null;    //如果有下一层的话, 下一层的最右节点
        int curLevelNodes = 0;
        int max = 0;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.left != null) {
                queue.add(cur.left);
                nextEnd = cur.left;
            }
            if (cur.right != null) {
                queue.add(cur.right);
                nextEnd = cur.right;
            }
            curLevelNodes++;  //因为队列刚弹出一个Node, 所以当前层节点要++
            if (cur == curEnd) { //当队列弹出的节点等于curEnd的时, 说明这个节点已经是到了该层的最后一个
                max = Math.max(max, curLevelNodes);
                curLevelNodes = 0; // 还没统计下一层,所以是0
                curEnd = nextEnd;  //到了下一层, 下一层的最后一个节点curEnd就是上一层统计的nextEnd
            }
        }
        return max;
    }


    public static void main(String[] args) {
        TreeNode node1 = new TreeNode("A");
        TreeNode node2 = new TreeNode("B");
        TreeNode node3 = new TreeNode("C");
        TreeNode node4 = new TreeNode("D");
        TreeNode node5 = new TreeNode("E");
        TreeNode node6 = new TreeNode("F");
        TreeNode node7 = new TreeNode("G");
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        int width = maxWidthNoMap(node1);
        System.out.println(width);
    }


}
