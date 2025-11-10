package com.hliu.random.leetcode;

import java.util.HashMap;
import java.util.Map;

public class Problem_2131_LongestPalindrome {

  /*
  Input: words = ["lc","cl","gg"]
  Output: 6

   */

  private static String reverse(String s) {
    return new StringBuilder(s).reverse()
                               .toString();
  }

  private static boolean duplicate(String s) {
    return s.charAt(0) == s.charAt(1);
  }

  public static void main(String[] args) {
    String[] words = {"lc", "cl", "gg"};
    System.out.println(longestPalindrome(words));

  }

  public static int longestPalindrome(String[] words) {
    int res = 0;
    Map<String, Integer> countMap = new HashMap<>();
    Map<String, Boolean> chanceMap = new HashMap<>();

    for (String word : words) {
      countMap.put(word, countMap.getOrDefault(word, 0) + 1);
    }

    for (String word : words) {
      if (duplicate(word)) {
        continue;
      }
      chanceMap.put(word, false);
      // [0] != [1]
      final String reversed = reverse(word);
      if (countMap.containsKey(reversed)) { // has a match
        if (!chanceMap.containsKey(reversed)) {
          chanceMap.put(word, true);
        }
      }
    }

    for (Map.Entry<String, Boolean> entry : chanceMap.entrySet()) {
      String word = entry.getKey();
      Boolean possible = entry.getValue();
      // no chance to match , skip duplicate words
      if (!possible) {
        continue;
      }
      int count = countMap.get(word);
      int reversedCount = countMap.get(reverse(word));
      // lc lc lc 3:2 cl cl
      int pair = Math.min(count, reversedCount);
      res += (pair * 4);
    }

    boolean foundOdd = false;
    for (String word : words) {
      if (!duplicate(word)) {
        continue;
      }
      int count = countMap.get(word);
      if (count % 2 == 0) {
        res += count * 2;
      } else {

        if (!foundOdd) {
          foundOdd = true;
        }
      }
    }

    return res + (foundOdd ? 2 : 0);
  }

}
