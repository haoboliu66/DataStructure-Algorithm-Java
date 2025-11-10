package com.hliu.fundamental.tree;

import static com.hliu.fundamental.tree.utils.BinaryTreeUtils.generateNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Serialization {

  public static void main(String[] args) {
    TreeNode treeNode1 = new TreeNode(1);
    TreeNode treeNode2 = new TreeNode(2);
    TreeNode treeNode3 = new TreeNode(3);
    TreeNode treeNode4 = new TreeNode(4);
    TreeNode treeNode5 = new TreeNode(5);
    treeNode1.left = treeNode2;
    treeNode1.right = treeNode3;
    treeNode3.left = treeNode4;
    treeNode3.right = treeNode5;

    Codec codec = new Codec();
    String s = codec.serializePostorder(treeNode1);
    System.out.println(s);

    TreeNode treeNode = codec.decodePostorderString(s.split(","), new int[]{s.split(",").length - 1});
    System.out.println(treeNode);

  }

  // https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
  public static class Codec {

    public String serialize(TreeNode root) {
      if (root == null) {
        return "";
      }
      StringBuilder path = new StringBuilder();
      preorder(root, path);
      return path.toString();
    }

    public String serializePostorder(TreeNode root) {
      if (root == null) {
        return "";
      }
      StringBuilder path = new StringBuilder();
      postorder(root, path);
      return path.toString();
    }

    public TreeNode deserializePostorder(String data) {
      if (data == null || data.length() == 0) {
        return null;
      }
      String[] str = data.split(",");
      return decodePostorderString(str, new int[]{str.length - 1});
    }

    private TreeNode decodePreorderString(String[] nodes, int[] indexTracker) {
      if (nodes[indexTracker[0]].equals("#")) {
        indexTracker[0]++;
        return null;
      }
      int curIndex = indexTracker[0]++;
      TreeNode root = new TreeNode(Integer.valueOf(nodes[curIndex]));
      root.left = decodePreorderString(nodes, indexTracker);
      root.right = decodePreorderString(nodes, indexTracker);
      return root;
    }

    // 1,2,#,#,3,4,#,#,5,#,#
    private void preorder(TreeNode node, StringBuilder sb) {
      if (node == null) {
        sb.append("#,");
        return;
      }
      sb.append(node.val)
        .append(",");
      preorder(node.left, sb);
      preorder(node.right, sb);
    }

    private TreeNode decodePostorderString(String[] nodes, int[] indexTracker) {
      if ("#".equals(nodes[indexTracker[0]])) {
        indexTracker[0]--;
        return null;
      }
      int curIndex = indexTracker[0]--;
      TreeNode root = new TreeNode(Integer.valueOf(nodes[curIndex]));
      root.right = decodePostorderString(nodes, indexTracker);
      root.left = decodePostorderString(nodes, indexTracker);
      return root;
    }

    // #,#,2,#,#,4,#,#,5,3,1
    private void postorder(TreeNode node, StringBuilder sb) {
      if (node == null) {
        sb.append("#,");
        return;
      }
      postorder(node.left, sb);
      postorder(node.right, sb);
      sb.append(node.val)
        .append(",");
    }
  }

  /*  preorder serialization(preSerial + preProcess) and deserialization(preDeserial)  */
  public static Queue<String> preSerial(TreeNode head) {
    if (head == null) {
      return null;
    }
    Queue<String> ans = new LinkedList<>();
    preProcess(head, ans);

    StringBuilder sb = new StringBuilder();
    while (!ans.isEmpty()) {
      sb.append(ans.poll())
        .append("#");
    }
    sb.deleteCharAt(sb.length() - 1);
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

  /* not working for inorder deserial */
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

}
