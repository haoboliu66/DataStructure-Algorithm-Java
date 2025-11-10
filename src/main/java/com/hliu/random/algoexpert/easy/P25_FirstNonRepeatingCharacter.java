package com.hliu.random.algoexpert.easy;

public class P25_FirstNonRepeatingCharacter {


  /*

  Write a function that takes in a string of
  lowercase English-alphabet letters and
  returns the index of the string's first nonrepeating character.
  The first non-repeating character is the first
  character in a string that occurs only once.

  If the input string doesn't have any nonrepeating characters, your function should return -1
   */
  public int firstNonRepeatingCharacter(String s) {
    int[] map = new int[128];
    char[] str = s.toCharArray();

    for (int i = 0; i < str.length; i++) {
      map[str[i]]++;
    }
    for (int i = 0; i < str.length; i++) {
      if (map[str[i]] == 1) {
        return i;
      }
    }
    return -1;
  }

}
