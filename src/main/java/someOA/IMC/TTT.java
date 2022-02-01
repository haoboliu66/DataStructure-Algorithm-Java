package src.main.java.someOA.IMC;

import java.util.EmptyStackException;
import java.util.Stack;

public class TTT {


    class AddingStack {

        Stack<Integer> stack;
        int[] incr;
        long sum;

        public AddingStack() {
            stack = new Stack<>();
            incr = new int[200000];
            sum = 0L;
        }


        public void push(int v) {
            // Complete the function below:
            stack.push(v);
            sum += v;
        }

        public void pop() {
            // Complete the function below:
            if (stack.isEmpty()) {
                return;
            }
            int incrIndex = stack.size() - 1;
            int res = stack.pop() + incr[incrIndex];

            if (incrIndex > 0) {
                incr[incrIndex - 1] += incr[incrIndex];
            }
            incr[incrIndex] = 0;
            sum -= res;
        }

        public void inc(int i, int val) {
            int index = Math.min(i, stack.size()) - 1;
            if (index >= 0) {
                incr[index] += val;
            }
            sum += i * val;
        }

        public int peek() {
            if (stack.isEmpty()) {
                throw new EmptyStackException();
            }
            int index = stack.size() - 1;
            int result = stack.peek();
            if(index >= 0){
                result += incr[index];
            }
            return result;
        }


        public long sum() {
            // Complete the function below:
            return sum;
        }


        public boolean empty() {
            // Complete the function below:
            return stack.isEmpty();
        }

    }


}
