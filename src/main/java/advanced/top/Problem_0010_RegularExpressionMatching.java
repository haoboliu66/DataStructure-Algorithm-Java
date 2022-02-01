package src.main.java.advanced.top;


public class Problem_0010_RegularExpressionMatching {

    /*
    c4 - class8
     */

    public static boolean isMatch(String s, String p) {

        char[] str = s.toCharArray();
        char[] pattern = p.toCharArray();

        if (!isValid(str, pattern)) {
            return false;
        }

        return process(str, 0, pattern, 0);
    }


    private static boolean isValid(char[] str, char[] pattern) {
        for (char c : str) {
            if (c == '*' || c == '.') return false;
        }
        for (int i = 0; i < pattern.length - 1; i++) {
            if ((i == 0 || pattern[i + 1] == '*') && pattern[i] == '*') {
                return false;
            }
        }
        return true;
    }


    public static boolean process(char[] str, int si, char[] pattern, int pi) {
        if (si == str.length) {
            if (pi == pattern.length) {
                return true;
            }
            if (pi + 1 < pattern.length && pattern[pi + 1] == '*') {
                return process(str, si, pattern, pi + 2);
            }
            return false;
        }

        if (pi == pattern.length) {
            return si == str.length;
        }

        // 都没终止, 两种情况

        // 1. [pi + 1] != '*'
        if (pi + 1 >= pattern.length || pattern[pi + 1] != '*') {
            return (pattern[pi] == '.' || str[si] == pattern[pi])
                    && process(str, si + 1, pattern, pi + 1);
        }

        // 2. [pi + 1] == '*'
        if (pattern[pi] != '.' && str[si] != pattern[pi]) {
            return process(str, si, pattern, pi + 2);
        }

        // str[si] == pattern[pi] && pattern[pi+1] == '*'

        if (process(str, si, pattern, pi + 2)) {  // 0份 *
            return true;
        }

        while (si < str.length && (str[si] == pattern[pi] || pattern[pi] == '.')) {
            if (process(str, si + 1, pattern, pi + 2)) {
                return true;
            }
            si++;
        }

        return false;
    }


    public static boolean isMatch2(String s, String p) {

        char[] str = s.toCharArray();
        char[] pattern = p.toCharArray();

        if (!isValid(str, pattern)) {
            return false;
        }
        int[][] dp = new int[str.length + 1][pattern.length + 1];
        // dp, 0表示未计算, -1表示false, 1表示true
        return process2(str, 0, pattern, 0, dp);
    }


    public static boolean process2(char[] str, int si, char[] pattern, int pi, int[][] dp) {
        if (dp[si][pi] != 0) {
            return dp[si][pi] == 1;
        }

        if (si == str.length) {
            if (pi == pattern.length) {
                dp[si][pi] = 1;
                return true;
            }
            if (pi + 1 < pattern.length && pattern[pi + 1] == '*') {
                dp[si][pi] = process2(str, si, pattern, pi + 2, dp) ? 1 : -1;
                return dp[si][pi] == 1;
            }

            dp[si][pi] = -1;
            return false;
        }

        if (pi == pattern.length) {
            dp[si][pi] = si == str.length ? 1 : -1;
            return dp[si][pi] == 1;
        }

        // 都没终止, 两种情况

        // 1. [pi + 1] != '*'
        if (pi + 1 >= pattern.length || pattern[pi + 1] != '*') {
            dp[si][pi] = (pattern[pi] == '.' || str[si] == pattern[pi])
                    && process2(str, si + 1, pattern, pi + 1, dp) ? 1 : -1;
            return dp[si][pi] == 1;
        }

        // 2. [pi + 1] == '*'
        if (pattern[pi] != '.' && str[si] != pattern[pi]) {
            dp[si][pi] = process2(str, si, pattern, pi + 2, dp) ? 1 : -1;
            return dp[si][pi] == 1;
        }

        // str[si] == pattern[pi] && pattern[pi+1] == '*'

        if (process2(str, si, pattern, pi + 2, dp)) {  // 0份 *
            dp[si][pi] = 1;
            return true;
        }

        while (si < str.length && (str[si] == pattern[pi] || pattern[pi] == '.')) {
            if (process2(str, si + 1, pattern, pi + 2, dp)) {
                dp[si][pi] = 1;
                return true;
            }
            si++;
        }

        dp[si][pi] = -1;
        return false;
    }


    public static void main(String[] args) {
        String s1 = "ab";
        String p = ".*c";
        System.out.println(isValid(s1.toCharArray(), p.toCharArray()));
        System.out.println(isMatch(s1, p));
    }
}
