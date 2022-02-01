package src.main.java.advanced.c4.class2;

public class C04_DistinctSubsequences {

    /*
    115. Distinct Subsequences -- hard
     */

    public static int numDistinct2(String S, String T) {

        char[] str1 = S.toCharArray();
        char[] str2 = T.toCharArray();
        int M = str1.length;
        int N = str2.length;
        int[][] dp = new int[M][N];// dp[i][j]: str1 0...i删成str2的 0...j有几种方法

        dp[0][0] = str1[0] == str2[0] ? 1 : 0;//第一行其余位置都是0
        // 相等的话, 有一种方案, 叫什么也不删

        int t = dp[0][0];
        for (int i = 1; i < M; i++) { //填第一列
            dp[i][0] = str1[i] == str2[0] ? ++t : t;
        }
        //普遍位置 0...i 变成 0...j
        for (int i = 1; i < M; i++) {
            for (int j = 1; j < N; j++) {
                dp[i][j] = dp[i - 1][j]; // 0...i-1变成0...j

                if (str1[i] == str2[j]) { // 0...i-1变成0...j-1
                    dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }
        return dp[M - 1][N - 1];
    }


    public static void main(String[] args) {
        String s1 = "aabb";
        String s2 = "ab";
        int res = numDistinct2(s1, s2);
        System.out.println(res);
    }


}
