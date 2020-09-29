package advanced.c3.class1;

import org.junit.Test;

/**
 * @author andy-liu
 * @date 2020/6/25 - 8:56 AM
 */
public class C0203_Parentheses {

    /*
    Q1:括号配对, 只有一种括号(), 判断是否valid
     */
    public static boolean isValid(String s) {
        if ("".equals(s)) {
            return true;
        }
        char[] str = s.toCharArray();
        int count = 0;
        for (int i = 0; i < str.length; i++) {
            count += str[i] == '(' ? 1 : -1;
            if (count < 0) {
                return false;
            }
        }
        return count == 0;
    }

    /*
    Q2:  921 Minimum Add to Make Parentheses Valid
     */
    public static int needParentheses(String s) {

        char[] str = s.toCharArray();
        int count = 0;
        int need = 0;
        for (int i = 0; i < str.length; i++) {
            count += str[i] == '(' ? 1 : -1;
            if (count == -1) {
                need++;
                count = 0;
            }
        }
        return count + need;
    }


    /*
    Q: 求一个有效括号字符串内, 最大的括号嵌套层数
     */
    public static int parenthesesDeep(String s) {
        if ("".equals(s)) {
            return 0;
        }
        char[] str = s.toCharArray();
        int count = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < str.length; i++) {
            count += (str[i] == '(' ? 1 : -1);
            max = Math.max(max, count);
        }
        return max;
    }

    public static int deep(String s) {
        char[] str = s.toCharArray();
        int count = 0;
        int max = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '(') {
                max = Math.max(max, ++count);
            } else {
                count--;
            }
        }
        return max;
    }

    @Test
    public void test() {
        String s = "((((()))(((())))))";
        System.out.println(parenthesesDeep(s));
        System.out.println(deep(s));
    }

    /*
    Q: 给定一个有效括号字符串, 最长有效括号子串长度
     */
    public static int maxLength(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        char[] str = s.toCharArray();
        int[] dp = new int[str.length];
        int pre = 0;
        int res = 0;
        // dp[0] == 0
        for (int i = 1; i < str.length; i++) {
            if (str[i] == ')') {   //以每个位置结尾, 只需要考虑以右括号结尾的情况, 如果以左括号结尾dp[i]=0
                pre = i - dp[i - 1] - 1; // i - 1位置有效子串的最左侧
                if (pre >= 0 && str[pre] == '(') {
                    dp[i] = dp[i - 1] + 2;  //dp[i]至少有dp[i-1]+2这么长
                    dp[i] += pre > 0 ? dp[pre - 1] : 0;//再向前看一个位置, 如果不是0就加起来
                }
            }
            res = Math.max(res, dp[i]); //每个位置的值都跟max比
        }


        return res;
    }


}
