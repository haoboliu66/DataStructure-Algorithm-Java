package com.hliu.fundamental.graph.topologysort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TopologySortQuestions {

  //

  public static void main(String[] args) {

    int[] nums = {0, 2, 3, 4, 6, 8, 9};

    TopologySortQuestions q = new TopologySortQuestions();

    int[][] prerequisites = {
        {1, 4}, {2, 4}, {3, 1}, {3, 2}
    };
    boolean res = q.canFinish(5, prerequisites);
    System.out.println(res);

  }

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


  public boolean detectCapitalUse(String word) {

    int lowercaseCount = 0;
    int uppercaseCount = 0;
    char[] str = word.toCharArray();

    for (char ch : str) {

      if (ch >= 'A' && ch <= 'Z') {
        uppercaseCount++;
      } else {
        lowercaseCount++;
      }
    }

    return (uppercaseCount == 1 && str[0] >= 'A' && str[0] <= 'Z') || uppercaseCount == str.length
        || lowercaseCount == str.length;
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
