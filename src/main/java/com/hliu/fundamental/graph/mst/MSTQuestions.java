package com.hliu.fundamental.graph.mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/*
  MST解决的是undirected weighted graph中连接节点的最小代价问题
  有N个node, MST得到的结果就是N-1条边

  Kruskal & Prim

 */

public class MSTQuestions {

  // https://www.luogu.com.cn/problem/P3366
  static class MstTemplate {

    private static int MAX_NODE = 5001;
    private static int MAX_EDGE = 200001;
    private static int[][] edge = new int[MAX_EDGE][3];

    private static int[] parent = new int[MAX_NODE];
    private static int nodeCount;

    private static void buildUnionFind(int nodeCount) {
      for (int i = 1; i < nodeCount; i++) {
        parent[i] = i;
      }
    }

    private static int findParent(int x) {
      int cur = x;
      while (cur != parent[cur]) {
        cur = parent[cur];
      }
      return cur;
    }

    private static boolean isSameSet(int x, int y) {
      return findParent(x) == findParent(y);
    }

    private static void union(int x, int y) {
      int parentX = findParent(x);
      int parentY = findParent(y);
      if (parentX != parentY) {
        parent[parentY] = parentX;
      }
    }

    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StreamTokenizer in = new StreamTokenizer(br);
      PrintWriter out = new PrintWriter(System.out);
      while (in.nextToken() != StreamTokenizer.TT_EOF) {
        nodeCount = (int) in.nval; // n
        in.nextToken();
        buildUnionFind(nodeCount); // node from 1....n
        int edgeCount = (int) in.nval; // m
        for (int i = 0; i < edgeCount; i++) { // process following edges
          edge[i] = new int[3];
          in.nextToken();
          int from = (int) in.nval;
          in.nextToken();
          int to = (int) in.nval;
          in.nextToken();
          int weight = (int) in.nval;
          edge[i][0] = from;
          edge[i][1] = to;
          edge[i][2] = weight;
        }
        int res = getMSTWeightSum();
        if (res == -1) {
          out.println("orz");
        } else {
          out.println(res);
        }

        break;
      }
      out.flush();
      out.close();
      br.close();
    }

