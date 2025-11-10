package com.hliu.random.algoexpert.hard;

/*
Write a function that takes in a string and returns its longest substring
without duplicate characters.
You can assume that there will only be one longest substring without
duplication

string = "clementisacap"
"mentisac"
 */
public class P43_LongestStringWithoutDuplicate {

  public static void main(String[] args) {
    String s  = "clementisacap";
    System.out.println(longestSubstringWithoutDuplication(s));
  }

  public static String longestSubstringWithoutDuplication(String s) {
    if (s == null || s.isEmpty()) {
      return s;
    }
    char[] str = s.toCharArray();
    int start = 0, end = 0;
    int L = 0, R = 0;
    int max = 0;
    int[] map = new int[128];
    for (; R < str.length;) {
      map[str[R]]++;
      while(map[str[R]] > 1){
        map[str[L++]]--;
      }
      R++;
      int len = R - L;
      if(len > max){
        max = len;
        start = L;
        end = R;
      }
    }

    return s.substring(start, end);
  }

}
