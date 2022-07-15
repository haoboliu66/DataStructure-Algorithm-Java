package com.hliu.advanced.c3.class1;

public class C02_03_Parentheses {

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
  Q3: 求一个有效括号字符串内, 最大的括号嵌套层数
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

  /*
  Q4: 给定一个括号字符串, 求最长有效括号子串长度
  lc 32 https://leetcode.com/problems/longest-valid-parentheses/
   */
  public static int longestValidParentheses(String s) {
    if (s == null || s.length() < 2) {
      return 0;
    }
    char[] str = s.toCharArray();
    int[] dp = new int[str.length];
    int max = 0;
    for (int i = 1; i < str.length; i++) {
      if (str[i] == ')') {
        // str[i] == ')'
        // i >= 1
        int preIndex = i - 1 - dp[i - 1];
        if (preIndex >= 0 && str[preIndex] == '(') {
          dp[i] = 2 + dp[i - 1];
          dp[i] += (preIndex - 1 > 0 ? dp[preIndex - 1] : 0);
        }
      }
      max = Math.max(dp[i], max);
    }
    return max;
  }

  public static void main(String[] args) {
    int len = 200, times = 100000;
    String s;
    System.out.println("Started");
    for (int i = 0; i < times; i++) {
      s = generateRandomParenthesis(len);
      int res1 = longestValidParentheses(s);
      int res2 = longestValidParenthesesRight(s);
      if (res1 != res2) {
        System.out.println("Oops");
        System.out.println(s);
        break;
      }
    }
    System.out.println("Done");
  }

  public static int longestValidParenthesesRight(String s) {
    if (s == null || s.length() < 2) {
      return 0;
    }
    char[] str = s.toCharArray();
    int max = 0;
    int count;
    for (int i = 0; i < str.length; i++) {
      count = 0;
      for (int j = i; j < str.length; j++) {
        if (str[j] == '(') {
          count++;
        }
        if (str[j] == ')') {
          count--;
        }
        if (count < 0) {
          break;
        }
        if (count == 0) {
          max = Math.max(max, j - i + 1);
        }
      }
    }
    return max;
  }

  private static String generateRandomParenthesis(int len) {
    char[] str = new char[(int) ((Math.random() + 1) * len)];
    for (int i = 0; i < str.length; i++) {
      str[i] = (Math.random() > 0.5 ? ')' : '(');
    }
    return new String(str);
  }


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
