package fundamental.linear.stackqueue;

import java.util.Stack;

public class GetMinStack {

    public static class MinStack{

        Stack<Integer> help;
        Stack<Integer> stack;

        /** initialize your data structure here. */
        public MinStack() {
            stack = new Stack<>();
            help = new Stack<>();
        }

        public void push(int x) {

            if(help.isEmpty()){
                stack.push(x);
                help.push(x);
                return;
            }
            stack.push(x);
            help.push(Math.min(x, help.peek()));

        }

        public int pop() {
            if(stack.isEmpty()){
                throw new RuntimeException("Empty stack");
            }
            help.pop();
            return stack.pop();
        }

        public int peek() {
            if(stack.isEmpty()){
                throw new RuntimeException("Empty stack");
            }
            return stack.peek();
        }

        public int getMin() {
            if(stack.isEmpty()){
                throw new RuntimeException("Empty stack");
            }
            return help.peek();
        }

        public static void main(String[] args) {

            MinStack stack = new MinStack();
            stack.push(1);
            stack.push(3);
            stack.push(2);
            stack.push(7);
            stack.push(8);
            stack.push(0);
            stack.push(9);
            System.out.println(stack.getMin());
            System.out.println(stack.pop());
            System.out.println(stack.pop());
            System.out.println(stack.getMin());
        }


    }
}
