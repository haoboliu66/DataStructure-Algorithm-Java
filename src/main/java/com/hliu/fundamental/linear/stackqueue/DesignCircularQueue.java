package com.hliu.fundamental.linear.stackqueue;

public class DesignCircularQueue {

  /* design a circular queue with a capacity

  https://leetcode.com/problems/design-circular-queue
  */

  class CircularQueue {

    int[] queue;
    int size;   // decouple pushIndex & pollIndex
    int cap;
    int pollIndex;
    int pushIndex;

    public CircularQueue(int maxCapacity) {
      queue = new int[maxCapacity];
      cap = maxCapacity;
      pollIndex = pushIndex = size = 0;
    }

    public boolean isEmpty() {
      return size == 0;
    }

    public boolean isFull() {
      return size == cap;
    }

    public boolean enqueue(int value) {
      if (isFull()) {
        return false;
      }
      queue[pushIndex] = value;
      size++;
      pushIndex = nextIndex(pushIndex);
      return true;
    }

    public boolean dequeue() {
      if (isEmpty()) {
        return false;
      }
      size--;
      pollIndex = nextIndex(pollIndex);
      return true;
    }

    public int front() {
      if (isEmpty()) {
        return -1;
      }
      return queue[pollIndex];
    }

    public int rear() {
      if (isEmpty()) {
        return -1;
      }
      int last = lastValidIndex();
      return queue[last];
    }

    private int lastValidIndex() {
      return prevIndex(pushIndex);
    }

    public int nextIndex(int i) {
      return i < cap - 1 ? i + 1 : 0;
    }

    public int prevIndex(int i) {
      return i > 0 ? i - 1 : cap - 1;
    }
  }

  class MyCircularDeque {

    int[] queue;
    int cap;
    int size;
    int frontIndex;
    int rearIndex;

    public MyCircularDeque(int k) {
      queue = new int[k];
      cap = k;
      size = frontIndex = rearIndex = 0;
    }

    public boolean insertFront(int value) {
      if (isFull()) {
        return false;
      }
      size++;
      frontIndex = prevIndex(frontIndex);
      queue[frontIndex] = value;
      return true;
    }
    /*
     insertFront和insertLast只有一个index可以直接写入, 另一个要退让
     queue[rearIndex] = value => 如果rearIndex直接赋值, 那么insertFront时, frontIndex就要先退一步, 避免同一个index被overwrite
     e.g  初始状态rearIndex = frontIndex = 0, insertLast(2) => rearIndex = 1, frontIndex = 0, queue[0] = 2;
          insertFront(1) => frontIndex此时是0, 而0已经被写入了, 必须让frontIndex先退一步变成cap-1, 再写入queue
     */

    public boolean insertLast(int value) {
      if (isFull()) {
        return false;
      }
      size++;
      queue[rearIndex] = value;
      rearIndex = nextIndex(rearIndex);
      return true;
    }

    public boolean deleteFront() {
      if (isEmpty()) {
        return false;
      }
      size--;
      frontIndex = nextIndex(frontIndex);
      return true;
    }

    public boolean deleteLast() {
      if (isEmpty()) {
        return false;
      }
      size--;
      rearIndex = prevIndex(rearIndex);
      return true;
    }

    public int getFront() {
      if (isEmpty()) {
        return -1;
      }
      return queue[frontIndex];
    }

    public int getRear() {
      if (isEmpty()) {
        return -1;
      }
      return queue[prevIndex(rearIndex)];
    }

    public boolean isEmpty() {
      return size == 0;
    }

    public boolean isFull() {
      return size == cap;
    }

    private int nextIndex(int index) {
      return index < cap - 1 ? index + 1 : 0;
    }

    private int prevIndex(int index) {
      return index > 0 ? index - 1 : cap - 1;
    }
  }

}
