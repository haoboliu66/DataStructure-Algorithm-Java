package src.main.java.advanced.c4.class5;

public class C01_PalindromeSubsequence {

    /*
    516. Longest Palindromic Subsequence
    Done
     */
    public static void main(String[] args) {
        String s = "bbbab";
        int res = longestPalindromeSubseq(s);
        System.out.println(res);
    }

    public static int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] str = s.toCharArray();
        int n = str.length;

        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int i = 0; i < n - 1; i++) {
            dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
        }
        for (int j = 2; j < n; j++) {
            for (int i = 0; i < n - j; i++) {
                dp[i][i + j] = Math.max(dp[i + 1][i + j], dp[i][i + j - 1]);
                if (str[i] == str[i + j]) {
                    dp[i][i + j] = Math.max(dp[i][i + j], 2 + dp[i + 1][i + j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }

}
