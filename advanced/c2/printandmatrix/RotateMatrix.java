package advanced.c2.printandmatrix;

/**
 * @author andy-liu
 * @date 2020/6/11 - 2:39 PM
 */
public class RotateMatrix {

    /*
    LeetCode48. Rotate Image
     */

    public static void rotate(int[][] matrix) {

        int aR = 0;
        int aC = 0;
        int bR = matrix.length - 1;
        int bC = matrix[0].length - 1;
        while (aR <= bR && aC <= bC) {
            rotate(matrix, aR++, aC++, bR--, bC--);
        }
    }

    private static void rotate(int[][] matrix, int aR, int aC, int bR, int bC) {
        for (int i = 0; i < bC - aC; i++) {
            int temp = matrix[aR][aC + i];
            matrix[aR][aC + i] = matrix[bR - i][aC];
            matrix[bR - i][aC] = matrix[bR][bC - i];
            matrix[bR][bC - i] = matrix[aR + i][bC];
            matrix[aR + i][bC] = temp;
        }
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        printMatrix(matrix);
        rotate(matrix);
        System.out.println("=========");
        printMatrix(matrix);

    }
}
