package com.hliu.fundamental.linear.stack;

import java.util.Stack;

public class Problem_0735_AsteroidCollision {

  public int[] asteroidCollision(int[] asteroids) {
    Stack<Integer> stack = new Stack<>();

    for (int i = 0; i < asteroids.length; ) {
      int rock = asteroids[i];
      if (!stack.isEmpty()) {
        int prev = stack.pop();
        if (prev > 0 && rock < 0) {  // positive vs negative
          if (prev + rock > 0) { // prev lives, it goes back to the stack
            stack.push(prev);
            i++;
          } else if (prev + rock == 0) { // both die
            i++;
          } else {
            // the rock lives, i doesn't inc, to check against the next stack top
          }
        } else {
          stack.push(prev);
          stack.push(rock);
          i++;
        }
      } else {
        stack.push(rock);
        i++;
      }
    }

    int[] res = new int[stack.size()];
    for (int i = stack.size() - 1; i >= 0; i--) {
      res[i] = stack.pop();
    }
    return res;
  }

}
