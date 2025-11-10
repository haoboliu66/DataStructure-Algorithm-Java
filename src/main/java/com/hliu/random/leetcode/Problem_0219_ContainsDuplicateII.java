package com.hliu.random.leetcode;

import java.util.HashMap;

public class Problem_0219_ContainsDuplicateII {

  public boolean containsNearbyDuplicate(int[] arr, int k) {
    if (arr == null || arr.length == 0) {
      return false;
    }

    for (int i = 0; i < arr.length; i++) {
      for (int j = i + 1; j < arr.length; j++) {
        if (arr[i] == arr[j] && Math.abs(i - j) <= k) {
          return true;
        }
      }
    }

    return false;
  }


  public boolean containsNearbyDuplicate2(int[] arr, int k) {
    if (arr == null || arr.length == 0) {
      return false;
    }

    HashMap<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < arr.length; i++) {
      if (map.containsKey(arr[i]) && Math.abs(i - map.get(arr[i])) <= k) {
        return true;
      }
      map.put(arr[i], i);
    }

    return false;
  }

}
