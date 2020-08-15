package advanced.c3.class3;

/**
 * @author andy-liu
 * @date 2020/6/30 - 8:12 AM
 */
public class C05_LCSubstring {


    public static int longestCommonSubstring1(String s1, String s2) {
        if (s1 == null || s2 == null || "".equals(s1) || "".equals(s2)) {
            return 0;
        }

        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int M = str1.length;
        int N = str2.length;
        int[][] dp = new int[M][N];
        int max = 0;
        int end = 0;
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int i = 1; i < M; i++) {
            dp[i][0] = str2[0] == str1[i] ? 1 : 0;
        }
        for (int j = 1; j < N; j++) {
            dp[0][j] = str1[0] == str2[j] ? 1 : 0;
        }

        for (int i = 1; i < M; i++) {
            for (int j = 1; j < N; j++) {
                dp[i][j] = str1[i] == str2[j] ? dp[i - 1][j - 1] + 1 : 0;
                if (dp[i][j] > max) {
                    max = dp[i][j];
                    end = i;
                }
            }
        }
        System.out.println(s1.substring(end - max + 1, end + 1));
        return max;
    }


    public static int longestCommonSubstring2(String s1, String s2) {
        if (s1 == null || s2 == null || "".equals(s1) || "".equals(s2)) {
            return 0;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();

        //起始位置 (row,col)在二维表右上角
        int row = 0;
        int col = str2.length - 1;

        int max = 0;
        int end = 0;

        while (row < str1.length) {  //先向左走, 再向下走, 所以row是最后的边界
            int i = row;
            int j = col;
            int len = 0;
            // 走每条对角线
            while (i < str1.length && j < str2.length) {
                if (str1[i] == str2[j]) {
                    len++;
                } else {
                    len = 0;
                }
                i++;
                j++;
                //每走一步就检查更新max
                if (len > max) {
                    end = i;
                    max = len;
                }
            }

            //判断第一行走没走完
            if (col > 0) {
                col--;
            } else {
                row++;
            }

        }
        System.out.println(s1.substring(end - max + 1, end + 1));
        return max;
    }

    public static void main(String[] args) {
        String s1 = "abcde";
        String s2 = "abcdtuopruabc";
        int len1 = longestCommonSubstring1(s1, s2);
        int len2 = longestCommonSubstring2(s1, s2);
        System.out.println(len1);
        System.out.println(len2);

    }
}
