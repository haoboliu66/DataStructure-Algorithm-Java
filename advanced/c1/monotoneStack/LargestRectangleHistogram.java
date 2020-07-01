package advanced.c1.monotoneStack;

import java.util.Stack;

/**
 * @author andy-liu
 * @date 2020/5/29 - 11:37 AM
 */
public class LargestRectangleHistogram {


    public static int largestRectangleArea(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        Stack<Integer> incrStack = new Stack<>();
        int N = arr.length;
        int res = 0;

        for (int i = 0; i < N; i++) {

            while (!incrStack.isEmpty() && arr[incrStack.peek()] >= arr[i]) {

                int popIndex = incrStack.pop();
                int leftIndex = incrStack.isEmpty() ? 0 : incrStack.peek() + 1;
                int rightIndex = i - 1;
                res = Math.max(arr[popIndex] * (rightIndex - leftIndex + 1), res);
            }
            incrStack.push(i);

        }

        while (!incrStack.isEmpty()) {
            int popIndex = incrStack.pop();
            int leftIndex = incrStack.isEmpty() ? 0 : incrStack.peek() + 1;
            int rightIndex = N - 1;
            res = Math.max(arr[popIndex] * (rightIndex - leftIndex + 1), res);
        }

        return res;

    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 6, 2, 3};
        int res = largestRectangleArea(arr);
        System.out.println(res);
    }


}
