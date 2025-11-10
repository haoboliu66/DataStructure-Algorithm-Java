package com.hliu.random.algoexpert.hard;

import java.util.PriorityQueue;

/*
Write a function that takes in a non-negative
integer k and a k-sorted array of integers and
returns the sorted version of the array. Your
function can either sort the array in place or
create an entirely new array.
A k-sorted array is a partially sorted array in
which all elements are at most k positions
away from their sorted position. For example,
the array [3, 1, 2, 2] is k-sorted with
k = 3 , because each element in the array is
at most 3 positions away from its sorted
position.
Note that you're expected to come up with an
algorithm that can sort the k-sorted array
faster than in O(nlog(n)) time.

 */
public class P23_SortKSortedArray {

  public int[] sortKSortedArray(int[] arr, int k) {
    // Write your code here.
    if (arr == null || arr.length == 0) {
      return new int[]{};
    }
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    int i = 0;
    for (; i <= Math.min(k, arr.length - 1); i++) { // O(k)
      minHeap.add(arr[i]);
    }

    int index = 0;
    int[] res = new int[arr.length];
    while (!minHeap.isEmpty()) {     // O(n)
      res[index++] = minHeap.poll(); //  log(k)
      if (i < arr.length) {
        minHeap.add(arr[i++]);
      }
    }
    return res;
  }

}
