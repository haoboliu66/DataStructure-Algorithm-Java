package advanced.c3.class6;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author andy-liu
 * @date 2020/7/9 - 8:58 PM
 */
public class C04_TopKSumCrossTwoArrays {
    /*
    Q: 两个有序数组arr1,arr2,一定整数K, 求两个数累加和最大的前K个, 两个数必须分别来自arr1和arr2

    大根堆 + 二维表
     */

    static class Node {
        int index1;
        int index2;
        int sum;

        public Node(int index1, int index2, int sum) {
            this.index1 = index1;
            this.index2 = index2;
            this.sum = sum;
        }
    }

    //  sum从大到小排序
    static class NodeComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o2.sum - o1.sum;
        }
    }


    // arr1,arr2有序
    public static int[] topKSum(int[] arr1, int[] arr2, int K) {
        if (arr1 == null || arr2 == null) {
            return null;
        }

        int M = arr1.length;
        int N = arr2.length;

        K = Math.min(K, M * N); //无效参数过滤, 累加和共有 M * N 种组合

        int[] res = new int[K];
        int index = 0;

        PriorityQueue<Node> maxHeap = new PriorityQueue<>(new NodeComparator());

        boolean[][] isEnter = new boolean[M][N];

        //最大的sum一定是arr1[M-1] + arr2[N-1]
        Node max = new Node(M - 1, N - 1, arr1[M - 1] + arr2[N - 1]);
        maxHeap.add(max);
        isEnter[M - 1][N - 1] = true;

        while (index < K) {
            Node cur = maxHeap.poll();
            int arrIndex1 = cur.index1;
            int arrIndex2 = cur.index2;
            res[index++] = cur.sum;

            if (arrIndex1 - 1 >= 0 && !isEnter[arrIndex1 - 1][arrIndex2]) {
                isEnter[arrIndex1 - 1][arrIndex2] = true;
                maxHeap.add(new Node(arrIndex1 - 1, arrIndex2, arr1[arrIndex1 - 1] + arr2[arrIndex2]));
            }

            if (arrIndex2 - 1 >= 0 && !isEnter[arrIndex1][arrIndex2 - 1]) {
                isEnter[arrIndex1][arrIndex2 - 1] = true;
                maxHeap.add(new Node(arrIndex1, arrIndex2 - 1, arr1[arrIndex1] + arr2[arrIndex2 - 1]));
            }
        }

        return res;
    }

    // For test, this method is inefficient but absolutely right
    public static int[] topKSumTest(int[] arr1, int[] arr2, int topK) {
        int[] all = new int[arr1.length * arr2.length];
        int index = 0;
        for (int i = 0; i != arr1.length; i++) {
            for (int j = 0; j != arr2.length; j++) {
                all[index++] = arr1[i] + arr2[j];
            }
        }
        Arrays.sort(all);
        int[] res = new int[Math.min(topK, all.length)];
        index = all.length - 1;
        for (int i = 0; i != res.length; i++) {
            res[i] = all[index--];
        }
        return res;
    }

    public static int[] generateRandomSortArray(int len) {
        int[] res = new int[len];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * 50000) + 1;
        }
        Arrays.sort(res);
        return res;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static boolean isEqual(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null || arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i != arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int a1Len = 5000;
        int a2Len = 4000;
        int k = 20000000;
        int[] arr1 = generateRandomSortArray(a1Len);
        int[] arr2 = generateRandomSortArray(a2Len);
        long start = System.currentTimeMillis();
        int[] res = topKSum(arr1, arr2, k);
        long end = System.currentTimeMillis();
        System.out.println(end - start + " ms");

        start = System.currentTimeMillis();
        int[] absolutelyRight = topKSumTest(arr1, arr2, k);
        end = System.currentTimeMillis();
        System.out.println(end - start + " ms");

        System.out.println(isEqual(res, absolutelyRight));

    }

}
