package com.hliu.random.leetcode;

import java.util.HashMap;

public class Problem_1328_Break_A_Palindrome {

  public static String breakPalindrome(String palindrome) {
    if (palindrome == null || "".equals(palindrome) || palindrome.length() < 2) {
      return "";
    }

    char[] str = palindrome.toCharArray();
    HashMap<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < str.length / 2; i++) {
      map.put(str[i], map.getOrDefault(str[i], 0) + 1);
    }
    int minIndex = 0;
    while (minIndex != str.length) {
      if (str[minIndex] != 'a') {
        str[minIndex] = 'a';
        break;
      }
      minIndex++;
    }
//        int i = 0, j = str.length - 1;
//        while (i != j) {
//            if (map.get(str[i]) < minIndex)
//            i++;
//            j--;
//        }
    return new String(str);
  }
}
