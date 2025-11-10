package com.hliu.fundamental.linear.stackqueue;

import java.util.Stack;

public class QueueWithStack {

  /*
  https://leetcode.com/problems/implement-queue-using-stacks/
   */

  class MyQueue {

    Stack<Integer> in;
    Stack<Integer> out;

    public MyQueue() {
      in = new Stack<>();
      out = new Stack<>();
    }

    public void push(int x) {
      in.push(x);
    }

    public int pop() {
      if (!out.isEmpty()) {
        return out.pop();
      }
      in2out();
      return out.pop();
    }

    public int peek() {
      if (!out.isEmpty()) {
        return out.peek();
      }
      in2out();
      return out.peek();
    }

    private void in2out() {
      if (out.isEmpty()) {
        while (!in.isEmpty()) {
          out.push(in.pop());
        }
      }
    }

    public boolean empty() {
      return in.isEmpty() && out.isEmpty();
    }
  }

}
