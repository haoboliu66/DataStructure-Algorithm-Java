package tree;

import java.util.*;

/**
 * @author andy-liu
 * @date 2020/5/22 - 8:39 AM
 */
public class BFS {

    public static void main(String[] args) {
        Node node1 = new Node("A");
        Node node2 = new Node("B");
        Node node3 = new Node("C");
        Node node4 = new Node("D");
        Node node5 = new Node("E");
        Node node6 = new Node("F");
        Node node7 = new Node("G");
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        breadthFirstSearch(node1);
    }

    public static void breadthFirstSearch(Node head) {
        if (head == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur);
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }

    public static void BFSNoQueue(Node head){
        if(head == null){
            return;
        }
        Node curEnd = head;
        Node nextEnd = null;
    }


    public static int maxWidthUsingMap(Node head) {
        if (head == null) {
            return 0;
        }
        HashMap<Node, Integer> levelMap = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        levelMap.put(head, 1);
        int curLevel = 1; //当前层
        int curLevelNodes = 0; //一个节点出队列的时候再加到宽度上
        int max = 0;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
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
        //最后一层节点没有在下一层个他结算, 所以最后还要再算一次
        max = Math.max(curLevelNodes, max);
        return max;
    }

    public static int maxWidthNoMap(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Node curEnd = head;  // 当前层的最右节点
        Node nextEnd = null;    //如果有下一层的话, 下一层的最右节点
        int curLevelNodes = 0;
        int max = 0;
        while(!queue.isEmpty()){
            Node cur = queue.poll();
            if(cur.left != null){
                queue.add(cur.left);
                nextEnd = cur.left;
            }
            if(cur.right != null){
                queue.add(cur.right);
                nextEnd = cur.right;
            }
            curLevelNodes++;  //因为队列刚弹出一个Node, 所以当前层节点要++
            if(cur == curEnd){ //当队列弹出的节点等于curEnd的时, 说明这个节点已经是到了该层的最后一个
                max = Math.max(max,curLevelNodes);
                curLevelNodes = 0; // 还没统计下一层,所以是0
                curEnd = nextEnd;  //到了下一层, 下一层的最后一个节点curEnd就是上一层统计的nextEnd
            }
        }
        return max;
    }


}
