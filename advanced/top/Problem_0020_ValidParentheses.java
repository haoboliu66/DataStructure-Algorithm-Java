package advanced.top;

import java.util.Stack;

/**
 * @author andy-liu
 * @date 2020/8/25 - 10:58 AM
 */
public class Problem_0020_ValidParentheses {

    public static boolean isValid(String s) {

        char[] str = s.toCharArray();
        Stack<Character> stack = new Stack<>();

        int i = 0;
        while (i < str.length) {

            if (str[i] == '(' || str[i] == '{' || str[i] == '[') {
                stack.push(str[i]);

            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                if ((str[i] == ')' && stack.peek() == '(')
                        || (str[i] == ']' && stack.peek() == '[')
                        || (str[i] == '}' && stack.peek() == '{')) {
                    stack.pop();
                } else {
                    return false;
                }
            }
            i++;
        }

        return stack.isEmpty();
    }

}
