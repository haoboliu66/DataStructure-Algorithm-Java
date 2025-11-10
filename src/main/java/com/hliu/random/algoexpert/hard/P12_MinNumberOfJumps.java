package com.hliu.random.algoexpert.hard;



/*
You're given a non-empty array of positive
integers where each integer represents the
maximum number of steps you can take
forward in the array. For example, if the
element at index 1 is 3 , you can go from
index 1 to index 2 , 3 , or 4 .
Write a function that returns the minimum
number of jumps needed to reach the final
index.
Note that jumping from index i to index
i + x always constitutes one jump, no
matter how large x is.

Sample Input
array = [3, 4, 2, 1, 2, 3, 7, 1, 1, 1, 3]

Sample Output:4

 */

import java.util.Arrays;

public class P12_MinNumberOfJumps {

  public static void main(String[] args) {
    int[] arr = {3, 4, 2, 1, 2, 3, 7, 1, 1, 1, 3};
    System.out.println(minNumberOfJumpsDP(arr));
  }


  public static int minNumberOfJumpsDP(int[] arr) {
    if (arr == null || arr.length <= 1) {
      return 0;
    }
    int[] dp = new int[arr.length];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;

    for (int i = 1; i < arr.length; i++) {
      for (int j = 0; j < i; j++) {
        if (arr[j] + j >= i) {  // 从j可以跳到i位置
          dp[i] = Math.min(dp[i], dp[j] + 1);
        }
      }
    }

    return dp[arr.length - 1];
  }


  public static int minNumberOfJumps(int[] arr) {
    if (arr == null || arr.length <= 1) {
      return 0;
    }
    int dest = arr.length - 1;

    int steps = 0;
    int curEnd = 0;  // 最开始在0位置, 走了0步, 意味着我哪也到不了
    int nextEnd = 0; // 如果我迈出下一步, 我可以到达的最大位置
    for (int i = 0; i < arr.length; i++) {

      nextEnd = Math.max(i + arr[i], nextEnd); // 如果我此时在i, 再迈出下一步, 我可以最远到哪

      if (curEnd <= i) {  // 我当前迈了steps步, 无法超过i位置了, 那么我必须再加一步才能向前走
        steps++; // 走出一步了
        curEnd = nextEnd; // 那么走出一步之后我能到哪里呢?
      }
      if (curEnd >= dest) {
        break;
      }
    }

    return steps;
  }

}
