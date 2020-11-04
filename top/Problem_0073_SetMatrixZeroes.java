package top;

public class Problem_0073_SetMatrixZeroes {

    public void setZeroes(int[][] matrix) {

        boolean rowAllZero = false;
        boolean colAllZero = false;

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
