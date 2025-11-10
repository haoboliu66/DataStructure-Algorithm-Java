package com.hliu.fundamental.linear.twopointer;

/*

Given a string word to which you can insert letters "a", "b" or "c" anywhere and any number of times, return the minimum number of letters that must be inserted so that word becomes valid.

A string is called valid if it can be formed by concatenating the string "abc" several times.


Input: word = "b"
Output: 2
Explanation: Insert the letter "a" right before "b", and the letter "c" right next to "b" to obtain the valid string "abc".

Input: word = "aaa"
Output: 6
Explanation: Insert letters "b" and "c" next to each "a" to obtain the valid string "abcabcabc".

Input: word = "abc"
Output: 0
Explanation: word is already valid. No modifications are needed.

 */
public class Problem_2645_MinimumAdditionsToMakeValidString {


  // 如果我们检查的不只是是abc呢? 例如abcdefg, 这个方法的计算更加通用
  public int addMinimum1(String word) {
    char[] str = word.toCharArray();
    int addition = 0;
    char expectedChar = 'a';
    for (int i = 0; i < str.length; i++) {
      char curr = str[i];
      if (curr == expectedChar) {
        expectedChar = nextChar(expectedChar);
        continue;
      }

      if (curr > expectedChar) {
        addition += (curr - expectedChar);
      } else {
        int x = ('c' - prevChar(expectedChar) + (curr - 'a'));
        addition += x;
      }

      //     abcde   97 98 99 100 101

      //     curr exp
      //      d    c          b  d    (x - y)
      //      x  > y        'e' - x + y - 'a'
      //  e.g c - a > 0      'e'   'c'
      // e.g. c - b > 0      'e' - 'c' + 'b' - 'a'      //   a b  c

      //    x < y       //   'b'  'd'    cb      e - c + b - a
      //e.g c - e < 0        //  'e' - 'd' + 'c' - 'a'

      expectedChar = nextChar(curr);
    }

    // 退出循环后, 检查最后一个char, 如果不是c (max char of the string), 就要相应的加值
    char last = str[str.length - 1];
    addition += ('c' - last);

    return addition;
  }


  public int addMinimum(String word) {
    if (word == null || word.length() == 0) {
      return 3;
    }
    char[] str = word.toCharArray();
    if (str.length == 1) {
      return 2;
    }
    int addition = 0;
    char expectedChar = 'a';
    for (int i = 0; i < str.length; i++) {

      char curr = str[i];
      if (curr == expectedChar) {
        expectedChar = nextChar(expectedChar);
        continue;
      }
      // curr 'a' , expected 'b'   // aa  + 2
      // curr 'b', expected 'a'   //  cb  + 1
      // curr 'b', expected 'c'   //  bb  + 2
      // curr 'c', expected 'b'   //  ac  + 1
      if (curr - expectedChar == 1) {
        addition += 1;
      } else if (curr - expectedChar == -1) {
        addition += 2;
      }
      // 2
      // curr 'c', expected 'a'   //  cabc + 2
      // curr 'a', expected 'c'   //  bca + 1
      else if (curr - expectedChar == 2) {
        addition += 2;
      } else if (curr - expectedChar == -2) {
        addition += 1;
      }
      expectedChar = nextChar(curr);
    }

    // 退出循环后, 如果expectedChar是a才能表明是valid, 所以如果是其他letter, 就要额外加入
    if (expectedChar == 'b') {
      addition += 2;
    } else if (expectedChar == 'c') {
      addition += 1;
    }

    return addition;
  }

  private char prevChar(char c) {
    if (c == 'a') {
      return 'c';
    }
    if (c == 'b') {
      return 'a';
    }
    return 'b';
  }

  private char nextChar(char c) {
    if (c == 'a') {
      return 'b';
    }
    if (c == 'b') {
      return 'c';
    }
    return 'a';
  }

}
