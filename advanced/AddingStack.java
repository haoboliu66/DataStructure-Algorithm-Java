package advanced;

import java.util.Stack;

public class AddingStack {

    Stack<Integer> stack;
    Stack<Integer> help;

    int sum = 0;

    public AddingStack() {
        stack = new Stack<>();
        help = new Stack<>();
    }

    public void push(int i) {
        stack.push(i);
        sum += i;
    }

    public void pop() {
        sum -= stack.peek();
        stack.pop();
    }

    public void inc(int i, int v) {
        while (stack.size() != i) {
            help.push(stack.pop());
        }
        while (stack.size() != 0) {
            help.push(stack.pop() + v);
        }
        while (help.size() != 0) {
            stack.push(help.pop());
        }
    }

    public int peek() {
        return stack.peek();
    }


}
