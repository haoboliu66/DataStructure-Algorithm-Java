package src.main.java.fundamental.recursion;

public class BackPack {

    public static int backPackProblem(int[] weights, int[] values, int bag) {
        return process3(weights, values, 0, 0, 0, bag);
    }

    public static int process3(int[] weights, int[] values, int i, int weight, int value, int bag) {
        if (i == weights.length) {
            return value;
        }
        if (weights[i] + weight > bag) {
            return value;
        }
        int no = process3(weights, values, i + 1, weight, value, bag);
        int yes = process3(weights, values, i + 1, weight + weights[i], value + values[i], bag);
        return Math.max(yes, no);
    }


    public static int getMaxValue1(int[] weight, int[] values, int bag) {
        return process1(weight, values, 0, 0, bag);
    }

    private static int process1(int[] w, int[] v, int index, int alreadyW, int bag) {
        if (alreadyW > bag) {
            return -1;
        }
        if (index == w.length) {
            return 0;
        }
        int p1 = process1(w, v, index + 1, alreadyW, bag);
        int p2 = -1;
        int p2Next = process1(w, v, index, alreadyW + w[index], bag);
        if (p2Next != -1) {
            p2 = v[index] + p2Next; //当前价值加上装上下一个物品向后递归累加的价值
        }
        return Math.max(p1, p2);
    }

    public static int getMaxValue2(int[] weights, int[] values, int bag) {
        return process2(weights, values, 0, bag);
    }

    private static int process2(int[] w, int[] v, int index, int rest) {
        if (rest < 0) {
            return -1;
        }
        if (index == w.length || rest == 0) {
            return 0;
        }
        int p1 = process2(w, v, index + 1, rest);
        int p2 = -1;
        int p2Next = process2(w, v, index + 1, rest - w[index]);
        if (p2Next != -1) {
            p2 = v[index] + p2Next;
        }
        return Math.max(p1, p2);
    }

    /*   优化成DP  */
    public static int getMaxValue3(int[] w, int[] v, int bag) {
        int N = w.length;
        int[][] dp = new int[N + 1][bag + 1];
        // dp[N][...]  N行所有位置都是0, 默认是0不用再填
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= bag; rest++) {

                int p1 = dp[index + 1][rest];
                int p2 = -1;
                if (rest - w[index] >= 0) {  // 要保证rest轴下标不越界
                    p2 = v[index] + dp[index + 1][rest - w[index]];
                }

                dp[index][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][bag];
    }


    public static void main(String[] args) {
        int[] weights = {100, 200, 300, 400};
        int[] values = {10, 25, 35, 50};
        int bag = 400;
        int v1 = getMaxValue1(weights, values, bag);
        int v2 = getMaxValue2(weights, values, bag);
        int v3 = getMaxValue3(weights, values, bag);
        System.out.println(v1);
        System.out.println(v2);
        System.out.println(v3);

    }

}
