package sys.c4;

public class C07_InterleavingString {

//    https://leetcode.com/problems/interleaving-string/

    public boolean isInterleave(String s1, String s2, String s3) {
        int M = s1.length();
        int N = s2.length();
        if (M + N != s3.length()) return false;
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        char[] str3 = s3.toCharArray();
        boolean[][] dp = new boolean[M + 1][N + 1];
        // dp[i][j]
        dp[0][0] = true;
        for (int i = 1; i <= M; i++) {
            dp[i][0] = dp[i - 1][0] && str1[i - 1] == str3[i - 1];
        }
        for (int j = 1; j <= N; j++) {
            dp[0][j] = dp[0][j - 1] && str2[j - 1] == str3[j - 1];
        }

        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                dp[i][j] = str1[i - 1] == str3[i + j - 1] && dp[i - 1][j];
                dp[i][j] |= str2[j - 1] == str3[i + j - 1] && dp[i][j - 1];
            }
        }

        return dp[M][N];
    }

}
