package com.hliu.advanced.c1.manacher;


public class AddShortestEnd {

  /*
  214. Shortest Palindrome
  https://leetcode.com/problems/shortest-palindrome/
   */
  public static String shortestEnd(String s) {
    if (s == null || s.length() == 0) {
      return null;
    }
    char[] str = manacherString(s);
    int[] pArr = new int[str.length];
    int R = -1;
    int C = -1;
//        int max = Integer.MIN_VALUE;
    int maxContainsEnd = -1;
    for (int i = 0; i < str.length; i++) {
      pArr[i] = R > i ? Math.min(R - i, pArr[i]) : 1;
      while (i + pArr[i] < str.length && i - pArr[i] > -1) {
        if (str[i + pArr[i]] == str[i - pArr[i]]) {
          pArr[i]++;
        } else {
          break;
        }
      }
      if (i + pArr[i] > R) {
        R = i + pArr[i];
        C = i;
//                max = Math.max(max, pArr[i]);
      }
      if (R == str.length) {
        maxContainsEnd = pArr[i];
        break;
      }
    }
    char[] res = new char[s.length() - (maxContainsEnd - 1)];
    for (int i = 0; i < res.length; i++) {
      res[res.length - 1 - i] = str[2 * i + 1];
    }

    return new String(res);
  }

  public static char[] manacherString(String s) {
    char[] charArray = s.toCharArray();
    char[] res = new char[charArray.length * 2 + 1];
    int index = 0;
    for (int i = 0; i < res.length; i++) {
      res[i] = (i & 1) == 0 ? '#' : charArray[index++];
    }
    return res;
  }


  public static void main(String[] args) {
    String str1 = "babad";
    System.out.println(longestPalindrome(str1));
  }


  public static String longestPalindrome(String s) {
    if (s == null || s.length() == 0) {
      return "";
    }

    char[] str = manacherString(s);
    int[] pArr = new int[str.length];
    int R = -1;
    int C = -1;
    int center = -1;
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < str.length; i++) {
      pArr[i] = R > i ? Math.min(R - i, pArr[2 * C - i]) : 1;
      while (i + pArr[i] < str.length && i - pArr[i] > -1) {
        if (str[i + pArr[i]] == str[i - pArr[i]]) {
          pArr[i]++;
        } else {
          break;
        }
      }

      if (i + pArr[i] > R) {
        R = i + pArr[i];
        C = i;
      }
      if (pArr[i] > max) { // 要用变量记录求出最长回文半径max时对应的中心位置
        center = i;
        max = pArr[i];
      }
    }

    int left = center - (max - 1);
    int right = center + (max - 1);
    String res = "";
    for (int i = left + 1; i < right; i += 2) {
      res += str[i];
    }

    return res;
  }


}
