package com.hliu.advanced.system._27_fibonacci;

public class MatrixPower {

  // [a,b] * [b,c] = [a,c]
  public static int[][] multiplyMatrix(int[][] m1, int[][] m2) {
    int[][] res = new int[m1.length][m2[0].length];

    for (int i = 0; i < res.length; i++) {
      for (int j = 0; j < res[0].length; j++) {
        for (int k = 0; k < m2.length; k++) { // b
          res[i][j] += m1[i][k] * m2[k][j];
        }
      }
    }

    return res;
  }

  public static int[][] generateMatrix(int x, int y, int maxVal) {
    int[][] res = new int[x][y];
    for (int i = 0; i < x; i++) {
      for (int j = 0; j < res[i].length; j++) {
        res[i][j] = (int) (Math.random() * maxVal + 1);
      }
    }
    return res;
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
    int[][] m1 = generateMatrix(4, 3, 50);
    int[][] m2 = generateMatrix(3, 2, 50);
    printMatrix(m1);
    System.out.println("------------");
    printMatrix(m2);
    System.out.println("------------");
    int[][] res = multiplyMatrix(m1, m2);
    printMatrix(res);
  }

}
