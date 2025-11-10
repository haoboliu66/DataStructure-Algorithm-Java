package com.hliu.big.c10;

import org.junit.Test;

public class C01_JumpGame {

    /*
    [2,3,1,1,4]
    2
     */

  public int jump0(int[] arr) {
    if (arr.length == 1) {
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
        nextWithOneMoreStep =
            Math.max(nextWithOneMoreStep, i + arr[i]);
      } else {
        // cur >= i
        nextWithOneMoreStep =
            Math.max(nextWithOneMoreStep, i + arr[i]);
      }

    }
    return steps;
  }

  public int jump(int[] arr) {
    if (arr.length == 1) {
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


  @Test
  public void test1() {
    int[] arr = {2, 3, 1, 1, 4};
    System.out.println(jump(arr));
  }

  // https://leetcode.com/problems/jump-game-iii/

  @Test
  public void testReach() {
    int[] arr = {3, 0, 2, 1, 2};
    int start = 2;
    boolean res = canReach(arr, start);
    System.out.println(res);
  }

  public boolean canReach(int[] arr, int start) {
    return process(arr, start);
  }

  public boolean process(int[] arr, int index) {
    if (index < 0 || index >= arr.length || arr[index] == -1) {
      return false;
    }
    if (arr[index] == 0) {
      return true;
    }
    boolean p = false;

    int tmp = arr[index];
    arr[index] = -1;
    // visited[index] = true;

    p |= process(arr, index + tmp);
    arr[index] = tmp;

    arr[index] = -1;
    p |= process(arr, index - tmp);

    arr[index] = tmp;

    return p;

  }


}
