package com.hliu.fundamental.recursion.classic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Subsets {

  // https://leetcode.com/problems/subsets/

  public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    dfs(nums, 0, new LinkedList<>(), res);
    return res;
  }

  private void dfs(int[] arr, int index, LinkedList<Integer> path, List<List<Integer>> res) {
    if (index == arr.length) {
      res.add(new ArrayList<>(path));
      return;
    }
    path.add(arr[index]);
    dfs(arr, index + 1, path, res);
    path.removeLast();

    dfs(arr, index + 1, path, res);
  }

  // https://leetcode.com/problems/subsets-ii/
  public List<List<Integer>> subsetsWithDup(int[] nums) {
    Arrays.sort(nums);
    List<List<Integer>> res = new ArrayList<>();
    dfs(nums, 0, new LinkedList<>(), res);
    return res;
  }

}
