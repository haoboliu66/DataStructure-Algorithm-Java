package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class E_Problem_0872_LeafSimilarTrees {

    private class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
    }


    /* My Solution   */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        process(root1, list1);
        process(root2, list2);
        if(list1.size() != list2.size()) return false;
        for(int i = 0; i < list1.size(); i++){
            if(list1.get(i) != list2.get(i)) return false;
        }
        // Or return list1.equals(list2);
        return true;
    }

    public void process(TreeNode root, List<Integer> list){
        if(root.left == null && root.right == null){
            list.add(root.val);
        }
        if(root.left != null){
            process(root.left, list);
        }
        if(root.right != null){
            process(root.right, list);
        }
    }

    /* From Discuss - Lee  */
    public boolean leafSimilar2(TreeNode root1, TreeNode root2) {
       Stack<TreeNode> s1 = new Stack<>(), s2 = new Stack<>();
        s1.push(root1); s2.push(root2);
        while(!s1.isEmpty() && !s2.isEmpty()){
            if(dfs(s1) != dfs(s2)) return false;
        }
        return s1.isEmpty() && s2.isEmpty();
    }

    public int dfs(Stack<TreeNode> stack){
        while(true){
            TreeNode cur = stack.pop();
            if(cur.left == null && cur.right == null) return cur.val;
            if(cur.right != null){
                stack.push(cur.right);
            }
            if(cur.left != null){
                stack.push(cur.left);
            }
        }
    }


}
