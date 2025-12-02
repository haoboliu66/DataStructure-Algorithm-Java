package com.hliu.fundamental.graph.topologysort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/*

  https://leetcode.com/problems/course-schedule/
  https://leetcode.com/problems/course-schedule-ii/
  https://www.luogu.com.cn/problem/U107394
  https://leetcode.cn/problems/alien-dictionary/
  https://leetcode.com/problems/stamping-the-sequence/
  https://www.luogu.com.cn/problem/P4017
  https://leetcode.com/problems/loud-and-rich/
  https://leetcode.com/problems/parallel-courses-iii/
  https://leetcode.com/problems/maximum-employees-to-be-invited-to-a-meeting/

 */
public class TopologySortQuestions {

  // https://leetcode.com/problems/course-schedule/
  public boolean canFinish(int numCourses, int[][] prerequisites) {
    if (prerequisites == null || prerequisites.length == 0) {
      return true;
    }
    List<List<Integer>> graph = new ArrayList<>();
    int[] inDegree = new int[numCourses];
    for (int i = 0; i < numCourses; i++) {
      graph.add(new ArrayList<>());
    }
    for (int[] dep : prerequisites) {
      graph.get(dep[1])
           .add(dep[0]);
      inDegree[dep[0]]++;
    }

    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < inDegree.length; i++) {
      if (inDegree[i] == 0) {
        queue.add(i);
      }
    }
    if (queue.size() == 0) {
      return false;
    }
    int totalPass = 0;
    while (!queue.isEmpty()) {
      int course = queue.poll();
      totalPass++;
      List<Integer> nextCourses = graph.get(course);
      for (int courseNext : nextCourses) {
        if (--inDegree[courseNext] == 0) {
          queue.add(courseNext);
        }
      }
    }
    return totalPass == numCourses;
  }

  // https://leetcode.com/problems/course-schedule-ii/
  public int[] findOrder(int numCourses, int[][] prerequisites) {
    List<List<Integer>> graph = new ArrayList<>();
    int[] inDegree = new int[numCourses];
    for (int i = 0; i < numCourses; i++) {
      graph.add(new ArrayList<>());
    }
    for (int[] dep : prerequisites) {
      graph.get(dep[1])
           .add(dep[0]);
      inDegree[dep[0]]++;
    }

    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < inDegree.length; i++) {
      if (inDegree[i] == 0) {
        queue.add(i);
      }
    }
    if (queue.size() == 0) {
      return new int[0];
    }
    int[] res = new int[numCourses];
    int totalPass = 0;
    while (!queue.isEmpty()) {
      int course = queue.poll();
      res[totalPass++] = course;
      List<Integer> nextCourses = graph.get(course);
      for (int courseNext : nextCourses) {
        if (--inDegree[courseNext] == 0) {
          queue.add(courseNext);
        }
      }
    }
    return totalPass == numCourses ? res : new int[0];
  }

  // https://www.luogu.com.cn/problem/U107394
  static class TopologySortTemplate {

    private static Map<Integer, List<Integer>> graph;
    private static int[] inDegree;
    private static PriorityQueue<Integer> minHeap;
    private static int nodeCount;

    private static void buildGraph(int numberOfNode) {
      nodeCount = numberOfNode;
      graph = new HashMap<>();
      for (int i = 1; i <= nodeCount; i++) {
        graph.put(i, new ArrayList<>());
      }
      inDegree = new int[nodeCount + 1];
      minHeap = new PriorityQueue<>();
    }

    private static void addEdge(int from, int to) {
      graph.get(from)
           .add(to);
    }

    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StreamTokenizer in = new StreamTokenizer(br);
      PrintWriter out = new PrintWriter(System.out);
      while (in.nextToken() != StreamTokenizer.TT_EOF) {
        int nodeCount = (int) in.nval; // n
        in.nextToken();
        int edgeCount = (int) in.nval; // m
        buildGraph(nodeCount); // node from 1....n
        for (int i = 0; i < edgeCount; i++) { // process following edges
          in.nextToken();
          int from = (int) in.nval;
          in.nextToken();
          int to = (int) in.nval;
          addEdge(from, to);
          inDegree[to]++;
        }
        int[] res = sort();
        for (int node : res) {
          out.print(node + " ");
        }
        out.println();
        break;
      }
      out.flush();
      out.close();
      br.close();
    }

    private static int[] sort() {
      for (int node = 1; node <= nodeCount; node++) {
        if (inDegree[node] == 0) {
          minHeap.offer(node);
        }
      }
      int[] result = new int[nodeCount];
      int index = 0;
      while (!minHeap.isEmpty()) {
        int node = minHeap.poll();
        result[index++] = node;
        List<Integer> neighbors = graph.get(node);

        for (int neighbor : neighbors) {
          if (--inDegree[neighbor] == 0) {
            minHeap.offer(neighbor);
          }
        }
      }
      return result;
    }

  }

  // https://leetcode.cn/problems/alien-dictionary/

  // https://leetcode.com/problems/stamping-the-sequence/
  public int[] movesToStamp(String stamp, String target) {

    return null;
  }

  // https://www.luogu.com.cn/problem/P4017
  static class FoodChain {

    private static Map<Integer, List<Integer>> graph;
    private static int[] inDegree;
    private static Queue<Integer> queue;
    private static int[] pathCount; // 这个数据count要在拓扑排序过程中逐步从上游向下游传播更新
    private static int nodeCount;

    private static void buildGraph(int numberOfNode) {
      nodeCount = numberOfNode;
      graph = new HashMap<>();
      for (int i = 1; i <= nodeCount; i++) {
        graph.put(i, new ArrayList<>());
      }
      inDegree = new int[nodeCount + 1];
      pathCount = new int[nodeCount + 1];
      queue = new LinkedList<>();
    }

    private static void addEdge(int from, int to) {
      graph.get(from)
           .add(to);
    }

    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StreamTokenizer in = new StreamTokenizer(br);
      PrintWriter out = new PrintWriter(System.out);
      while (in.nextToken() != StreamTokenizer.TT_EOF) {
        int nodeCount = (int) in.nval; // n
        in.nextToken();
        int edgeCount = (int) in.nval; // m
        buildGraph(nodeCount); // node from 1....n
        for (int i = 0; i < edgeCount; i++) { // process following edges
          in.nextToken();
          int from = (int) in.nval;
          in.nextToken();
          int to = (int) in.nval;
          addEdge(from, to);
          inDegree[to]++;
        }
        int count = pathCount();
        out.println(count);
        break;
      }
      out.flush();
      out.close();
      br.close();
    }

    private static int pathCount() {
      for (int node = 1; node <= nodeCount; node++) {
        if (inDegree[node] == 0) {
          queue.offer(node);
          pathCount[node] = 1;
        }
      }
      int result = 0;
      while (!queue.isEmpty()) {
        int node = queue.poll();
        List<Integer> neighbors = graph.get(node);
        if (neighbors.size() == 0) {
          result = (result + pathCount[node]) % 80112002;
          continue;
        }
        for (int neighbor : neighbors) {
          pathCount[neighbor] = (pathCount[neighbor] + pathCount[node]) % 80112002;
          if (--inDegree[neighbor] == 0) {
            queue.offer(neighbor);
          }
        }
      }
      return result;
    }

  }

  // https://leetcode.com/problems/loud-and-rich/
  public int[] loudAndRich(int[][] richer, int[] quiet) {
    int numberOfPeople = quiet.length;
    int[] result = new int[numberOfPeople];
    for (int i = 0; i < numberOfPeople; i++) {
      result[i] = i;
    }
    int[] inDegree = new int[numberOfPeople];
    Map<Integer, List<Integer>> graph = new HashMap<>();
    for (int i = 0; i < numberOfPeople; i++) {
      graph.put(i, new ArrayList<>());
    }
    for (int[] relation : richer) {
      graph.get(relation[0])
           .add(relation[1]);
      inDegree[relation[1]]++;
    }
    Queue<Integer> queue = new LinkedList<>();
    for (int personId = 0; personId < inDegree.length; personId++) {
      if (inDegree[personId] == 0) {
        queue.offer(personId);
      }
    }
    while (!queue.isEmpty()) {
      int curPerson = queue.poll();
      /*
       在curPerson消除自己对下一个节点的影响时, 要把自己目前的result传递给下一个节点
       i.e. 我当前找到结果是:result[curPerson], 如果它的quiet值比result[nextPerson]的quiet值低,就更新下一个节点的result
       */
      for (int nextPerson : graph.get(curPerson)) {
        if (quiet[result[nextPerson]] > quiet[result[curPerson]]) {
          result[nextPerson] = result[curPerson];
        }
        if (--inDegree[nextPerson] == 0) {
          queue.offer(nextPerson);
        }
      }
    }
    return result;
  }

  // https://leetcode.com/problems/parallel-courses-iii/
  public int minimumTime(int n, int[][] relations, int[] time) {
    Map<Integer, List<Integer>> graph = new HashMap<>();
    for (int course = 1; course <= n; course++) {
      graph.put(course, new ArrayList<>());
    }
    int[] inDegree = new int[n + 1];
    for (int[] relation : relations) {
      int from = relation[0];
      int to = relation[1];
      inDegree[to]++;
      graph.get(from)
           .add(to);
    }
    int[] timeToComplete = new int[n + 1];
    Queue<Integer> queue = new LinkedList<>();
    for (int course = 1; course < inDegree.length; course++) {
      if (inDegree[course] == 0) {
        queue.offer(course);
      }
    }
    int totalTime = 0;
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        int course = queue.poll();
        timeToComplete[course] += time[course - 1];
        totalTime = Math.max(totalTime, timeToComplete[course]);
        // timeToComplete[i] = 自己的时间 + 前置课程中最长的时间 (对于入度为0的课程来说, 前置课程时间为0)
        // 前置课程的时间会被逐步更新到timeToComplete[course]中
        for (int nextCourse : graph.get(course)) {
          timeToComplete[nextCourse] = Math.max(timeToComplete[nextCourse], timeToComplete[course]);
          if (--inDegree[nextCourse] == 0) {
            queue.offer(nextCourse);
          }
        }
      }
    }
    return totalTime;
  }

