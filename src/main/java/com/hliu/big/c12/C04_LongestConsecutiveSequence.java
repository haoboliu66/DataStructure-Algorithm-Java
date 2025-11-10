package com.hliu.big.c12;

import java.util.*;

public class C04_LongestConsecutiveSequence {

//    public static void main(String[] args) {
//        int[] arr = {-2,-3,-3,7,-3,0,5,0,-8,-4,-1,2};
//        System.out.println(longestConsecutive(arr));
//
//    }

  public static void main(String[] args) {
    TreeSet<Integer> set = new TreeSet<>();
    set.add(5);
    set.add(1);
    set.add(3);
    set.add(2);
    set.add(9);
    set.add(6);
    Iterator<Integer> iterator = set.iterator();
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
    }
  }

  // O(N)
  public static int longestConsecutive(int[] arr) {
    if (arr == null || arr.length == 0) {
      return 0;
    }
    Map<Integer, Integer> map = new HashMap<>();
    int max = 1, len;
    for (int i = 0; i < arr.length; i++) {
      if (!map.containsKey(arr[i])) {
        map.put(arr[i], 1);
        len = 1;
        int pre = arr[i] - 1, post = arr[i] + 1;
        if (map.containsKey(pre)) {
          int preLen = map.get(pre);
          int head = pre - preLen + 1;

          if (map.get(head) != null) {
            map.put(head, preLen + 1);
            map.put(arr[i], preLen + 1);
            len = map.get(head);
          }
        }
        if (map.containsKey(post)) {
          int postLen = map.get(post);
          int tail = post + postLen - 1;

          if (map.get(tail) != null) {
            len = map.get(arr[i]);
            map.put(tail, postLen + len);
            map.put(arr[i] - len + 1, postLen + len);
            len = map.get(tail);
          }
        }
        max = Math.max(max, len);
      }
    }
    return max;
  }

  // O(N*logN)
  public static int rightLongestConsecutive0(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    int[] arr = removeDuplicate(nums);
    Arrays.sort(arr);
    int len, max = 1;
    for (int i = 1; i < arr.length; i++) {
      len = 1;
      while (i < arr.length && arr[i] - arr[i - 1] == 1) {
        len++;
        i++;
      }
      max = Math.max(max, len);
    }

    return max;
  }

  private static int[] removeDuplicate(int[] arr) {
    Set<Integer> set = new HashSet<>();
    for (int i : arr) {
      set.add(i);
    }
    int[] res = new int[set.size()];
    int index = 0;
    for (int n : set) {
      res[index++] = n;
    }
    return res;
  }


}
