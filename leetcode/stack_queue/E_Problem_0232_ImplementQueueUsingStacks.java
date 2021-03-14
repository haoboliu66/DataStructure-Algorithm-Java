package leetcode.stack_queue;

import java.util.Stack;

public class E_Problem_0232_ImplementQueueUsingStacks {


    private class MyQueue {

        /** Initialize your data structure here. */

        Stack<Integer> in;
        Stack<Integer> out;

        public MyQueue() {
            in = new Stack<>();
            out = new Stack<>();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            in.push(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            if(!out.isEmpty()){
                return out.pop();
            }
            while(in.size() != 1){
                out.push(in.pop());
            }
            return in.pop();
        }

        /** Get the front element. */
        public int peek() {
            if(!out.isEmpty()){
                return out.peek();
            }
            while(in.size() != 0){
                out.push(in.pop());
            }

            return out.peek();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return in.size() == 0  && out.size() == 0;
        }
    }
}
