package com.hliu.random.leetcode.bfs_dfs;

import java.util.LinkedList;
import java.util.Queue;

public class M_Problem_0490_TheMaze {

  public static void main(String[] args) {

  }

  // dfs todo
  public boolean hasPath1(int[][] maze, int[] start, int[] dest) {
    boolean[][] visited = new boolean[maze.length][maze[0].length];
    return dfs(maze, start[0], start[1], dest[0], dest[1], visited);
  }

  public boolean dfs(int[][] maze, int x, int y, int ei, int ej, boolean[][] visited) {
    if (x == ei && y == ej) {
      return true;
    }
    if (visited[x][y]) {
      return false;
    }
    boolean res = false;
    visited[x][y] = true;

    int row = x;
    while (row + 1 < maze.length && maze[row + 1][y] == 0) {
      row++;
    }
    res |= dfs(maze, row, y, ei, ej, visited);

    row = x;
    while (row - 1 >= 0 && maze[row - 1][y] == 0) {
      row--;
    }
    res |= dfs(maze, row, y, ei, ej, visited);

    int col = y;
    while (col + 1 < maze[0].length && maze[row][col + 1] == 0) {
      col++;
    }
    res |= dfs(maze, x, col, ei, ej, visited);

    col = y;
    while (col - 1 >= 0 && maze[row][col - 1] == 0) {
      col--;
    }
    res |= dfs(maze, x, col, ei, ej, visited);

    visited[x][y] = false;
    return res;
  }

  // bfs
  public boolean hasPath0(int[][] maze, int[] start, int[] dest) {
    Queue<int[]> q = new LinkedList<>();
    q.offer(start);
    boolean[][] visited = new boolean[maze.length][maze[0].length];
    while (!q.isEmpty()) {
      int[] cur = q.poll();
      int x = cur[0], y = cur[1];
      if (x == dest[0] && y == dest[1]) {
        return true;
      }
      int row = x;
      while (row + 1 < maze.length && maze[row + 1][y] == 0) {
        row++;
      }
      if (!visited[row][y]) {
        visited[row][y] = true;
        q.offer(new int[]{row, y});
      }
      row = x;
      while (row - 1 >= 0 && maze[row - 1][y] == 0) {
        row--;
      }
      if (!visited[row][y]) {
        visited[row][y] = true;
        q.offer(new int[]{row, y});
      }
      int col = y;
      while (col + 1 < maze[0].length && maze[x][col + 1] == 0) {
        col++;
      }
      if (!visited[x][col]) {
        visited[x][col] = true;
        q.offer(new int[]{x, col});
      }
      col = y;
      while (col - 1 >= 0 && maze[x][col - 1] == 0) {
        col--;
      }
      if (!visited[x][col]) {
        visited[x][col] = true;
        q.offer(new int[]{x, col});
      }
    }
    return false;
  }


  public boolean fromValidPositions(int[][] maze, int x, int y, boolean[][] visited) {
    return (x - 1 >= 0 /*&& visited[x - 1][y]*/ && (x + 1 >= maze.length || (x + 1 < maze.length && maze[x + 1][y] ==
        1)))
        || (x + 1 < maze.length /*&& visited[x + 1][y]*/ && (x - 1 < 0 || (x - 1 >= 0 && maze[x - 1][y] == 1)))
        || (y - 1 >= 0 /*&& visited[x][y - 1]*/ && (y + 1 >= maze[0].length || (y + 1 < maze[0].length
        && maze[x][y + 1] == 1))) ||
        (y + 1 < maze[0].length /*&& visited[x][y + 1]*/ && (y - 1 < 0 || (y - 1 >= 0 && maze[x][y - 1] == 1)));
  }

  public boolean hasPath(int[][] maze, int[] start, int[] dest) {
    Queue<int[]> q = new LinkedList<>();
    q.offer(start);
    boolean[][] visited = new boolean[maze.length][maze[0].length];
    while (!q.isEmpty()) {
      int size = q.size();
      for (int i = 0; i < size; i++) {
        int[] cur = q.poll();
        int x = cur[0], y = cur[1];
        if (x == dest[0] && y == dest[1] && fromValidPositions(maze, x, y, visited)) {
          return true;
        }
        if (x - 1 >= 0 && maze[x - 1][y] != 1 && (!visited[x - 1][y] || (x - 1 == dest[0] && y == dest[1]))) {
          visited[x - 1][y] = true;
          q.offer(new int[]{x - 1, y});
        }
        if (x + 1 < maze.length && maze[x + 1][y] != 1 && (!visited[x + 1][y] || (x + 1 == dest[0] && y == dest[1]))) {
          visited[x + 1][y] = true;
          q.offer(new int[]{x + 1, y});
        }
        if (y - 1 >= 0 && maze[x][y - 1] != 1 && (!visited[x][y - 1] || (x == dest[0] && y - 1 == dest[1]))) {
          visited[x][y - 1] = true;
          q.offer(new int[]{x, y - 1});
        }
        if (y + 1 < maze[0].length && maze[x][y + 1] != 1 && (!visited[x][y + 1] || (x == dest[0]
            && y + 1 == dest[1]))) {
          visited[x][y + 1] = true;
          q.offer(new int[]{x, y + 1});
        }
      }
    }
    return false;
  }


}
