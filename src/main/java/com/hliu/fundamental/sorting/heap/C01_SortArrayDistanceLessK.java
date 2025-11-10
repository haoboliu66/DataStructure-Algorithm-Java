package com.hliu.fundamental.sorting.heap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class C01_SortArrayDistanceLessK {

  public int maximumCount(int[] nums) {
    int positive = 0;
    int negative = 0;
    for (int x : nums) {
      if (x > 0) {
        positive++;
      } else if (x < 0) {
        negative++;
      }
    }
    return Math.max(positive, negative);
  }


  public int longestConsecutive(int[] arr) {
    Map<Integer, Integer> map = new HashMap<>(arr.length);
    int max = 0;
    for (int i = 0; i < arr.length; i++) {
      int curr = arr[i];

      int preLen = 0;
      int postLen = 0;
      preLen = !map.containsKey(curr - 1) ? 0 : map.get(curr - 1);
      postLen = !map.containsKey(curr + 1) ? 0 : map.get(curr + 1);
      max = Math.max(max, preLen + postLen + 1);

    }

    return max;
  }


  public int[] productExceptSelf1(int[] arr) {

    int[] res = new int[arr.length];
    res[0] = 1;
    // 1,2,4,6
    for (int i = 1; i < arr.length; i++) {
      res[i] = arr[i - 1] * res[i - 1];
    }
    int postProduct = 1;
    for (int i = arr.length - 2; i >= 0; i--) {
      res[i] *= postProduct;
      postProduct *= arr[i + 1];
    }
    return res;
  }

  public int[] productExceptSelf(int[] arr) {
    int[] preProduct = new int[arr.length];
    int[] postProduct = new int[arr.length];

    for (int i = 0; i < arr.length; i++) {
      preProduct[i] = i == 0 ? 1 : preProduct[i - 1] * arr[i - 1];
    }
    for (int i = arr.length - 1; i >= 0; i--) {
      postProduct[i] = i == arr.length - 1 ? 1 : postProduct[i + 1] * arr[i + 1];
    }
    System.out.println(Arrays.toString(preProduct));
    System.out.println(Arrays.toString(postProduct));

    int[] res = new int[arr.length];
    // 1,2,4,6
    for (int i = 0; i < arr.length; i++) {
      int pre = i == 0 ? 1 : preProduct[i - 1];
      int post = i == arr.length - 1 ? 1 : postProduct[i + 1];
      res[i] = pre * post;
    }

    return res;
  }

  public static void sortedArrayDistanceLessK(int[] arr, int k) {
    PriorityQueue<Integer> heap = new PriorityQueue<>();
    int index = 0;
    // 先把数组中前k + 1个数(下标是到k)组织成小根堆, 因为第k + 1往前移动k步都满足要求
    for (; index <= Math.min(k, arr.length); index++) {
      heap.add(arr[index]);
    }
    int i = 0;
    // index继续向后走, 开始从小根堆中弹出值并赋值给arr, 弹出的永远是剩下的值中最小的
    for (; index < arr.length; i++, index++) {
      heap.add(arr[index]);
      arr[i] = heap.poll();
    }
    //剩下的值依次弹出
    while (!heap.isEmpty()) {
      arr[i++] = heap.poll();
    }

  }

}
