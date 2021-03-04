package advanced.c3.class3;

public class C03_MinPathSum {



    /*
    64. Minimum Path Sum

    空间压缩
     */

    public static int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        return process(grid, 0, 0);
    }

    // 从(i, j)开始走到右下角的最小sum
    public static int process(int[][] grid, int i, int j) {
        if (i == grid.length - 1 && j == grid[0].length - 1) return grid[i][j];

        int right = Integer.MAX_VALUE;
        int down = Integer.MAX_VALUE;

        if (j + 1 < grid[0].length) {
            right = process(grid, i, j + 1);
        }

        if (i + 1 < grid.length) {
            down = process(grid, i + 1, j);
        }

        return Math.min(right, down) + grid[i][j];
    }

    // Memoization
    public static int minPathSum1(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int M = grid.length, N = grid[0].length;
        int[][] dp = new int[M][N];
        return process1(grid, 0, 0, dp);
    }

    // 从(i, j)开始走到右下角的最小sum
    public static int process1(int[][] grid, int i, int j, int[][] dp) {
        if (dp[i][j] != 0) return dp[i][j];

        if (i == grid.length - 1 && j == grid[0].length - 1) return grid[i][j];

        int right = Integer.MAX_VALUE;
        int down = Integer.MAX_VALUE;

        if (j + 1 < grid[0].length) {
            right = process1(grid, i, j + 1, dp);
        }

        if (i + 1 < grid.length) {
            down = process1(grid, i + 1, j, dp);
        }

        dp[i][j] = Math.min(right, down) + grid[i][j];
        return dp[i][j];
    }


    // DP
    public static int minPathSum2(int[][] grid) {

        if (grid == null || grid.length == 0) return 0;

        int M = grid.length, N = grid[0].length;
        int[][] dp = new int[M][N];

        dp[0][0] = grid[0][0];

        for (int i = 1; i < M; i++) {
            dp[i][0] = grid[i][0] + dp[i - 1][0];
        }

        for (int j = 1; j < N; j++) {
            dp[0][j] = grid[0][j] + dp[0][j - 1];
        }

        for (int i = 1; i < M; i++) {
            for (int j = 1; j < N; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }

        return dp[M - 1][N - 1];
    }


}
