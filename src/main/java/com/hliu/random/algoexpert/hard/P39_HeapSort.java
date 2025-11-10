package com.hliu.random.algoexpert.hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class P39_HeapSort {

  // max-heap

  // i -> i*2+1, i*2+2
  // (i-1)/2

  // 0 1 2 3 4 5 6 7 8


  public String customSortString(String order, String s) {
    if (order == null || "".equals(order) || s == null || "".equals(s)) {
      return s;
    }

    Map<Character, Integer> map = new HashMap<>();
    int score = order.length();
    for (char c : order.toCharArray()) {
      map.put(c, score--);
    }

    PriorityQueue<Character> maxHeap = new PriorityQueue<>((c1, c2) -> map.get(c2) - map.get(c1));

    for (char c : s.toCharArray()) {
      maxHeap.add(c);
    }

    StringBuilder sb = new StringBuilder();
    while (!maxHeap.isEmpty()) {
      sb.append(maxHeap.poll());

    }
    return sb.toString();
  }


  public static void main(String[] args) {
    int[] arr = new int[]{2, 3, 1, 7, 9, 3, 50, 0, -1, -7 - 4, -9};
    int[] arr2 = new int[]{5, 1, 1, 2, 0, 0};
    int[] res = heapSort(arr2);
    System.out.println(Arrays.toString(res));
  }

  public static int[] heapSort(int[] arr) {
    int[] heap = new int[arr.length];
    int index = 0;

    for (int n : arr) {
      index = heapInsert(heap, index, n);
    }
    int curEnd = arr.length - 1;
    while (curEnd > 0) {
      swap(heap, 0, curEnd);
      heapify(heap, curEnd);
      curEnd--;
    }
    return heap;
  }

  public static int heapInsert(int[] arr, int index, int num) {
    if (index == arr.length) {
      throw new RuntimeException("heap is full");
    }
    int cur = index++;
    int parent;
    arr[cur] = num;
    while (cur > 0 && (parent = (cur - 1) / 2) >= 0 && num > arr[parent]) {
      swap(arr, cur, parent);
      cur = parent;
    }
    return index;
  }

  public static void heapify(int[] arr, int boundary) {
    int endIndex = boundary - 1;
    int cur = 0;
    int leftChild = cur * 2 + 1, rightChild = cur * 2 + 2;
    if (leftChild > endIndex) {
      return;
    }
    int largerIndex = rightChild <= endIndex && arr[rightChild] > arr[leftChild] ? rightChild : leftChild;

    while (cur <= endIndex && largerIndex <= endIndex && arr[cur] < arr[largerIndex]) {
      swap(arr, cur, largerIndex);
      cur = largerIndex;
      leftChild = cur * 2 + 1;
      rightChild = cur * 2 + 2;
      largerIndex = rightChild <= endIndex && arr[rightChild] > arr[leftChild] ? rightChild : leftChild;
    }

  }

  public static void swap(int[] arr, int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }

}