    private static int getMSTWeightSum() {
      Arrays.sort(edge, (edge1, edge2) -> edge1[2] - edge2[2]);
      int weightSum = 0;
      int edgeCount = 0;
      for (int[] pair : edge) {
        int from = pair[0];
        int to = pair[1];
        int weight = pair[2];
        if (!isSameSet(from, to)) {
          union(from, to);
          weightSum += weight;
          edgeCount++;
        }
      }
      return edgeCount == nodeCount - 1 ? weightSum : -1;
    }
  }

  // https://leetcode.com/problems/min-cost-to-connect-all-points/
  public int minCostConnectPoints(int[][] points) {
    int[][][] edge = new int[2][2][1];
    int minCost = 0;

    return minCost;
  }

  // https://leetcode.com/problems/optimize-water-distribution-in-a-village/
  /*
  这道题需要先分析转换一下问题结构, 把每个房子单独打井的cost看作是从一个虚拟节点0到每个房子的边的cost,
  也就是说再加上额外从虚拟节点0到每个house的N条边; 所以整个图的结构就变成了: house节点[1...N] + 虚拟节点[0], 以及他们互相连接的edge
  在这个图上计算MST即可
   */
  public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
    // pipes[j] = [house1j, house2j, costj]
    List<int[]> edges = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      edges.add(new int[]{0, i + 1, wells[i]});
    }
    for (int[] pipe : pipes) {
      int from = pipe[0];
      int to = pipe[1];
      int cost = pipe[2];
      edges.add(new int[]{from, to, cost});
    }
    edges.sort((edge1, edge2) -> edge1[2] - edge2[2]);
    UnionFind unionFind = new UnionFind(n);
    int minCost = 0;
    for (int[] edge : edges) {
      int from = edge[0];
      int to = edge[1];
      int cost = edge[2];
      if (!unionFind.isSameSet(from, to)) {
        unionFind.union(from, to);
        minCost += cost;
      }
    }
    return minCost;
  }

  // https://leetcode.com/problems/checking-existence-of-edge-length-limited-paths/
  public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
    boolean[] result = new boolean[queries.length];

    int[][] questions = new int[queries.length][4];
    for (int i = 0; i < queries.length; i++) {
      questions[i][0] = queries[i][0];
      questions[i][1] = queries[i][1];
      questions[i][2] = queries[i][2];
      questions[i][3] = i;
    }
    Arrays.sort(edgeList, (e1, e2) -> e1[2] - e2[2]);
    Arrays.sort(questions, (e1, e2) -> e1[2] - e2[2]);
    UnionFind unionFind = new UnionFind(n);

    int i = 0, j = 0;
    for (; i < questions.length; i++) {
      int[] curQuestion = questions[i];
      int limit = curQuestion[2];
      for (; j < edgeList.length && edgeList[j][2] < limit; j++) {
        int from = edgeList[j][0];
        int to = edgeList[j][1];
        unionFind.union(from, to);
      }
      int index = curQuestion[3];
      result[index] = unionFind.isSameSet(curQuestion[0], curQuestion[1]);
    }

    return result;
  }

  public boolean[] distanceLimitedPathsExist1(int n, int[][] edgeList, int[][] queries) {
    boolean[] result = new boolean[queries.length];

    int[][] questions = new int[queries.length][4];
    for (int i = 0; i < queries.length; i++) {
      questions[i][0] = queries[i][0];
      questions[i][1] = queries[i][1];
      questions[i][2] = queries[i][2];
      questions[i][3] = i;
    }
    Arrays.sort(edgeList, (e1, e2) -> e1[2] - e2[2]);
    UnionFind unionFind = new UnionFind(n);

    int i = 0;
    for (; i < questions.length; i++) {
      int[] curQuestion = questions[i];
      int limit = curQuestion[2];
      for (int j = 0; j < edgeList.length && edgeList[j][2] < limit; j++) {
        int from = edgeList[j][0];
        int to = edgeList[j][1];
        unionFind.union(from, to);
      }
      int index = curQuestion[3];
      result[index] = unionFind.isSameSet(curQuestion[0], curQuestion[1]);
    }

    return result;
  }

  // https://www.luogu.com.cn/problem/P2330
  /*
  最小瓶颈树（Minimum Bottleneck Spanning Tree，简称 MBST） 是指在所有MST中，最大边权值最小的生成树。

  最小生成树一定是最小瓶颈树 (证明略), 所以用K算法求最小生成树即可
   */
  static class MinimumBottleneckSpanningTree {

    private static int MAX_NODE = 5001;
    private static int MAX_EDGE = 200001;
    private static int[][] edge = new int[MAX_EDGE][3];

    private static int[] parent = new int[MAX_NODE];
    private static int nodeCount;

    private static void buildUnionFind(int nodeCount) {
      for (int i = 1; i < nodeCount; i++) {
        parent[i] = i;
      }
    }

    private static int findParent(int x) {
      int cur = x;
      while (cur != parent[cur]) {
        cur = parent[cur];
      }
      return cur;
    }

    private static boolean isSameSet(int x, int y) {
      return findParent(x) == findParent(y);
    }

    private static void union(int x, int y) {
      int parentX = findParent(x);
      int parentY = findParent(y);
      if (parentX != parentY) {
        parent[parentY] = parentX;
      }
    }

    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StreamTokenizer in = new StreamTokenizer(br);
      PrintWriter out = new PrintWriter(System.out);
      while (in.nextToken() != StreamTokenizer.TT_EOF) {
        nodeCount = (int) in.nval; // n
        in.nextToken();
        buildUnionFind(nodeCount); // node from 1....n
        int edgeCount = (int) in.nval; // m
        for (int i = 0; i < edgeCount; i++) { // process following edges
          edge[i] = new int[3];
          in.nextToken();
          int from = (int) in.nval;
          in.nextToken();
          int to = (int) in.nval;
          in.nextToken();
          int weight = (int) in.nval;
          edge[i][0] = from;
          edge[i][1] = to;
          edge[i][2] = weight;
        }

        out.println(nodeCount - 1 + " " + getMaxWeight());
        break;
      }
      out.flush();
      out.close();
      br.close();
    }

    private static int getMaxWeight() {
      Arrays.sort(edge, (edge1, edge2) -> edge1[2] - edge2[2]);
      int maxWeight = 0;
      int edgeCount = 0;
      for (int[] pair : edge) {
        int from = pair[0];
        int to = pair[1];
        int weight = pair[2];
        if (!isSameSet(from, to)) {
          union(from, to);
          maxWeight = Math.max(maxWeight, weight);
          edgeCount++;
          if (edgeCount == nodeCount - 1) {
            break;
          }
        }
      }
      return maxWeight;
    }
  }

  // https://leetcode.com/problems/redundant-connection/
  public int[] findRedundantConnection(int[][] edges) {
    int maxNodeId = 1;
    for (int[] edge : edges) {
      maxNodeId = Math.max(maxNodeId, Math.max(edge[0], edge[1]));
    }
    UnionFind unionFind = new UnionFind(maxNodeId);
    for (int[] edge : edges) {
      int from = edge[0];
      int to = edge[1];
      if (unionFind.isSameSet(from, to)) {
        return edge;
      } else {
        unionFind.union(from, to);
      }
    }
    return new int[0];
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
      while (!stack.isEmpty()) {
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
