package com.hliu.random.leetcode.stack_queue;

import java.util.Arrays;
import java.util.Stack;

public class M_Problem_0739_DailyTemperatures {


    /* Monotone Stack */
    // not done yet

    /*
    Given a list of daily temperatures T, return a list such that, for each day in the input,
    tells you how many days you would have to wait until a warmer temperature.
    If there is no future day for which this is possible, put 0 instead.

    For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].
     */

    public static void main(String[] args) {
        int[] arr = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] res = dailyTemperatures(arr);
        System.out.println(Arrays.toString(res));
    }

    // Stack Solution
    public static int[] dailyTemperatures(int[] arr) {
        Stack<Integer> descStack = new Stack<>();
        descStack.push(0);
        int[] days = new int[arr.length];
        for(int i = 1; i < arr.length; i++){

            while(!descStack.isEmpty() && arr[i] > arr[descStack.peek()]){  // new ele > peek()
                int curIndex = descStack.pop();
                days[curIndex] =  i - curIndex;
            }
            descStack.push(i);
        }

        /*  unnecessary as default value is 0   */
//        while(!descStack.isEmpty()){
//            int curIndex = descStack.pop();
//            daysl

        return days;
    }

    // Array Solution
    public static int[] dailyTemperatures2(int[] arr){
        return null;
    }

}
