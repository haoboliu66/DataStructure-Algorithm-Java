package com.hliu.random.leetcode;

public class Problem_2109_AddingSpacesToString {


  public static void main(String[] args) {
    String s = "LeetcodeHelpsMeLearn";
    int[] spaces = {8, 13, 15};
    System.out.println(addSpaces(s, spaces));
  }

  public static String addSpaces(String s, int[] spaces) {
    char[] str = s.toCharArray();
    int total = str.length + spaces.length;

    char[] res = new char[total];

    // where should we put all the spaces
    for(int i = 0; i < spaces.length; i++) {
      int spaceIndex = spaces[i] + i;
      res[spaceIndex] = ' ';
    }

    // fill in rest of the chars
    int j = str.length - 1;
    for(int i = res.length - 1; i >= 0; i--) {
      if(res[i] != ' ') {
        res[i] = str[j];
        j--;
      }
    }
    return new String(res);
  }

}
