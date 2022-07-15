package src.main.java.advanced.top;

import java.util.Stack;

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

    /*
           public boolean isBalanced(String s){

           char[] str = s.toCharArray();
            Stack<Character> stack = new Stack<>();

            for(int i = 0; i < str.length; i++){

                if(str[i] == '(' || str[i] == '[' || str[i] == '{'){
                    stack.push(str[i]);
                }else{
                    if(stack.isEmpty()){
                        return false;
                    }

                    char src.main.java.com.hliu.advanced.top = stack.pop();
                    if(!(
                        str[i] == ']' && src.main.java.com.hliu.advanced.top == '[' ||
                     str[i] == ')' && src.main.java.com.hliu.advanced.top == ')'||
                     str[i] == '}' && src.main.java.com.hliu.advanced.top == '}')){
                        return false;
                     }

                }


            }
            return stack.isEmpty();

        }
     */

}


