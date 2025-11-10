package com.hliu.random.blind75;

import java.util.Stack;

public class ValidParentheses {

  public boolean isValid(String s) {
    if (s == null || s.length() < 1) {
      return true;
    }
    char[] str = s.toCharArray();
    Stack<Character> leftBrackets = new Stack<>();
    for (int i = 0; i < str.length; ) {
      if (leftBrackets.isEmpty() || str[i] == '(' || str[i] == '{' || str[i] == '[') {
        leftBrackets.push(str[i++]);
        continue;
      }
      char curRight = str[i];
      Character prevLeft = leftBrackets.pop();

      if (getMatchLeft(curRight) != prevLeft) {
        return false;
      }
      i++;
    }
    return leftBrackets.isEmpty();
  }

  private Character getMatchLeft(char right) {
    if (right == ')') {
      return '(';
    } else if (right == '}') {
      return '{';
    } else {
      return '[';
    }
  }

}
