package src.main.java.advanced.c3.class6;

import java.util.*;

public class C04_TopKSumCrossTwoArrays {

    /*
    Q: 两个有序数组arr1,arr2,一整数K, 求两个数累加和最大的前K个, 两个数必须分别来自arr1和arr2

    大根堆 + 二维表 Set

    https://www.nowcoder.com/practice/7201cacf73e7495aa5f88b223bbbf6d1
     */

    private static class SumNode {
        int index1;
        int index2;
        int sum;

        public SumNode(int i, int j, int s) {
            index1 = i;
            index2 = j;
            sum = s;
        }
    }


    public static int[] topKSumInTwoSortedArrays(int[] arr1, int[] arr2, int K) {
        int[] res = new int[K];
        int i = 0;
        PriorityQueue<SumNode> maxHeap = new PriorityQueue<>((SumNode n1, SumNode n2) -> {
            return n2.sum - n1.sum;
        });
        int index1 = arr1.length - 1, index2 = arr2.length - 1;
        SumNode cur = new SumNode(index1, index2, arr1[index1] + arr2[index2]);
        maxHeap.offer(cur);
        Set<Long> visited = new HashSet<>();
        visited.add(index(index1, index2, arr1.length));
        while (!maxHeap.isEmpty()) {
            cur = maxHeap.poll();
            res[i++] = cur.sum;
            index1 = cur.index1;
            index2 = cur.index2;
            visited.remove(index(index1, index2, arr1.length));
            if (i == K) {
                break;
            }
            if (index1 > 0 && !visited.contains(index(index1 - 1, index2, arr1.length))) {
                maxHeap.offer(new SumNode(index1 - 1, index2, arr1[index1 - 1] + arr2[index2]));
                visited.add(index(index1 - 1, index2, arr1.length));
            }
            if (index2 > 0 && !visited.contains(index(index1, index2 - 1, arr1.length))) {
                maxHeap.offer(new SumNode(index1, index2 - 1, arr1[index1] + arr2[index2 - 1]));
                visited.add(index(index1, index2 - 1, arr1.length));
            }
        }
        return res;
    }

    // 二维 转 一维
    private static long index(int index1, int index2, int N) {
        return (long) (index1 * N + index2);
    }



    public static int[] topKSumInTwoArrays(int[] arr1, int[] arr2, int K) {
        int m = arr1.length, n = arr2.length, index = 0;
        int[] res = new int[K];
        PriorityQueue<SumNode> maxHeap = new PriorityQueue<>((n1, n2) -> n2.sum - n1.sum);
        boolean[][] used = new boolean[m][n];
        maxHeap.add(new SumNode(m - 1, n - 1, arr1[m - 1] + arr2[n - 1]));
        used[m - 1][n - 1] = true;
        while (!maxHeap.isEmpty()) {
            SumNode cur = maxHeap.poll();
            int index1 = cur.index1;
            int index2 = cur.index2;
            res[index++] = cur.sum;
            if (index >= K) break;
            if (index1 - 1 >= 0 && !used[index1 - 1][index2]) {
                maxHeap.offer(new SumNode(index1 - 1, index2, arr1[index1 - 1] + arr2[index2]));
                used[index1 - 1][index2] = true;
            }
            if (index2 - 1 >= 0 && !used[index1][index2 - 1]) {
                maxHeap.offer(new SumNode(index1, index2 - 1, arr1[index1] + arr2[index2 - 1]));
                used[index1][index2 - 1] = true;
            }
        }
        return res;
    }





    /* ---------------------------- */
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

    // For test
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

    private static int[] generateRandomSortArray(int len) {
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
        int a1Len = 10;
        int a2Len = 12;
        int k = 7;
        int[] arr1, arr2;
        System.out.println("Go");
        for (int i = 0; i < 10000; i++) {
            arr1 = generateRandomSortArray(a1Len);
            arr2 = generateRandomSortArray(a2Len);
            int[] res = topKSum(arr1, arr2, k);
            int[] res2 = topKSumInTwoArrays(arr1, arr2, k);
            int[] absolutelyRight = topKSumTest(arr1, arr2, k);
            if (!isEqual(res, res2) || !isEqual(res2, absolutelyRight)) {
                System.out.println("res: " + Arrays.toString(res));
                System.out.println("res2: " + Arrays.toString(res2));
                System.out.println("right: " + Arrays.toString(absolutelyRight));
                System.out.println("Oops");
                break;
            }
        }
        System.out.println("Done");

    }

}
