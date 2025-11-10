package com.hliu.random.algoexpert.medium;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

// https://leetcode.com/problems/rotting-oranges/
public class P31_MinimumPassesOfMatrix {

  public static void main(String[] args) {
    int[][] matrix = {
        {0, -1, -3, 2, 0},
        {1, -2, -5, -1, -3},
        {3, 0, 0, -4, -1}
    };
    System.out.println(minimumPassesOfMatrix(matrix));
  }

  public static int minimumPassesOfMatrix(int[][] matrix) {
    // Write your code here.
    int neg = 0;
    int m = matrix.length, n = matrix[0].length;
    Set<Integer> set = new HashSet<>();
    Queue<int[]> q = new LinkedList<>();
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {

        if (matrix[i][j] < 0) {
          neg++;
        }

        if (matrix[i][j] > 0) {
          q.offer(new int[]{i, j});
          set.add(i * n + j);
        }
      }
    }
    if (neg == 0) {
      return 0;
    }

    int round = 0;
    while (!q.isEmpty()) {
      int size = q.size();
      for (int i = 0; i < size; i++) {
        int[] cur = q.poll();
        int x = cur[0], y = cur[1];
        if (x + 1 < m && !set.contains((x + 1) * n + y) && matrix[x + 1][y] < 0) {
          set.add((x + 1) * n + y);
          q.offer(new int[]{x + 1, y});
          matrix[x + 1][y] = -matrix[x + 1][y];
          neg--;
        }
        if (x - 1 >= 0 && !set.contains((x - 1) * n + y) && matrix[x - 1][y] < 0) {
          set.add((x - 1) * n + y);
          q.offer(new int[]{x - 1, y});
          matrix[x - 1][y] = -matrix[x - 1][y];
          neg--;
        }

        if (y - 1 >= 0 && !set.contains(x * n + y - 1) && matrix[x][y - 1] < 0) {
          set.add((x) * n + y - 1);
          q.offer(new int[]{x, y - 1});
          matrix[x][y - 1] = -matrix[x][y - 1];
          neg--;
        }
        if (y + 1 < n && !set.contains(x * n + y + 1) && matrix[x][y + 1] < 0) {
          set.add(x * n + y + 1);
          q.offer(new int[]{x, y + 1});
          matrix[x][y + 1] = -matrix[x][y + 1];
          neg--;
        }

      }
      round++;
      if (neg == 0) {
        break;
      }
    }

    return neg == 0 ? round : -1;
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
