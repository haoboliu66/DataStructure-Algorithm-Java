package src.main.java.fundamental.tree;

public class TreeNode {

    public String value;
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(String value) {
        this.value = value;
    }

    public TreeNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "SBTNode{" +
                "value='" + value + '\'' +
                '}';
    }
}
