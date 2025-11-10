package com.hliu.fundamental.linear.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*

Input: s = "3[a]2[bc]"
Output: "aaabcbc"

Input: s = "3[a2[c]]"
Output: "accaccacc"

Input: s = "2[abc]3[cd]ef"
Output: "abcabccdcdcdef"

 */
public class Problem_0394_DecodeString {


  public String decodeString(String s) {
    char[] str = s.toCharArray();
    Stack<Character> stack = new Stack<>();

    for (char c : str) {

      if (isAlphaNumeric(c) || c == '[') {
        stack.push(c);
      } else {
        StringBuilder sb = new StringBuilder();
        // ']'
        while (!stack.isEmpty() && stack.peek() != '[') {
          sb.append(stack.pop());
        }
        stack.pop(); // discard '['

        StringBuilder number = new StringBuilder();
        while (!stack.isEmpty() && isNumeric(stack.peek())) {
          number.append(stack.pop());
        }
        int count = Integer.parseInt(number.reverse()
                                           .toString());
        List<Character> repeatStr = new ArrayList<>();
        for (int i = 0; i < count; i++) {
          for (int j = sb.length() - 1; j >= 0; j--) {
            repeatStr.add(sb.charAt(j));
          }
        }
        for (char x : repeatStr) {
          stack.push(x);
        }
      }

    }

    List<Character> characters = new ArrayList<>(stack);
    StringBuilder res = new StringBuilder();
    for (char c : characters) {
      res.append(c);
    }

    return res.toString();
  }

  private boolean isNumeric(char c) {
    return c >= '0' && c <= '9';
  }

  private boolean isAlphaNumeric(char c) {
    return (c >= '0' && c <= '9') || (c >= 'a' && c <= 'z');
  }

}
