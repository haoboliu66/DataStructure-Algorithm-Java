package com.hliu.random.algoexpert.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/*
You're given a list of arbitrary jobs that need
to be completed; these jobs are represented
by distinct integers. You're also given a list of
dependencies. A dependency is represented
as a pair of jobs where the first job is a
prerequisite of the second one. In other
words, the second job depends on the first
one; it can only be completed once the first
job is completed.
Write a function that takes in a list of jobs and
a list of dependencies and returns a list
containing a valid order in which the given
jobs can be completed. If no such order exists,
the function should return an empty array.

[1,2,3,4]
[[1,2], [1,3], [3,2], [4,2], [4,3]]

[1,4,3,2] or [4,1,3,2]
 */
public class P20_TopologicalSort {

  public static void main(String[] args) {

    List<Integer> jobs = List.of(1, 2, 3, 4, 5);
    List<Integer[]> deps = List.of(new Integer[]{1, 4}, new Integer[]{5, 2});

    List<Integer> res = topologicalSort(jobs, deps);
    System.out.println(res);
  }

  public static List<Integer> topologicalSort(List<Integer> jobs, List<Integer[]> deps) {
    if (deps.isEmpty()) {
      return jobs;
    }
    List<Integer> res = new ArrayList<>();
    Queue<Integer> q = new LinkedList<>();
    Map<Integer, Integer> inMap = new HashMap<>();
    Map<Integer, List<Integer>> map = new HashMap<>();
    for (int i = 0; i < deps.size(); i++) {
      Integer[] curPair = deps.get(i);
      int from = curPair[0];
      int to = curPair[1];
      map.computeIfAbsent(from, x -> new ArrayList<>())
         .add(to);
      inMap.put(to, inMap.getOrDefault(to, 0) + 1);
    }
    for (int node : jobs) {
      if (!inMap.containsKey(node)) {
        q.offer(node);
      }
    }

    while (!q.isEmpty()) {
      Integer cur = q.poll();
      res.add(cur);

      List<Integer> nexts = map.get(cur);
      map.remove(cur);

      if (nexts != null) {
        for (int nextNode : nexts) {
          Integer nextIn = inMap.get(nextNode);
          nextIn--;
          if (nextIn == 0) {
            q.offer(nextNode);

          } else {
            inMap.put(nextNode, nextIn);
          }
        }
      }
    }
    return res.size() != jobs.size() ? new ArrayList<>() : res;
  }

}
