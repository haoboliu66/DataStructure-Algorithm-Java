package java.someOA;

import java.util.Arrays;

public class Problem_304_RangeSumQuery2D_Immutable {

    // https://leetcode.com/problems/range-sum-query-2d-immutable/

    public static void main(String[] args) {
        int[][] m = {{3, 0, 1}, {5, 6, 3}, {1, 2, 0}};
        NumMatrix matrix = new NumMatrix(m);
        System.out.println(Arrays.deepToString(matrix.sum));
    }

    private static class NumMatrix {

        int[][] matrix;
        int[][] sum;

        public NumMatrix(int[][] matrix) {
            this.matrix = matrix;
            int m = matrix.length, n = matrix[0].length;
            sum = new int[m + 1][n + 1];
            getMatrixSum(this.matrix, sum);
        }

        private void getMatrixSum(int[][] matrix, int[][] sum) {
            for (int i = 1; i <= matrix.length; i++) {
                for (int j = 1; j <= matrix[0].length; j++) {
                    sum[i][j] = matrix[i - 1][j - 1] + sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return sum[row2 + 1][col2 + 1] - sum[row2 + 1][col1] - sum[row1][col2 + 1] + sum[row1][col1];
        }


    }

}
