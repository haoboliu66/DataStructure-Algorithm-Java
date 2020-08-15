package advanced.c4.class2;

/**
 * @author andy-liu
 * @date 2020/7/18 - 9:23 PM
 */
public class C05_DungeonGame {

    /*
    174. Dungeon Game
     */

    public static int calculateMinimumHP1(int[][] matrix) {
        if (matrix == null) {
            return 0;
        }

        return process(matrix, matrix.length, matrix[0].length, 0, 0);
    }

    /**
     * @param matrix 固定参数
     * @param M      固定参数
     * @param N      固定参数
     * @param row    当前来到位置row
     * @param col    当前来到位置col
     * @return 最少需要的HP
     */
    private static int process(int[][] matrix, int M, int N, int row, int col) {
        if (row == M - 1 && col == N - 1) { //到最后一个位置
            return matrix[row][col] < 0 ? 1 - matrix[row][col] : 1;
        }

        if (row == M - 1) { //只能往右走
            int rightNeed = process(matrix, M, N, row, col + 1);
            if (matrix[row][col] < 0) { //当前位置值<0, [-5,3]
                return -matrix[row][col] + rightNeed;

            } else if (matrix[row][col] >= rightNeed) {
                //当前位置的值>=rightNeed,  [7, 3]或[3,3], 没登上这个位置前, 有1就够了
                return 1;

            } else {
                //当前位置的值 < rightNeed,  [1, 3], 还需要2才能向右走
                return rightNeed - matrix[row][col];
            }
        }

        if (col == N - 1) { //只能往下走
            int downNeed = process(matrix, M, N, row + 1, col);
            if (matrix[row][col] < 0) {
                return -matrix[row][col] + downNeed;
            } else if (matrix[row][col] >= downNeed) {
                //当前位置的值>=downNeed,  [7, 3]或[3,3]
                return 1;
            } else {
                //当前位置的值 < downNeed,  [1, 3], 还需要2才能向右走
                return downNeed - matrix[row][col];
            }
        }

        //普遍位置
        int minNeed = Math.min(process(matrix, M, N, row, col + 1), process(matrix, M, N, row + 1, col));
        if (matrix[row][col] < 0) {
            return -matrix[row][col] + minNeed;
        } else if (matrix[row][col] >= minNeed) {
            return 1;
        } else {
            return minNeed - matrix[row][col];
        }
    }

    /* -----------------------------------------------------------------------------*/

    //Memoization 傻缓存
    public static int calculateMinimumHP2(int[][] matrix) {
        if (matrix == null) {
            return 0;
        }
        int M = matrix.length;
        int N = matrix[0].length;
        int[][] dp = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        return process2(matrix, M, N, 0, 0, dp);
    }

    private static int process2(int[][] matrix, int M, int N, int row, int col, int[][] dp) {
        if (dp[row][col] != Integer.MAX_VALUE) { //缓存
            return dp[row][col];
        }

        if (row == M - 1 && col == N - 1) { //到最后一个位置
            dp[row][col] = matrix[row][col] < 0 ? 1 - matrix[row][col] : 1;
            return matrix[row][col] < 0 ? 1 - matrix[row][col] : 1;
        }

        if (row == M - 1) { //只能往右走
            int rightNeed = process2(matrix, M, N, row, col + 1, dp);
            if (matrix[row][col] < 0) { //当前位置值<0, [-5,3]
                dp[row][col] = -matrix[row][col] + rightNeed;
                return -matrix[row][col] + rightNeed;

            } else if (matrix[row][col] >= rightNeed) {
                //当前位置的值>=rightNeed,  [7, 3]或[3,3], 没登上这个位置前, 有1就够了
                dp[row][col] = 1;
                return 1;

            } else {
                //当前位置的值 < rightNeed,  [1, 3], 还需要2才能向右走
                dp[row][col] = rightNeed - matrix[row][col];
                return rightNeed - matrix[row][col];
            }
        }

        if (col == N - 1) { //只能往下走
            int downNeed = process2(matrix, M, N, row + 1, col, dp);
            if (matrix[row][col] < 0) {
                dp[row][col] = -matrix[row][col] + downNeed;
                return -matrix[row][col] + downNeed;
            } else if (matrix[row][col] >= downNeed) {
                //当前位置的值>=downNeed,  [7, 3]或[3,3]
                dp[row][col] = 1;
                return 1;
            } else {
                //当前位置的值 < downNeed,  [1, 3], 还需要2才能向右走
                dp[row][col] = downNeed - matrix[row][col];
                return downNeed - matrix[row][col];
            }
        }

        //普遍位置
        int minNeed = Math.min(process2(matrix, M, N, row, col + 1, dp), process2(matrix, M, N, row + 1, col, dp));
        if (matrix[row][col] < 0) {
            dp[row][col] = -matrix[row][col] + minNeed;
            return -matrix[row][col] + minNeed;
        } else if (matrix[row][col] >= minNeed) {
            dp[row][col] = 1;
            return 1;
        } else {
            dp[row][col] = minNeed - matrix[row][col];
            return minNeed - matrix[row][col];
        }
    }

