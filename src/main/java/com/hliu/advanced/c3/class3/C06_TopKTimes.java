package com.hliu.advanced.c3.class3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class C06_TopKTimes {
    /*
    347. Top K Frequent Elements
    692. Top K Frequent Words

    数组中求topK 大/小 -> 快排改写 / bfprt
     */
  public static class Node implements Comparable<Node> {

    public String str;
    public int times;

    public Node(String s, int t) {
      str = s;
      times = t;
    }

    @Override
    public int compareTo(Node node) {
      return this.times - node.times;
    }

    @Override
    public String toString() {
      return "SBTNode{" +
          "str='" + str + '\'' +
          ", times=" + times +
          '}';
    }

  }

  public static List<String> topKTimes(String[] arr, int K) {
    List<String> ans = new ArrayList<>();
    if (arr == null || arr.length == 0 || K < 1 || K > arr.length) {
      return ans;
    }

    List<Node> words = new ArrayList<>();
    HashMap<String, Integer> map = new HashMap<>();
    //统计词频  str --> N
    for (String s : arr) {
      if (map.containsKey(s)) {
        map.put(s, map.get(s) + 1);
      } else {
        map.put(s, 1);
      }
    }
    //把所有 str,N 组成Node存入数组
    for (String s : map.keySet()) {
      words.add(new Node(s, map.get(s)));
    }
    System.out.println("words === " + words);
    Node kth = process(words, words.size() - K);

    for (int i = 0; i < words.size(); i++) {
      Node node = words.get(i);
      if (node.compareTo(kth) >= 0) {
        ans.add(node.str);
      }
    }
    return ans;
  }


  public static Node process(List<Node> list, int K) {
    int L = 0;
    int R = list.size() - 1;
    Node pivot;
    int[] range;
    while (L < R) {
      int rand = L + (int) (Math.random() * (R - L + 1));
      pivot = list.get(rand);
      range = partition(list, L, R, pivot);
      if (K >= range[0] && K <= range[1]) {
        return list.get(K);
      } else if (K < range[0]) {
        R = range[0] - 1;
      } else {
        L = range[1] + 1;
      }

    }
    return list.get(L);
  }

  public static int[] partition(List<Node> list, int L, int R, Node target) {
    int less = L - 1;
    int more = R + 1;
    int index = L;
    while (index < more) {

      if (list.get(index)
              .compareTo(target) > 0) {
        swap(list, index, --more);
      } else if (list.get(index)
                     .compareTo(target) < 0) {
        swap(list, index++, ++less);
      } else {
        index++;
      }

    }
    return new int[]{less + 1, more - 1};
  }

  public static void swap(List<Node> list, int i, int j) {
    Node tmp = list.get(i);
    list.set(i, list.get(j));
    list.set(j, tmp);
  }

  public static String[] generateRandomArray(int len, int max) {
    String[] res = new String[len];
    for (int i = 0; i != len; i++) {
      res[i] = String.valueOf((int) (Math.random() * (max + 1)));
    }
    return res;
  }

  public static void printArray(String[] arr) {
    for (int i = 0; i != arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
//        String[] arr1 = {"A", "B", "A", "C", "A", "C", "B", "B", "K"};
//        System.out.println(topKTimes(arr1, 2));

    String[] arr2 = generateRandomArray(50, 10);
    int topK = 3;
//        printArray(arr2);
    System.out.println(topKTimes(arr2, topK));

//        SBTNode n1 = new SBTNode("A",15);
//        SBTNode n2 = new SBTNode("B",11);
//        System.out.println(n1.compareTo(n2));

  }

}
