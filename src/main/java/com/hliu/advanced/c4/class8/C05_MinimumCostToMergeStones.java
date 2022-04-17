package src.main.java.advanced.c4.class8;

public class C05_MinimumCostToMergeStones {

    /*
    1000. Minimum Cost to Merge Stones

    1167. Minimum Cost to Connect Sticks
     */

    public static int mergeStones1(int[] stones, int K) {
        int N = stones.length;
        if ((N - 1) % (K - 1) > 0) {
            return -1;
        }

        int[] preSum = new int[N + 1];
        for (int i = 0; i < N; i++) {
            //preSum[1] = preSum[0] + stones[0];
            preSum[i + 1] = preSum[i] + stones[i];
        }

        return process1(0, N - 1, 1, stones, K, preSum);
    }

    // part >= 1
    // arr[L..R]  一定要弄出part份，返回最低代价
    // arr、K、presum（前缀累加和数组，求i..j的累加和，就是O(1)了）
    public static int process1(int L, int R, int part, int[] arr, int K, int[] presum) {
        if (L == R) {  //只有一个数字, 只有part==1才能合成
            return part == 1 ? 0 : -1;
        }
        // [L...R]不只一个数字
        if (part == 1) {  //N个数字一次合并K个,想合成1份
            int next = process1(L, R, K, arr, K, presum); //子问题:先合并成K份, 才能最后合成1份
            if (next == -1) {
                return -1;
            } else {
                // L...R合成K份的代价是next, K份再合成1份的代价就是L...R的整体累加和
                return next + presum[R + 1] - presum[L];
            }
        } else {   // part > 1
            int ans = Integer.MAX_VALUE;
            for (int mid = L; mid < R; mid += K - 1) {
                //枚举左侧的第一份位置 [L...mid], 因为右侧至少要留下一份, 所以不能取到R
                // 剩下的part-1部分范围是[mid+1 ... R]
                int next1 = process1(L, mid, 1, arr, K, presum);
                int next2 = process1(mid + 1, R, part - 1, arr, K, presum);
                if (next1 != -1 && next2 != -1) {
                    ans = Math.min(ans, next1 + next2);
                }
            }
            return ans;
        }
    }


    // Memoization
    public static int mergeStones2(int[] stones, int K) {
        int N = stones.length;
        if ((N - 1) % (K - 1) > 0) {
            return -1;
        }
        int[] preSum = new int[N + 1];
        for (int i = 0; i < N; i++) {
            //preSum[1] = preSum[0] + stones[0];
            preSum[i + 1] = preSum[i] + stones[i];
        }
        int[][][] dp = new int[N][N][K + 1];

        return process2(0, N - 1, 1, stones, K, preSum, dp);
    }

    // part >= 1
    // arr[L..R]  一定要弄出part份，返回最低代价
    // arr、K、presum（前缀累加和数组，求i..j的累加和，就是O(1)了）
    public static int process2(int L, int R, int part, int[] arr, int K, int[] presum, int[][][] dp) {
        if (dp[L][R][part] != 0) {
            return dp[L][R][part];
        }

        if (L == R) {  //只有一个数字, 只有part==1才能合成
            return part == 1 ? 0 : -1;
        }
        // [L...R]不只一个数字
        if (part == 1) {  //N个数字一次合并K个,想合成1份
            int next = process2(L, R, K, arr, K, presum, dp); //先合并成K份, 才能最后合成1份
            if (next == -1) {
                return -1;
            } else {
                return next + presum[R + 1] - presum[L];
            }
        } else {   // part > 1
            int ans = Integer.MAX_VALUE;
            for (int mid = L; mid < R; mid += K - 1) {
                //枚举前缀
                int next1 = process2(L, mid, 1, arr, K, presum, dp);
                int next2 = process2(mid + 1, R, part - 1, arr, K, presum, dp);
                if (next1 != -1 && next2 != -1) {
                    ans = Math.min(ans, next1 + next2);
                }
            }
            dp[L][R][part] = ans;
            return ans;
        }
    }

    public static void main(String[] args) {


    }


}
