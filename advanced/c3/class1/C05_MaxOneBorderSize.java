package advanced.c3.class1;

public class C05_MaxOneBorderSize {
    /*
    lc 85. Maximal Rectangle

    lc 221. Maximal Square
    */

    // brute force O(N^4)
    public static int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;

        int M = matrix.length;
        int N = matrix[0].length;
        int limit = Math.min(M, N);

        int area = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (matrix[i][j] != '1') continue;

                // side length of the square
                for (int len = 1; len <= limit; len++) {
                    // 验证square四边是否都为'1'
                    if (validate(matrix, i, j, len)) {
                        area = Math.max(area, len * len);
                    } else {
                        break;
                    }
                }
            }
        }
        return area;
    }

    // 验证: matrix里, [i][j]位置开始, 长度为len的正方形, 边上是否都为'1'
    public static boolean validate(char[][] matrix, int i, int j, int len) {
        int rA = i;
        int cA = j;
        int rB = i + len - 1;
        int cB = j + len - 1;

        if (rB >= matrix.length || cB >= matrix[0].length) return false;

        for (int r = rA; r <= rB; r++) {
            if (matrix[r][cA] != '1') return false;
        }
        for (int r = rA; r <= rB; r++) {
            if (matrix[r][cB] != '1') return false;
        }
        for (int c = cA; c <= cB; c++) {
            if (matrix[rA][c] != '1') return false;
        }
        for (int c = cA; c <= cB; c++) {
            if (matrix[rB][c] != '1') return false;
        }

        return true;
    }
/* -------------------------------------------------------------  */

    // O(N^3)
    public static int maximalSquare2(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;

        int M = matrix.length;
        int N = matrix[0].length;
        int limit = Math.min(M, N);

        int[][] right = new int[M][N]; // [i][j]位置右侧最多有几个'1'
        int[][] down = new int[M][N];  // [i][j]位置往下最多有几个'1'

        fillHelpMatrix(matrix, right, down);

        int area = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (matrix[i][j] != '1') continue;

                // side length of the square
                for (int len = 1; len <= limit; len++) {
                    // 验证square四边是否都为'1'
                    if (validate1(matrix, i, j, len, right, down)) {
                        area = Math.max(area, len * len);
                        System.out.println("len == " + len);
                        System.out.println("area == " + area);
                    } else {
                        break;
                    }
                }
            }
        }
        return area;
    }

    // 验证: matrix中, [i][j]位置,长度为len的正方形内, 边长是否有足够的'1'
    private static boolean validate1(char[][] matrix, int i, int j, int len, int[][] right, int[][] down) {
        int rA = i;
        int cA = j;
        int rB = i + len - 1;
        int cB = j + len - 1;
        if (rB >= matrix.length || cB >= matrix[0].length) return false;

        return down[rA][cA] >= len && down[rA][cB] >= len
                && right[rA][cA] >= len && right[rB][cA] >= len;
    }

    // 填辅助数组, O(N)
    private static void fillHelpMatrix(char[][] matrix, int[][] right, int[][] down) {
        int M = matrix.length;
        int N = matrix[0].length;
        // 填right的最右侧一列
        for (int i = 0; i < M; i++) {
            right[i][N - 1] = matrix[i][N - 1] == '1' ? 1 : 0;
        }
        // 从右-->左依次填right
        for (int i = 0; i < M; i++) {
            for (int j = N - 2; j >= 0; j--) {
                right[i][j] = matrix[i][j] == '0' ? 0 : 1 + right[i][j + 1];
            }
        }
        // 填down的最底下一行
        for (int j = 0; j < N; j++) {
            down[M - 1][j] = matrix[M - 1][j] == '1' ? 1 : 0;
        }
        // 从下-->上依次填down
        for (int j = 0; j < N; j++) {
            for (int i = M - 2; i >= 0; i--) {
                down[i][j] = matrix[i][j] == '0' ? 0 : 1 + down[i + 1][j];
            }
        }
    }

    // for test
    public static void printMatrix(char[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
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
        char[][] matrix = {{'0', '1'}};
        char[][] matrix2 = {{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}};
        System.out.println(maximalSquare(matrix2));
        System.out.println(maximalSquare2(matrix2));
    }
}
