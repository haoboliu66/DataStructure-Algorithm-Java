package advanced.c4.class2;

/**
 * @author andy-liu
 * @date 2020/7/18 - 10:23 PM
 */
public class C06_CherryPickup {

    /*
    741. Cherry Pickup
    1463. Cherry Pickup II
     */

    public static int comeGoMaxPathSum(int[][] matrix) {
        if (matrix == null || matrix[0].length == 0) {
            return 0;
        }

        return process(matrix, 0, 0, 0);
    }

    public static int process(int[][] matrix, int Ar, int Ac, int Br) {
        int M = matrix.length;
        int N = matrix[0].length;
        if (Ar == M - 1 && Ac == N - 1) {
            return matrix[Ar][Ac];
        }
        // 还没到右下角
        // A 下   B 右
        // A 下   B 下
        // A 右   B 右
        // A 右   B 下
        int Bc = Ar + Ac - Br;
        int ADownBRight = -1;
        if (Ar + 1 < M && Bc + 1 < N) {
            ADownBRight = process(matrix, Ar + 1, Ac, Br);
        }
        int ADownBDown = -1;
        if (Ar + 1 < M && Br + 1 < M) {
            ADownBDown = process(matrix, Ar + 1, Ac, Br + 1);
        }
        int ARightBRight = -1;
        if (Ac + 1 < N && Bc + 1 < N) {
            ARightBRight = process(matrix, Ar, Ac + 1, Br);
        }
        int ARightBDown = -1;
        if (Ac + 1 < N && Br + 1 < M) {
            ARightBDown = process(matrix, Ar, Ac + 1, Br + 1);
        }

        int nextBest = Math.max(Math.max(ADownBRight, ADownBDown), Math.max(ARightBRight, ARightBDown));

        // A和B走到了相同的位置
        if (Ar == Br) {
            return matrix[Ar][Ac] + nextBest;
        }

        // A和B走到了不同的位置
        return matrix[Ar][Ac] + matrix[Br][Bc] + nextBest;
    }


    public static int cherryPick(int[][] matrix) {
        if (matrix == null || matrix[0].length == 0) {
            return 0;
        }
        int M = matrix.length;
        int N = matrix[0].length;
        int[][][] dp = new int[M][N][M];
//        if (Ar == M - 1 && Ac == N - 1) {
//            return matrix[Ar][Ac];
//        }
        dp[M - 1][N - 1][M - 1] = matrix[M - 1][N - 1];


//        int ADownBRight = -1;
//        if (Ar + 1 < M && Bc + 1 < N) {
//            ADownBRight = process(matrix, Ar + 1, Ac, Br);
//        }
//        int ADownBDown = -1;
//        if (Ar + 1 < M && Br + 1 < M) {
//            ADownBDown = process(matrix, Ar + 1, Ac, Br + 1);
//        }
//        int ARightBRight = -1;
//        if (Ac + 1 < N && Bc + 1 < N) {
//            ARightBRight = process(matrix, Ar, Ac + 1, Br);
//        }
//        int ARightBDown = -1;
//        if (Ac + 1 < N && Br + 1 < M) {
//            ARightBDown = process(matrix, Ar, Ac + 1, Br + 1);
//        }
        for (int i = M - 2; i >= 0; i--) {
            for (int j = N - 2; j >= 0; j--) {
                for (int k = M - 2; k >= 0; k--) {
                    //Ar--i,  Ac--j, Br--k, Bc-- i+j-k
                    int ADownBRight = i + 1 < M && i + j - k + 1 < N ? dp[i + 1][j][k] : -1;
                    int ADownBDown = i + 1 < M && k + 1 < M ? dp[i + 1][j][k + 1] : -1;
                    int ARightBRight = j + 1 < N && i + j - k + 1 < N ? dp[i][j + 1][k] : -1;
                    int ARightBDown = j + 1 < N && k + 1 < M ? dp[i][j + 1][k + 1] : -1;
                    int nextBest = Math.max(Math.max(ADownBRight, ADownBDown), Math.max(ARightBRight, ARightBDown));

                    dp[i][j][k] = i == k ? matrix[i][j] + nextBest : matrix[i][j] + matrix[k][i + j - k] + nextBest;

                }
            }
        }

        return dp[0][0][0];
    }


    public static void main(String[] args) {
        int[][] matrix = {
                {1,1,1,1,1,0,0,0,0,0},
                {0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,1,0,0,0,0,1},
                {1,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,1,1,1,1,1,1}
        };

        System.out.println(comeGoMaxPathSum(matrix));
        System.out.println(cherryPick(matrix));


    }


    /*
     741. Cherry Pickup
     */
    public static int cherryPickup(int[][] matrix) {
        if (matrix == null || matrix[0].length == 0) {
            return 0;
        }

        return process2(matrix, 0, 0, 0);
    }

    public static int process2(int[][] matrix, int Ar, int Ac, int Br) {
        int M = matrix.length;
        int N = matrix[0].length;
        if (Ar == M - 1 && Ac == N - 1) {
            return matrix[Ar][Ac];
        }
        // 还没到右下角
        // A 下   B 右
        // A 下   B 下
        // A 右   B 右
        // A 右   B 下
        int Bc = Ar + Ac - Br;
        int ADownBRight = -1;
        if (Ar + 1 < M && Bc + 1 < N && matrix[Ar + 1][Ac] != -1 && matrix[Br][Bc + 1] != -1) {
            ADownBRight = process(matrix, Ar + 1, Ac, Br);
        }
        int ADownBDown = -1;
        if (Ar + 1 < M && Br + 1 < M && matrix[Ar + 1][Ac] != -1 && matrix[Br + 1][Bc] != -1) {
            ADownBDown = process(matrix, Ar + 1, Ac, Br + 1);
        }
        int ARightBRight = -1;
        if (Ac + 1 < N && Bc + 1 < N && matrix[Ar][Ac + 1] != -1 && matrix[Br][Bc + 1] != -1) {
            ARightBRight = process(matrix, Ar, Ac + 1, Br);
        }
        int ARightBDown = -1;
        if (Ac + 1 < N && Br + 1 < M && matrix[Ar][Ac + 1] != -1 && matrix[Br + 1][Bc] != -1) {
            ARightBDown = process(matrix, Ar, Ac + 1, Br + 1);
        }

        int nextBest = Math.max(Math.max(ADownBRight, ADownBDown), Math.max(ARightBRight, ARightBDown));

        // A和B走到了相同的位置
        if (Ar == Br) {
            return matrix[Ar][Ac] + nextBest;
        }

        // A和B走到了不同的位置
        return matrix[Ar][Ac] + matrix[Br][Bc] + nextBest;
    }


}
