package com.hliu.random.leetcode;

import java.util.Stack;

public class M_Problem_0735_AsteroidCollision {

    /* stack */

    public int[] asteroidCollision(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int n = arr.length;
        int cur = 0;
        for (int i = 0; i < n; ) {

            if (stack.isEmpty() || neverHit(cur, arr[i])) {
                stack.push(arr[i++]);
                cur = stack.peek();

            } else {
                // cur > 0 && arr[i] < 0
                if (cur + arr[i] == 0) {
                    stack.pop();
                    i++;
                } else if (cur + arr[i] < 0) {
                    stack.pop();
                } else {
                    i++;
                }
                // move cur to the next element to compare
                if (!stack.isEmpty()) {
                    cur = stack.peek();
                }
            }
        }

        int size = stack.size();
        int[] res = new int[size];
        int index = size - 1;
        while (size != 0) {
            res[index--] = stack.pop();
            size--;
        }
        return res;
    }

    private boolean neverHit(int a, int b) {
        return (a == b) || (a > 0 && b > 0) || (a < 0 && b < 0) || (a < 0 && b > 0);
    }

    /* ---------------------------------------  */

    public int[] asteroidCollision2(int[] asteroids) {
        if (asteroids.length <= 1) return asteroids;  // Handling Corner cases

        Stack<Integer> stack = new Stack<>();
        for (int asteriod : asteroids) {
            if (asteriod > 0) { // Pushing all +ve asteroids
                stack.push(asteriod);
            } else {
                // Remove all positive asteroids before our current asteroid
                while (!stack.isEmpty() && stack.peek() > 0 && Math.abs(stack.peek()) < Math.abs(asteriod))
                    stack.pop();
                // Checking if the stack is empty or the recent asteriod is negative!
                if (stack.isEmpty() || stack.peek() < 0)
                    stack.push(asteriod);
                    // If recent asteriod <= our asteriod, We broke our outer loop if equal we pop it.
                else if (stack.peek() == Math.abs(asteriod))
                    stack.pop();
            }
        }

        int[] output = new int[stack.size()];
        for (int i = output.length - 1; i >= 0; i--)
            output[i] = stack.pop();

        return output;
    }


}
