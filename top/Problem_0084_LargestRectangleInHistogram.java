package advanced.top;

import java.util.Stack;

public class Problem_0084_LargestRectangleInHistogram {
//
//    public int largestRectangleArea(int[] heights) {
//
//        int maxArea = Integer.MIN_VALUE;
//        Stack<Integer> stack = new Stack<>();
//        for (int i = 0; i < heights.length; i++) {
//
//            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
//                int curIndex = stack.pop();
//                int curArea =  (i - curIndex + 1) * Math.min(heights[i], heights[curIndex]);
//                maxArea = Math.max(maxArea, curArea);
//            }
//            stack.push(i);
//        }
//
//
//    }
}
