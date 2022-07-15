package com.hliu.advanced.c3.class3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Problem_1244_DesignALeaderboard {

  public static class MyMaxHeap {

    ArrayList<Node> heap;
    int heapSize;
    int index;
    Map<Node, Integer> heapIndexMap;
    Map<Integer, Node> playerIdMap;
    Comparator<Node> comparator;

    public MyMaxHeap(int k, Comparator<Node> comp) {
      index = 0;
      heapSize = k;
      heap = new ArrayList<>();
      heapIndexMap = new HashMap<>();
      playerIdMap = new HashMap<>();
      comparator = comp;
    }

    public MyMaxHeap(Comparator<Node> comp) {
      index = 0;
      heap = new ArrayList<>();
      heapIndexMap = new HashMap<>();
      comparator = comp;
    }

    public boolean isEmpty() {
      return index == 0;
    }

    public void add(int id, int score) {
      // new str comes
      if (!playerIdMap.containsKey(id)) {
//                if (isFull()) {
//                    throw new RuntimeException("Heap is Full");
//                }
        Node newNode = new Node(id, score);
        heap.add(newNode);
        playerIdMap.put(id, newNode);
        heapIndexMap.put(newNode, index);
        heapInsert(heap, index++, newNode);
      } else {
        // old str comes
        Node curNode = playerIdMap.get(id);
        Integer curIndex = heapIndexMap.get(curNode);
        curNode.score += score;
        heapInsert(heap, curIndex, curNode);
      }
    }

    public void reset(int id) {
      if (!playerIdMap.containsKey(id)) {
        return;
      }
      Node nodeToReset = playerIdMap.get(id);
      nodeToReset.score = 0;
      int nodeToResetIndex = heapIndexMap.get(nodeToReset);
      heapify(heap, nodeToResetIndex);
    }

    public int top(int k) {
      int sum = 0;
      while (k != 0) {
        sum += pop().score;
        k--;
      }
      return sum;
    }

    private Node pop() {
      if (isEmpty()) {
        throw new RuntimeException("Heap is Empty");
      }
      Node res = heap.get(0);
      swap(heap, 0, --index);
      heapify(heap, 0);

      return res;
    }

    private void heapify(ArrayList<Node> heap, int i) {
      int leftChild = i * 2 + 1;
      int larger = leftChild + 1 < index &&
          comparator.compare(heap.get(leftChild), heap.get(leftChild + 1)) < 0 ? leftChild + 1 : leftChild;
      while (comparator.compare(heap.get(i), heap.get(larger)) < 0) {
        swap(heap, i, larger);
        i = larger;
        if (i * 2 >= index) {
          break;
        }
        larger = leftChild + 1 < index &&
            comparator.compare(heap.get(leftChild), heap.get(leftChild + 1)) < 0 ? leftChild + 1 : leftChild;
      }
    }

    private void heapInsert(ArrayList<Node> heap, int index, Node node) {
      int parent = (index - 1) / 2;
      // [index] > [parent]
      while (comparator.compare(heap.get(parent), heap.get(index)) > 0) {
        swap(heap, index, parent);
        index = parent;
        parent = (index - 1) / 2;
      }
    }

    private void swap(ArrayList<Node> heap, int index1, int index2) {
      Node node1 = heap.get(index1);
      Node node2 = heap.get(index2);
      // swap index record in Map
      heapIndexMap.put(node2, index1);
      heapIndexMap.put(node1, index2);
      // swap node in heap[]
      heap.set(index1, node2);
      heap.set(index2, node1);
    }
  }

  static class Leaderboard {

    MyMaxHeap heap;

    public Leaderboard() {
      heap = new MyMaxHeap(new NodeComparator());
    }

    public void addScore(int playerId, int score) {
      heap.add(playerId, score);
    }

    public int top(int K) {
      return heap.top(K);
    }

    public void reset(int playerId) {
      heap.reset(playerId);
    }
  }

  public static class Node {

    public int id;
    public int score;

    public Node(int id, int score) {
      this.id = id;
      this.score = score;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      Node node = (Node) o;

      if (id != node.id) {
        return false;
      }
      return score == node.score;
    }

    @Override
    public int hashCode() {
      int result = id;
      result = 31 * result + score;
      return result;
    }
  }

  private static class NodeComparator implements Comparator<Node> {

    public int compare(Node n1, Node n2) {
      return n2.score - n1.score;
    }
  }

}
