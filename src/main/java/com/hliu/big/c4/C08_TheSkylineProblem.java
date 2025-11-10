package com.hliu.big.c4;

import java.util.*;

public class C08_TheSkylineProblem {

  private static class Node {

    int x;
    boolean isAdd;
    int height;

    public Node(int x, boolean isAdd, int height) {
      this.x = x;
      this.isAdd = isAdd;
      this.height = height;
    }
  }

  public static List<List<Integer>> getSkyline(int[][] buildings) {
    if (buildings == null || buildings.length == 0) {
      return Collections.emptyList();
    }
    int n = buildings.length, index = 0;
    Node[] nodes = new Node[n << 1];
    for (int[] building : buildings) {
      int x1 = building[0];
      int x2 = building[1];
      int h = building[2];
      nodes[index++] = new Node(x1, true, h);
      nodes[index++] = new Node(x2, false, h);
    }
    Arrays.sort(nodes, (n1, n2) -> n1.x - n2.x);

    TreeMap<Integer, Integer> heightMap = new TreeMap<>();
    TreeMap<Integer, Integer> xMap = new TreeMap<>();
    for (int i = 0; i < nodes.length; i++) {
      Node cur = nodes[i];
      if (cur.isAdd) {
        if (!heightMap.containsKey(cur.height)) {
          heightMap.put(cur.height, 1);
        } else {
          heightMap.put(cur.height, heightMap.get(cur.height) + 1);
        }

      } else {
        if (heightMap.get(cur.height) == 1) {
          heightMap.remove(cur.height);
        } else {
          heightMap.put(cur.height, heightMap.get(cur.height) - 1);
        }
      }
      if (heightMap.isEmpty()) {
        xMap.put(cur.x, 0);
      } else {
        xMap.put(cur.x, heightMap.lastKey());
      }
    }
    List<List<Integer>> ans = new ArrayList<>();
    for (Map.Entry<Integer, Integer> entry : xMap.entrySet()) {
      Integer x = entry.getKey();
      Integer h = entry.getValue();
      ans.add(new ArrayList<>(Arrays.asList(x, h)));
    }

    return ans;
  }

  public static void main(String[] args) {
    int[][] buildings = {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
    getSkyline(buildings);
  }


}
