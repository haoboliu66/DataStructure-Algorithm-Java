package com.hliu.random.algoexpert.medium;

public class P43_ThreeNumberSort {

  static class DoublyLinkedList {

    public Node head;
    public Node tail;

    public void setHead(Node node) {
      if (!containsNodeWithValue(node.value)) {
        if (head == null) {
          head = node;
          tail = node;
          return;
        }
        node.next = head;
        head.prev = node;
        head = node;
      } else {
        remove(node);
        insertBefore(head, node);
      }

    }

    public void setTail(Node node) {
      if (!containsNodeWithValue(node.value)) {
        if (tail == null) {
          head = node;
          tail = node;
          return;
        }
        node.prev = tail;
        tail.next = node;
        tail = node;
      } else {
        remove(node);
        insertAfter(tail, node);
      }
    }

    public void insertBefore(Node node, Node nodeToInsert) {
      if (node == null || nodeToInsert == null) {
        return;
      }
      if (node == head) {
        nodeToInsert.next = head;
        head.prev = nodeToInsert;
        head = nodeToInsert;
        return;
      }

      Node pre = node.prev;
      pre.next = nodeToInsert;
      nodeToInsert.prev = pre;
      nodeToInsert.next = node;
      node.prev = nodeToInsert;
    }

    public void insertAfter(Node node, Node nodeToInsert) {
      if (node == null || nodeToInsert == null) {
        return;
      }
      if (node == tail) {
        tail.next = nodeToInsert;
        nodeToInsert.prev = tail;
        tail = nodeToInsert;
        return;
      }
      Node next = node.next;
      node.next = nodeToInsert;
      nodeToInsert.prev = node;
      nodeToInsert.next = next;
      next.prev = nodeToInsert;
    }

    public void insertAtPosition(int position, Node nodeToInsert) {
      if (nodeToInsert == null) {
        return;
      }
      Node cur = head;
      position--;
      while (cur != null && position != 0) {
        cur = cur.next;
        position--;
      }
      if (cur == null) {
        setTail(nodeToInsert);
        return;
      }
      insertBefore(cur, nodeToInsert);
    }

    public void removeNodesWithValue(int value) {
      if (!containsNodeWithValue(value)) {
        return;
      }
      Node target = null;
      Node cur = head;
      while (cur != null) {
        if (cur.value == value) {
          target = cur;
          break;
        }
        cur = cur.next;
      }
      remove(target);
    }

    public void remove(Node node) {
      if (node == null || !containsNodeWithValue(node.value)) {
        return;
      }
      if (node == head) {
        head = head.next;
        head.prev = null;
        return;
      }
      if (node == tail) {
        tail = tail.prev;
        tail.next = null;
        return;
      }
      Node pre = node.prev;
      pre.next = node.next;
      if (node.next != null) {
        node.next.prev = pre;
      }
    }

    public boolean containsNodeWithValue(int value) {
      Node cur = head;
      while (cur != null) {
        if (cur.value == value) {
          return true;
        }
        cur = cur.next;
      }
      return false;
    }
  }

  // Do not edit the class below.
  static class Node {

    public int value;
    public Node prev;
    public Node next;

    public Node(int value) {
      this.value = value;
    }
  }


}
