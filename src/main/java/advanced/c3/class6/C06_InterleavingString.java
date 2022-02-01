package src.main.java.advanced.c3.class6;


public class C06_InterleavingString {

    /*
    lc 97. Interleaving String
    https://leetcode.com/problems/interleaving-string/
     */

    public static boolean isCross1(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) {
            return false;
        }
        if (s3.length() != s2.length() + s1.length()) {
            return false;
        }

        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        char[] aim = s3.toCharArray();
        int M = str1.length;
        int N = str2.length;
        //dp[i][j]含义: str1拿出i长度, str2拿出j长度,能否组成aim的i + j长度
        boolean[][] dp = new boolean[M + 1][N + 1];
        dp[0][0] = true;
        // 第一列: str1拿出0个长度, str2拿出j个长度, 能否组成aim的j长度
        for (int j = 1; j <= N; j++) {
            if (str2[j - 1] != aim[j - 1]) {
                break;
            }
            dp[0][j] = true;
        }
        // 第一行: str1拿出i个长度, str2拿出0个长度, 能否组成aim的i长度
        for (int i = 1; i <= M; i++) {
            if (str1[i - 1] != aim[i - 1]) {
                break;
            }
            dp[i][0] = true;
        }

        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {

                dp[i][j] = str1[i - 1] == aim[i + j - 1] && dp[i - 1][j]
                        ||
                        str2[j - 1] == aim[i + j - 1] && dp[i][j - 1];
            }
        }

        return dp[M][N];
    }


}
