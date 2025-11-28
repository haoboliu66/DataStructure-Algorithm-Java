package com.hliu.fundamental.linear.stackqueue.stack.monotonic;

import java.util.Stack;

public class MonotonicStackQuestions {

  // https://leetcode.com/problems/daily-temperatures/
  public int[] dailyTemperatures(int[] temperatures) {
    int[] result = new int[temperatures.length];
    Stack<Integer> indexStack = new Stack<>();
    for (int i = 0; i < temperatures.length; i++) {
      while (!indexStack.isEmpty() && temperatures[i] > indexStack.peek()) {
        int curIndex = indexStack.pop();
        result[curIndex] = i - curIndex;
      }
      indexStack.push(i);
    }

    while (!indexStack.isEmpty()) {
      result[indexStack.pop()] = 0;
    }
    return result;
  }

  // https://leetcode.com/problems/sum-of-subarray-minimums/
  public int sumSubarrayMins(int[] arr) {

    return 0;
  }

  // https://leetcode.com/problems/largest-rectangle-in-histogram/
  public int largestRectangleArea(int[] heights) {
    if (heights == null || heights.length == 0) {
      return 0;
    }
    int maxArea = 0;
    int[] leftLess = new int[heights.length];
    int[] rightLess = new int[heights.length];
    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < heights.length; i++) {
      while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
        rightLess[stack.pop()] = i;
      }
      stack.push(i);
    }
    while (!stack.isEmpty()) {
      rightLess[stack.pop()] = heights.length;
    }
    for (int i = heights.length - 1; i >= 0; i--) {
      while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
        leftLess[stack.pop()] = i;
      }
      stack.push(i);
    }
    while (!stack.isEmpty()) {
      leftLess[stack.pop()] = -1;
    }
    for (int i = 0; i < heights.length; i++) {
      int leftLessIndex = leftLess[i];
      int rightLessIndex = rightLess[i];
      int currentArea = heights[i] * (rightLessIndex - leftLessIndex - 1);
      maxArea = Math.max(maxArea, currentArea);
    }
    return maxArea;
  }

  // https://leetcode.com/problems/maximal-rectangle/
  /*
   压缩数组的技巧: 在矩阵中, 必须以[i]行为底, 来计算这个矩形的面积; 如果当前行的位置是'0', 那么高度就是0; 如果是'1', 那么高度就是上一行的高度+1

   把[i]行到0行范围的矩阵压缩为一个数组, 然后就可以借用LargestRectangleHistogram的代码来求解
   */
  public int maximalRectangle(char[][] matrix) {

    return 0;
  }

  // https://leetcode.com/problems/maximum-width-ramp/
  public int maxWidthRampBruteForce(int[] nums) {
    int maxWidth = 0;
    for (int i = 0; i < nums.length; i++) {
      for (int j = 0; j < i; j++) {
        if (nums[j] <= nums[i]) {
          maxWidth = Math.max(maxWidth, i - j);
        }
      }
    }
    return maxWidth;
  }

  /*
  除了单调栈最经典的用法之外，在很多问题里单调栈还可以 维持求解答案的可能性
  单调栈里的所有对象按照 规定好的单调性 来组织

  当某个对象进入单调栈时， 会从 栈顶开始 依次淘汰单调栈里 对后续求解答案没有帮助 的对象
  每个对象从栈顶弹出的时候 结算当前对象参与的答案，随后这个对象 不再参与与后续求解答案的过程
  其实是 先有对题目的分析！ 进而发现单调性，然后利用 单调栈的特征 去实现
   */
  public int maxWidthRamp(int[] nums) {
    int maxWidth = 0;
    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < nums.length; i++) {
      if (!stack.isEmpty() || nums[stack.peek()] > nums[i]) {
        stack.push(i);
      }
    }
    for (int i = nums.length - 1; i >= 0; ) {
      if (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
        int leftIndex = stack.pop();
        maxWidth = Math.max(maxWidth, i - leftIndex);
      } else {
        i--;
      }
    }
    return maxWidth;
  }

  // https://leetcode.com/problems/remove-duplicate-letters/
  public String removeDuplicateLetters(String s) {
    int[] charCountMap = new int[128];
    boolean[] inStack = new boolean[128];
    for (char c : s.toCharArray()) {
      charCountMap[c]++;
    }
    Stack<Character> stack = new Stack<>(); // small -> big
    for (char ch : s.toCharArray()) {

      if (inStack[ch]) {
        charCountMap[ch]--;
        continue;
      }

      while (!stack.isEmpty() && ch < stack.peek() && charCountMap[stack.peek()] > 1) {
        charCountMap[stack.peek()]--;
        inStack[stack.peek()] = false;
        stack.pop();
      }
      stack.push(ch);
      inStack[ch] = true;

    }
    StringBuilder res = new StringBuilder();
    while (!stack.isEmpty()) {
      res.append(stack.pop());
    }
    return res.reverse()
              .toString();
  }

  // https://leetcode.com/problems/count-submatrices-with-all-ones/
  public int numSubmat(int[][] mat) {

    return 0;
  }
}
