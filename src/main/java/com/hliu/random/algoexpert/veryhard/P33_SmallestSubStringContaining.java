package com.hliu.random.algoexpert.veryhard;

/*
You're given two non-empty strings: a big
string and a small string. Write a function that
returns the smallest substring in the big string
that contains all of the small string's
characters.
Note that:
The substring can contain other
characters not found in the small string.
The characters in the substring don't
have to be in the same order as they
appear in the small string.
If the small string has duplicate
characters, the substring has to contain
those duplicate characters (it can also
contain more, but not fewer).
You can assume that there will only be one
relevant smallest substring.

Input:     String s1 = "abcd$ef$axb$c$";
          String s2 = "$$abf";
Output:   f$axb$

 */
public class P33_SmallestSubStringContaining {


  public static void main(String[] args) {

    String s1 = "abcd$ef$axb$c$";
    String s2 = "$$abf";
    System.out.println(smallestSubstringContaining(s1, s2));

  }

  public static String smallestSubstringContaining(String s1, String s2) {
    int count = s2.length();
    int windowSize = s2.length();
    int[] map = new int[256];
    for (char c : s2.toCharArray()) {
      map[c]++;
    }
    for (int i = 0; i < map.length; i++) {
      if (map[i] == 0) {
        map[i] = Integer.MIN_VALUE;
      }
    }
    char[] str = s1.toCharArray();
    int L = 0, R = 0;
    for (; L <= str.length - s2.length() && R < str.length; ) {

      if (R - L + 1 < windowSize) {
        if (map[str[R]] != Integer.MIN_VALUE) {
          map[str[R]]--;
          count--;
        }
        R++;
        continue;
      }
      if (count == 0) {
        return s1.substring(L, R + 1);
      }
      if (map[str[L]] >= 0) {
        map[str[L]]++;
        count++;
      }
      L++;

    }

    return "";
  }

}
