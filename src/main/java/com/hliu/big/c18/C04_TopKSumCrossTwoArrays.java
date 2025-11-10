package com.hliu.big.c18;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class C04_TopKSumCrossTwoArrays {


  private static class Node {

    int index1;
    int index2;
    int sum;

    public Node(int index1, int index2, int sum) {
      this.index1 = index1;
      this.index2 = index2;
      this.sum = sum;
    }
  }

  public static int[] topKSum(int[] arr1, int[] arr2, int K) {
    int[] res = new int[K];
    int i = 0;
    PriorityQueue<Node> maxHeap = new PriorityQueue<>((Node n1, Node n2) -> {
      return n2.sum - n1.sum;
    });
    int index1 = arr1.length - 1, index2 = arr2.length - 1;
    Node cur = new Node(index1, index2, arr1[index1] + arr2[index2]);
    maxHeap.offer(cur);
    Set<Long> visited = new HashSet<>();
    visited.add(index(index1, index2, arr1.length));
    while (!maxHeap.isEmpty()) {
      cur = maxHeap.poll();
      res[i++] = cur.sum;
      index1 = cur.index1;
      index2 = cur.index2;
      if (i == K) {
        break;
      }
      if (index1 > 0 && !visited.contains(index(index1 - 1, index2, arr1.length))) {
        maxHeap.offer(new Node(index1 - 1, index2, arr1[index1 - 1] + arr2[index2]));
        visited.add(index(index1 - 1, index2, arr1.length));
      }
      if (index2 > 0 && !visited.contains(index(index1, index2 - 1, arr1.length))) {
        maxHeap.offer(new Node(index1, index2 - 1, arr1[index1] + arr2[index2 - 1]));
        visited.add(index(index1, index2 - 1, arr1.length));
      }
    }
    return res;
  }

  private static long index(int index1, int index2, int N) {
    return (long) (index1 * N + index2);
  }


}
