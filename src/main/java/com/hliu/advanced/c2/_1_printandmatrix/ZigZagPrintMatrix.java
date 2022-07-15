package com.hliu.advanced.c2._1_printandmatrix;

public class ZigZagPrintMatrix {

  public static void printMatrixZigZag(int[][] matrix) {
    boolean bottomUp = true; //第一次从下到上打印
    int aR = 0;
    int aC = 0;
    int bR = 0;
    int bC = 0;
    int endR = matrix.length - 1;
    int endC = matrix[0].length - 1;
    while (aR < endR + 1) {
      printLevel(matrix, aR, aC, bR, bC, bottomUp);
      aR = aC == endC ? aR + 1 : aR;
      aC = aC == endC ? aC : aC + 1;
      bC = bR == endR ? bC + 1 : bC;
      bR = bR == endR ? bR : bR + 1;
      bottomUp = !bottomUp;
    }
    System.out.println();
  }

  private static void printLevel(int[][] matrix, int aR, int aC, int bR, int bC, boolean bottomUp) {
    if (bottomUp) { //由下到上
      while (bR != aR - 1) {
        System.out.print(matrix[bR--][bC++] + " ");
      }
      System.out.println();
    } else { //由上到下
      while (aC != bC - 1) {
        System.out.print(matrix[aR++][aC--] + " ");
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
    printMatrixZigZag(matrix);

  }


  public static class tt {

    public static void printMatrixZigZag(int[][] matrix) {
      int aR = 0;
      int aC = 0;
      int bR = 0;
      int bC = 0;
      int endR = matrix.length - 1;
      int endC = matrix[0].length - 1;
      boolean fromUp = false;
      while (aR != endR + 1) {
        printLevel(matrix, aR, aC, bR, bC, fromUp);
        aR = aC == endC ? aR + 1 : aR;
        aC = aC == endC ? aC : aC + 1;
        bC = bR == endR ? bC + 1 : bC;
        bR = bR == endR ? bR : bR + 1;
        fromUp = !fromUp;
      }
      System.out.println();
    }

    public static void printLevel(int[][] m, int aR, int aC, int bR, int bC,
        boolean f) {
      if (f) {
        while (aR != bR + 1) {
          System.out.print(m[aR++][aC--] + " ");
        }
      } else {
        while (bR != aR - 1) {
          System.out.print(m[bR--][bC++] + " ");
        }
      }
    }

  }

}
