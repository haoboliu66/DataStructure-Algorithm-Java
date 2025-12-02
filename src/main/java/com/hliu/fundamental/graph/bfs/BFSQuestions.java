package com.hliu.fundamental.graph.bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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

  // 从s中消除掉所有sticker的字符, 返回剩余的字符串
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

  // 把字符串的int[]表示转换为一个string, 用于去重
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

  // 从一个字符串int[]表示中消除掉sticker的所有字符, 返回剩余的字符串int[]表示
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


   1，distance[i]表示从源点到i点的最短距离，初始时所有点的distance设置为无穷大
  2，源点进入双端队列，distance[源点]=0
  3，双端队列 头部弹出 x，
     A，如果x是目标点，返回distance[x]表示源点到目标点的最短距离
     B，考察从x出发的每一条边，假设某边去y点，边权为w
        1）如果 distance[y] > distance[x] + w，处理该边；否则忽略该边
        2）处理时，更新distance[y] = distance[x] + w
           如果w==0，y从头部进入双端队列；如果w==1，y从尾部进入双端队列
        3）考察完x出发的所有边之后，重复步骤3
  4，双端队列为空停止
  正确性证明 以及 为什么不需要visited来标记节点


   */

  /*
   01bfs的模板题

   https://leetcode.com/problems/minimum-obstacle-removal-to-reach-corner
   */
  public int minimumObstacles(int[][] grid) {
    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int m = grid.length, n = grid[0].length;
    int[][] distance = new int[m][n];
    for (int i = 0; i < m; i++) {
      Arrays.fill(distance[i], Integer.MAX_VALUE);
    }
    Deque<int[]> deque = new ArrayDeque<>();
    deque.addLast(new int[]{0, 0});
    distance[0][0] = 0;
    while (!deque.isEmpty()) {
      int[] curPoint = deque.removeFirst();
      int x = curPoint[0];
      int y = curPoint[1];
      if (x == m - 1 && y == n - 1) {
        return distance[x][y];
      }
      for (int i = 0; i < directions.length; i++) {
        int nextX = x + directions[i][0];
        int nextY = y + directions[i][1];

        if (nextX >= 0 && nextY >= 0 && nextX < m && nextY < n) {
          int weight = grid[nextX][nextY];
        /*
        下一个位置如果是0, 表示从当前{x,y}过去不需要移除障碍, 权重为0
        下一个位置如果是1, 表示从当前{x,y}过去需要移除障碍, 权重为1
         */

          if (distance[x][y] + weight < distance[nextX][nextY]) { // 只有能让下一个点distance变更小的边才处理
            distance[nextX][nextY] = distance[x][y] + weight;

            if (weight == 0) {
              deque.addFirst(new int[]{nextX, nextY});
            } else {
              deque.addLast(new int[]{nextX, nextY});
            }
          }
        }
      }
    }
    return -1;
  }

  /*
   01bfs
   https://leetcode.com/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid/
   */
  public int minCost(int[][] grid) {
    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int m = grid.length, n = grid[0].length;
    int[][] distance = new int[m][n];
    for (int i = 0; i < m; i++) {
      Arrays.fill(distance[i], Integer.MAX_VALUE);
    }
    Deque<int[]> deque = new ArrayDeque<>();
    deque.addLast(new int[]{0, 0});
    distance[0][0] = 0;
    while (!deque.isEmpty()) {
      int[] curPoint = deque.removeFirst();
      int x = curPoint[0];
      int y = curPoint[1];
      if (x == m - 1 && y == n - 1) {
        return distance[x][y];
      }

      for (int i = 0; i < directions.length; i++) {
        int nextX = x + directions[i][0];
        int nextY = y + directions[i][1];

        // grid[x][y]: 1-> right, 2-> left, 3-> down, 4-> up

        if (nextX >= 0 && nextY >= 0 && nextX < m && nextY < n) {
          int weight = 1;
          if (nextY == y + 1 && grid[x][y] == 1) {
            weight = 0;
          } else if (nextY == y - 1 && grid[x][y] == 2) {
            weight = 0;
          } else if (nextX == x + 1 && grid[x][y] == 3) {
            weight = 0;
          } else if (nextX == x - 1 && grid[x][y] == 4) {
            weight = 0;
          }

          if (distance[nextX][nextY] > distance[x][y] + weight) {
            distance[nextX][nextY] = distance[x][y] + weight;

            if (weight == 0) {
              deque.addFirst(new int[]{nextX, nextY});
            } else {
              deque.addLast(new int[]{nextX, nextY});
            }
          }
        }
      }
    }
    return -1;
  }

  // https://leetcode.com/problems/trapping-rain-water-ii/
  /*
  bfs + minHeap
   */
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

  public static void main(String[] args) {
    BFSQuestions questions = new BFSQuestions();
    String beginWord = "qa";
    String endWord = "sq";
    List<String> wordList = Arrays.asList("si", "go", "se", "cm", "so", "ph", "mt", "db", "mb", "sb", "kr", "ln",
        "tm",
        "le", "av", "sm", "ar", "ci", "ca", "br", "ti", "ba", "to", "ra", "fa", "yo", "ow", "sn", "ya", "cr", "po",
        "fe", "ho", "ma", "re", "or", "rn", "au", "ur", "rh", "sr", "tc", "lt", "lo", "as", "fr", "nb", "yb", "if",
        "pb", "ge", "th", "pm", "rb", "sh", "co", "ga", "li", "ha", "hz", "no", "bi", "di", "hi", "qa", "pi", "os",
        "uh", "wm", "an", "me", "mo", "na", "la", "st", "er", "sc", "ne", "mn", "mi", "am", "ex", "pt", "io", "be",
        "fm", "ta", "tb", "ni", "mr", "pa", "he", "lr", "sq", "ye");

    System.out.println(questions.findLadders(beginWord, endWord, wordList)
                                .size());

    Code06_WordLadderII q = new Code06_WordLadderII();
    List<List<String>> res = q.findLadders(beginWord, endWord, wordList);
    System.out.println(res.size());
  }

  // from Zuo
  public static class Code06_WordLadderII {

    // 单词表 ： list -> hashSet
    public static HashSet<String> dict;

    public static HashSet<String> curLevel = new HashSet<>();

    public static HashSet<String> nextLevel = new HashSet<>();

    // 反向图
    public static HashMap<String, ArrayList<String>> graph = new HashMap<>();

    // 记录路径，当生成一条有效路的时候，拷贝进ans！
    public static LinkedList<String> path = new LinkedList<>();

    public static List<List<String>> ans = new ArrayList<>();

    public static void build(List<String> wordList) {
      dict = new HashSet<>(wordList);
      graph.clear();
      ans.clear();
      curLevel.clear();
      nextLevel.clear();
    }

    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
      build(wordList);
      if (!dict.contains(endWord)) {
        return ans;
      }
      if (bfs(beginWord, endWord)) {
        System.out.println("============== zuo graph =============");
        System.out.println(graph);
        dfs(endWord, beginWord);
      }
      return ans;
    }

    // begin -> end ，一层层bfs去，建图
    // 返回值：真的能找到end，返回true；false
    public static boolean bfs(String begin, String end) {
      boolean find = false;
      curLevel.add(begin);
      while (!curLevel.isEmpty()) {
        dict.removeAll(curLevel);
        for (String word : curLevel) {
          // word : 去扩
          // 每个位置，字符a~z，换一遍！检查在词表中是否存在
          // 避免，加工出自己
          char[] w = word.toCharArray();
          for (int i = 0; i < w.length; i++) {
            char old = w[i];
            for (char ch = 'a'; ch <= 'z'; ch++) {
              w[i] = ch;
              String str = String.valueOf(w);
              if (dict.contains(str) && !str.equals(word)) {
                if (str.equals(end)) {
                  find = true;
                }
                graph.putIfAbsent(str, new ArrayList<>());
                graph.get(str)
                     .add(word);
                nextLevel.add(str);
              }
            }
            w[i] = old;
          }
        }
        if (find) {
          return true;
        } else {
          HashSet<String> tmp = curLevel;
          curLevel = nextLevel;
          nextLevel = tmp;
          nextLevel.clear();
        }
      }
      return false;
    }

    public static void dfs(String word, String aim) {
      path.addFirst(word);
      if (word.equals(aim)) {
        ans.add(new ArrayList<>(path));
      } else if (graph.containsKey(word)) {
        for (String next : graph.get(word)) {
          dfs(next, aim);
        }
      }
      path.removeFirst();
    }
  }

  // TODO: https://leetcode.com/problems/word-ladder-ii/
  public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
    List<List<String>> result = new LinkedList<>();
    Set<String> wordSet = new HashSet<>(wordList);
    if (!wordSet.contains(endWord)) {
      return result;
    }
    Map<String, List<String>> graph = new HashMap<>();

    Set<String> curLevel = new HashSet<>();
    Set<String> nextLevel = new HashSet<>();
    curLevel.add(beginWord);

    boolean found = false;
    while (!curLevel.isEmpty()) {
      wordSet.remove(curLevel);
      for (String curWord : curLevel) {
        char[] curWordStr = curWord.toCharArray();
        for (int p = 0; p < curWordStr.length; p++) {
          char oldChar = curWordStr[p];
          for (char ch = 'a'; ch <= 'z'; ch++) {
            curWordStr[p] = ch;
            String nextWord = new String(curWordStr);
            if (nextWord.equals(curWord) || !wordSet.contains(nextWord)) { // skip words not in wordSet
              continue;
            }
            if (endWord.equals(nextWord)) {
              found = true;
            }
            // nextWord一定属于wordSet
            graph.putIfAbsent(nextWord, new ArrayList<>());
            graph.get(nextWord)
                 .add(curWord);
            nextLevel.add(nextWord);
          }
          curWordStr[p] = oldChar;
        }
      }
      if (!found) {
        Set<String> tmp = curLevel;
        curLevel = nextLevel;
        nextLevel = tmp;
        nextLevel.clear();
      } else {
        break;
      }
    }

    if (found) {
      System.out.println(graph);
      System.out.println("????? doing dfs");
//      dfs(graph, endWord, beginWord, new LinkedList<>(), result);
    }
    return result;
  }

  private void dfs(Map<String, List<String>> graph, String cur, String dest,
      LinkedList<String> path, List<List<String>> result) {
    path.addLast(cur);
    if (cur.equals(dest)) {
      result.add(new ArrayList<>(path));
    } else {
      if (graph.containsKey(cur)) {
        for (String nextWord : graph.get(cur)) {
          dfs(graph, nextWord, dest, path, result);
        }
      }
    }
    path.removeLast();
  }

  /*
  通过BFS找到从beginWord到endWord的最短路径长度
   */
  public int ladderLength0(String beginWord, String endWord, List<String> wordList) {
    Set<String> wordSet = new HashSet<>(wordList);
    if (!wordSet.contains(endWord)) {
      return 0;
    }
    Queue<String> queue = new LinkedList<>();
    queue.offer(beginWord);
    Set<String> visited = new HashSet<>();
    visited.add(beginWord);
    int level = 1;
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        String curWord = queue.poll();
        char[] curWordStr = curWord.toCharArray();
        for (int p = 0; p < curWordStr.length; p++) {
          char oldChar = curWordStr[p];
          for (char ch = 'a'; ch <= 'z'; ch++) {
            if (ch != oldChar) {
              curWordStr[p] = ch;
              String nextWord = new String(curWordStr);
              if (!wordSet.contains(nextWord)) { // skip words not in wordSet
                continue;
              }
              if (endWord.equals(nextWord)) {
                return level + 1;
              }
              // nextWord一定属于wordSet
              if (!visited.contains(nextWord)) {
                visited.add(nextWord);
                queue.offer(nextWord);
              }
            }
          }
          curWordStr[p] = oldChar;
        }
      }
      level++;
    }
    return 0;
  }

  public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    Set<String> wordSet = new HashSet<>(wordList);
    if (!wordSet.contains(endWord)) {
      return 0;
    }
    Map<String, Integer> distance = new HashMap<>();
    for (String word : wordSet) {
      distance.put(word, Integer.MAX_VALUE);
    }
    Queue<String> queue = new LinkedList<>();
    queue.offer(beginWord);
    distance.put(beginWord, 0);
    Set<String> curLevel = new HashSet<>();
    curLevel.add(beginWord);
    int level = 1;
    while (!queue.isEmpty()) {
      int size = queue.size();
      wordSet.removeAll(queue);
      for (int i = 0; i < size; i++) {
        String curWord = queue.poll();
        char[] curWordStr = curWord.toCharArray();
        for (int p = 0; p < curWordStr.length; p++) {
          char oldChar = curWordStr[p];
          for (char ch = 'a'; ch <= 'z'; ch++) {
            curWordStr[p] = ch;
            String nextWord = new String(curWordStr);
            if (nextWord.equals(curWord) || !wordSet.contains(nextWord)) { // skip words not in wordSet
              continue;
            }
            if (endWord.equals(nextWord)) {
              return level + 1;
            }
            // nextWord一定属于wordSet
            if (level + 1 < distance.get(nextWord)) { // nextWord此前记录的距离比level + 1大, 说明此前的结果的不够好
              distance.put(nextWord, level + 1);
              queue.offer(nextWord);
            } else if (level + 1 == distance.get(nextWord)) { // nextWord的距离已经记录为了level + 1, 跳过
            }
          }
          curWordStr[p] = oldChar;
        }
      }
      level++;
    }
    return 0;
  }

  // https://leetcode.com/problems/rotting-oranges/
  public int orangesRotting(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    Queue<int[]> queue = new LinkedList<>();
    boolean[][] visited = new boolean[grid.length][grid[0].length];
    int freshCount = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == 1) {
          freshCount++;
        }
        if (grid[i][j] == 2) {
          queue.offer(new int[]{i, j});
          visited[i][j] = true;
        }
      }
    }
    if (freshCount == 0) {
      return 0;
    }
    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int level = 0;
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        int[] curPoint = queue.poll();
        int x = curPoint[0];
        int y = curPoint[1];
        for (int j = 0; j < directions.length; j++) {
          int nextX = x + directions[j][0];
          int nextY = y + directions[j][1];

          if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && !visited[nextX][nextY]) {
            if (grid[nextX][nextY] == 1) {
              visited[nextX][nextY] = true;
              freshCount--;
              queue.offer(new int[]{nextX, nextY});
            }
          }
        }
      }
      level++;
    }
    return freshCount == 0 ? level - 1 : -1;
  }
}