//  public static void main(String[] args) {
//    int[][] relations = {
//        {1, 5},
//        {1, 3},
//        {1, 2},
//        {4, 2},
//        {4, 5},
//        {2, 5},
//        {1, 4},
//        {4, 3},
//        {3, 5},
//        {3, 2}
//    };
//    int n = 5;
//    int k = 3;
//    TopologySortQuestions q = new TopologySortQuestions();
//    int res = q.minNumberOfSemesters(n, relations, k);
//    System.out.println(res);
//  }

  // TODO: fix bug
  public int minNumberOfSemesters(int n, int[][] relations, int k) {
    List<List<Integer>> graph = new ArrayList<>(n + 1);
    int[] inDegree = new int[n + 1];
    for (int i = 0; i <= n; i++) {
      graph.add(new ArrayList<>());
    }
    for (int[] relation : relations) {
      int from = relation[0];
      int to = relation[1];
      graph.get(from)
           .add(to);
      inDegree[to]++;
    }
    Queue<Integer> queue = new LinkedList<>();
    for (int i = 1; i < inDegree.length; i++) {
      if (inDegree[i] == 0) {
        queue.offer(i);
      }
    }
    int[] semesterTaken = new int[n + 1];
    Arrays.fill(semesterTaken, 1);
    int semester = 1;
    int courseTaken = 0;
    int maxSemester = 0;
    while (!queue.isEmpty()) {
      int course = queue.poll();
      courseTaken++;
      if (courseTaken % k == 0) {
        semester++;
      }
      semesterTaken[course] = Math.max(semester, semesterTaken[course]);

      System.out.println("curCourse: " + course + ", semesterTaken[curCourse]: " + semesterTaken[course]);
      for (int nextCourse : graph.get(course)) {
        semesterTaken[nextCourse] = Math.max(semesterTaken[course] + 1, semesterTaken[nextCourse]);
        if (--inDegree[nextCourse] == 0) {
          queue.offer(nextCourse);
        }
      }
    }
    for (int s : semesterTaken) {
      maxSemester = Math.max(s, maxSemester);
    }
    return maxSemester;
  }

  // https://leetcode.com/problems/maximum-employees-to-be-invited-to-a-meeting/
  /*
  基环树（Base-Ring Tree / Base-Cycle Tree）
   */
  public static void main(String[] args) {
    TopologySortQuestions q = new TopologySortQuestions();
    int[] favorite = {2, 2, 1, 2};
    int res = q.maximumInvitations(favorite);
    System.out.println(res);
  }

  public int maximumInvitations(int[] favorite) {
    // path: i -> favorite[i]
    int[] inDegree = new int[favorite.length];
    for (int i = 0; i < favorite.length; i++) {
      inDegree[favorite[i]]++;
    }
    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < inDegree.length; i++) {
      if (inDegree[i] == 0) {
        queue.offer(i);
      }
    }
    int[] deep = new int[favorite.length];
    while (!queue.isEmpty()) {
      int person = queue.poll();
      int fav = favorite[person];
      deep[fav] = Math.max(deep[fav], deep[person] + 1);
      if (--inDegree[fav] == 0) {
        queue.offer(fav);
      }
    }
    int smallRingsSum = 0, bigRingMax = 0;
    /*
    情况1: 2个人互相是favorite,那么这2个人组成一个环, 然后分别加上各自两端的最长链长度即可
    情况2: 超过2个人组成一个环, 那么只有成环的一批人可以安排入座
     */
    for (int person = 0; person < inDegree.length; person++) {
      if (inDegree[person] != 0) { // 说明person在环上, 要开始统计ringSize
        int ringSize = 0;
        int cur = person;
        while (inDegree[cur] != 0) {
          ringSize++;
          inDegree[cur] = 0; // mark as visited; 或者使用一个全局的boolean[] visited
          cur = favorite[cur];
        }
        if (ringSize == 2) {
          smallRingsSum += (2 + deep[person] + deep[favorite[person]]);
        } else {
          bigRingMax = Math.max(bigRingMax, ringSize);
        }
      }
    }
    return Math.max(smallRingsSum, bigRingMax);
  }

  public boolean repeatedSubstringPattern(String s) {

    int[] countMap = new int[128];
    char[] str = s.toCharArray();

    for (char c : str) {
      if (countMap[c] > 0) {
        countMap[c]--;
      } else {
        countMap[c]++;
      }
    }

    for (int count : countMap) {
      if (count < 0 || count > 1) {
        return false;
      }
    }
    return true;

  }

  public List<String> restoreIpAddresses(String s) {
    List<String> res = new ArrayList<>();
    char[] str = s.toCharArray();
    StringBuilder path = new StringBuilder();
    process(str, 0, path, res);
    return res;
  }

  private void process(char[] str, int index, StringBuilder path, List<String> res) {
    if (index == str.length) {
      res.add(path.toString());
      return;
    }
    char cur = str[index];
    int i = path.lastIndexOf(".");
    int num = !path.substring(i + 1, index)
                   .isEmpty() ? Integer.parseInt(path.substring(i + 1, index)) : 0;
    if (num == 0) {
      path.append('.');
      process(str, index + 1, path, res);
    } else if (num * 10 + (cur - '0') <= 255) {
      path.append(cur);
      process(str, index + 1, path, res);
      path.deleteCharAt(path.length() - 1);
    } else {
      path.append('.');
      process(str, index + 1, path, res);
    }
  }

}
