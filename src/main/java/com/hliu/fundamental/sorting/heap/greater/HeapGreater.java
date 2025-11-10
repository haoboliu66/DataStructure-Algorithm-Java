package com.hliu.fundamental.sorting.heap.greater;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class HeapGreater<T> {

  private final ArrayList<T> heap;
  private int heapSize;
  private Map<T, Integer> indexMap;
  private Comparator<? super T> comparator;

  public HeapGreater(Comparator<? super T> comparator) {
    this.heap = new ArrayList<>();
    this.heapSize = 0;
    this.indexMap = new HashMap<>();
    this.comparator = comparator; // minHeap by default
  }

  public boolean isEmpty() {
    return heapSize == 0;
  }

  public int size() {
    return heapSize;
  }

  public T peek() {
    return heap.get(0);
  }

  public void push(T obj) {
    heap.add(obj);
    indexMap.put(obj, heapSize);
    heapInsert(heapSize++);
  }

  private void heapInsert(int index) {
    while (comparator.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
      swap(heap, index, (index - 1) / 2);  //调整堆, 交换父与子节点, 并需要更改indexMap的记录
      index = (index - 1) / 2;
    }
  }

  public T pop() {
    if (heap.isEmpty()) {
      return null;
    }
    T result = heap.get(0);
    swap(heap, --heapSize, 0);
    heapify();
    heap.remove(heapSize);
    indexMap.remove(result);
    return result;
  }

  private void heapify() {
    int index = 0;
    int leftChild;
    while ((leftChild = index * 2 + 1) < heapSize) {
      int swapIndex = leftChild + 1 >= heapSize ? leftChild :
          comparator.compare(heap.get(leftChild), heap.get(leftChild + 1)) < 0 ?
              leftChild : leftChild + 1;
      if (comparator.compare(heap.get(index), heap.get(swapIndex)) == 0) {
        break;
      }
      swap(heap, index, swapIndex);
      index = swapIndex;
    }

  }

  private void swap(ArrayList<T> heap, int i, int j) {
    T tmp = heap.get(i);
    heap.set(i, heap.get(j));
    heap.set(j, tmp);
  }

  public static void main(String[] args) {
    HeapGreater<Integer> minHeap = new HeapGreater<>((a, b) -> a - b);
    minHeap.push(3);
    minHeap.push(2);
    minHeap.push(6);
    minHeap.push(7);
    minHeap.push(8);
    minHeap.push(-5);
    System.out.println(minHeap.heap);
    System.out.println(minHeap.pop());
    System.out.println(minHeap.pop());
    System.out.println(minHeap.pop());
    System.out.println(minHeap.pop());
    System.out.println(minHeap.pop());
    minHeap.push(70);
    minHeap.push(81);
    System.out.println(minHeap.pop());
    System.out.println(minHeap.pop());
    System.out.println(minHeap.pop());
  }


}
