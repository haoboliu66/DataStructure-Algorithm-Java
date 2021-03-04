package tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Serialization {

    /*  preorder serialization(preSerial + preProcess) and deserialization(preDeserial)  */
    public static Queue<String> preSerial(TreeNode head) {
        if (head == null) {
            return null;
        }
        Queue<String> ans = new LinkedList<>();
        preProcess(head, ans);
        return ans;
    }

    private static void preProcess(TreeNode node, Queue<String> ans) {
        if (node == null) {
            ans.add(null);
            return;
        }
        String nodeString = String.valueOf(node.value);
        ans.add(nodeString);
        preProcess(node.left, ans);
        preProcess(node.right, ans);
    }

    public static TreeNode preDeserial(Queue<String> preList) {
        String first = preList.poll();
        if (first == null) {
            return null;
        }
        TreeNode head = new TreeNode(first);
        head.left = preDeserial(preList);
        head.right = preDeserial(preList);
        return head;
    }


    /*  inorder serialization(inSerial + inProcess) and deserialization(inDeserial)  */
    public static Queue<String> inSerial(TreeNode head) {
        if (head == null) {
            return null;
        }
        Queue<String> ans = new LinkedList<>();
        inProcess(head, ans);
        return ans;
    }

    private static void inProcess(TreeNode node, Queue<String> ans) {
        if (node == null) {
            ans.add(null);
            return;
        }
        inProcess(node.left, ans);
        ans.add(String.valueOf(node.value));
        inProcess(node.right, ans);
    }

    /* not working for inorder deserial  */
    public static TreeNode inDeserial(Queue<String> inList) {
        return null;
    }


    /*  postorder serialization(postSerial + postProcess) and deserialization(postDeserial)  */
    public static Queue<String> postSerial(TreeNode head) {
        if (head == null) {
            return null;
        }
        Queue<String> ans = new LinkedList<>();
        postProcess(head, ans);
        return ans;
    }

    private static void postProcess(TreeNode node, Queue<String> ans) {
        if (node == null) {
            ans.add(null);
            return;
        }
        postProcess(node.left, ans);
        postProcess(node.right, ans);
        ans.add(String.valueOf(node.value));
    }

    public static TreeNode postDeserial(Queue<String> postList) {
        if (postList == null || postList.size() == 0) {
            return null;
        }
        Stack<String> stack = new Stack<>();
        while (!postList.isEmpty()) {
            stack.push(postList.poll());
        }

        return postDeserialProcess(stack);
    }

    public static TreeNode postDeserialProcess(Stack<String> stack) {
        String first = stack.pop();
        if (first == null) {
            return null;
        }
        TreeNode head = new TreeNode(first);
        head.right = postDeserialProcess(stack);
        head.left = postDeserialProcess(stack);
        return head;
    }


    /* Serialization by Level  */
    public static Queue<String> levelSerial(TreeNode head) {
        Queue<String> ans = new LinkedList<>();
        if (head == null) {
            return null;
        }
        ans.add(String.valueOf(head.value));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll(); //实质就是BFS, 然后把节点都加入Queue, null也要加
            if (cur.left != null) {
                queue.add(cur.left);
                ans.add(String.valueOf(cur.left.value));
            } else {
                ans.add(null);
            }

            if (cur.right != null) {
                queue.add(cur.right);
                ans.add(String.valueOf(cur.right.value));
            } else {
                ans.add(null);
            }
        }
        return ans;
    }

    public static TreeNode levelDeserial(Queue<String> levelList) {
        if (levelList == null || levelList.size() == 0) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode head = generateNode(levelList.poll());
        if (head != null) {
            queue.add(head);
        }
        TreeNode node = null;
        while (!queue.isEmpty()) {
            node = queue.poll();
            String left = levelList.poll();
            TreeNode leftNode = generateNode(left);
            if (leftNode != null) {
                queue.add(leftNode);
            }
            String right = levelList.poll();
            TreeNode rightNode = generateNode(right);
            if (rightNode != null) {
                queue.add(rightNode);
            }
            node.left = leftNode;
            node.right = rightNode;
        }
        return head;
    }

    private static TreeNode generateNode(String val) {
        if (val == null) {
            return null;
        }
        return new TreeNode(val);
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
        Queue<String> queue = postSerial(node1);
        System.out.println(queue);
        System.out.println("**************");
//        SBTNode head = levelDeserial(queue);
//        PreOrder.preOrderIter(head);
    }

}
