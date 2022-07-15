package com.hliu.advanced.c3.class6;

public class C03_JumpGame {

    /*
    Input: nums = [2,3,1,1,4]
    Output: 2
    https://leetcode.com/problems/jump-game-ii/
     */

  public int jump(int[] arr) {
    if (arr.length <= 1) {
      return 0;
    }
    int steps = 0, curWithoutMoreStep = 0, nextWithOneMoreStep = 0;
    for (int i = 0; i < arr.length; i++) {
      if (curWithoutMoreStep < i) {
        steps++;
        curWithoutMoreStep = nextWithOneMoreStep;
        if (curWithoutMoreStep >= arr.length - 1) {
          break;
        }
      }
      nextWithOneMoreStep = Math.max(nextWithOneMoreStep, i + arr[i]);

    }
    return steps;
  }


}
