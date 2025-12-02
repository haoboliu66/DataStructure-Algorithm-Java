package com.hliu.fundamental.recursion.classic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class C04_PermutationsWithDuplicate {

  // https://leetcode.com/problems/permutations-ii/

  /*
  Time Complexity: O(N! * N)
   */

  public static void main(String[] args) {
    List<List<Integer>> permute = new C04_PermutationsWithDuplicate().permuteUnique(new int[]{1, 1, 2});
    System.out.println(permute);
  }

  public List<List<Integer>> permuteUnique(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    dfs(nums, 0, result);
    return result;
  }

  private void dfs(int[] arr, int index, List<List<Integer>> res) {
    if (index == arr.length) {
      List<Integer> list = new ArrayList<>();
      for (int n : arr) {
        list.add(n);
      }
      res.add(list);
    } else {
      Set<Integer> set = new HashSet<>();
      for (int i = index; i < arr.length; i++) {
        if(!set.contains(arr[i])) {
          set.add(arr[i]);
          swap(arr, i, index);
          dfs(arr, index + 1, res);
          swap(arr, i, index);
        }
      }
    }
  }

  private void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

}
