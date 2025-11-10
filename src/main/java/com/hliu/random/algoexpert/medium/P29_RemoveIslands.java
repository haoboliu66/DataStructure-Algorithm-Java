package com.hliu.random.algoexpert.medium;

public class P29_RemoveIslands {

  public static void main(String[] args) {
    int[][] matrix = {
        {1, 0, 0, 0, 0, 0},
        {0, 1, 0, 1, 1, 1},
        {0, 0, 1, 0, 1, 0},
        {1, 1, 0, 0, 1, 0},
        {1, 0, 1, 1, 0, 0},
        {1, 0, 0, 0, 0, 1}
    };
  }

  public int[][] removeIslands(int[][] matrix) {
    int m = matrix.length, n = matrix[0].length;
    infectBorder(matrix, 1, -1);
    print(matrix);

    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        if (matrix[i][j] == 1) {
          infect(matrix, i, j, 0);
        }
      }
    }

    infectBorder(matrix, -1, 1);

    return matrix;
  }

  public void infect(int[][] matrix, int i, int j, int target) {
    int m = matrix.length, n = matrix[0].length;
    if (i >= m || i < 0 || j < 0 || j >= n || matrix[i][j] == target || matrix[i][j] == 0) {
      return;
    }
    matrix[i][j] = target;
    infect(matrix, i + 1, j, target);
    infect(matrix, i - 1, j, target);
    infect(matrix, i, j + 1, target);
    infect(matrix, i, j - 1, target);
  }

  public void infectBorder(int[][] matrix, int cur, int target) {
    int m = matrix.length, n = matrix[0].length;
    for (int i = 0; i < m; i++) {
      if (matrix[i][0] == cur) {
        infect(matrix, i, 0, target);
      }
      if (matrix[i][n - 1] == cur) {
        infect(matrix, i, n - 1, target);
      }
    }

    for (int j = 0; j < n; j++) {
      if (matrix[0][j] == cur) {
        infect(matrix, 0, j, target);
      }
      if (matrix[m - 1][j] == cur) {
        infect(matrix, m - 1, j, target);
      }
    }
  }


  // for test
  public static void print(int[][] matrix) {
    int m = matrix.length, n = matrix[0].length;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
    }
  }

}
