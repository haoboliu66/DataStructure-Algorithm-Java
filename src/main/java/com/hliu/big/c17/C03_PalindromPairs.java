package com.hliu.big.c17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class C03_PalindromPairs {

  // https://leetcode.com/problems/palindrome-pairs/


  public static List<List<Integer>> palindromePairs2(String[] words) {
    List<List<Integer>> ans = new ArrayList<>();
    int n = words.length;

    return ans;
  }

  // Brute force  O(N^2 * K)
  public static List<List<Integer>> palindromePairs(String[] words) {
    List<List<Integer>> ans = new ArrayList<>();
    int n = words.length;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (i != j) {
          if (isPalindrome(words[i] + words[j])) {
            List<Integer> list = Arrays.asList(i, j);
            //List<Integer> list1 = Arrays.asList(j, i);
            ans.add(list);
            //ans.add(list1);
          }
        }
      }
    }
    return ans;
  }

  public static boolean isPalindrome(String s) {
    for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
      if (s.charAt(i) != s.charAt(j)) {
        return false;
      }
    }
    return true;

  }

}
