package src.main.java.advanced.c5.class2;

public class C03_BinaryTreeCameras {

    /*
    968. Binary Tree Cameras
     */

    private static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.value = val;
        }
    }

    public static int minCameraCover1(TreeNode root) {

        Info res = process1(root);
        return (int) Math.min(Math.min(res.coveredHasCamera, res.coveredNoCamera), res.uncovered + 1);
    }

    // X节点的状态: 三种可能性
    public static Info process1(TreeNode node) {
        if (node == null) {
            return new Info(Integer.MAX_VALUE, Integer.MAX_VALUE, 0);
        }
        Info left = process1(node.left);
        Info right = process1(node.right);
        long uncovered = left.coveredNoCamera + right.coveredNoCamera;

        long coveredHasCamera = Math.min(
                Math.min(left.coveredHasCamera, left.coveredNoCamera), left.uncovered) +
                Math.min(Math.min(right.coveredHasCamera, right.coveredNoCamera),
                        right.uncovered)
                + 1;

        long coveredNoCamera = Math.min(
                Math.min(left.coveredHasCamera + right.coveredHasCamera,
                        left.coveredNoCamera + right.coveredHasCamera)
                , left.coveredHasCamera + right.coveredNoCamera);

        return new Info(uncovered, coveredHasCamera, coveredNoCamera);
    }


    public static class Info {
        long uncovered;
        long coveredHasCamera;
        long coveredNoCamera;

        public Info(long uncovered, long coveredHasCamera, long coveredNoCamera) {
            this.uncovered = uncovered;
            this.coveredHasCamera = coveredHasCamera;
            this.coveredNoCamera = coveredNoCamera;
        }
    }

    public static enum Status {
        UNCOVERED, COVERED_NO_CAMERA, COVERED_HAS_CAMERA
    }

    public static int minCameraCover2(TreeNode root) {
        NodeInfo res = process2(root);
        return res.status == Status.UNCOVERED ? res.camera + 1 : res.camera;
    }

    public static NodeInfo process2(TreeNode X) {
        if (X == null) {
            return new NodeInfo(Status.COVERED_NO_CAMERA, 0);
        }
        NodeInfo left = process2(X.left);
        NodeInfo right = process2(X.right);
        // left和right有一个是uncovered, 那当前节点X一定要放相机
        if (left.status == Status.UNCOVERED || right.status == Status.UNCOVERED) {
            return new NodeInfo(Status.COVERED_HAS_CAMERA, left.camera + right.camera + 1);
        }
        // left和right都是covered
        // left和right有一个是有相机的, 当前节点X就不用放了
        if (left.status == Status.COVERED_HAS_CAMERA || right.status == Status.COVERED_HAS_CAMERA) {
            return new NodeInfo(Status.COVERED_NO_CAMERA, left.camera + right.camera);
        }

        // left和right都是covered
        // left和right都是COVERED_NO_CAMERA, 那么当前节点X也无需放相机,
        // 且贪心策略决定, 当前节点X状态是UNCOVERED, 需要由X的parent去cover

        return new NodeInfo(Status.UNCOVERED, left.camera + right.camera);
    }

    public static class NodeInfo {
        Status status;
        int camera;

        public NodeInfo(Status status, int camera) {
            this.status = status;
            this.camera = camera;
        }
    }


    public static void main(String[] args) {

    }

}
