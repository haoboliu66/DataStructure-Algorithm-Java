package com.hliu.sys.c8;

import java.util.LinkedList;

public class C01_BasicCalculator {

  // https://leetcode.com/problems/basic-calculator-ii/
  public static int calculate2(String s) {
    char[] str = shedSpace(s);
    LinkedList<String> queue = new LinkedList<>();
    int cur = 0;
    for (int i = 0; i < str.length; i++) {
      if (str[i] <= '9' && str[i] >= '0') {
        cur = cur * 10 + (str[i] - '0');
      } else {
        // is operator
        addNum(queue, cur);
        cur = 0;
        queue.offerLast(String.valueOf(str[i]));
      }
    }
    addNum(queue, cur);
    return getNum(queue);
  }

  private static int getNum(LinkedList<String> queue) {
    int res = Integer.parseInt(queue.pollFirst());
    while (!queue.isEmpty()) {
      String operator = queue.pollFirst();
      int num = Integer.parseInt(queue.pollFirst());
      res += ("+").equals(operator) ? num : (~num + 1);
    }
    return res;
  }

  private static void addNum(LinkedList<String> queue, int num) {
    if (!queue.isEmpty()) {
      String operator = queue.peekLast();
      if ("*".equals(operator) || "/".equals(operator)) {
        queue.pollLast();
        int val = Integer.parseInt(queue.pollLast());
        num = "*".equals(operator) ? val * num : val / num;
      }
    }
    queue.offerLast(String.valueOf(num));
  }

  // https://leetcode.com/problems/basic-calculator/
  public static int calculate(String s) {
    char[] str = shedSpace(s);

    return process(str, 0)[0];
  }

  // int[] post   0: 这一段计算的结果,  1: 这一段计算的结束位置下标
  public static int[] process(char[] str, int i) {
    LinkedList<String> queue = new LinkedList<>();
    int cur = 0;
    int[] post;
    while (i < str.length && str[i] != ')') {
      if (str[i] >= '0' && str[i] <= '9') {
        cur = cur * 10 + (str[i] - '0');
        i++;
      } else if (str[i] != '(') {
        addNum(queue, cur);
        cur = 0;
        queue.offerLast(String.valueOf(str[i++]));
      } else {  // str[i] == '('
        post = process(str, i + 1);
        i = post[1] + 1;
        cur = post[0];
      }
    }
    addNum(queue, cur);

    return new int[]{getNum(queue), i};
  }

  public static char[] shedSpace(String s) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) != ' ') {
        sb.append(s.charAt(i));
      }
    }
    return sb.toString()
             .toCharArray();
  }


}
