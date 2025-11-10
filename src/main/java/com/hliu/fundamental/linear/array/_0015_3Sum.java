package com.hliu.fundamental.linear.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _0015_3Sum {

  public List<List<Integer>> threeSum(int[] arr) {

    Arrays.sort(arr);

    List<List<Integer>> res = new ArrayList<>();

    for (int i = 0; i < arr.length; i++) {
      int cur = arr[i];

      List<List<Integer>> twoSums = twoSum(arr, i + 1, 0 - cur);
      for (List<Integer> pair : twoSums) {
        pair.add(cur);
        res.add(pair);
      }
    }

    return res;
  }

  private List<List<Integer>> twoSum(int[] arr, int from, int rest) {
    List<List<Integer>> res = new ArrayList<>();

    for (int i = from; i < arr.length; i++) {

    }

    return res;
  }


}
