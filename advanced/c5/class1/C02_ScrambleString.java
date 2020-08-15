package advanced.c5.class1;

/**
 * @author andy-liu
 * @date 2020/8/13 - 2:23 PM
 */
public class C02_ScrambleString {

    /*
    87. Scramble String
     */

    // boolean f(str1, L1, R1, str2, L2, R2)
    public static boolean isScramble1(String s1, String s2) {

        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        if (str1.length != str2.length) {
            return false;
        }
        int N = str1.length;

        return process(str1, 0, str2, 0, N);
    }

    public static boolean process(char[] str1, int L1, char[] str2, int L2, int size) {
        if (size == 1) {
            return str1[L1] == str2[L2];
        }

        // 枚举左侧的部分
        for (int leftPart = 1; leftPart < size; leftPart++) {
            // 每个第一刀, 都对应两种情况
            // 1. L1的第一部分对应L2的第一部分
            // 2. L1的第一部分对应L2的第二部分
            if ((process(str1, L1, str2, L2, leftPart) &&
                    process(str1, L1 + leftPart, str2, L2 + leftPart, size - leftPart))

                    || (process(str1, L1, str2, size + L2 - leftPart, leftPart) &&
                    process(str1, L1 + leftPart, str2, L2, size - leftPart))) {
                return true;
            }
        }

        return false;
    }

    //Memoization
    public static boolean isScramble2(String s1, String s2) {

        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        if (str1.length != str2.length) {
            return false;
        }
        int N = str1.length;
        int[][][] dp = new int[N][N][N + 1];
        // dp[i][j][k] == 1 true
        // dp[i][j][k] == -1 false
        // dp[i][j][k] == 0 还未计算过
        return processDP(str1, 0, str2, 0, N, dp);
    }

    public static boolean processDP(char[] str1, int L1, char[] str2, int L2, int size, int[][][] dp) {
        if (dp[L1][L2][size] != 0) {
            return dp[L1][L2][size] == 1;
        }

        if (size == 1) {
            return str1[L1] == str2[L2];
        }

        for (int leftPart = 1; leftPart < size; leftPart++) {
            if ((processDP(str1, L1, str2, L2, leftPart, dp) &&
                    processDP(str1, L1 + leftPart, str2, L2 + leftPart, size - leftPart, dp))

                    || (processDP(str1, L1, str2, size + L2 - leftPart, leftPart, dp) &&
                    processDP(str1, L1 + leftPart, str2, L2, size - leftPart, dp))) {
                dp[L1][L2][size] = 1;
                return true;
            }
        }
        dp[L1][L2][size] = -1;
        return false;
    }


    public static boolean isScrambleDP(String s1, String s2) {

        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        if (str1.length != str2.length) {
            return false;
        }
        int N = str1.length;
        boolean[][][] dp = new boolean[N][N][N + 1];
        /*
        if (size == 1) {
            return str1[L1] == str2[L2];
        }
         */
        // base case
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j][1] = str1[i] == str2[j];
            }
        }

        /*
        for (int leftPart = 1; leftPart < size; leftPart++) {
            if ((processDP(str1, L1, str2, L2, leftPart, dp) &&
                    processDP(str1, L1 + leftPart, str2, L2 + leftPart, size - leftPart, dp))
                    || (processDP(str1, L1, str2, size + L2 - leftPart, leftPart, dp) &&
                    processDP(str1, L1 + leftPart, str2, L2, size - leftPart, dp))) {
                dp[L1][L2][size] = 1;
                return true;
            }
        }
         */
        for (int k = 1; k <= N; k++) {
            for (int i = 0; i <= N - k; i++) {
                for (int j = 0; j <= N - k; j++) {
                    for (int leftPart = 1; leftPart < k; leftPart++) {
                        dp[i][j][k] = (dp[i][j][leftPart] && dp[i + leftPart][j + leftPart][k - leftPart]) ||
                                (dp[i][j + k - leftPart][leftPart] && dp[i + leftPart][j][k - leftPart]);
                        if (dp[i][j][k]) {
                            break;
                        }
                    }
                }
            }
        }

        return dp[0][0][N];
    }


    /*
    algorithm zuo
     */
    public static boolean isScramble3(String s1, String s2) {
        if ((s1 == null && s2 != null) || (s1 != null && s2 == null)) {
            return false;
        }
        if (s1 == null && s2 == null) {
            return true;
        }
        if (s1.equals(s2)) {
            return true;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        if (!sameTypeSameNumber(str1, str2)) {
            return false;
        }
        int N = s1.length();
        return process(str1, str2, 0, 0, N);
    }

    // 返回str1[从L1开始往右长度为size的子串]和str2[从L2开始往右长度为size的子串]是否互为旋变字符串
    // 在str1中的这一段和str2中的这一段一定是等长的，所以只用一个参数size
    public static boolean process(char[] str1, char[] str2, int L1, int L2, int size) {
        if (size == 1) {
            return str1[L1] == str2[L2];
        }
        // 枚举每一种情况，有一个计算出互为旋变就返回true。都算不出来最后返回false
        for (int leftPart = 1; leftPart < size; leftPart++) {
            if (
                // 如果1左对2左，并且1右对2右
                    (process(str1, str2, L1, L2, leftPart)
                            && process(str1, str2, L1 + leftPart, L2 + leftPart, size - leftPart))

                            ||
                            // 如果1左对2右，并且1右对2左
                            (process(str1, str2, L1, L2 + size - leftPart, leftPart)
                                    && process(str1, str2, L1 + leftPart, L2, size - leftPart))) {
                return true;
            }
        }
        return false;
    }

    public static boolean sameTypeSameNumber(char[] str1, char[] str2) {
        if (str1.length != str2.length) {
            return false;
        }
        int[] map = new int[256];
        for (int i = 0; i < str1.length; i++) {
            map[str1[i]]++;
        }
        for (int i = 0; i < str2.length; i++) {
            if (--map[str2[i]] < 0) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        String test1 = "abcd";
        String test2 = "cdab";
        System.out.println(isScramble1(test1, test2));
        System.out.println(isScramble2(test1, test2));
        System.out.println(isScrambleDP(test1, test2));

        test1 = "abcd";
        test2 = "cadb";
        System.out.println(isScramble1(test1, test2));
        System.out.println(isScramble2(test1, test2));
        System.out.println(isScrambleDP(test1, test2));

        test1 = "bcdebcdebcdebcdebcdebcdebcdebcdebcdebcdebcdebcdebcdebcdebcdebcdebcdebcdebcdebcdebcdebcde";
        test2 = "ebcdeebcdebebcdebcdebcdecdebcbcdcdebcddebcbdebbbcdcdebcdeebcdebcdeebcddeebccdebcdbcdebcd";
        // System.out.println(isScramble1(test1, test2));
        System.out.println(isScramble2(test1, test2));
        System.out.println(isScramble2(test1, test2));
        //System.out.println(dp(test1, test2));
        System.out.println(isScrambleDP(test1, test2));
    }


}
