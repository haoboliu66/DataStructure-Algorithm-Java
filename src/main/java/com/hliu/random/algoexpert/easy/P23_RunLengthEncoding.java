package com.hliu.random.algoexpert.easy;

public class P23_RunLengthEncoding {

  public static void main(String[] args) {

    String s = "........______=========AAAA   AAABBBB   BBB";

    System.out.println(runLengthEncoding(s));
  }

  public static String runLengthEncoding(String s) {

    char[] str = s.toCharArray();
    for (int i = 1; i < str.length; i++) {
      if (str[i] == str[i - 1]) {
        str[i - 1] = 0;
      }
    }

    StringBuilder res = new StringBuilder();
    int preIndex = -1;
    for (int i = 0; i < str.length; i++) {
      while (str[i] == 0) {
        i++;
      }
      int len = i - preIndex;
      preIndex = i;
      res.append(getString(str[i], len));
    }

    return res.toString();
  }

  public static String getString(char c, int len) {
    if (len < 10) {
      return String.valueOf(len) + c;
    }
    int group = len / 9;
    int last = len % 9;
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < group; i++) {
      sb.append(String.valueOf(9) + c);
    }
    sb.append(String.valueOf(last) + c);

    return sb.toString();
  }


  // Solution
  

}
