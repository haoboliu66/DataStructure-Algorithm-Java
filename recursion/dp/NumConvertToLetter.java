package recursion;

/**
 * @author andy-liu
 * @date 2020/5/24 - 10:51 AM
 */
public class NumConvertToLetter {

    public static int convert(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return process(str.toCharArray(), 0);
    }

    //str 0...i-1位置已经处理完, 只看i...往后的位置
    public static int process(char[] str, int i) {
        if (i == str.length) { //base case
            return 1;
        }
        if (str[i] == '0') {
            return 0;
        }
        if (str[i] == '1') {
            int res = process(str, i + 1);
            if (i + 1 < str.length) {
                res += process(str, i + 2);
            }
            return res;
        }
        if (str[i] == '2') {
            int res = process(str, i + 1); // i自己一组, i + 1向下递归
            if (i + 1 < str.length && str[i + 1] >= '0' && str[i + 1] <= '6') {
                // i 和 i + 1组合,  i + 2去递归
                res += process(str, i + 2);
            }
            return res;
        }
        // str[i] = '3' ~ '9'
        return process(str, i + 1);
    }


    public static int convertDP(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int i = N - 1; i >= 0; i--) {//因为N是边界, 已经单独赋值了, 所以从N-1开始即可
            if (str[i] == '0') {
                dp[i] = 0;
            }
            else if (str[i] == '1') {
                dp[i] = dp[i + 1];
                if (i + 1 < N) {
                    dp[i] += dp[i + 2];
                }
            }
            else if (str[i] == '2') {
                dp[i] = dp[i + 1];
                if (i + 1 < N && str[i + 1] >= '0' && str[i + 1] <= '6') {
                    dp[i] += dp[i + 2];
                }
            }
            // str[i] = '3' ~ '9'
            else dp[i] = dp[i + 1];
        }
        return dp[0];
    }


    public static void main(String[] args) {
        int count = convert("1111");
        int count1 = convertDP("1111");
        System.out.println(count);
        System.out.println(count1);
    }

}
