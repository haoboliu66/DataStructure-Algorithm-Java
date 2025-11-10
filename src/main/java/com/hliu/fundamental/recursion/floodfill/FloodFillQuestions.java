package com.hliu.fundamental.recursion.floodfill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class FloodFillQuestions {

  // https://leetcode.com/problems/number-of-islands/
  public int numIslands(char[][] grid) {
    int island = 0;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == '1') {
          island++;
          infect(grid, i, j);
        }
      }
    }
    return island;
  }

  private void infect(char[][] grid, int i, int j) {
    if (i < 0 || i > grid.length - 1 || j < 0 || j > grid[0].length - 1 || grid[i][j] != '1') {
      return;
    }
    grid[i][j] = '2';
    infect(grid, i + 1, j);
    infect(grid, i - 1, j);
    infect(grid, i, j + 1);
    infect(grid, i, j - 1);
  }

  // https://leetcode.com/problems/surrounded-regions/
  public void solve(char[][] board) {
    int m = board.length;
    int n = board[0].length;
    for (int i = 0; i < m; i++) {
      markEdge(board, i, 0);
      markEdge(board, i, n - 1);
    }
    for (int j = 0; j < n; j++) {
      markEdge(board, 0, j);
      markEdge(board, m - 1, j);
    }

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (board[i][j] == 'O') {
          board[i][j] = 'X';
        }
        if (board[i][j] == 'E') {
          board[i][j] = 'O';
        }
      }
    }
  }

  private void mark(char[][] board, int i, int j, char expected, char mark) {
    if (i < 0 || i > board.length - 1 || j < 0 || j > board[0].length - 1 || board[i][j] != expected) {
      return;
    }
    board[i][j] = mark;
    mark(board, i + 1, j, expected, mark);
    mark(board, i - 1, j, expected, mark);
    mark(board, i, j + 1, expected, mark);
    mark(board, i, j - 1, expected, mark);
  }

  private void markEdge(char[][] board, int i, int j) {
    mark(board, i, j, 'O', 'E');
  }

  // https://leetcode.com/problems/making-a-large-island
  public int largestIsland(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;
    int islandId = 2;

    // 1. mark each islands with unique ids
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == '1') {
          dfsMarkIslandId(grid, i, j, islandId);
          islandId++;
        }
      }
    }
    int maxSize = 0;
    // 2. calculate all existing island size
    int[] islandSizes = new int[islandId]; //  size = islandSizes[islandId]
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        int id = grid[i][j];
        if (id != '1' && id != '0' && id > 1) {
          islandSizes[id]++;
          maxSize = Math.max(maxSize, islandSizes[id]);
        }
      }
    }

    // 3. for each '0', calculate the potential island size by connecting adjacent islands
    boolean[] visited = new boolean[islandId];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {

        if (grid[i][j] == '0') {
          int curSize = 1;
          int up = i > 0 ? grid[i - 1][j] : 0;
          int down = i < m - 1 ? grid[i + 1][j] : 0;
          int left = j > 0 ? grid[i][j - 1] : 0;
          int right = j < n - 1 ? grid[i][j + 1] : 0;
          if (!visited[up]) {
            curSize += islandSizes[up];
            visited[up] = true;
          }
          if (!visited[down]) {
            curSize += islandSizes[down];
            visited[down] = true;
          }
          if (!visited[left]) {
            curSize += islandSizes[left];
            visited[left] = true;
          }
          if (!visited[right]) {
            curSize += islandSizes[right];
            visited[right] = true;
          }
          maxSize = Math.max(maxSize, curSize);
          visited[up] = false;
          visited[down] = false;
          visited[left] = false;
          visited[right] = false;
        }
      }
    }
    return maxSize;
  }

  public void dfsMarkIslandId(int[][] grid, int i, int j, int id) {
    if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != 1) {
      return;
    }
    grid[i][j] = id;
    dfsMarkIslandId(grid, i + 1, j, id);
    dfsMarkIslandId(grid, i - 1, j, id);
    dfsMarkIslandId(grid, i, j + 1, id);
    dfsMarkIslandId(grid, i, j - 1, id);
  }

  // https://leetcode.com/problems/bricks-falling-when-hit/
  // 时光倒流技巧

  /*
  1. 先把所有炮弹位置减1
  2. floodfill所有的天花板的1砖块，标记为2
  3. 时光倒流, 逆序处理所有炮弹
     - 如果炮弹位置原本是0，说明没有砖块，继续下一个炮弹
     - 如果炮弹位置原本是1，说明有砖块，先把该位置设为1，然后floodfill该位置，如果该位置和天花板相连，则会把和它相连的所有砖块都标记为2
     - 计算当前floodfill后，天花板的砖块数量和之前的数量之差，减1就是掉落的砖块数量 (炮弹本身击中的位置不算掉落砖块)
   */

  public int[] hitBricks(int[][] grid, int[][] hits) {
    int m = grid.length, n = grid[0].length;
    int[] res = new int[hits.length];
    if (hits == null || hits.length == 0 || m <= 1) {
      return res;
    }
    // 1. 先把所有炮弹位置减1
    for (int[] hit : hits) {
      int x = hit[0], y = hit[1];
      grid[x][y]--;
    }
    // 2. 标记roof的所有1为2
    for (int j = 0; j < n; j++) {
      floodFill(grid, 0, j, m, n);
    }
    // 3. 时光倒流
    for (int i = hits.length - 1; i >= 0; i--) {
      int x = hits[i][0], y = hits[i][1];
      grid[x][y]++;
      if (grid[x][y] == 1 && isConnectedToOrIsRoof(grid, x, y, m, n)) {
        int bricksConnected = infect(grid, x, y, m, n);
        res[i] = bricksConnected - 1;
      }
    }
    return res;
  }

  private int infect(int[][] grid, int i, int j, int m, int n) {
    if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != 1) {
      return 0;
    }
    grid[i][j] = 2;
    int count = 1;
    count += infect(grid, i + 1, j, m, n);
    count += infect(grid, i - 1, j, m, n);
    count += infect(grid, i, j + 1, m, n);
    count += infect(grid, i, j - 1, m, n);
    return count;
  }

  private boolean isConnectedToOrIsRoof(int[][] grid, int i, int j, int m, int n) {
    return i == 0 || (i + 1 < m && grid[i + 1][j] == 2) || (i - 1 >= 0 && grid[i - 1][j] == 2) ||
        (j + 1 < n && grid[i][j + 1] == 2) || (j - 1 >= 0 && grid[i][j - 1] == 2);
  }

  private void floodFill(int[][] grid, int i, int j, int m, int n) {
    if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != 1) {
      return;
    }
    grid[i][j] = 2;
    floodFill(grid, i + 1, j, m, n);
    floodFill(grid, i - 1, j, m, n);
    floodFill(grid, i, j + 1, m, n);
    floodFill(grid, i, j - 1, m, n);
  }

  public static void main(String[] args) {

          // [0,5) => [1,6)
    int x = (int)(Math.random() * 5) + 1;
    if(x == 1) {
      System.out.println(x);
    }
  }

  public String removeDuplicates2(String s, int k) {
    if (s == null || s.length() < 2) {
      return s;
    }
    char[] str = s.toCharArray();
    Stack<Character> stack = new Stack<>();
    char[] count = new char[128];

    for (char c : str) {
      count[c]++;
      if (!stack.isEmpty() && stack.peek() == c) {
        // aacbbc c
        if (count[c] >= k) {
          count[c] -= k;
          for (int i = 0; i < k; i++) {
            stack.pop();
          }
        } else {
          stack.push(c);
        }

      } else {
        stack.push(c);
      }
    }
    return null;
  }

  public String removeDuplicates(String s, int k) {
    if (s == null || s.length() < 2) {
      return s;
    }
    char[] str = s.toCharArray();
    char[] stack = new char[str.length];
    char[] count = new char[26];
    int sp = 0;

    for (char c : str) {
      count[c - 'a']++;
      if (sp > 0 && stack[sp - 1] == c) {
        if (count[c - 'a'] == k) {
          sp -= k - 1;
          count[c - 'a'] -= k;
        } else {
          stack[sp++] = c;
        }
      } else {
        stack[sp++] = c;
      }
    }
    return new String(stack, 0, sp);
  }

  public List<String> invalidTransactions(String[] transactions) {
    List<String> invalidTx = new ArrayList<>(transactions.length);
    if (transactions == null || transactions.length <= 1) {
      return invalidTx;
    }

    List<Transaction> allTx = new ArrayList<>(transactions.length);
    for (String txStr : transactions) {
      String[] parts = txStr.split(",");
      String name = parts[0];
      int time = Integer.parseInt(parts[1]);
      int amount = Integer.parseInt(parts[2]);
      if (amount > 1000) {
        invalidTx.add(txStr);
        continue;
      }
      String city = parts[3];
      Transaction tx = new Transaction(name, time, amount, city);
      allTx.add(tx);
    }
    Map<String, List<Transaction>> txMap = new HashMap<>();
    for (Transaction tx : allTx) {
      txMap.computeIfAbsent(tx.name, v -> new ArrayList<>())
           .add(tx);
    }
    for (int i = 0; i < allTx.size(); i++) {
      Transaction cur = allTx.get(i);
      List<Transaction> transactionsSameName = txMap.get(cur.name);
      for (Transaction tx : transactionsSameName) {
        if (tx == cur) {
          continue;
        }
        if (!tx.city.equals(cur.city) && Math.abs(tx.time - cur.time) <= 60) {
          invalidTx.add(cur.name + "," + cur.time + "," + cur.amount + "," + cur.city);
          break;
        }
      }
    }

    return invalidTx;
  }

  private static class Transaction {

    String name;
    int time;
    int amount;
    String city;

    public Transaction(String name, int time, int amount, String city) {
      this.name = name;
      this.time = time;
      this.amount = amount;
      this.city = city;
    }
  }

}
