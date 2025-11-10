package com.hliu.fundamental.linear.twopointer;

/*

Given an array of characters chars, compress it using the following algorithm:

Begin with an empty string s. For each group of consecutive repeating characters in chars:

If the group's length is 1, append the character to s.
Otherwise, append the character followed by the group's length.
The compressed string s should not be returned separately, but instead, be stored in the input character array chars. Note that group lengths that are 10 or longer will be split into multiple characters in chars.

After you are done modifying the input array, return the new length of the array.

You must write an algorithm that uses only constant extra space.


Input: chars = ["a","a","b","b","c","c","c"]
Output: Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]

Input: chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
Output: Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].
Explanation: The groups are "a" and "bbbbbbbbbbbb". This compresses to "ab12".

 */
public class Problem_0443_StringCompression {

  public static int compress(char[] chars) {
    if (chars.length == 1) {
      return 1;
    }
    int count = 1;
    char prev = chars[0];
    char curr = ' ';
    int validIndex = 0;

    for (int i = 1; i < chars.length; i++) {
      curr = chars[i];
      if (curr == prev) { // no new char found, inc the counter
        count++;
      } else {
        // curr != prev, compress the last char, only do insert count number when count > 1
        chars[validIndex] = prev;
        validIndex++;
        if (count > 1) {
          char[] countArr = String.valueOf(count)
                                  .toCharArray();
          for (int j = 0; j < countArr.length; j++) {
            chars[validIndex++] = countArr[j];
          }
        }
        count = 1;
        prev = curr;
      }
    }
    chars[validIndex] = prev;
    validIndex++;
    if (count > 1) {
      char[] countArr = String.valueOf(count)
                              .toCharArray();
      for (int j = 0; j < countArr.length; ) {
        chars[validIndex++] = countArr[j++];
      }
    }
    return validIndex;
  }

  public static int compress1(char[] chars) {
    if (chars.length == 1) {
      return 1;
    }
    int count = 0;
    char prev = chars[0];
    int validIndex = 0;

    for (int i = 0; ; ) {
      while (i < chars.length && chars[i] == prev) {
        count++;
        i++;
      }
      chars[validIndex] = prev;
      validIndex++;
      if (count > 1) {
        char[] countArr = String.valueOf(count)
                                .toCharArray();
        for (int j = 0; j < countArr.length; j++) {
          chars[validIndex++] = countArr[j];
        }
      }
      count = 0;
      if (i >= chars.length) {
        break;
      }
      prev = chars[i];
    }

    return validIndex;
  }

}
