package someOA.IMC;

public class Test {

//    class AddingStack {
//
//        Stack<Long> stack;
//        List<Long> incr;
//        long sum;
//
//        public AddingStack(){
//            stack = new Stack<>();
//            incr = new ArrayList<>();
//            sum = 0L;
//        }
//
//
//        public void push(int v) {
//            // Complete the function below:
//            stack.push(v);
//            sum += v;
//            incr.add(0);
//        }
//
//        public void pop() {
//            // Complete the function below:
//            if(stack.isEmpty()){
//                return;
//            }
//            int index = stack.size() - 1;
//            int res = stack.pop();
//            int incrVal = incr.get(index);
//            if(index > 0){
//                incr.set(index - 1, incr.get(index - 1) + incrVal);
//            }
//            res += incrVal;
//            incr.set(index, 0);
//            sum -= res;
//        }
//
//        public void inc(int index, int v) {
//            if(stack.isEmpty()){
//                return;
//            }
//            // if(index >= 0){
//            int val = incr.get(index - 1);
//            incr.set(index - 1, val + v);
//            //  4 3 2 1 0
//            sum += v * index;
//            // }
//        }
//
//        public int peek() {
//            if(stack.isEmpty()){
//                throw new EmptyStackException();
//            }
//            int index = stack.size() - 1;
//            int result = stack.peek();
//            if(index >= 0){
//                result += incr.get(index);
//            }
//            // stack.set(index, result);
//            return result;
//        }
//
//
//
//
//
//        public long sum() {
//            // Complete the function below:
//            return sum;
//        }
//
//
//
//        public boolean empty() {
//            // Complete the function below:
//            return stack.isEmpty();
//        }
//
//    }



//    class AddingStack0 {
//
//        Stack<Integer> stack;
//        Map<Integer, Integer> map;
//        long sum;
//
//        public AddingStack0(){
//            stack = new Stack<>();
//        }
//
//        public AddingStack0(int n){
//            stack = new Stack<>();
//
//            sum = 0L;
//        }
//
//
//        public void push(int v) {
//            stack.push(v);
//            sum += v;
//        }
//
//        public void pop() {
//            if(stack.isEmpty()){
//                return;
//            }
//            int index = stack.size() - 1;
//            int res = stack.pop();
//            if(index > 0){
//                incr[index-1] += incr[index];
//            }
//            if(index >= 0){
//                res -= incr[index];
//            }
//            incr[index] = 0;
//            sum -= res;
//        }
//
//        public void inc(int index, int v) {
//            if(stack.isEmpty()){
//                return;
//            }
//            if(index >= 0){
//                incr[index] += v;
//                //  4 3 2 1 0
//                sum += v * index;
//            }
//        }
//
//        public boolean empty() {
//            // Complete the function below:
//            return stack.isEmpty();
//        }
//
//        public int peek() {
//            if(stack.isEmpty()){
//                throw new EmptyStackException();
//            }
//            int index = stack.size() - 1;
//            int result = stack.peek();
//            if(index >= 0){
//                result += incr[index];
//            }
//            return result;
//        }
//
//        public long sum() {
//            // Complete the function below:
//            return sum;
//        }
//    }





}
