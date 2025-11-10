package com.hliu.random.leetcode.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class M_Problem_0743_NetworkDelayTime {

  public static void main(String[] args) {
    int[][] times = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};

    System.out.println(networkDelayTime(times, 4, 2));
  }

  public static int networkDelayTime(int[][] times, int n, int k) {
    // k => [1...n]
    if (k > n || k < 1) {
      return -1;
    }

    Map<String, Integer> ws = new HashMap<>();
    Map<Integer, List<Integer>> graph = new HashMap<>();
    for (int[] t : times) {
      int from = t[0];
      int to = t[1];
      int weight = t[2];
      ws.put(from + "->" + to, weight);

      graph.computeIfAbsent(from, x -> new ArrayList<>())
           .add(to);
    }
    int result = 0;
    Queue<Integer> queue = new LinkedList<>();
    queue.offer(k);
    while (!queue.isEmpty()) {
      Integer cur = queue.poll();
      List<Integer> nexts = graph.get(cur);
      if (nexts != null) {
        int curRound = 0;
        for (int next : nexts) {
          int weight = ws.getOrDefault(cur + "->" + next, 0);
          result += weight;
          if(graph.containsKey(next)){
            queue.offer(next);
          }

        }
      }
    }

    return result;
  }

}
