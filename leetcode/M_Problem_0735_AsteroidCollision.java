package leetcode;

import java.util.Stack;

public class M_Problem_0735_AsteroidCollision {

    /* stack */

    // not done yet

    // 5,10,-5
    public int[] asteroidCollision(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        stack.add(arr[0]);
        for(int i = 1; i < arr.length; i++){
            int cur = arr[i];
            int front = stack.peek();
            if(front < 0 && cur < 0 || front > 0 && cur > 0 || front < 0 && cur > 0){
                stack.push(front);
                stack.push(cur);
            }
            //  -5
            if(front > 0 && cur < 0){
                while(!stack.isEmpty() && stack.peek() > 0){
                    front = stack.pop();
                    int t = Math.abs(front) == Math.abs(cur)? 0: Math.abs(front) > Math.abs(cur)? front: cur;
                    if(t > 0){
                        stack.push(t);
                        break;
                    }else if(t == 0){
                        break;
                    }else{
                        cur = t;
                    }

                }

            }


        }
        int n = stack.size();
        int[] res = new int[n];
        for(int i = n - 1; i >= 0; i--){
            res[i] = stack.pop();
        }
        return res;
    }
}
