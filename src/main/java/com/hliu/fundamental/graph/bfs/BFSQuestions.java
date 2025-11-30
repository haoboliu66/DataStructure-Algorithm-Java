package com.hliu.fundamental.graph.bfs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class BFSQuestions {

  // https://leetcode.com/problems/as-far-from-land-as-possible/
  public int maxDistance(int[][] grid) {
    int maxDistance = 0;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == 0) {
          int distance = bfs(grid, i, j);
          maxDistance = Math.max(maxDistance, distance);
        }
      }
    }
    return maxDistance;
  }

  public int maxDistance1(int[][] grid) {
    int waterCount = 0;
    Queue<int[]> queue = new LinkedList<>();
    boolean[][] visited = new boolean[grid.length][grid[0].length];
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == 1) {
          queue.offer(new int[]{i, j});
          visited[i][j] = true;
        } else {
          waterCount++;
        }
      }
    }
    if (waterCount == 0 || waterCount == grid.length * grid[0].length) {
      return -1;
    }
    int level = 0;
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        int[] curPoint = queue.poll();
        int x = curPoint[0];
        int y = curPoint[1];
        if (x > 0 && !visited[x - 1][y]) {
          queue.offer(new int[]{x - 1, y});
          visited[x - 1][y] = true;
        }
        if (x < grid.length - 1 && !visited[x + 1][y]) {
          queue.offer(new int[]{x + 1, y});
          visited[x + 1][y] = true;
        }
        if (y > 0 && !visited[x][y - 1]) {
          queue.offer(new int[]{x, y - 1});
          visited[x][y - 1] = true;
        }
        if (y < grid[0].length - 1 && !visited[x][y + 1]) {
          queue.offer(new int[]{x, y + 1});
          visited[x][y + 1] = true;
        }
      }
      level++;
    }
    return level - 1;
  }

  private int bfs(int[][] grid, int m, int n) {
    int level = 0;
    Queue<int[]> queue = new LinkedList<>();
    boolean[][] visited = new boolean[grid.length][grid[0].length];
    queue.offer(new int[]{m, n});
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        int[] curPoint = queue.poll();
        int x = curPoint[0];
        int y = curPoint[1];
        if (grid[x][y] == 1) {
          return level;
        }
        if (x > 0 && !visited[x - 1][y]) {
          queue.offer(new int[]{x - 1, y});
          visited[x - 1][y] = true;
        }
        if (x < grid.length - 1 && !visited[x + 1][y]) {
          queue.offer(new int[]{x + 1, y});
          visited[x + 1][y] = true;
        }
        if (y > 0 && !visited[x][y - 1]) {
          queue.offer(new int[]{x, y - 1});
          visited[x][y - 1] = true;
        }
        if (y < grid[0].length - 1 && !visited[x][y + 1]) {
          queue.offer(new int[]{x, y + 1});
          visited[x][y + 1] = true;
        }
      }
      level++;
    }
    return -1;
  }

  // https://leetcode.com/problems/stickers-to-spell-word/
  /*
  针对一个string状态S, 我们可以用所有的sticker来尝试减掉S中的字符, 得到新的状态S1, S2, ...
    这就得到了BFS的逻辑:
    1. 每次从队列中取出当前的字符串状态, 然后遍历sticker, 尝试用当前字符串减掉每一个sticker, 得到一组新的字符串状态
    2. 如果这个新的字符串状态没有出现过, 就加入队列
    3. 循环这个过程
   */
  public int minStickers(String[] stickers, String target) {
    Set<String> seen = new HashSet<>();
    Queue<String> queue = new LinkedList<>();
    queue.offer(target);
    seen.add(target);
    int level = 1;
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        String s = queue.poll();
        for (String sticker : stickers) {  // 这里不一定需要遍历所有的sticker, 可以按照左神的方法引入一个graph, 只遍历有用的sticker
          String nextStr = minus(s, sticker);
          if ("".equals(nextStr)) {
            return level;
          }
          if (!seen.contains(nextStr)) {
            seen.add(nextStr);
            queue.offer(nextStr);
          }
        }
      }
      level++;
    }
    return -1;
  }

  private String minus(String s, String sticker) {
    int[] charCount = new int[128];
    for (char ch : sticker.toCharArray()) {
      charCount[ch]++;
    }
    StringBuilder sb = new StringBuilder();
    for (char ch : s.toCharArray()) {
      if (charCount[ch] > 0) {
        charCount[ch]--;
      } else {
        sb.append(ch);
      }
    }
    return sb.toString();
  }

  /*
   进一步的优化, 状态压缩： 传统的字符串状态 ($S$) 转换为一个表示剩余字母频率的编码或位掩码，可以大大加快查找和存储速度。
   使用int[26]数组来表示当前状态下每个字符串的状态，而不是使用字符串straight value。
   把int[26]字符状态转换为 a#1b#0c#2...z#0 这样的编码来表示当前字符状态，用于去重。
   */
  public int minStickers1(String[] stickers, String target) {
    Set<String> seen = new HashSet<>();
    Queue<int[]> queue = new LinkedList<>();
    int[] initial = new int[26];
    for (char ch : target.toCharArray()) {
      initial[ch - 'a']++;
    }
    queue.offer(initial);
    seen.add(buildKey(initial));
    int level = 1;
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        int[] s = queue.poll();
        for (String sticker : stickers) {
          int[] nextStr = superMinus(s, sticker);
          String key = buildKey(nextStr);
          if ("".equals(key)) {
            return level;
          }
          if (!seen.contains(key)) {
            seen.add(key);
            queue.offer(nextStr);
          }
        }
      }
      level++;
    }
    return -1;
  }

  private String buildKey(int[] s) {
    int totalChar = 0;
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < s.length; i++) {
      char ch = (char) (i + 'a');
      int count = s[i];
      totalChar += count;
      sb.append(ch)
        .append("#")
        .append(count);
    }
    return totalChar == 0 ? "" : sb.toString();
  }

  private int[] superMinus(int[] s, String sticker) {
    int[] copy = Arrays.copyOf(s, s.length);
    for (char ch : sticker.toCharArray()) {
      if (copy[ch - 'a'] != 0) {
        copy[ch - 'a']--;
      }
    }
    return copy;
  }

  /*
   01bfs: graph中边的权重只有0和1两种情况
   使用双端队列来解决, 如果经过某条边的权重是0, 那么新节点从头部入队列; 如果经过某条边的权重是1, 那么新节点从队尾进入。

   在标准 BFS 中，我们使用一个队列，节点总是从队尾入队，从队头出队。
   它的正确性依赖于：保证： 队列中的节点总是按照距离起点 $D$ 的非递减顺序排列。因为所有边的权重都是 1，所以第k层的所有节点都会在第k+1层的所有节点之前被访问到，从而保证找到最短路径。
   */

  /*
   01bfs的模板题

   https://leetcode.com/problems/minimum-obstacle-removal-to-reach-corner
   */
  public int minimumObstacles(int[][] grid) {

    return 0;
  }

  // https://leetcode.com/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid/


  // https://leetcode.com/problems/trapping-rain-water-ii/
  public int trapRainWater(int[][] heightMap) {
    int m = heightMap.length, n = heightMap[0].length;
    PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[2] - b[2]);
    boolean[][] visited = new boolean[m][n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (i == 0 || j == 0 || i == m - 1 || j == n - 1) {
          minHeap.offer(new int[]{i, j, heightMap[i][j]});
          visited[i][j] = true;
        }
      }
    }
    int totalWater = 0;
    while (!minHeap.isEmpty()) {
      int[] cell = minHeap.poll();
      int x = cell[0], y = cell[1], waterHeight = cell[2];
      if (x != 0 && y != 0 && x != m - 1 && y != n - 1) {
        totalWater += (waterHeight - heightMap[x][y]);
      }
      // process neighbors
      if (x > 0 && !visited[x - 1][y]) {
        visited[x - 1][y] = true;
        minHeap.offer(new int[]{x - 1, y, Math.max(waterHeight, heightMap[x - 1][y])});
      }
      if (y > 0 && !visited[x][y - 1]) {
        visited[x][y - 1] = true;
        minHeap.offer(new int[]{x, y - 1, Math.max(waterHeight, heightMap[x][y - 1])});
      }
      if (x < m - 1 && !visited[x + 1][y]) {
        visited[x + 1][y] = true;
        minHeap.offer(new int[]{x + 1, y, Math.max(waterHeight, heightMap[x + 1][y])});
      }
      if (y < n - 1 && !visited[x][y + 1]) {
        visited[x][y + 1] = true;
        minHeap.offer(new int[]{x, y + 1, Math.max(waterHeight, heightMap[x][y + 1])});
      }
    }
    return totalWater;
  }


}
