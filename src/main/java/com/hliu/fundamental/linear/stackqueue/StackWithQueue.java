package com.hliu.fundamental.linear.stackqueue;

import java.util.LinkedList;
import java.util.Queue;

public class StackWithQueue {

    /*
    https://leetcode.com/problems/implement-stack-using-queues/
     */

    public static class MyStack {
        Queue<Integer> queue;
        Queue<Integer> help;

        public MyStack() {
            queue = new LinkedList<>();
            help = new LinkedList<>();
        }

        public int size() {
            return queue.size();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }

        public void push(int e) {
            queue.offer(e);
        }

        public int pop() {
            if(isEmpty()){
                throw new RuntimeException("Stack is Empty");
            }
            int res;
            while (queue.size() != 1) {
                help.add(queue.poll());
            }
            res = queue.poll();
            Queue<Integer> tmp = queue; // the empty queue
            queue = help;
            help = tmp;
            return res;
        }

        public int peek() {
            if(isEmpty()){
                throw new RuntimeException("Stack is Empty");
            }
            int res;
            while (queue.size() != 1) {
                help.add(queue.poll());
            }
            res = queue.poll();
            Queue<Integer> tmp = queue; // the empty queue
            queue = help;
            help = tmp;
            queue.offer(res);
            return res;
        }

        @Override
        public String toString() {
            return "MyStack{" +
                    "queue=" + queue +
                    '}';
        }
    }


    public static void main(String[] args) {
        MyStack ms = new MyStack();
        ms.push(1);
        ms.push(2);
        ms.push(3);
        ms.push(4);
        ms.push(5);
        ms.push(6);
        System.out.println(ms);
        System.out.println(ms.peek());
        System.out.println(ms.peek());
        System.out.println(ms.peek());
        System.out.println(ms.pop());
        System.out.println(ms.pop());
        System.out.println(ms.peek());
        System.out.println(ms);
    }
}
