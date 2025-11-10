package com.hliu.fundamental.sorting.heap;

import java.util.Arrays;

public class MyRegularMaxHeap {

  public static void main(String[] args) {
    MaxHeap heap = new MaxHeap(6);
    heap.push(5);
    heap.push(7);
    heap.push(8);
    heap.push(6);
    heap.push(3);
    System.out.println(heap);
    System.out.println("________");
    System.out.println(heap.pop());
    System.out.println(heap);
    heap.push(16);
    System.out.println(heap);
    System.out.println(heap.pop());
    System.out.println(heap);
    heap.push(12);
    System.out.println(heap);
  }

  public static class MaxHeap {

    private int[] heap;
    private int heapSize;
    private final int limit;

    public MaxHeap(int limit) {
      heap = new int[limit];
      this.limit = limit;
      heapSize = 0;
    }

    public boolean isEmpty() {
      return heapSize == 0;
    }

    public boolean isFull() {
      return heapSize == limit;
    }

    public int size() {
      return heapSize;
    }

    public void push(int val) {
      if (isFull()) {
        throw new RuntimeException("HeapSize reaches the limit");
      }
      heap[heapSize] = val;  // put val at the end
      heapInsert(heap, heapSize++); // deal with the val
    }

    /**
     * insert a new number into heap
     */
    private void heapInsert(int[] arr, int index) {
      while (arr[index] > arr[(index - 1) / 2]) {
        swap(heap, index, (index - 1) / 2);
        index = (index - 1) / 2;
      }
    }

    private void swap(int[] arr, int i, int j) {
      int temp = arr[i];
      arr[i] = arr[j];
      arr[j] = temp;
    }

    /**
     * return the largest number in the heap
     */
    public int pop() {
      if (isEmpty()) {
        throw new RuntimeException("Heap is empty");
      }
      int max = heap[0];
      swap(heap, 0, --heapSize); // remove the largest element out of heap
      heapify(heap, 0, heapSize);
      return max;
    }

    /**
     * rearrange heap array into Max-heap
     */
    private void heapify(int[] arr, int index, int heapSize) {
      int left = 2 * index + 1;
      while (left < heapSize) {
        // pick largest out of left and right child
        int largest = (left + 1 < heapSize && arr[left + 1] > arr[left]) ? left + 1 : left;
        // compare root and largest between left and right child
        largest = (arr[index] > arr[largest]) ? index : largest;
        if (largest == index) { // no sink will occur
          break;
        }
        swap(arr, largest, index);
        index = largest;  // move index to the largest child
        left = 2 * index + 1; // get next left child
      }

    }

    @Override
    public String toString() {
      return "MaxHeap{" +
          "heap=" + Arrays.toString(heap) +
          '}';
    }
  }


}
