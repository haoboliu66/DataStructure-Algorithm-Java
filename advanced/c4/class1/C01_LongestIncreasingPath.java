package advanced.c4.class1;

public class C01_LongestIncreasingPath {

    /*
    leetcode 329. Longest Increasing Path in a Matrix
     */

    //方法一: 暴力递归
    public static int longestIncreasingPath1(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        int ans = Integer.MIN_VALUE;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {

                ans = Math.max(ans, process1(matrix, i, j));
            }
        }

        return ans;
    }

    public static int process1(int[][] matrix, int row, int col) {
        if (row < 0 || row == matrix.length || col < 0 || col == matrix[0].length) {
            return 0;
        }

        int up = 0;
        int down = 0;
        int left = 0;
        int right = 0;

        if (row - 1 >= 0 && matrix[row - 1][col] > matrix[row][col]) {
            up += process1(matrix, row - 1, col);
        }

        if (row + 1 < matrix.length && matrix[row + 1][col] > matrix[row][col]) {
            down += process1(matrix, row + 1, col);
        }

        if (col - 1 >= 0 && matrix[row][col - 1] > matrix[row][col]) {
            left += process1(matrix, row, col - 1);
        }

        if (col + 1 < matrix[0].length && matrix[row][col + 1] > matrix[row][col]) {
            right += process1(matrix, row, col + 1);
        }

        int res = 0;
        res = 1 + Math.max(Math.max(up, down), Math.max(left, right));

        return res;
    }

    //----------------------------------------------------------------------------------------//
    //方法二: Memoization
    public static int longestIncreasingPath2(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        int[][] dp = new int[matrix.length][matrix[0].length];
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                ans = Math.max(ans, process1(matrix, i, j));
            }
        }
        return ans;
    }

    // 假设在matrix中，从i行，j列出发，能走出的最长递增路径，返回
    // dp[i][j]   == 0   process(i,j) 之前没遇到过
    // dp[i][j]   != 0   process(i,j) 之前已经算过了
    public static int process2(int[][] matrix, int row, int col, int[][] dp) {
        if (row < 0 || row == matrix.length || col < 0 || col == matrix[0].length) {
            return 0;
        }
        // 命中cache
        if (dp[row][col] != 0) {
            return dp[row][col];
        }

        int up = 0;
        int down = 0;
        int left = 0;
        int right = 0;

        if (row - 1 >= 0 && matrix[row - 1][col] > matrix[row][col]) {
            up += process2(matrix, row - 1, col, dp);
        }
        if (row + 1 != matrix.length && matrix[row + 1][col] > matrix[row][col]) {
            down += process2(matrix, row + 1, col, dp);
        }
        if (col - 1 >= 0 && matrix[row][col - 1] > matrix[row][col]) {
            left += process2(matrix, row, col - 1, dp);
        }
        if (col + 1 != matrix[0].length && matrix[row][col + 1] > matrix[row][col]) {
            right += process2(matrix, row, col + 1, dp);
        }
        //存入cache
        dp[row][col] = 1 + Math.max(Math.max(up, down), Math.max(left, right));

        return dp[row][col];
    }


    public static int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int max = 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                max = Math.max(max, process(matrix, i, j));
            }
        }
        return max;
    }


    // 从(i,j)开始, 最长递增序列的长度
    public static int process(int[][] matrix, int i, int j) {

        if (i < 0 || i == matrix.length || j < 0 || j == matrix[0].length) {
            return 0;
        }

        int left = 1;
        int right = 1;
        int up = 1;
        int down = 1;

        down += (i + 1 < matrix.length && matrix[i + 1][j] > matrix[i][j]) ? process(matrix, i + 1, j) : 0;
        up += (i - 1 >= 0 && matrix[i - 1][j] > matrix[i][j]) ? process(matrix, i - 1, j) : 0;
        left += (j - 1 >= 0 && matrix[i][j - 1] > matrix[i][j]) ? process(matrix, i, j - 1) : 0;
        right += (j + 1 < matrix[0].length && matrix[i][j + 1] > matrix[i][j]) ? process(matrix, i, j + 1) : 0;

        return Math.max(down, Math.max(up, Math.max(left, right)));
    }


    public static int longestIncreasingPath3(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int max = 1;
        int M = matrix.length, N = matrix[0].length;
        int[][] dp = new int[M][N];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                max = Math.max(max, process3(matrix, i, j, dp));
            }
        }
        return max;
    }

    public static int process3(int[][] matrix, int i, int j, int[][] dp) {

        if (i < 0 || i == matrix.length || j < 0 || j == matrix[0].length) {
            return 0;
        }

        if (dp[i][j] != 0) return dp[i][j];

        int left = 1;
        int right = 1;
        int up = 1;
        int down = 1;

        down += (i + 1 < matrix.length && matrix[i + 1][j] > matrix[i][j]) ? process3(matrix, i + 1, j, dp) : 0;
        up += (i - 1 >= 0 && matrix[i - 1][j] > matrix[i][j]) ? process3(matrix, i - 1, j, dp) : 0;
        left += (j - 1 >= 0 && matrix[i][j - 1] > matrix[i][j]) ? process3(matrix, i, j - 1, dp) : 0;
        right += (j + 1 < matrix[0].length && matrix[i][j + 1] > matrix[i][j]) ? process3(matrix, i, j + 1, dp) : 0;

        dp[i][j] = Math.max(down, Math.max(up, Math.max(left, right)));
        return dp[i][j];
    }


}
