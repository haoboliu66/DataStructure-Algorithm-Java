package com.hliu.fundamental.graph.mst;

import java.util.*;

public class ConnectingCitiesWithMinimumCost {

  public int minimumCost0(int n, int[][] connections) {

    // map a -> (b, c)  a->b  c(cost)
    Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
    Set<Integer> nodeSet = new HashSet<>();
    for (int[] conn : connections) {
      int from = conn[0];
      int to = conn[1];
      int cost = conn[2];
      if (!graph.containsKey(from)) {
        graph.put(from, new HashMap<>());
      }
      graph.get(from)
           .put(to, cost);
    }
    PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[2] - b[2]);
    Set<int[]> result = new HashSet<>();
    // start from a random node
    for (Integer cur : graph.keySet()) {

      if (!nodeSet.contains(cur)) {
        nodeSet.add(cur);
        Map<Integer, Integer> curAdjs = graph.get(cur);
        // 解锁这个node的相邻边, 放入堆
        for (Integer to : curAdjs.keySet()) {
          minHeap.add(new int[]{cur, to, curAdjs.get(to)});
        }
        while (!minHeap.isEmpty()) {
          int[] curEdge = minHeap.poll();
          int to = curEdge[1];
          if (!nodeSet.contains(to)) {
            nodeSet.add(to);
            result.add(curEdge);
            Map<Integer, Integer> nexts = graph.get(to);
            for (Integer next : nexts.keySet()) {
              minHeap.add(new int[]{cur, to, curAdjs.get(to)});
            }
          }
        }
      }
    }
      if (nodeSet.size() != n) {
          return -1;
      }
    int sum = 0;
    for (int[] edge : result) {
      sum += edge[2];
    }

    return sum;
  }


  public int minimumCost(int n, int[][] connections) {

    List<Edge> conns = new ArrayList<>();
    PriorityQueue<Edge> minHeap = new PriorityQueue<>((c1, c2) -> c1.cost - c2.cost);
    Map<Integer, Set<Edge>> graph = new HashMap<>();
    Set<Edge> result = new HashSet<>();
    Set<Integer> visitedNode = new HashSet<>();
    Set<Edge> visitedEdge = new HashSet<>();

    for (int[] c : connections) {
      int from = c[0];
      int to = c[1];
      int cost = c[2];
      conns.add(new Edge(from, to, cost));
    }

    for (Edge c : conns) {
      int from = c.from;
      int to = c.to;
      int cost = c.cost;
      graph.computeIfAbsent(from, x -> new HashSet<>())
           .add(c);
      graph.computeIfAbsent(to, x -> new HashSet<>())
           .add(new Edge(to, from, cost));
    }

    for (Integer node : graph.keySet()) {
      if (!visitedNode.contains(node)) {
        visitedNode.add(node);

        Set<Edge> edges = graph.get(node);
        for (Edge e : edges) {
          minHeap.add(e);
        }

        while (!minHeap.isEmpty()) {
          Edge cur = minHeap.poll();
          int to = cur.to;
          if (!visitedNode.contains(to)) {
            visitedNode.add(to);
            result.add(cur);

            for (Edge nextEdge : graph.get(to)) {
              if (!visitedEdge.contains(nextEdge)) {
                visitedEdge.add(nextEdge);
                minHeap.add(nextEdge);
              }

            }
          }
        }
      }
    }
      if (result.size() != n - 1) {
          return -1;
      }

    int sum = 0;
    for (Edge e : result) {
      sum += e.cost;
    }
    return sum;
  }


  public int minimumCost1(int n, int[][] connections) {

    List<Edge> conns = new ArrayList<>();
    PriorityQueue<Edge> minHeap = new PriorityQueue<>((c1, c2) -> c1.cost - c2.cost);
    Map<Integer, Set<Edge>> graph = new HashMap<>();
    Set<Edge> result = new HashSet<>();
    Set<Integer> visitedNode = new HashSet<>();
    Set<Edge> visitedEdge = new HashSet<>();

    for (int[] c : connections) {
      int from = c[0];
      int to = c[1];
      int cost = c[2];
      conns.add(new Edge(from, to, cost));
    }

    for (Edge c : conns) {
      int from = c.from;
      int to = c.to;
      int cost = c.cost;
      graph.computeIfAbsent(from, x -> new HashSet<>())
           .add(c);
      graph.computeIfAbsent(to, x -> new HashSet<>())
           .add(new Edge(to, from, cost));
    }

    for (Integer node : graph.keySet()) {
      if (!visitedNode.contains(node)) {
        visitedNode.add(node);

        Set<Edge> edges = graph.get(node);
        for (Edge e : edges) {
          minHeap.add(e);
        }

        while (!minHeap.isEmpty()) {
          Edge cur = minHeap.poll();
          int to = cur.to;
          if (!visitedNode.contains(to)) { // 不含有的时候，就是新的点
            visitedNode.add(to);
            result.add(cur);

            for (Edge nextEdge : graph.get(to)) {
              minHeap.add(nextEdge);
            }
          }
        }
      }
    }
      if (result.size() != n - 1) {
          return -1;
      }

    int sum = 0;

    for (Edge e : result) {
      sum += e.cost;
    }

    return sum;
  }

  public static class Edge {

    int from;
    int to;
    int cost;

    public Edge(int f, int t, int c) {
      from = f;
      to = t;
      cost = c;
    }

    public String toString() {
      return from + " -> " + to + "(" + cost + ")";
    }
  }
}