    /* --------------------------------------------------------------------------------*/

    // 递归改DP
    public static int calculateMinimumHP3(int[][] matrix) {

        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        int M = matrix.length;
        int N = matrix[0].length;

        int[][] dp = new int[M][N];//dp[i][j] 到i,j需要的最少生命值

//        if (row == M - 1 && col == N - 1) { //到最后一个位置
//            return matrix[row][col] < 0 ? 1 - matrix[row][col] : 1;
//        }
        dp[M - 1][N - 1] = matrix[M - 1][N - 1] < 0 ? 1 - matrix[M - 1][N - 1] : 1;

//        if (row == M - 1) { //只能往右走
//            int rightNeed = process(matrix, M, N, row, col + 1);
//            if (matrix[row][col] < 0) { //当前位置值<0, [-5,3]
//                return -matrix[row][col] + rightNeed;
//
//            } else if (matrix[row][col] >= rightNeed) {
//                //当前位置的值>=rightNeed,  [7, 3]或[3,3], 没登上这个位置前, 有1就够了
//                return 1;
//
//            } else {
//                //当前位置的值 < rightNeed,  [1, 3], 还需要2才能向右走
//                return rightNeed - matrix[row][col];
//            }
//        }

        for (int j = N - 2; j >= 0; j--) { //填最后一行, 从右到做
//            if (matrix[M - 1][j] < 0 || matrix[M - 1][j] < dp[M - 1][j + 1]) {
//                dp[M - 1][j] = dp[M - 1][j + 1] - matrix[M - 1][j];
//            } else {
//                dp[M - 1][j] = 1;
//            }
//            简化 ↓↓↓
            dp[M - 1][j] = (matrix[M - 1][j] < 0 || matrix[M - 1][j] < dp[M - 1][j + 1]) ? dp[M - 1][j] = dp[M - 1][j + 1] - matrix[M - 1][j] : 1;
        }


//        if (col == N - 1) { //只能往下走
//            int downNeed = process(matrix, M, N, row + 1, col);
//            if (matrix[row][col] < 0) {
//                return -matrix[row][col] + downNeed;
//            } else if (matrix[row][col] >= downNeed) {
//                //当前位置的值>=downNeed,  [7, 3]或[3,3]
//                return 1;
//            } else {
//                //当前位置的值 < downNeed,  [1, 3], 还需要2才能向右走
//                return downNeed - matrix[row][col];
//            }
//        }

        for (int i = M - 2; i >= 0; i--) { //填最后一列, 从下到上
//            if (matrix[i][N - 1] < 0 || matrix[i][N - 1] < dp[i + 1][N - 1]) {
//                dp[i][N - 1] = dp[i + 1][N - 1] - matrix[i][N - 1];
//            } else {
//                dp[i][N - 1] = 1;
//            }
//            简化 ↓↓↓
            dp[i][N - 1] = (matrix[i][N - 1] < 0 || matrix[i][N - 1] < dp[i + 1][N - 1]) ? dp[i][N - 1] = dp[i + 1][N - 1] - matrix[i][N - 1] : 1;
        }

        //普遍位置
//        int minNeed = Math.min(process(matrix, M, N, row, col + 1), process(matrix, M, N, row + 1, col));
//        if (matrix[row][col] < 0) {
//            return -matrix[row][col] + minNeed;
//        } else if (matrix[row][col] >= minNeed) {
//            return 1;
//        } else {
//            return minNeed - matrix[row][col];
//        }
        for (int i = M - 2; i >= 0; i--) {
            for (int j = N - 2; j >= 0; j--) {
                int minNeed = Math.min(dp[i + 1][j], dp[i][j + 1]);
                if (matrix[i][j] < 0 || matrix[i][j] < minNeed) {
                    dp[i][j] = minNeed - matrix[i][j];
                } else {
                    dp[i][j] = 1;
                }
            }
        }

        return dp[0][0];
    }


}
