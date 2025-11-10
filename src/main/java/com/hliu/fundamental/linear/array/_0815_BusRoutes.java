package com.hliu.fundamental.linear.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class _0815_BusRoutes {

  public int numBusesToDestination(int[][] routes, int source, int target) {

    Map<Integer, Set<Integer>> routesMap = new HashMap<Integer, Set<Integer>>();

    for (int[] route : routes) {
      for (int i = 0; i < route.length; i++) {
        int stop = route[i];
        routesMap.computeIfAbsent(stop, x -> new HashSet<>())
                 .add(route[((i + 1) % route.length)]);
      }
    }
    if (!routesMap.containsKey(source) || !routesMap.containsKey(target)) {
      return -1;
    }
    int count = 0;
    int cur = source;

    return 0;
  }


}
