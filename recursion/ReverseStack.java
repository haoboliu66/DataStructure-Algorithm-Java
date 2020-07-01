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
        int result = stack.pop(); // 用栈保留每一层的值, 然后向下递归
        if(stack.isEmpty()){
            return result;
        }
        int last = getBottom(stack); //返回下一层栈的返回值
        stack.push(result); //返回前把本层的推回去
        return last;
    }


}
