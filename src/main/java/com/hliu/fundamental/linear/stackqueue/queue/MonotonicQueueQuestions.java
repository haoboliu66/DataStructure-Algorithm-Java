package com.hliu.fundamental.linear.stackqueue.queue;

import java.util.Deque;
import java.util.LinkedList;

public class MonotonicQueueQuestions {

  /*
  使用单调队列可以确定一个sliding window中的每个时刻的max or min
   */


  public static void main(String[] args) {
    MonotonicQueueQuestions questions = new MonotonicQueueQuestions();
    int[] nums = {8,2,4,7};
    int res = questions.longestSubarray(nums, 4);
    System.out.println(res);
  }

  // https://leetcode.com/problems/sliding-window-maximum/
  public int[] maxSlidingWindow(int[] nums, int k) {
    int[] res = new int[nums.length - k + 1]; // 结果数组长度为 nums.length - k + 1
    Deque<Integer> deque = new LinkedList<>();
    int left = 0, right = 0;
    for (int index = 0; index < res.length; ) {
      if (right - left != k) {
        while (!deque.isEmpty() && nums[deque.peekLast()] < nums[right]) {
          deque.pollLast();
        }
        deque.offerLast(right);
        right++;
      } else {
        res[index++] = nums[deque.peekFirst()];
        left++; // 收集完当前窗口的答案, 那么接下来一定要让窗口从左侧缩小
        if (deque.peekFirst() < left) { // 如果当前队列头的元素已经不在window中了，就remove
          deque.pollFirst();
        }
      }
    }
    return res;
  }

  // https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/

  /*
  单调性分析: 1.如果一个窗口中max-min达标, 那么一定其他的任意2个值都满足要求
  2.如果一个窗口中max-min达标, 那么这个窗口的所有子窗口都达标
  3.如果一个窗口中max-min不达标, 那么无论如何扩展这个窗口, 这个窗口都不可能达标
   */
  public int longestSubarray(int[] nums, int limit) {
    Deque<Integer> maxQueue = new LinkedList<>();
    Deque<Integer> minQueue = new LinkedList<>();
    int maxLength = 0;
    int left = 0, right = 1;
    maxQueue.offerLast(nums[0]);
    minQueue.offerLast(nums[0]);

    for (; right < nums.length; ) {

      while (right < nums.length && !maxQueue.isEmpty()) {

        if (maxQueue.peekFirst() - minQueue.peekFirst() <= limit) {
          maxLength = Math.max(maxLength, right - left);
          while (!maxQueue.isEmpty() && maxQueue.peekLast() < nums[right]) { // 8 6 4 5
            maxQueue.pollLast();
          }
          maxQueue.offerLast(nums[right]);
          while (!minQueue.isEmpty() && minQueue.peekLast() > nums[right]) { // 4 6 7 5
            minQueue.pollLast();
          }
          minQueue.offerLast(nums[right]);
          right++;
        } else {
          left++;
          if (maxQueue.peekFirst() < nums[left]) {
            maxQueue.pollFirst();
          }
          if (minQueue.peekFirst() < nums[left]) {
            minQueue.pollFirst();
          }
        }
      }
    }

    return maxLength;
  }

}
