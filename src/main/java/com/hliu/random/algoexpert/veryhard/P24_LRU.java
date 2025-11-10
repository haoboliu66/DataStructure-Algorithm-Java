package com.hliu.random.algoexpert.veryhard;

import java.util.HashMap;
import java.util.Map;

public class P24_LRU {

  static class Node {

    String key;
    int value;
    Node prev;
    Node next;

    public Node() {
    }

    public Node(String key, int value) {
      this.key = key;
      this.value = value;
    }
  }

  static class LRUCache {

    Map<String, Node> nodeMap;
    int maxSize;
    Node head;
    Node tail;

    public LRUCache(int maxSize) {
      this.maxSize = maxSize > 1 ? maxSize : 1;
      nodeMap = new HashMap<>(maxSize);
    }

    public void addNewNode(Node newNode){
      if (head == null) {
        head = newNode;
        tail = newNode;
        return;
      }
      tail.next = newNode;
      newNode.prev = tail;
      tail = newNode;
    }

    public Node removeHeadFromList() {
      return null;
    }

    public void moveNodeToTail(Node node) {
      if (head == null || node == tail || head == tail) {
        return;
      }
      if(node == head){
        Node cur = head.next;

      }

    }

    public void insertKeyValuePair(String key, int value) {

      if (!nodeMap.containsKey(key)) {
        Node newNode = new Node(key, value);
        if(nodeMap.size() == maxSize){
          // evicted one node, and add newNode
          Node oldHead = removeHeadFromList();
          nodeMap.remove(oldHead.key);
        }
        nodeMap.put(key, newNode);

      }else{
        Node cur = nodeMap.get(key);
        cur.value = value;
        moveNodeToTail(cur);
      }

    }

    public LRUResult getValueFromKey(String key) {
      // Write your code here.
      return null;
    }

    public String getMostRecentKey() {
      // Write your code here.
      return null;
    }
  }

  static class LRUResult {

    boolean found;
    int value;

    public LRUResult(boolean found, int value) {
      this.found = found;
      this.value = value;
    }
  }

}
