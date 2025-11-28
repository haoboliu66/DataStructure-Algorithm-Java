package com.hliu.fundamental.linear.stackqueue.stack.monotonic;

import java.util.Stack;

public class Score_Of_Parentheses {

  public static void main(String[] args) {

    String s = "(())";

    System.out.println(scoreOfParentheses(s));

  }

  // ( () (()) )
  public static int scoreOfParentheses(String s) {
    if (s == null || s.length() < 2) {
      return 0;
    }
    char[] str = s.toCharArray();
    Stack<Character> stack = new Stack<>();
    int sum = 0;
    for (int i = 0; i < str.length; i++) {
      int cur = 0;
      if (str[i] == '(') {
        stack.push(str[i]);
        continue;
      }
      if (!stack.isEmpty()) {
        cur++;
        stack.pop();
      }
      if (cur != 0) {
        sum += (1 << (cur - 1));
      }
    }

    return sum;
  }


}
