package leetcode;

public class M_Problem_0074_Search2DMatrix {

    // 下一行的所有数字一定都 > 当前行的数字
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int x = 0, y = n - 1;
        while (x <= m - 1 && y >= 0) {
            if (matrix[x][y] == target) {
                return true;
            }
            else if (target < matrix[x][y]) {
                y--;
            } else {
                x++;
            }
        }
        return false;
    }
}
