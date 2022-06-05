package com.hliu.sys.c19;

import java.util.*;

public class C04_SmallestRangeCoveringElementsFromKLists {

  //  https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/

  public static int[] smallestRange(List<List<Integer>> nums) {
    int N = nums.size();
    TreeSet<ListNode> set = new TreeSet<>();
    for (int i = 0; i < N; i++) {
      set.add(new ListNode(nums.get(i)
                               .get(0), 0, i));
    }
    int l = set.first().val, r = set.last().val;
    while (set.size() == N) {
      ListNode first = set.first();
      List<Integer> curList = nums.get(first.listIndex);
      int nextIndex = first.index + 1;
      set.remove(first);
      if (nextIndex < curList.size()) {
        set.add(new ListNode(curList.get(nextIndex), nextIndex, first.listIndex));
        if (set.last().val - set.first().val < r - l) {
          l = set.first().val;
          r = set.last().val;
        }
      }
    }
    return new int[]{l, r};
  }

  private static class ListNode implements Comparable<ListNode> {

    int val;
    int index;
    int listIndex;

    public ListNode(int val, int index, int listIndex) {
      this.val = val;
      this.index = index;
      this.listIndex = listIndex;
    }

    @Override
    public int compareTo(ListNode o) {
      return this.val != o.val ? this.val - o.val : this.listIndex - o.listIndex;
    }
  }

  // 有bug的方法, 问题出在定义Node的compare方法上, 会导致add过程中有序表丢失节点
  public static int[] smallestRange1(List<List<Integer>> nums) {
    int N = nums.size();
    TreeSet<Node> set = new TreeSet<>();
    for (int i = 0; i < N; i++) {
      set.add(new Node(nums.get(i)
                           .get(0), 0, i));
    }
    int l = set.first().val, r = set.last().val;
    while (set.size() == N) {
      Node first = set.first();
      List<Integer> curList = nums.get(first.listIndex);
      int nextIndex = first.index + 1;
      set.remove(first);
      if (nextIndex < curList.size()) {
        set.add(new Node(curList.get(nextIndex), nextIndex, first.listIndex));
        if (set.last().val - set.first().val < r - l) {
          l = set.first().val;
          r = set.last().val;
        }
      }
    }
    return new int[]{l, r};
  }

  private static class Node implements Comparable<Node> {

    int val;
    int index;
    int listIndex;

    public Node(int val, int index, int listIndex) {
      this.val = val;
      this.index = index;
      this.listIndex = listIndex;
    }

    @Override
    public String toString() {
      return "TreeNode{" +
          "val=" + val +
          ", index=" + index +
          ", listIndex=" + listIndex +
          '}';
    }

    @Override
    public int compareTo(Node o) {
      return this.val - o.val;
    }

  }


  private static List<List<Integer>> generateRandomList(int maxSize, int maxVal) {
    List<List<Integer>> ans = new ArrayList<>();
    for (int i = 0; i < maxSize; i++) {
      List<Integer> list = new ArrayList<>();
      for (int j = 0; j < Math.random() * maxSize; j++) {
        list.add((int) ((Math.random() + 1) * maxVal - (Math.random() + 0.5) * maxVal));
      }
      Collections.sort(list);
      ans.add(list);
    }
    return ans;
  }

  private static boolean isEqual(int[] arr1, int[] arr2) {
    return arr1[0] == arr2[0] && arr1[1] == arr2[1];
  }


  public static void main(String[] args) {
//        List<List<Integer>> ans = new ArrayList<>();
//        List<Integer> list1 = new ArrayList<>();
//        list1.add(-16);
//        list1.add(35);
//        list1.add(41);
//        List<Integer> list2 = new ArrayList<>();
//        list2.add(35);
//        list2.add(48);
//        list2.add(62);
//        List<Integer> list3 = new ArrayList<>();
//        list3.add(18);
//        list3.add(29);
//        ans.add(list1);
//        ans.add(list2);
//        ans.add(list3);
//        int[] res = smallestRange1(ans);
//        System.out.println(Arrays.toString(res));

    int maxSize = 3, maxVal = 100, times = 10000;
    List<List<Integer>> list;
    System.out.println("Go");
    for (int i = 0; i < times; i++) {
      list = generateRandomList(maxSize, maxVal);
      int[] ans1 = smallestRange(list);
      int[] ans2 = smallestRange1(list);
      if (!isEqual(ans1, ans2)) {
        System.out.println("Oops");
        System.out.println("right: " + Arrays.toString(ans1));
        System.out.println("ans2: " + Arrays.toString(ans2));
        System.out.println("list: " + list);
        break;
      }
    }
    System.out.println("Done");
  }


}
