package advanced.c4.class8;

public class C01_RegularExpressionMatching {

    /*
    10. Regular Expression Matching
     */

    public static boolean isMatch(String s, String p) {
        char[] str = s.toCharArray();
        char[] match = p.toCharArray();

        int M = match.length;
        int N = str.length;

        boolean[][] dp = new boolean[M][N];
        dp[0][0] = (str[0] == match[0] || match[0] == '.');

        for (int i = 1; i < N; i++) {
            if (dp[i - 1][0]) {
                dp[i][0] = match[i] == '*';
            } else {
                dp[i][0] = match[i - 1] == '*' && (match[i] == str[0] || match[i] == '.');
            }
        }

        for (int i = 1; i < M; i++) {
            for (int j = 1; j < N; j++) {

                if (match[i] == '*') {
                    dp[i][j] = dp[i - 1][j];
                    if (match[i - 1] == str[j]) {

                    } else {

                    }
                }

                if (str[j] == match[i] || match[i] == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                }

            }
        }

        return dp[M - 1][N - 1];
    }

    public static void main(String[] args) {
        String s = "aab";
        String p = "c*a*b";
        System.out.println(isMatch(s, p));
    }
}
