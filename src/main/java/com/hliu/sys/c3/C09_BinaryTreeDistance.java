package src.main.java.sys.c3;

import java.util.*;

public class C09_BinaryTreeDistance {

//    https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode() {

        }

        public TreeNode(int v) {
            val = v;
        }
    }

    /*
    生成一个parentMap, 然后以target节点开始开始进行BFS查找K层
     */

    public static List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        parentMap.put(root, null);
        generateParent(root, parentMap);

        List<Integer> ans = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();
        queue.offer(target);
        visited.add(target);
        int level = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (level == k){
                    ans.add(cur.val);
                }
                if (parentMap.get(cur) != null && !visited.contains(parentMap.get(cur))) {
                    queue.offer(parentMap.get(cur));
                    visited.add(parentMap.get(cur));
                }
                if (cur.left != null && !visited.contains(cur.left)) {
                    queue.offer(cur.left);
                    visited.add(cur.left);
                }
                if (cur.right != null && !visited.contains(cur.right)) {
                    queue.offer(cur.right);
                    visited.add(cur.right);
                }
            }
             if(level == k) break;
            level++;
        }

        return ans;
    }


    public static void generateParent(TreeNode treeNode, Map<TreeNode, TreeNode> parentMap) {
        if (treeNode.left == null && treeNode.right == null) {
            return;
        }
        if (treeNode.left != null) {
            parentMap.put(treeNode.left, treeNode);
            generateParent(treeNode.left, parentMap);
        }
        if (treeNode.right != null) {
            parentMap.put(treeNode.right, treeNode);
            generateParent(treeNode.right, parentMap);
        }
    }

}
