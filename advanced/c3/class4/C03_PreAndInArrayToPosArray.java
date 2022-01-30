package advanced.c3.class4;

import java.util.HashMap;

public class C03_PreAndInArrayToPosArray {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    public static int[] preInToPos1(int[] pre, int[] in) {
        if (pre.length != in.length) {
            return null;
        }
        int N = pre.length;
        int[] pos = new int[N];

        process1(pre, 0, N - 1, in, 0, N - 1, pos, 0, N - 1);

        return pos;
    }

    public static void process1(int[] pre, int L1, int R1,
                                int[] in, int L2, int R2,
                                int[] pos, int L3, int R3) {
        if (L1 > R1 || L2 > R2) {
            return;
        }
        if (L1 == R1) {
            pos[L3] = pre[L1];
            return;
        }
        pos[R3] = pre[L1];
        int mid = L2;
        for (; mid < R2; mid++) {
            if (in[mid] == pre[L1]) {
                break;
            }
        }
        int leftSize = mid - L2;
        process1(pre, L1 + 1, L1 + leftSize, in, L2, mid - 1, pos, L3, L3 + leftSize - 1);
        process1(pre, L1 + leftSize + 1, R1, in, mid + 1, R2, pos, L3 + leftSize, R3 - 1);
    }


    // 递归过程中查找坐标的过程使用Map优化
    public static int[] preInToPos2(int[] pre, int[] in) {
        if (pre.length != in.length) {
            return null;
        }
        int N = pre.length;
        HashMap<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            indexMap.put(in[i], i);
        }
        int[] pos = new int[N];

        process2(pre, 0, N - 1, in, 0, N - 1, pos, 0, N - 1, indexMap);
        return pos;
    }

    public static void process2(int[] pre, int L1, int R1, int[] in, int L2, int R2, int[] pos, int L3, int R3, HashMap<Integer, Integer> indexMap) {
        if (L1 > R1) {
            return;
        }
        if (L1 == R1) {
            pos[L3] = pre[L1];
            return;
        }
        pos[R3] = pre[L1];
        int mid = indexMap.get(pre[L1]);
        int leftSize = mid - L2;

        //下标对应, 找到左树和右树对应的范围, 递归填到pos数组中
        process2(pre, L1 + 1, L1 + leftSize, in, L2, mid - 1, pos, L3, L3 + leftSize - 1, indexMap);
        process2(pre, L1 + leftSize + 1, R1, in, mid + 1, R2, pos, L3 + leftSize, R3 - 1, indexMap);
    }



}
