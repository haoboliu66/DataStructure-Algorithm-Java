package advanced.c4.class3;

import java.util.HashMap;
import java.util.Map;

public class C03_LongestSumEqualK {

    /*
    437. Path Sum III
     */
    public static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static int ans;

    public static int pathSum(Node head, int K) {
        if (head == null) {
            return 0;
        }
        ans = 0;
        Map<Integer, Integer> sumMap = new HashMap<>();
        sumMap.put(0, -1); //前缀和方法必预处理内容
        process(head, 0, 0, K, sumMap);
        return ans;
    }

    public static void process(Node node, int level, int preSum, int K, Map<Integer, Integer> sumMap) {
        if (node == null) {
            return;
        }
        int allSum = node.value + preSum;
        if (sumMap.containsKey(allSum - K)) { //包括了到root的距离, 如果allSum==K了,ans就直接连到root
            ans = Math.max(ans, level - sumMap.get(allSum - K));
        }
        if (!sumMap.containsKey(allSum)) {
            sumMap.put(allSum, level);
        }

        process(node.left, level + 1, allSum, K, sumMap);
        process(node.right, level + 1, allSum, K, sumMap);
        if (sumMap.get(allSum) == level) {
            sumMap.remove(allSum);
        }

    }


    public static void main(String[] args) {


    }

}
