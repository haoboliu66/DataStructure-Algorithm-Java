package leetcode.stack_queue;

import java.util.Stack;

public class M_Problem_0150_EvaluateReversePolishNotation {

    public static void main(String[] args) {
        String[] tokens = {"4", "13", "5", "/", "+"};
        int res = evalRPN(tokens);
        System.out.println(res);
    }

    public static int evalRPN(String[] str) {
        Stack<Integer> stack = new Stack<>();

        for(int i = 0 ; i < str.length; i++){
            String cur = str[i];
            if(!isOperator(cur)){
                stack.push(Integer.parseInt(cur));
                continue;
            }
            // str[i] --> operator
            int first = stack.pop();
            int second = stack.pop();
            int tmp = calculate(first, second, cur.charAt(0));
            stack.push(tmp);
        }

        return stack.peek();
    }

    public static int calculate(int first, int second, char operator){
        int res;
        switch(operator){
            case '+':
                res = first + second;
                break;
            case '-':
                res = second - first;
                break;
            case '*':
                res = second * first;
                break;
            default:
                res = second / first;
        }
        return res;
    }

    public static boolean isOperator(String c){
        return c.length() == 1 && (c.charAt(0) == '+'|| c.charAt(0) == '-' || c.charAt(0) == '*'|| c.charAt(0) == '/');
    }
}
