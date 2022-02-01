package src.main.java.advanced.c1.fibonacci;



public class Fibonacci {

    public static int fibonacci(int N) {
        if (N < 1) {
            return 0;
        }
        if (N == 1 || N == 2) {
            return 1;
        }
        int[] dp = new int[N + 1];
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[N];
    }

    // log(N)
    public static int fibonacci1(int N) {
        if (N < 1) {
            return 0;
        }
        if (N == 1 || N == 2) {
            return 1;
        }
        int[][] f = {
                {1, 1},
                {1, 0}
        };
        int[][] res = matrixPower(f, N - 2);
        return res[0][0] + res[1][0];
    }

    public static int[][] matrixPower(int[][] base, int p) {
        int[][] res = new int[base.length][base[0].length];
        // 初始化单位矩阵res
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
        int[][] t = {{1, 1}, {1, 0}};
        while (p != 0) {
            if ((p & 1) != 0) {
                res = multiplyMatrix(res, t);
            }
            t = multiplyMatrix(t, t);
            p >>= 1;
        }
        return res;
    }

    // [a,b] * [b,c] = [a,c]
    public static int[][] multiplyMatrix(int[][] m1, int[][] m2) {
        int[][] res = new int[m1.length][m2[0].length];

        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[0].length; j++) {
                for (int k = 0; k < m2.length; k++) { // b
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }

        return res;
    }


    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int testTime = 1000;
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * 1000);
            if (fibonacci(N) != fibonacci1(N)) {
                System.out.println("Oops");
                break;
            }
        }
        System.out.println("Finished");
    }
}
