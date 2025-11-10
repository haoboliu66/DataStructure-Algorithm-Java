package com.hliu.random.leetcode.bfs_dfs;

import java.util.*;

public class M_Problem_0752_OpenTheLock {

  /* need to optimize  */

  public static void main(String[] args) {
    String[] deadends = {"8887", "8889", "8878", "8898", "8788", "8988", "7888", "9888"};
    String target = "8888";
    int res = openLock(deadends, target);
    System.out.println(res);
  }

  static String start = "0000";

  public static int openLock(String[] deadends, String target) {
      if (start.equals(target)) {
          return 0;
      }
    HashSet<String> set = new HashSet<>();
    for (String s : deadends) {
        if (s.equals(target) || start.equals(s)) {
            return -1;
        }
      set.add(s);
    }
    Set<String> visited = new HashSet<>();
    Queue<String> q = new LinkedList<>();
    q.add(start);
    int level = 0;
    while (!q.isEmpty()) {
      int size = q.size();
      for (int i = 0; i < size; i++) {
        String cur = q.poll();
        List<String> neighbours = getNeighbours(cur, set);
          if (neighbours.size() == 0) {
              return -1;
          }
        for (String adj : neighbours) {
          if (!visited.contains(adj)) {
            visited.add(adj);
            q.offer(adj);
          }
          if (adj.equals(target)) {
            return level + 1;
          }
        }
      }
      level++;
    }

    return -1;
  }

  public static List<String> getNeighbours(String cur, HashSet<String> deadends) {
    char[] str = cur.toCharArray();
    List<String> neighbours = new LinkedList<>();
    for (int i = 0; i < str.length; i++) {
      char tmp = str[i];
      str[i] = addOne(tmp);
      String t = new String(str);
      if (!deadends.contains(t)) {
        neighbours.add(t);
      }

      str[i] = minusOne(tmp);
      t = new String(str);
      if (!deadends.contains(t)) {
        neighbours.add(t);
      }
      str[i] = tmp;
    }

    return neighbours;
  }

  public static char addOne(char c) {
    return c == '9' ? '0' : (char) (c + 1);
  }

  public static char minusOne(char c) {
    return c == '0' ? '9' : (char) (c - 1);
  }

}
