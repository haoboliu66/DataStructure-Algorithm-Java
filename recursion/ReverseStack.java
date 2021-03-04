package recursion;

import java.util.Stack;


public class ReverseStack {
    /* reverse a stack only by recursion  */

    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int bottom = getBottom(stack);
        reverse(stack);
        stack.push(bottom);
    }

    public static int getBottom(Stack<Integer> stack) {
        int result = stack.pop(); // 用栈保留每一层的值, 然后向下递归
        if (stack.isEmpty()) {
            return result;
        }
        int last = getBottom(stack); //返回下一层栈的返回值
        stack.push(result); //返回前把本层的推回去
        return last;
    }


    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 6; i++) {
            stack.push(i);
        }
        System.out.println(stack);
        reverse(stack);
        System.out.println(stack);
    }


}
