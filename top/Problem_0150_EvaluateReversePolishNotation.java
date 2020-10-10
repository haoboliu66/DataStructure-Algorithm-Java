package top;

import java.util.Stack;

public class Problem_0150_EvaluateReversePolishNotation {

    public static int evalRPN(String[] s) {

        if (s == null || s.length == 0) {
            return 0;
        }
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < s.length; i++) {
            if ("+".equals(s[i]) || "-".equals(s[i]) || "*".equals(s[i]) || "/".equals(s[i])) {
                String opd1 = stack.pop();
                String opd2 = stack.pop();
                String res = operate(s[i], opd1, opd2);
                stack.push(res);
            } else {
                stack.push(s[i]);
            }

        }
        return Integer.valueOf(stack.peek());
    }

    private static String operate(String operator, String opd1, String opd2) {
        int o1 = Integer.valueOf(opd1);
        int o2 = Integer.valueOf(opd2);
        int res = 0;
        switch (operator) {
            case "+":
                res = o1 + o2;
                break;
            case "-":
                res = o2 - o1;
                break;
            case "*":
                res = o1 * o2;
                break;
            case "/":
                res = o2 / o1;
                break;
        }

        return String.valueOf(res);
    }

}
