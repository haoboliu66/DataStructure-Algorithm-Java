package com.hliu.random.leetcode;

import java.util.Stack;

public class M_Problem_1381_DesignStackWithIncrementOperation {

    /* Lazy Increment */

    // My Solution
    private class CustomStack {

        int[] arr;
        int limit;
        int size;

        public CustomStack(int maxSize) {
            limit = maxSize;
            arr = new int[limit];
            size = 0;
        }

        public boolean isEmpty(){
            return size == 0;
        }

        public void push(int x) {
            if(size < limit){
                arr[size++] = x;
            }
        }

        public int pop() {
            if(isEmpty()) return -1;
            int res = arr[--size];
            return res;
        }

        public void increment(int k, int val) {
            k = Math.min(k, size);
            for(int i = 0; i < k; i++){
                arr[i] += val;
            }
        }
    }

    // Optimal Solution
    private class CustomStack2 {

        Stack<Integer> stack;
        int[] incr;
        int limit;

        public CustomStack2(int maxSize) {
            stack = new Stack<>();
            incr = new int[maxSize];
            limit = maxSize;
        }

        public boolean isEmpty(){
            return stack.size() == 0;
        }

        public void push(int x) {
            if(stack.size() < limit){
                stack.push(x);
            }
        }

        public int pop() {
            if(isEmpty()) return -1;
            // stack.size() >= 1
            int incrIndex = stack.size() - 1;
            int res = stack.pop() + incr[incrIndex];
            if(incrIndex > 0){
                incr[incrIndex - 1] += incr[incrIndex];
            }
            return res;
        }

        public void increment(int k, int val) {
            int index = Math.min(k, stack.size()) - 1;
            if(index >= 0){
                incr[index] += val;
            }
        }

    }

}
