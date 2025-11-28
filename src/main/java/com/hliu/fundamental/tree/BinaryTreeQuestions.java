package com.hliu.fundamental.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/*
  https://leetcode.com/problems/maximum-depth-of-binary-tree/
  https://leetcode.com/problems/minimum-depth-of-binary-tree/
  https://leetcode.com/problems/count-complete-tree-nodes/
  https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
  https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/
  https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
  https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
  https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree
  https://leetcode.com/problems/path-sum-ii/
  https://leetcode.com/problems/path-sum-iii/
  https://leetcode.com/problems/balanced-binary-tree/
  https://leetcode.com/problems/validate-binary-search-tree/
  https://leetcode.com/problems/trim-a-binary-search-tree/
  https://leetcode.com/problems/house-robber-iii/

 */

public class BinaryTreeQuestions {

  // https://leetcode.com/problems/maximum-depth-of-binary-tree/
  public int maxDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
  }

  // https://leetcode.com/problems/minimum-depth-of-binary-tree/
  public int minDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    if (root.left == null && root.right == null) {
      return 1;
    }
    int leftHeight = Integer.MAX_VALUE;
    if (root.left != null) {  // 不为空才能调用, 避免返回0干扰, 因为可能某个中间节点只有一侧的子树
      leftHeight = minDepth(root.left);
    }
    int rightHeight = Integer.MAX_VALUE;
    if (root.right != null) {
      rightHeight = minDepth(root.right);
    }
    return Math.min(leftHeight, rightHeight) + 1;
  }

  // https://leetcode.com/problems/count-complete-tree-nodes/
  /*
  Time complexity: 因为每次都是先查验右子树的最左边界, 再决定如何向下一层递归
  所以每一轮递归查看的node个数是h, h-1, h-2, h-3 .... 1, 所以总时间复杂度是O(h^2), h是树高, 对于complete tree, h = logN, 所以总时间复杂度是O((logN)^2)
   */
  public int countNodes(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int height = mostLeftNode(root, 1);
    return countNodes(root, 1, height);
  }

  private int countNodes(TreeNode node, int level, int treeHeight) {
    if (level == treeHeight) { // 此时递归调用到了leaf node, leaf的level等于总树高, 返回1
      return 1;
    }
    // 右树的叶子节点生长到了总树高, 那么node.left一定是full
    if (mostLeftNode(node.right, level + 1) == treeHeight) {
      return 1 + (1 << (treeHeight - level)) - 1 + countNodes(node.right, level + 1, treeHeight);
    }
    // 右树没有叶子节点在总高度, 说明右树是full, 递归计算左树
    int rightHeight = treeHeight - 1 - level;
    return 1 + countNodes(node.left, level + 1, treeHeight) + (1 << rightHeight) - 1;
  }

  // get subtree height from node at curLevel
  private int mostLeftNode(TreeNode node, int curLevel) {
    while (node != null) {
      curLevel++;
      node = node.left;
    }
    return curLevel - 1;
  }

  // https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
  public TreeNode buildTree(int[] preorder, int[] inorder) {
    return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
  }

  private TreeNode buildTree(int[] preorder, int preL, int preR, int[] inorder, int inL, int inR) {
    if (preL > preR) {
      return null;
    }
    TreeNode root = new TreeNode(preorder[preL]);
    if (preL == preR) {
      return root;
    }
    int rootIndex = -1;
    for (int i = inL; i <= inR; i++) {
      if (inorder[i] == preorder[preL]) {
        rootIndex = i;
        break;
      }
    }
    int leftSize = rootIndex - inL;
    TreeNode left = buildTree(preorder, preL + 1, preL + leftSize, inorder, inL, rootIndex - 1);
    TreeNode right = buildTree(preorder, preL + leftSize + 1, preR, inorder, rootIndex + 1, inR);
    root.left = left;
    root.right = right;
    return root;
  }

  // 加一个map优化查找rootIndex的过程
  public TreeNode buildTree1(int[] preorder, int[] inorder) {
    Map<Integer, Integer> indexMap = new HashMap<>();
    for (int i = 0; i < inorder.length; i++) {
      indexMap.put(inorder[i], i);
    }
    return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, indexMap);
  }

  private TreeNode buildTree(int[] preorder, int preL, int preR, int[] inorder, int inL, int inR,
      Map<Integer, Integer> indexMap) {
    if (preL > preR) {
      return null;
    }
    TreeNode root = new TreeNode(preorder[preL]);
    if (preL == preR) {
      return root;
    }
    int rootIndex = indexMap.get(root.val);
    int leftSize = rootIndex - inL;
    TreeNode left = buildTree(preorder, preL + 1, preL + leftSize, inorder, inL, rootIndex - 1, indexMap);
    TreeNode right = buildTree(preorder, preL + leftSize + 1, preR, inorder, rootIndex + 1, inR, indexMap);
    root.left = left;
    root.right = right;
    return root;
  }

  // https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/
  public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
    Map<Integer, Integer> postIndexMap = new HashMap<>();
    for (int i = 0; i < postorder.length; i++) {
      postIndexMap.put(postorder[i], i);
    }
    return construct(preorder, 0, preorder.length - 1, postorder, 0, postorder.length - 1,
        postIndexMap);
  }

  private TreeNode construct(int[] preorder, int preL, int preR, int[] postorder, int postL, int postR,
      Map<Integer, Integer> postIndexMap) {
    if (preL > preR) {
      return null;
    }
    TreeNode root = new TreeNode(preorder[preL]);
    if (preL == preR) {
      return root;
    }
    int leftRootIndex = postIndexMap.get(preorder[preL + 1]);
    int leftSize = leftRootIndex - postL;
    TreeNode left = construct(preorder, preL + 1, preL + 1 + leftSize, postorder, postL, postL + leftRootIndex - 1,
        postIndexMap);
    TreeNode right = construct(preorder, preL + 1 + leftSize + 1, preR, postorder, leftRootIndex + 1, postR - 1,
        postIndexMap);
    root.left = left;
    root.right = right;
    return root;
  }

  // https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
  public TreeNode bstFromPreorder(int[] preorder) {
    return buildBst(preorder, 0, preorder.length - 1);
  }

  public TreeNode buildBst(int[] preorder, int preL, int preR) {
    if (preL > preR) {
      return null;
    }
    if (preL == preR) {
      return new TreeNode(preorder[preL]);
    }
    TreeNode root = new TreeNode(preorder[preL]);
    int rightIndex = -1;
    for (int i = preL + 1; i <= preR; i++) {
      if (preorder[i] > root.val) {  // the next val greater than root.val is the start of right subtree
        rightIndex = i;
        break;
      }
    }
    if (rightIndex == -1) {
      root.right = null;
      root.left = buildBst(preorder, preL + 1, preR);
    } else {
      root.left = buildBst(preorder, preL + 1, rightIndex - 1);
      root.right = buildBst(preorder, rightIndex, preR);
    }
    return root;
  }

  // https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/

  // https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree

  // https://leetcode.com/problems/path-sum-ii/
  public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null) {
      return res;
    }
    LinkedList<Integer> cur = new LinkedList<>();
    dfs(root, targetSum, cur, res);
    return res;
  }

  public void dfs(TreeNode node, int target, LinkedList<Integer> cur, List<List<Integer>> res) {
    if (node.left == null && node.right == null) {
      if (target == node.val) {
        cur.addLast(node.val);
        res.add(new ArrayList<>(cur)); // 因为cur会随着递归回溯而变化, 所以这里需要new一个新的list存当前路径
        cur.removeLast();
      }
      return;
    }
    cur.addLast(node.val);
    if (node.left != null) {
      dfs(node.left, target - node.val, cur, res);
    }
    if (node.right != null) {
      dfs(node.right, target - node.val, cur, res);
    }
    cur.removeLast();
  }

  // TODO: https://leetcode.com/problems/path-sum-iii/

  public int pathSum_iii(TreeNode root, int targetSum) {
    return dfsFrom(root, targetSum) + pathSum_iii(root.left, targetSum) + pathSum_iii(root.right, targetSum);
  }

  private int dfsFrom(TreeNode node, int target) {
    if (node == null) {
      return 0;
    }
    int paths = (node.val == target ? 1 : 0);
    paths += dfsFrom(node.left, target - node.val);
    paths += dfsFrom(node.right, target - node.val);
    return paths;
  }

  // https://leetcode.com/problems/balanced-binary-tree/
  public boolean isBalanced(TreeNode root) {
    return dfsHeight(root) != -1;
  }

  private int dfsHeight(TreeNode node) {
    if (node == null) {
      return 0;
    }
    int leftHeight = dfsHeight(node.left);
    int rightHeight = dfsHeight(node.right);

    if (Math.abs(leftHeight - rightHeight) > 1) {
      return -1;
    }
    if (leftHeight == -1 || rightHeight == -1) { // 一旦有一侧的子树返回-1, 就会一直向上传递
      return -1;
    }
    return 1 + Math.max(leftHeight, rightHeight);
  }

  // https://leetcode.com/problems/validate-binary-search-tree/
  public boolean isValidBST(TreeNode root) {
    int[] nodeValues = new int[10001];
    Stack<TreeNode> stack = new Stack<>();
    int right = 0;
    TreeNode head = root;
    while (head != null || !stack.isEmpty()) {
      while (head != null) {
        stack.push(head);
        head = head.left;
      }
      head = stack.pop();
      nodeValues[right++] = head.val;
      head = head.right;
    }
    for (int i = 1; i < right; i++) {
      if (nodeValues[i] <= nodeValues[i - 1]) {
        return false;
      }
    }
    return true;
  }

  public boolean isValidBST1(TreeNode root) {
    // get inorder sequence and compare values
    Stack<TreeNode> stack = new Stack<>();
    TreeNode head = root;
    TreeNode pre = null;
    while (head != null || !stack.isEmpty()) {
      while (head != null) {
        stack.push(head);
        head = head.left;
      }
      head = stack.pop();
      if (pre != null && pre.val >= head.val) {
        return false;
      }
      pre = head;
      head = head.right;
    }
    return true;
  }

  // https://leetcode.com/problems/trim-a-binary-search-tree/
  public TreeNode trimBST(TreeNode root, int low, int high) {
    return trimNode(root, low, high);
  }

  public TreeNode trimNode(TreeNode node, int low, int high) {
    if (node == null) {
      return null;
    }
    if (node.val == low) {
      node.left = null;  // all left subtree out of range
      node.right = trimNode(node.right, low, high);
      return node;
    }
    if (node.val == high) {
      node.left = trimNode(node.left, low, high);
      node.right = null; // all right subtree out of range
      return node;
    }
    if (node.val > high) {
      return trimNode(node.left, low, high);
    }
    if (node.val < low) {
      return trimNode(node.right, low, high);
    }
    // low < node.val < high
    node.left = trimNode(node.left, low, high);
    node.right = trimNode(node.right, low, high);
    return node;
  }

  // https://leetcode.com/problems/house-robber-iii/
  public int rob(TreeNode root) {
    Info result = dfs(root);
    return Math.max(result.robHead, result.notRobHead);
  }

  public Info dfs(TreeNode node) {
    if (node == null) {
      return new Info(0, 0);
    }
    Info leftInfo = dfs(node.left);
    Info rightInfo = dfs(node.right);

    int robHead = node.val + leftInfo.notRobHead + rightInfo.notRobHead;
    int notRobHead =
        Math.max(leftInfo.robHead, leftInfo.notRobHead) + Math.max(rightInfo.robHead, rightInfo.notRobHead);
    return new Info(robHead, notRobHead);
  }

  private static class Info {

    int robHead;
    int notRobHead;

    public Info(int robHead, int notRobHead) {
      this.robHead = robHead;
      this.notRobHead = notRobHead;
    }
  }
}