package com.hliu.fundamental.linear.stack;

import java.util.Stack;

public class Problem_2390_RemovingStarsFromAString {


  /*
  Stack simulation with two pointers

  追踪validIndex, 不断地把合法的char写入validIndex即可
   */
  public String removeStars1(String s) {
    if (s == null || s.length() == 0) {
      return "";
    }
    char[] str = s.toCharArray();
    int currIndex = 0;
    int validIndex = 0;
    for (; currIndex < str.length; ) {
      if (str[currIndex] == '*') {
        validIndex = Math.max(validIndex - 1, 0);
        currIndex++;
      } else {
        str[validIndex++] = str[currIndex++];
      }
    }
    return new String(str, 0, validIndex);
  }

  public String removeStars(String s) {
    if (s == null || s.length() == 0) {
      return "";
    }
    char[] str = s.toCharArray();
    Stack<Character> stack = new Stack<>();
    StringBuilder sb = new StringBuilder();
    for (char c : str) {
      if (c != '*') {
        stack.push(c);
      } else {
        if (!stack.isEmpty()) {
          stack.pop();
        }
      }
    }
    while (!stack.isEmpty()) {
      sb.append(stack.pop());
    }
    return sb.reverse()
             .toString();
  }

}
