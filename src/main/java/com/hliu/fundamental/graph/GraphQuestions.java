package com.hliu.fundamental.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GraphQuestions {

  public List<String> invalidTransactions(String[] transactions) {

    List<String> res = new ArrayList<>();


    return res;
  }

  public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
    List<List<Integer>> res = new ArrayList<>();
    int nodeCount = graph.length;

    Map<Integer, List<Integer>> nodeGraph = new HashMap<>();
    for (int i = 0; i < nodeCount; i++) {
      List<Integer> neighbors = new ArrayList<>();
      for (int dest : graph[i]) {
        neighbors.add(dest);
      }
      nodeGraph.put(i, neighbors);
    }

    dfs(0, nodeCount, nodeGraph, new LinkedList<>(), res);
    return res;
  }

  private void dfs(int start, int nodeCount, Map<Integer, List<Integer>> graph, LinkedList<Integer> path,
      List<List<Integer>> res) {

    if (start == nodeCount - 1) {
      path.addLast(start);
      res.add(new ArrayList<>(path));
      path.removeLast();
      return;
    }
    path.addLast(start);
    List<Integer> nexts = graph.get(start);

    for (int dest : nexts) {
      path.add(dest);
      dfs(dest, nodeCount, graph, path, res);
      path.removeLast();
    }

    path.removeLast();
  }


}
