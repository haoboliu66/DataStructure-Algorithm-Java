package tree.recursion;

/**
 * @author andy-liu
 * @date 2020/5/24 - 7:55 AM
 */
public class SuccessorNode {

    public static class Node{
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node getSuccessorNode(Node node){
        if(node == null){
            return null;
        }
        if(node.right != null){ //右树不空, successor是右树上的最左节点
            return getLeftMost(node.right);
        }
        //右树空的情况下, 往上找
        Node parent = node.parent;
        while(parent != null && parent.left != node){ // 最右节点的后继节点为null
            node = parent;
            parent = node.parent;
        }
        return parent;
    }

    private static Node getLeftMost(Node node) {
        while(node.left != null){
            node = node.left;
        }
        return node;
    }


}
