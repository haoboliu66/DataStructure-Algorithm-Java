package com.hliu.fundamental.graph.mst;

import java.util.Arrays;
import java.util.Stack;

public class MSTQuestions {


  /*
  MST解决的是undirected weighted graph中连接节点的最小代价问题
   */


  public int[] findRedundantConnection(int[][] edges) {
    int max = 1;
    for(int[] edge : edges) {
      max = Math.max(max, Math.max(edge[0], edge[1]));
    }
    UnionFind unionFind = new UnionFind(max);
    for(int[] edge : edges) {
      int from = edge[0];
      int to = edge[1];
      if (unionFind.isSameSet(from, to)) {
        return edge;
      } else {
        unionFind.union(from, to);
      }
    }
    return null;
  }

  public static void main(String[] args) {
    MSTQuestions q = new MSTQuestions();
    UnionFind unionFind = q.new UnionFind(4);
    System.out.println(unionFind.isSameSet(1, 2));
    unionFind.union(1, 2);
    System.out.println(unionFind.isSameSet(1, 2));
    System.out.println(unionFind.isSameSet(1, 3));
    System.out.println(unionFind.isSameSet(1, 4));
    unionFind.union(2, 3);
    System.out.println(unionFind.isSameSet(1, 3));
    unionFind.union(4, 3);
    System.out.println(unionFind.isSameSet(1, 4));
  }

  private class UnionFind {

    private int[] parent;
    private int[] size;

    public UnionFind(int nodeCount) {
      this.parent = new int[nodeCount + 1];
      this.size = new int[nodeCount + 1];
      Arrays.fill(size, 1);
      for (int i = 1; i <= nodeCount; i++) {
        parent[i] = i;
        size[i] = 1;
      }
    }

    public boolean isSameSet(int a, int b) {
      return findParent(a) == findParent(b);
    }

    private int findParent(int x) {
      int cur = x;
      Stack<Integer> stack = new Stack<>();
      while (cur != parent[cur]) {
        stack.push(cur);
        cur = parent[cur];
      }
      while(!stack.isEmpty()) {
        parent[stack.pop()] = cur;
      }
      return cur;
    }

    private void union(int a, int b) {
      int parentA = findParent(a);
      int parentB = findParent(b);
      if (parentA == parentB) {
        return;
      }
      int sizeA = size[parentA];
      int sizeB = size[parentB];
      int totalSize = sizeA + sizeB;
      if (sizeA >= sizeB) {
        parent[parentB] = parentA;
        size[parentA] = totalSize;
      } else {
        parent[parentA] = parentB;
        size[parentB] = totalSize;
      }
    }
  }

}
