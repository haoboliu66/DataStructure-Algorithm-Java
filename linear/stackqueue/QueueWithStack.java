package linear.stackqueue;

import java.util.Stack;

public class QueueWithStack {

    public static class MyQueue {
        Stack<Integer> stack;
        Stack<Integer> help;

        public MyQueue() {
            stack = new Stack<>();
            help = new Stack<>();
        }

        public void offer(int e) {
            stack.push(e);
        }

        public int poll() {
            if(isEmpty()){
                throw new RuntimeException("Queue is Empty");
            }

            while (stack.size() != 1) {
                help.push(stack.pop());
            }
            int res = stack.pop();
            while (!help.isEmpty()) {
                stack.push(help.pop());
            }
            return res;
        }

        public int peek() {
            if(isEmpty()){
                throw new RuntimeException("Queue is Empty");
            }

            while (stack.size() != 1) {
                help.push(stack.pop());
            }
            int res = stack.peek();
            while (!help.isEmpty()) {
                stack.push(help.pop());
            }
            return res;
        }


        public boolean isEmpty() {
            return stack.isEmpty();
        }

        public int size() {
            return stack.size();
        }

        @Override
        public String toString() {
            return "MyQueue{" +
                    "stack=" + stack +
                    '}';
        }
    }

    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        queue.offer(6);
        System.out.println(queue);
        System.out.println(queue.peek());
        System.out.println(queue.peek());
        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue);
    }


}
