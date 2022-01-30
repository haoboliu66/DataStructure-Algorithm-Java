package advanced.c3.class4;


public class C07_SubMatrixMaxSum {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 1, 1, 1, -3},
                {1, -5, -20, 1, 1},
                {1, 1, 1, 1, 1},
                {1, -15, 1, 1, -8},
                {1, 1, -10, 1, 1}};
        int res = maxSum(matrix);
        System.out.println(res);
    }


    public static int maxSum(int[][] matrix) {
        int M = matrix.length, N = matrix[0].length;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < M; i++) {
            for (int j = i; j < M; j++) {
                // row from [i] to [j]
                int[] arr = compact(matrix, i, j);
                int sum = maxSumOnArray(arr);
                max = Math.max(sum, max);
            }
        }
        return max;
    }

    // compact matrix from i to j into one array
    public static int[] compact(int[][] matrix, int startRow, int endRow) {
        if (startRow == endRow) {
            return matrix[startRow];
        }
        int col = matrix[startRow].length;
        int[] res = new int[col];
        int index, sum;
        for (index = 0; index < col; index++) {
            sum = 0;
            for (int i = startRow; i <= endRow; i++) {
                sum += matrix[i][index];
                res[index] = sum;
            }
        }
        return res;
    }


    public static int maxSumOnArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int cur = 0;
        for (int i = 0; i < arr.length; i++) {
            cur += arr[i];
            max = Math.max(cur, max);
            if (cur < 0) {
                cur = 0;
            }
        }
        return max;
    }


}
