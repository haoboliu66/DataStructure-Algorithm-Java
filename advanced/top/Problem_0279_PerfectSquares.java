package advanced.top;

import java.util.*;

public class Problem_0279_PerfectSquares {


    public int numSquares(int n) {
        Set<Integer> set = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(n);
        set.add(n);
        int res = 0;

        while (!queue.isEmpty()) {
            int len = queue.size();
            res++;
            for (int i = 0; i < len; i++) {
                int pop = queue.poll();

                for (int j = 1; j * j <= n; j++) {
                    int nice = pop - j * j;
                    if (nice == 0) return res;
                    if (nice < 0) break;
                    if (!set.contains(nice)) {
                        queue.add(nice);
                        set.add(nice);
                    }
                }
            }
        }
        return res;
    }

    // n = a + b + c  + ....
    public static List<Integer> generateSquareNumbers(int n) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i <= n; i++) {

        }
        return ans;
    }

}
