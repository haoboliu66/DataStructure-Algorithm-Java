package com.hliu.advanced.c4.class1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class C05_ExpressionAddOperators {


    /*
    https://leetcode.com/problems/expression-add-operators/
     */

  // not done
  public static void main(String[] args) {
    String num = "123";
    int target = 6;
    List<String> res = addOperators(num, target);
    System.out.println(res);
  }

  public static List<String> addOperators(String num, int target) {
    List<String> ans = new ArrayList<>();
    LinkedList<Character> path = new LinkedList<>();
    path.addLast(num.charAt(0));
    process(num.toCharArray(), 1, path, target, ans);
    return ans;
  }

  // 从i.... 完成rest目标, 结果收集到ans中
  public static void process(char[] str, int i, LinkedList<Character> path, int rest, List<String> ans) {
    if (i == str.length) {
      if (rest == 0) {
        StringBuilder sb = new StringBuilder();
        for (Character c : path) {
          sb.append(c);
        }
        ans.add(sb.toString());
      }
      return;
    }

    char c = str[i];
    path.addLast(c);
    // add +
    path.addLast('+');
    int tmp = (str[i] - '0') + (str[i - 1] - '0');
    process(str, i + 1, path, rest - tmp, ans);
    path.pollLast();
    // add -
    path.addLast('-');
    tmp = (str[i - 1] - '0') - (str[i] - '0');
    process(str, i + 1, path, rest - tmp, ans);
    path.pollLast();
    // add *
    if (c != '0') {
      path.addLast('*');
      tmp = (c - '0') * (str[i - 1] - '0');
      process(str, i + 1, path, rest - tmp, ans);
      path.pollLast();
    }
    path.pollLast();
  }

}
