package leetcode;

import java.util.*;

public class E_Problem_0257_BinaryTreePaths {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /* Recursive */
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) return Collections.emptyList();
        List<String> res = new ArrayList<>();
        LinkedList<String> path = new LinkedList<>();
        dfs(root, res, path);
        return res;
    }

    public String collect(LinkedList<String> path) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < path.size(); i++) {
            if (i == path.size() - 1) {
                sb.append(path.get(i));
            } else {
                sb.append(path.get(i)).append("->");
            }
        }
        return sb.toString();
    }

    public void dfs(TreeNode node, List<String> res, LinkedList<String> path) {
        if (node == null) return;

        if (node.left == null && node.right == null) {
            path.addLast(String.valueOf(node.val));
            String ans = collect(path);
            res.add(ans);
            path.pollLast();
            return;
        }

        path.addLast(String.valueOf(node.val));
        dfs(node.left, res, path);
        dfs(node.right, res, path);
        path.pollLast();
    }

    /* Iterative */
    public List<String> binaryTreePaths2(TreeNode root) {
        if (root == null) return Collections.emptyList();
        List<String> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        ArrayList<String> ans = new ArrayList<>();
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            ans.add(String.valueOf(cur.val));
            if(cur.left == null && cur.right == null) {
                ans.add(String.valueOf(cur));
                res.addAll(ans);
                ans = new ArrayList<>();
            }
            if (cur.right != null) {
                ans.add(cur.right.val + "->");
                stack.push(cur.right);
            }
            if (cur.left != null) {
                ans.add(cur.left.val + "->");
                stack.push(cur.left);
            }
        }

        return res;
    }

    public static void main(String[] args) {
    }

}
