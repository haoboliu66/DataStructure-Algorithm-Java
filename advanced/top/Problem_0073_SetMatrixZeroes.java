package advanced.top;

public class Problem_0073_SetMatrixZeroes {

    public void setZeroes(int[][] matrix) {

        boolean rowAllZero = false;
        boolean colAllZero = false;

        /*
         1.
         先用第一行和第一列做标识位置:
         从[1][1]开始遍历
         如果[i][j]是0, 就用[i][0]和[0][j]做标记,
         表示这[i]行和这[j]列都要变0

         --- 避免[i][0]和[0][j]本来位置就是0, 所以先提前遍历0行和0列, 检验这他们上是否有0

         2.
         再遍历一次, 如果[i][j]位置对应的[i][0]或者[0][j]是0, 那么它也要变0

         3.
         如果0行和0列需要变0, 单独遍历处理
         */


        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                colAllZero = true;
                break;
            }
        }

        for (int j = 0; j < matrix[0].length; j++) {
            if (matrix[0][j] == 0) {
                rowAllZero = true;
                break;
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (colAllZero) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }

        if (rowAllZero) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[0][j] = 0;
            }
        }
    }


}
