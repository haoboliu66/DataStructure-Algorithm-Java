package sys.c2;

import java.util.Arrays;

public class C04_AssignDriver {

    public static int maxIncome(int[][] income) {
        if (income == null || income.length < 2 || (income.length & 1) != 0) {
            return 0;
        }
        int N = income.length;
        int driversForA = N >> 1;
        return process(income, 0, driversForA);
    }


    public static int process(int[][] income, int i, int restForA) {
        if (i == income.length) {
            return 0;
        }
        // A区没有司机名额了
        if (restForA == 0) {
            return income[i][1] + process(income, i + 1, 0);
        }
        // B区没有司机名额了 (剩余的income的位置都是A的->和restForA大小一样)
        // 0 1 2 3 4 5(index) 6 7 8 9 10
        // 11 - 5
        if (restForA == income.length - i) {
            return income[i][0] + process(income, i + 1, restForA - 1);
        }

        // A区, B区都有司机名额
        int curDriverToA = income[i][0];
        int p1 = process(income, i + 1, restForA - 1);

        int curDriverToB = income[i][1];
        int p2 = process(income, i + 1, restForA);

        return Math.max(p1 + curDriverToA, p2 + curDriverToB);
    }

    public static int maxIncomeGreedy(int[][] income) {
        int n = income.length;
        int[] incomeDifference = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            incomeDifference[i] = income[i][1] - income[i][0];
            sum += income[i][0];
        }
        Arrays.sort(incomeDifference);
        int m = n >> 1;
        // 0,1,2,3,4,5
        for (int i = n - 1; i >= m; i--) {
            sum += incomeDifference[i];
        }
        return sum;
    }

    public static int maxMoneyZuo(int[][] income) {
        if (income == null || income.length < 2 || (income.length & 1) != 0) {
            return 0;
        }
        int N = income.length; // 司机数量一定是偶数，所以才能平分，A N /2 B N/2
        int M = N >> 1; // M = N / 2 要去A区域的人
        return process1(income, 0, M);
    }

    // index.....所有的司机，往A和B区域分配！
    // A区域还有rest个名额!
    // 返回把index...司机，分配完，并且最终A和B区域同样多的情况下，index...这些司机，整体收入最大是多少！
    public static int process1(int[][] income, int index, int rest) {
        if (index == income.length) {
            return 0;
        }
        // 还剩下司机！
        if (income.length - index == rest) {
            return income[index][0] + process1(income, index + 1, rest - 1);
        }
        if (rest == 0) {
            return income[index][1] + process1(income, index + 1, rest);
        }
        // 当前司机，可以去A，或者去B
        int p1 = income[index][0] + process1(income, index + 1, rest - 1);
        int p2 = income[index][1] + process1(income, index + 1, rest);
        return Math.max(p1, p2);
    }


    // 返回随机len*2大小的正数矩阵
    // 值在0~value-1之间
    public static int[][] randomMatrix(int len, int value) {
        int[][] ans = new int[len << 1][2];
        for (int i = 0; i < ans.length; i++) {
            ans[i][0] = (int) (Math.random() * value);
            ans[i][1] = (int) (Math.random() * value);
        }
        return ans;
    }

    public static void main(String[] args) {
        int N = 10;
        int value = 1000;
        int testTime = 2000;
        System.out.println("Started");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N) + 1;
            int[][] matrix = randomMatrix(len, value);
            int ans1 = maxMoneyZuo(matrix);
            int ans2 = maxIncome(matrix);
            int ans3 = maxIncomeGreedy(matrix);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("Done");
    }


}
