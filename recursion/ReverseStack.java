package recursion;

import java.util.Stack;

/**
 * @author andy-liu
 * @date 2020/5/21 - 9:30 AM
 */
public class ReverseStack {
    /* reverse a stack only by recursion  */

    public static void reverse(Stack<Integer> stack){
//        int bottom = getBottom(stack);
//        if(!stack.isEmpty()){
//            reverse(stack);
//            stack.push(bottom);
//            return;
//        }
//            stack.push(bottom);
        if(stack.isEmpty()){
            return;
        }
        int bottom = getBottom(stack);
        reverse(stack);
        stack.push(bottom);
    }

    public static int getBottom(Stack<Integer> stack){
        int result = stack.pop();
        if(stack.isEmpty()){
            return result;
        }
        int last = getBottom(stack);
        stack.push(result);
        return last;
    }


}
