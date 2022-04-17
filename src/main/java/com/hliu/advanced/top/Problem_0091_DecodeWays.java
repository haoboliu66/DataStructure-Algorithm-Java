package src.main.java.advanced.top;

public class Problem_0091_DecodeWays {

    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        return process(s.toCharArray(), 0);
    }

    public int process(char[] str, int i) {
        if (i == str.length) {
            return 1;
        }
        if (str[i] == '0') {
            return 0;
        }
        // i < str.length, i + 1一定还在递归参数限制范围内
        // i位置是1, 那么可能的后续是自己一组 /  i和i+1一组
        if (str[i] == '1') {
            return process(str, i + 1) + (i + 2 <= str.length ? process(str, i + 2) : 0);
        }
        // i位置是2, 那么可能的后续是自己一组 /  i和i+1一组,但前提是 i+1<='6', 然后继续走i+2
        if (str[i] == '2') {
            int res = 0;
            res += process(str, i + 1);
            if (i + 1 < str.length && str[i + 1] <= '6') {
                // i < str.length - 1 ==> i + 1 < str.length
                res += process(str, i + 2);
            }
        }

        //如果 [i] >= '3'
        return process(str, i + 1);
    }

    public int numDecodingsDP(String s) {
        char[] str = s.toCharArray();
        int N = str.length;
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int i = N - 1; i >= 0; i--) {
            if (str[i] == '0') return 0;
            if (str[i] == '1') {
                dp[i] = dp[i + 1] + (i + 2 <= N ? dp[i + 2] : 0);
            } else if (str[i] == '2') {
                dp[i] = dp[i + 1];
                if (i + 1 < str.length && str[i + 1] <= '6') {
                    dp[i] += dp[i + 2];
                }
            } else {
                dp[i] = dp[i + 1];
            }
        }
        return dp[0];
    }


    public int numDecodings2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        return process2(s.toCharArray(), 0);
    }

    public int process2(char[] str, int i) {
        if (i == str.length) {
            return 1;
        }
        if (str[i] == '0') {
            return 0;
        }

        int ways = process2(str, i + 1);

        if (i + 1 == str.length) {
            return ways;
        }

        int num = 10 * (str[i] - '0') + (str[i + 1] - '0');
        if (num <= 26) {
            ways += process2(str, i + 2);
        }

        return ways;
    }

}
