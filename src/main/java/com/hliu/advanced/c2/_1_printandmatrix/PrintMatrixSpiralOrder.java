package com.hliu.advanced.c2._1_printandmatrix;

import java.util.ArrayList;
import java.util.List;

public class PrintMatrixSpiralOrder {
    /*
    54. Spiral Matrix
    59. Spiral Matrix II
     */

  public List<Integer> spiralOrder(int[][] matrix) {
    int m = matrix.length, n = matrix[0].length;
    int ar = 0, ac = 0, br = m - 1, bc = n - 1;

    List<Integer> res = new ArrayList<>();
    /**
     1  2  3) 4
     -> 5  6  7  8 <-
     9 (10 11 12
     *
     *
     */
    while (ar < br && ac < bc) {
      print(matrix, ar++, ac++, br--, bc--, res);
    }

    if (ac == bc) {
             /*
            a
            b
            c
            */
      while (ar <= br) {
        res.add(matrix[ar++][ac]);
      }
    }
    if (ar == br) {
      /*  a b c */
      while (ac <= bc) {
        res.add(matrix[ar][ac++]);
      }
    }

    return res;
  }

  public void print(int[][] matrix, int ar, int ac, int br, int bc, List<Integer> res) {

    int Ar = ar, Ac = ac; // store for later reference

    while (ac < bc) {
      res.add(matrix[ar][ac++]);
    }
    while (ar < br) {
      res.add(matrix[ar++][bc]);
    }
    while (bc > Ac) {
      res.add(matrix[br][bc--]);
    }
    while (br > Ar) {
      res.add(matrix[br--][Ac]);
    }
  }


  private static class SpiralMatrix {

    //54. Spiral Matrix
    public static void spiralOrderPrint(int[][] matrix) {
      int aR = 0;
      int aC = 0;
      int bR = matrix.length - 1;
      int bC = matrix[0].length - 1;
      while (aR <= bR && aC <= bC) {
        printEdge(matrix, aR++, aC++, bR--, bC--);
      }
    }

    private static void printEdge(int[][] matrix, int aR, int aC, int bR, int bC) {
      if (aR == bR) {
        for (int i = aC; i <= bC; i++) {
          System.out.print(matrix[aR][i] + " ");
        }
      } else if (aC == bC) {
        for (int i = aR; i <= bR; i++) {
          System.out.print(matrix[i][aC] + " ");
        }
      } else {
        int curR = aR;
        int curC = aC;
        while (curC != bC) {
          System.out.print(matrix[aR][curC++] + " ");
        }
        while (curR != bR) {
          System.out.print(matrix[curR++][bC] + " ");
        }
        while (curC != aC) {
          System.out.print(matrix[bR][curC--] + " ");
        }
        while (curR != aR) {
          System.out.print(matrix[curR--][aC] + " ");
        }

      }
    }

    public static void main(String[] args) {
      int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12},
          {13, 14, 15, 16}};
      int[][] matrix1 = {{1, 2, 3}, {5, 6, 7}, {9, 10, 11},
          {13, 14, 15}};
      int[][] matrix2 = {{1, 2, 3, 4, 5}};
      spiralOrderPrint(matrix1);

    }
  }


  private static class SpiralMatrixII {

    //59. Spiral Matrix II
    public static int[][] generateMatrix(int n) {

      int[][] res = new int[n][n];
      int aRow = 0;
      int aCol = 0;
      int bRow = n - 1;
      int bCol = n - 1;
      int[] num = {1};
      while (aRow <= bRow) {
        fillMatrix(res, aRow++, aCol++, bRow--, bCol--, num);
      }

      return res;
    }

    public static void fillMatrix(int[][] matrix, int aRow, int aCol, int bRow, int bCol, int[] n) {
      if (aRow == bRow) {
        matrix[aRow][bCol] = n[0];
        return;
      }
      int curRow = aRow;
      int curCol = aCol;

      while (curCol < bCol) {
        matrix[aRow][curCol++] = n[0]++;
      }
      while (curRow < bRow) {
        matrix[curRow++][bCol] = n[0]++;
      }
      while (curCol > aCol) {
        matrix[bRow][curCol--] = n[0]++;
      }
      while (curRow > aRow) {
        matrix[curRow--][aCol] = n[0]++;
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
      int n = 5;
      int[][] matrix = generateMatrix(n);
      printMatrix(matrix);
    }
  }


}
