package src.main.java.advanced.top;


public class Problem_0044_WildcardMatching {

    public static boolean isMatch1(String s, String p) {
        if ("*".equals(p)) {
            return true;
        }

        return process1(s.toCharArray(), p.toCharArray(), 0, 0);
    }


    /*
    pattern中[pi...]往后能不能匹配出str中[si...]往后的所有
     */
    public static boolean process1(char[] str, char[] pattern, int si, int pi) {
        if (si == str.length) {
            if (pi == pattern.length) {
                return true;
            } else {
                return pattern[pi] == '*' && process1(str, pattern, si, pi + 1);
//                for (int i = pi; i < pattern.length; i++) {
//                    if (pattern[i] != '*') {
//                        return false;
//                    }
//                }
//                return true;
            }
        }
        if (pi == pattern.length) {
            return si == str.length;
        }

        // si后面还有, pi后面也还有
        if (pattern[pi] == '?') {
            return process1(str, pattern, si + 1, pi + 1);
        }

        if (pattern[pi] == '*') {
            for (int i = 0; si + i <= str.length; i++) {
                if (process1(str, pattern, si + i, pi + 1)) {
                    return true;
                }
            }
        }
//        if(pattern[pi] == str[si]){
//            return process1(str,pattern,si + 1,pi + 1);
//        }else{
//            return false;
//        }

        // [pi] != '*' && [pi] != '?'
        return pattern[pi] == str[si] && process1(str, pattern, si + 1, pi + 1);
    }


    public static boolean isMatch2(String s, String p) {
        if ("*".equals(p)) {
            return true;
        }

        int[][] dp = new int[s.length() + 1][p.length() + 1];
        return process2(s.toCharArray(), p.toCharArray(), 0, 0, dp);
    }

    /*
    pattern中[pi...]往后能不能匹配出str中[si...]往后的所有
     */
    public static boolean process2(char[] str, char[] pattern, int si, int pi, int[][] dp) {
        if (dp[si][pi] != 0) {
            return dp[si][pi] == 1;
        }

        if (si == str.length) {
            if (pi == pattern.length) {
                dp[si][pi] = 1;
                return true;
            } else {

                dp[si][pi] = pattern[pi] == '*' && process2(str, pattern, si, pi + 1, dp) ? 1 : -1;
                return dp[si][pi] == 1;
            }
        }
        if (pi == pattern.length) {
            dp[si][pi] = si == str.length ? 1 : -1;
            return dp[si][pi] == 1;
        }

        // si后面还有, pi后面也还有
        if (pattern[pi] == '?') {
            dp[si][pi] = process2(str, pattern, si + 1, pi + 1, dp) ? 1 : -1;
            return dp[si][pi] == 1;
        }

        if (pattern[pi] == '*') {
            for (int i = 0; si + i <= str.length; i++) {
                if (process2(str, pattern, si + i, pi + 1, dp)) {
                    dp[si][pi] = 1;
                    return true;
                }
            }
        }

        dp[si][pi] = pattern[pi] == str[si] && process2(str, pattern, si + 1, pi + 1, dp) ? 1 : -1;
        return dp[si][pi] == 1;
    }


    public static boolean isMatch3(String s, String p) {
        if ("*".equals(p)) {
            return true;
        }
        char[] str = s.toCharArray();
        char[] pattern = p.toCharArray();
        int M = str.length;
        int N = pattern.length;
        boolean[][] dp = new boolean[M + 1][N + 1];

        dp[M][N] = true;
        for (int j = N - 1; j >= 0; j--) {
            dp[M][j] = pattern[j] == '*' && dp[M][j + 1];
        }

        for (int i = M - 1; i >= 0; i--) {
            for (int j = N - 1; j >= 0; j--) {
                if (pattern[j] != '?' && pattern[j] != '*') {
                    dp[i][j] = str[i] == pattern[j] && dp[i + 1][j + 1];

                } else if (pattern[j] == '?') {
                    dp[i][j] = dp[i + 1][j + 1];

                } else {

                    for (int k = 0; i + k <= str.length; k++) {
                        if(dp[i][j] = dp[i + k][j + 1]){
                            break;
                        }
                    }

                }
            }
        }

        return dp[0][0];
    }


}
