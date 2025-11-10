package com.hliu.random.algoexpert.hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/*
Write a function that takes in an array of integers and returns an array of length 2
representing the largest range of integers contained in that array.
The first number in the output array should be the first number in the range, while the
second number should be the last number in the range. A range of numbers is defined as a set of
numbers that come right after each other in
the set of real integers.
For instance, the output array [2, 6] represents the range {2, 3, 4, 5, 6} , which is a range of
length 5. Note that numbers don't need to be
sorted or adjacent in the input array in order
to form a range.

You can assume that there will only be one largest range

 */
public class P03_LargestRange {

  public static void main(String[] args) {
    int[] arr = {0, -5, 9, 19, -1, 18, 17, 2, -4, -3, 10, 3, 12, 5, 16, 4, 11, 7, -6, -7, 6, 15, 12, 12, 2, 1, 6, 13,
        14, -2};
    int[] range = largestRange(arr);
    System.out.println(Arrays.toString(range));
    Arrays.sort(arr);
    System.out.println(Arrays.toString(arr));
  }


  public static int[] largestRange(int[] arr) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < arr.length; i++) {
      int pre = arr[i] - 1;
      int post = arr[i] + 1;

      if (!map.containsKey(arr[i])) {
        int preLen = map.containsKey(pre) ? map.get(pre) : 0;
        int postLen = map.containsKey(post) ? map.get(post) : 0;

        //     (1  5) 6 (7  9)
        if (preLen == 0 && postLen == 0) {
          map.put(arr[i], 1);
          continue;
        }

        if (preLen != 0 && postLen != 0) {
          map.put(pre - preLen + 1, preLen + 1 + postLen);
          map.put(post + postLen - 1, preLen + 1 + postLen);
//          map.remove(pre);
//          map.remove(post);
        } else if (preLen != 0) {
          map.put(pre - preLen + 1, preLen + 1 + postLen);
//          map.remove(pre);
          map.put(arr[i], preLen + 1);
        } else if (postLen != 0) {
          map.put(post + postLen - 1, preLen + 1 + postLen);
//          map.remove(post);
          map.put(arr[i], 1 + postLen);
        }

      }

    }
    Map.Entry<Integer, Integer> maxE = null;
    for (Map.Entry<Integer, Integer> e : map.entrySet()) {
      System.out.println(e.getKey() + " => " + e.getValue());
      if (maxE == null) {
        maxE = e;
        continue;
      }

      if (e.getValue() > maxE.getValue()) {
        maxE = e;
      }

    }

    return new int[]{maxE.getKey(), maxE.getKey() + maxE.getValue() - 1};
  }


}
