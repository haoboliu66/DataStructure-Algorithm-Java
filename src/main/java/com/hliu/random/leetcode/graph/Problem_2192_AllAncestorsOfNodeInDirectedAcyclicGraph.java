package com.hliu.random.leetcode.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;
import java.util.TreeSet;

public class Problem_2192_AllAncestorsOfNodeInDirectedAcyclicGraph {

  public static void main(String[] args) {
    int n = 8;
    int[][] edges = {{0, 3}, {0, 4}, {1, 3}, {2, 4}, {2, 7}, {3, 5}, {3, 6}, {3, 7}, {4, 6}};
    System.out.println(getAncestors1(n, edges));

  }

  public static List<List<Integer>> getAncestors1(int n, int[][] edges) {
    List<List<Integer>> res = new ArrayList<>();

    Map<Integer, List<Integer>> graph = new HashMap<>();
    for (int i = 0; i < n; i++) {
      res.add(new ArrayList<>());
      graph.put(i, new ArrayList<>());
    }
    for (int[] edge : edges) {
      int ancestor = edge[0];
      int child = edge[1];
      graph.get(child)
           .add(ancestor);
    }
    Stack<Integer> stack = new Stack<>();
    for (Entry<Integer, List<Integer>> entry : graph.entrySet()) {


    }
    return res;
  }

  private static void collectAncestors1(int cur, int node, Map<Integer, List<Integer>> graph,
      List<List<Integer>> result) {
    List<Integer> fathers = graph.get(cur);
    if (fathers.isEmpty()) {
      result.get(node)
            .add(cur);
      return;
    }
    for (int father : fathers) {
      collectAncestors1(father, node, graph, result);
    }
  }


  public static List<List<Integer>> getAncestors(int n, int[][] edges) {
    List<List<Integer>> res = new ArrayList<>();
    Map<Integer, List<Integer>> graph = new HashMap<>();
    for (int i = 0; i < n; i++) {
      res.add(new ArrayList<>());
      graph.put(i, new ArrayList<>());
    }
    for (int[] edge : edges) {
      int ancestor = edge[0];
      int child = edge[1];
      graph.get(child)
           .add(ancestor);
    }
    System.out.println(graph);
    for (Entry<Integer, List<Integer>> entry : graph.entrySet()) {
      TreeSet<Integer> current = new TreeSet<>();
      int child = entry.getKey();
      collectAncestors(child, graph, current);
      res.set(child, new ArrayList<>(current));
    }
    return res;
  }

  private static void collectAncestors(int child, Map<Integer, List<Integer>> graph,
      TreeSet<Integer> result) {
    List<Integer> ancestors = graph.get(child);
    if (ancestors.isEmpty()) {
      return;
    }
    result.addAll(ancestors);
    for (int node : ancestors) {
      collectAncestors(node, graph, result);
    }
  }
}
