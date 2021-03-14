package leetcode.stack_queue;

import java.util.LinkedList;
import java.util.Queue;

public class E_Problem_0225_ImplementStackUsingQueues {

   private class MyStack {

        /** Initialize your data structure here. */
        Queue<Integer> in;
        Queue<Integer> out;

        public MyStack() {
            in = new LinkedList<>();
            out = new LinkedList<>();
        }

        /** Push element x onto stack. */
        public void push(int x) {
            in.offer(x);
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            while(in.size() != 1){
                out.offer(in.poll());
            }
            int res = in.poll();
            swapReference();
            return res;
        }

        /** Get the top element. */
        public int top() {
            while(in.size() != 1){
                out.offer(in.poll());
            }
            int res = in.poll();
            out.offer(res);
            swapReference();
            return res;
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return in.isEmpty();
        }

        private void swapReference(){
            Queue<Integer> tmp;
            tmp = in;
            in = out;
            out = tmp;
        }

    }

}
