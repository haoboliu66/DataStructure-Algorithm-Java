package com.hliu.random.algoexpert.medium;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class P30_CycleInGraph {

  public static void main(String[] args) {

    int[][] edges = {
        {1, 2},
        {2},
        {}
    };
    System.out.println(new P30_CycleInGraph().cycleInGraph2(edges));

  }

  public boolean cycleInGraph(int[][] edges) {
    Set<Integer> set = new HashSet<>();
    boolean res = false;
    for (int i = 0; i < edges.length; i++) {
      if (edges[i].length != 0) {
        res = dfs(edges, i, set);
      }
    }
    return res;
  }

  public boolean dfs(int[][] graph, int i, Set<Integer> set) {
    if (graph[i].length == 0) {
      return false;
    }
    set.add(i);
    int[] nexts = graph[i];
    boolean res = false;
    for (int next : nexts) {

      if (set.contains(next)) {
        return true;
      }

      set.add(next);
      res |= dfs(graph, next, set);
      set.remove(next);

    }
    set.remove(i);
    return res;
  }

  public boolean cycleInGraph2(int[][] edges) {
    boolean[] visited = new boolean[edges.length];
    Stack<Integer> stack = new Stack<>();
//    boolean res = false;
    int s = -1;
    for (int i = 0; i < edges.length; i++) {
      if (edges[i].length != 0) {
        s = i;
        break;
      }
    }
    if (s == -1) {
      return false;
    }

    stack.push(s);
    while (!stack.isEmpty()) {
      int cur = stack.pop();
      visited[cur] = true; // 真正的走到了这个vertex

      int[] nexts = edges[cur];
      for (int next : nexts) {
        if (visited[next]) {
          return true;
        }
        stack.push(next);
      }
    }
    return false;
  }

}
