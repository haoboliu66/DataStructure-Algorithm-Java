package com.hliu.fundamental.recursion.nested;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

/*
 解决嵌套类问题的递归套路
 */
public class NestedStructuresProblem {

  /*
  basic-calculator的计算逻辑:
  1. 如果operator存的最后一个是+ - , 直接把当前数字和operator存进去
  2. 如果operator存的最后一个是*  /，把当前数字和上一个数字取出来计算后, 并把operator的最后一个弹出, 再把当前number和operator存进去
   */

  // https://leetcode.com/problems/basic-calculator-ii/
  // 不含括号, 只处理+-*/运算
  public int calculate2(String s) {
    char[] str = s.toCharArray(); // s中会有空字符, 无需额外处理, 遍历时会自动跳过的
    List<Integer> number = new ArrayList<>();
    List<Character> operator = new ArrayList<>();
    operator.add('+');  // 垫一个+, 方便最后同时遍历2个List做计算
    int curNum = 0;
    for (char ch : str) {
      if (ch >= '0' && ch <= '9') {
        curNum = curNum * 10 + (ch - '0');
      } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
        push(number, operator, curNum, ch);
        curNum = 0;
      }
    }
    push(number, operator, curNum, '+');
    return calculateFinal(number, operator);
  }

  private int calculateFinal(List<Integer> numbers, List<Character> operators) {
    int result = 0;
    for (int numIndex = 0, opIndex = 0; numIndex < numbers.size() && opIndex < operators.size();
        numIndex++, opIndex++) {
      int curNum = numbers.get(numIndex);
      result += (operators.get(opIndex) == '+' ? curNum : -curNum);
    }
    return result;
  }

  private void push(List<Integer> numbers, List<Character> operators, int curNum, char op) {
    int n = numbers.size();
    if (n == 0) {
      numbers.add(curNum);
      operators.add(op);
      return;
    }
    int lastOpIndex = operators.size() - 1;
    char lastOp = operators.get(lastOpIndex);
    if (lastOp == '*' || lastOp == '/') {
      int lastNum = numbers.get(n - 1);
      int newNum = lastOp == '*' ? lastNum * curNum : lastNum / curNum;
      numbers.set(n - 1, newNum);
      operators.remove(lastOpIndex);
      operators.add(op);
    } else {
      numbers.add(curNum);
      operators.add(op);
    }
  }

  // https://leetcode.com/problems/basic-calculator
  // 含括号, 只处理+-运算
  public int calculate(String s) {
    char[] str = s.toCharArray();
    return runCalculate(str, 0)[0];
  }

  private int[] runCalculate(char[] str, int i) {
    System.out.println("runCalculate called with i=" + i);
    List<Integer> numbers = new ArrayList<>();
    List<Character> operators = new ArrayList<>();
    operators.add('+');
    int curNum = 0;
    for (; i < str.length && str[i] != ')'; ) {
      if (str[i] == ' ') {
        i++;
        continue;
      }
      if (str[i] >= '0' && str[i] <= '9') {
        curNum = curNum * 10 + (str[i] - '0');
        i++;
      } else if (str[i] == '(') {
        int[] postResult = runCalculate(str, i + 1);
        curNum = postResult[0];
        i = postResult[1] + 1;
      } else { // operator
        push(numbers, operators, curNum, str[i]);
        curNum = 0;
        i++;
      }
    }
    push(numbers, operators, curNum, '+');
    return new int[]{calculateFinal(numbers, operators), i};
  }

  // https://leetcode.com/problems/decode-string
  public String decodeString(String s) {
    return (String) runStringDecode(s.toCharArray(), 0)[0];
  }

  private Object[] runStringDecode(char[] str, int i) {
    System.out.println("runDecode called with i=" + i);
    StringBuilder result = new StringBuilder();
    int curNum = 0;
    while (i < str.length && str[i] != ']') {
      if (str[i] >= '0' && str[i] <= '9') {
        curNum = curNum * 10 + (str[i] - '0');
        i++;
      } else if (str[i] == '[') {
        Object[] postResult = runStringDecode(str, i + 1);
        String postStr = (String) postResult[0];
        for (int j = 0; j < curNum; j++) {
          result.append(postStr);
        }
        i = ((int) postResult[1]) + 1;
        curNum = 0;
      } else if (str[i] >= 'a' && str[i] <= 'z') {
        result.append(str[i]);
        i++;
      } else {
        i++;
      }
    }
    return new Object[]{result.toString(), i};
  }

  // https://leetcode.com/problems/number-of-atoms/
  public String countOfAtoms(String formula) {
    TreeMap<String, Integer> resultMap = (TreeMap<String, Integer>) runCount(formula.toCharArray(), 0)[0];
    StringBuilder sb = new StringBuilder();
    for (Map.Entry<String, Integer> entry : resultMap.entrySet()) {
      int count = entry.getValue();
      sb.append(entry.getKey());
      if (count > 1) {
        sb.append(count);
      }
    }
    return sb.toString();
  }

  private Object[] runCount(char[] str, int i) {
    TreeMap<String, Integer> atomCount = new TreeMap<>();
    StringBuilder atom = new StringBuilder();
    int curCount = 0;
    while (i < str.length && str[i] != ')') {

      while (i < str.length && Character.isLetter(str[i])) {
        if (str[i] >= 'A' && str[i] <= 'Z' && atom.length() > 0) {
          atom.append("#1#");
        }
        atom.append(str[i]);
        i++;
      }
      // Mg#
      atom.append('#');

      while (i < str.length && Character.isDigit(str[i])) {
        curCount = curCount * 10 + (str[i] - '0');
        i++;
      }
      // Mg#1
      if (curCount == 0) {
        atom.append('1');
      } else {
        atom.append(curCount);
      }
      curCount = 0; // get ready for next atom if there's one
      // Mg#1#
      atom.append('#');

      if (i < str.length && str[i] == '(') {
        Object[] postResult = runCount(str, i + 1);
        TreeMap<String, Integer> postCountMap = (TreeMap<String, Integer>) postResult[0];
        i = ((int) postResult[1]) + 1;

        int trailingCount = 0;
        while (i < str.length && Character.isDigit(str[i])) {
          trailingCount = trailingCount * 10 + (str[i] - '0');
          i++;
        }
        for (Map.Entry<String, Integer> entry : postCountMap.entrySet()) {
          String key = entry.getKey();
          int val = entry.getValue();
          atom.append(key)
              .append('#')
              .append(val * (trailingCount == 0 ? 1 : trailingCount))
              .append('#');
        }
      }
    }
    fillSumMap(atomCount, atom.toString());
    return new Object[]{atomCount, i};
  }

  private void fillSumMap(TreeMap<String, Integer> atomMap, String result) {
    // SO#3#OH#2
    // SO#3#Mg#2
    String[] strs = result.split("#");
    for (int i = 0; i < strs.length - 1; i += 2) {
      String atom = strs[i];
      if (!atom.isBlank()) {
        int count = Integer.parseInt(strs[i + 1]);
        atomMap.put(atom, atomMap.getOrDefault(atom, 0) + count);
      }
    }
  }


  public static void main(String[] args) {
    NestedStructuresProblem solution = new NestedStructuresProblem();
    String[] s = {"2", "1", "+", "3", "*"};
    int result = solution.evalRPN(s);
    System.out.println(result);
  }

  // https://leetcode.com/problems/evaluate-reverse-polish-notation/
  public int evalRPN(String[] tokens) {
    Stack<String> stack = new Stack<>();
    for (String token : tokens) {
      if (!isOperator(token)) {
        stack.push(token);
      } else {
        String num2 = stack.pop();
        String num1 = stack.pop();
        stack.push(calculate(num1, num2, token));
      }
    }
    return Integer.parseInt(stack.pop());
  }

  private String calculate(String num1, String num2, String operator) {
    int n1 = Integer.parseInt(num1);
    int n2 = Integer.parseInt(num2);
    int result = 0;
    switch (operator) {
      case "+":
        result = n1 + n2;
        break;
      case "-":
        result = n1 - n2;
        break;
      case "*":
        result = n1 * n2;
        break;
      case "/":
        result = n1 / n2;
        break;
    }
    return String.valueOf(result);
  }

  private boolean isOperator(String token) {
    return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
  }

}
