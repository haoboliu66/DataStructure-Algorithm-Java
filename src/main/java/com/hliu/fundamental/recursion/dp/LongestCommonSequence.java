package com.hliu.fundamental.recursion.dp;


public class LongestCommonSequence {

    public static int getCommon(char[] str1, char[] str2) {
        if (str1 == null || str2 == null || str1.length == 0 || str2.length == 0) {
            return 0;
        }

        int[][] dp = new int[str1.length][str2.length];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int i = 1; i < str1.length; i++) { // 填第一列
            dp[i][0] = Math.max(dp[i - 1][0], str1[i] == str2[0] ? 1 : 0);
        }
        for (int j = 1; j < str2.length; j++) { // 填第一行
            dp[0][j] = Math.max(dp[0][j - 1], str2[j] == str1[0] ? 1 : 0);
        }

        //填普遍位置
        for (int i = 1; i < str1.length; i++) {
            for (int j = 1; j < str2.length; j++) {

                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);//情况2, 3取大值

                if (str1[i] == str2[j]) { //情况4, 因为情况4比情况1大, 所以可以舍弃情况1
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
//                else {
//                    dp[i][j] = dp[i - 1][j - 1]; //被舍弃的情况1
//                }
            }
        }
        return dp[str1.length - 1][str2.length - 1];
    }

    public static void main(String[] args) {
        String str1 = "abcd";
        String str2 = "aaade";
        System.out.println(getCommon(str1.toCharArray(), str2.toCharArray()));
    }

}
