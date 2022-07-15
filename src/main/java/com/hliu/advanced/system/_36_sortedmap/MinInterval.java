package com.hliu.advanced.system._36_sortedmap;

import java.util.TreeMap;

public class MinInterval {

  /*
  Q: 二维数组中, 每个数组有序, 找出最小区间[a, b], 让每个数组中都至少有一个数在该区间内, 求起始值最小的区间范围
   */
  public static int[] getMinInterval(int[][] arr) {
    int[] res = new int[2];
    TreeMap<Integer, Node> treeMap = new TreeMap<>();
    for (int i = 0; i < arr.length; i++) {
      treeMap.put(arr[i][0], new Node(i, 0));
    }

    int min = treeMap.firstKey();
    int max = treeMap.lastKey();
    int interval = max - min;

    int row = treeMap.pollFirstEntry()
                     .getValue().row;
    int col = treeMap.pollFirstEntry()
                     .getValue().col;
    int newValue = arr[treeMap.pollFirstEntry()
                              .getValue().row][col + 1];

    return res;
  }

  public static class Node {

    int row;
    int col;

    public Node(int row, int col) {
      this.row = row;
      this.col = col;
    }
  }

  public static void main(String[] args) {
    TreeMap<Integer, Integer> map = new TreeMap<>();
    map.put(3, 4);
    map.put(2, 5);
    map.put(6, 3);
    map.put(1, 2);
    System.out.println(map.firstKey());
    System.out.println(map.lastKey());
  }

}
