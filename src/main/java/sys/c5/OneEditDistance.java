package src.main.java.sys.c5;

public class OneEditDistance {

    // https://leetcode.com/problems/one-edit-distance/

    public static void main(String[] args) {
        String s = "ab";
        String t = "b";
        System.out.println(isOneEditDistance0(s, t));
    }

    public static boolean isOneEditDistance0(String s, String t) {
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        int M = str1.length, N = str2.length;
        return process(str1, 0, M - 1, str2, 0, N - 1);
    }

    private static boolean process(char[] str1, int L1, int R1, char[] str2, int L2, int R2) {

        int len1 = R1 - L1 + 1;
        int len2 = R2 - L2 + 1;
        if (Math.abs(len1 - len2) > 1) {
            return false;
        }
        if (len1 == 0 && len2 == 0) return false;

        if (len1 == 0 && len2 == 1 || len2 == 0 && len1 == 1) {
            return true;
        }
        if (len1 == 1 && len2 == 1) {
            return str1[L1] != str2[L2];
        }

        if (len1 == len2) {
            // replace or equal
            if (str1[L1] != str2[L2] && str1[R1] != str2[R2]) {
                return false;
            }
            return process(str1, L1 + 1, R1 - 1, str2, L2 + 1, R2 - 1);

        } else if (len1 > len2) {
            // delete
            if (str1[L1] == str2[L2] && str1[R1] == str2[R2]) {
                return process(str1, L1 + 1, R1 - 1, str2, L2 + 1, R2 - 1);
            }
            if (str1[L1] != str2[L2]) {
                return process(str1, L1 + 1, R1 - 1, str2, L2, R2 - 1);
            } else {
                // str1[R1] != str2[R2]
                return process(str1, L1 + 1, R1 - 1, str2, L2 + 1, R2);
            }

        } else {
            // len1 < len2  insert
            if (str1[L1] == str2[L2] && str1[R1] == str2[R2]) {
                return process(str1, L1 + 1, R1 - 1, str2, L2 + 1, R2 - 1);
            }
            if (str1[L1] != str2[L2]) {
                return process(str1, L1, R1, str2, L2 + 1, R2);
            } else {
                // str1[R1] != str2[R2]
                return process(str1, L1, R1, str2, L2, R2 - 1);
            }
        }
    }


    public static boolean isOneEditDistance(String s, String t) {
        if (s.equals(t)) {
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        int M = str1.length, N = str2.length;
        if (Math.abs(M - N) > 1) return false;
        if (M == 1 && N == 0 || (M == 0 && N == 1)) return true;
        boolean[][] dp = new boolean[M][N];
        dp[0][0] = true;
        for (int i = 1; i < M; i++) {
            if (i == 1) {
                dp[i][0] = str1[0] == str2[0] || str1[1] == str2[0];
            }
        }
        for (int j = 1; j < N; j++) {
            if (j == 1) {
                dp[0][j] = str1[0] == str2[0] || str1[0] == str2[1];
            }
        }
        for (int i = 1; i < M; i++) {
            for (int j = 1; j < N; j++) {
                dp[i][j] = dp[i][j - 1] || dp[i - 1][j] || dp[i - 1][j - 1];

            }

        }
        return dp[M - 1][N - 1];
    }

}
