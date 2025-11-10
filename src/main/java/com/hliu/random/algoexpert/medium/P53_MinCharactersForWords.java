package com.hliu.random.algoexpert.medium;

import java.util.Arrays;

/*
Write a function that takes in an array of words and returns the
smallest array of characters needed to form all of the words. The
characters don't need to be in any particular order.
For example, the characters ["y", "r", "o", "u"] are needed
to form the words ["your", "you", "or", "yo"] .
Note: the input words won't contain any spaces; however, they might
contain punctuation and/or special characters.
 */
public class P53_MinCharactersForWords {

  public static void main(String[] args) {

    String[] words = {"this", "that", "did", "deed", "them!", "a"};

    char[] res = new P53_MinCharactersForWords().minimumCharactersForWords(words);
    System.out.println(Arrays.toString(res));
  }

  public char[] minimumCharactersForWords(String[] words) {
    int[] map = new int[128];
    for (String w : words) {
      int[] help = Arrays.copyOf(map, map.length); // O(1)
      for (char c : w.toCharArray()) {
        if (help[c] > 0) {
          help[c]--;
        } else {
          map[c]++;
        }
      }
    }
    int c = 0;
    for (int i = 0; i < map.length; i++) {
      c += map[i];
    }
    char[] res = new char[c];
    int index = 0;
    for (int i = 0; i < map.length; i++) {
      while (map[i]-- != 0) {
        res[index++] = (char) i;
      }
    }
    // Write your code here.
    return res;
  }

}
