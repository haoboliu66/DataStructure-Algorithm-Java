package com.hliu.random.leetcode.dp;

import java.util.ArrayList;
import java.util.List;

public class M_Problem_0241_AddParentheses {

  public List<Integer> diffWaysToCompute(String s) {
    List<Integer> res = new ArrayList<>();

    return process(s.toCharArray(), 0, s.length() - 1);
  }

  public List<Integer> process(char[] str, int L, int R) {
    if (R == L) {
      List<Integer> list = new ArrayList<>();
      list.add(str[L] - '0');
      return list;
    }
    if (R - L == 1) {
      List<Integer> list = new ArrayList<>();
      list.add((str[L] - '0') * 10 + (str[R] - '0'));
      return list;
    }

    List<Integer> res = new ArrayList<>();

    for (int i = L + 1; i < R; i++) {
      if (str[i] != '+' && str[i] != '-' && str[i] != '*') {
        continue;
      }
      char op = str[i];
      List<Integer> leftResult = process(str, L, i - 1);
      List<Integer> rightResult = process(str, i + 1, R);

      res.addAll(getParts(leftResult, rightResult, op));
    }
    return res;
  }

  private List<Integer> getParts(List<Integer> left, List<Integer> right, char op) {
    List<Integer> partialResults = new ArrayList<>();
    for (int i : left) {
      for (int j : right) {
        partialResults.add(calculate(i, j, op));
      }
    }
    return partialResults;
  }

  private int calculate(int a, int b, char op) {
    if (op == '+') {
      return a + b;
    }
    if (op == '-') {
      return a - b;
    }
    return a * b;
  }

}
