package com.hliu.advanced.c3.class5;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class C04_LRU {

  // https://leetcode.com/problems/lru-cache/

  static class LRUCache0 {

    Map<Integer, CacheNode> map;
    LRULinkedList0 list;
    int cap;

    public LRUCache0(int capacity) {
      map = new HashMap<>();
      list = new LRULinkedList0();
      cap = capacity;
    }

    public int get(int key) {
      if (!map.containsKey(key)) {
        return -1;
      }
      CacheNode node = map.get(key);
      list.moveNodeToTail(node);
      return node.value;
    }

    public void put(int key, int value) {
      if (!map.containsKey(key)) { // 新的key
        // 容量超限了, 先evict, 再加入新节点
        if (map.size() == cap) {
          CacheNode oldHead = list.evict();
          map.remove(oldHead.key);
        }
        // 容量没超限, 直接加入新节点
        CacheNode newNode = new CacheNode(key, value);
        map.put(key, newNode);
        list.add(newNode);
      } else {
        // 老的key, 找到老的节点, 去更新值, 并把老节点移到链表末尾
        // 老的key 无关乎是否超限
        CacheNode node = map.get(key);
        node.value = value;
        list.moveNodeToTail(node);
      }
    }
  }


  static class LRULinkedList0 {

    CacheNode head;
    CacheNode tail;

    public void add(CacheNode node) {
      if (head == null) {
        head = node;
        tail = node;
      } else {
        tail.next = node;
        node.prev = tail;
        tail = node;
      }
    }

    public void moveNodeToTail(CacheNode node) {
      if (node == tail) {
        return;
      }
      if (node == head) {
        head = node.next;
        node.next = null;
        head.prev = null;
      } else {
        // node是一个非头非尾的中间节点
        node.prev.next = node.next;
        node.next.prev = node.prev;
      }
      // node移动到最后
      tail.next = node;
      node.prev = tail;
      tail = node;
    }

    public CacheNode evict() {
      CacheNode tmp = head;
      if (head == tail) {
        head = null;
        tail = null;
        return tmp;
      }
      head = head.next;
      tmp.next = null;
      head.prev = null;
      return tmp;
    }
  }


  private static class CacheNode {

    int key;
    int value;
    CacheNode next;
    CacheNode prev;

    public CacheNode(int key, int value) {
      this.key = key;
      this.value = value;
      next = null;
      prev = null;
    }

    @Override
    public String toString() {
      return "CacheNode{" +
          "key=" + key +
          ", value=" + value +
          '}';
    }
  }

  /* --------------------------------------  */

  public static class LRUCache<K, V> {

    HashMap<K, Node<K, V>> map;
    LRULinkedList<K, V> linkedList;
    int capacity;

    public LRUCache(int capacity) {
      map = new HashMap<>();
      linkedList = new LRULinkedList<>();
      this.capacity = capacity;
    }

    public V get(K key) {
      if (map.containsKey(key)) {
        Node<K, V> node = map.get(key);
        linkedList.moveNodeToTail(node);
        return node.value;
      }
      System.out.println("return -1");
      return null;
    }

    public void put(K key, V value) {
      if (map.containsKey(key)) { //包含key,修改值, 移动Node到链表尾
        Node<K, V> node = map.get(key);
        node.value = value;
        //修改值后把node移到末尾
        linkedList.moveNodeToTail(node);

      } else {  //不包含key, 新添加节点到尾部
        //检查是否超量
        Node<K, V> newNode = new Node<>(key, value);
        if (map.size() == capacity) {
          Node<K, V> oldHead = linkedList.removeHead();
          map.remove(oldHead.key);
        }
        map.put(key, newNode);
        linkedList.add(newNode);
      }
    }
  }

  private static class Node<K, V> {

    public K key;
    public V value;
    public Node<K, V> prev;
    public Node<K, V> next;

    public Node(K key, V value) {
      this.key = key;
      this.value = value;
    }
  }

  public static class LRULinkedList<K, V> {

    Node<K, V> head;
    Node<K, V> tail;

    public void add(Node<K, V> node) {
      if (node == null) {
        return;
      }
      if (head == null) {
        head = node;
        tail = node;
        return;
      }
      tail.next = node;
      node.prev = tail;
      tail = node;
    }

    public void moveNodeToTail(Node<K, V> node) {
      if (node == null) {
        return;
      }
      if (node == tail) {
        return;
      }
      Node<K, V> cur = node;
      if (cur == head) {
        head = head.next;
        head.prev = null;
        cur.next = null;
      } else {
        cur.prev.next = cur.next;
        cur.next.prev = cur.prev;
      }
      tail.next = cur;
      cur.prev = tail;
      tail = cur;
    }


    public Node<K, V> removeHead() {
      if (head == null) {
        return null;
      }
      Node<K, V> cur = head;
      head = head.next;
      head.prev = null;
      cur.next = null;

      return cur;
    }
  }


  // LRU implementation with LinkedHashMap
  public static class LRUCache1<K, V> {

    LinkedHashMap<K, V> map;
    int capacity;

    public LRUCache1(int capacity) {
      this.capacity = capacity;
      map = new LinkedHashMap<>(capacity, 0.75f, true);
    }

    public V get(K key) {
      if (map.containsKey(key)) {
        return map.get(key);
      }
      return null;
    }


    public void put(K key, V value) {
      if (map.containsKey(key)) {
        map.put(key, value);
        return;
      }
      if (map.size() == this.capacity) {
        for (K k : map.keySet()) {
          map.remove(k);
          break;
        }
      }
      map.put(key, value);
    }
  }


  public static class LRUCache2 {

    private LinkedHashMap<Integer, Integer> map;
    private final int CAPACITY;

    public LRUCache2(int capacity) {
      CAPACITY = capacity;
      map = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
        protected boolean removeEldestEntry(Map.Entry eldest) {
          return size() > CAPACITY;
        }
      };
    }

    public int get(int key) {
      return map.getOrDefault(key, -1);
    }

    public void set(int key, int value) {
      map.put(key, value);
    }
  }


  public static void main(String[] args) {
    LRUCache<Integer, Integer> cache = new LRUCache<Integer, Integer>(2);
    cache.put(1, 1);
    System.out.println();
    cache.put(2, 2);
    System.out.println();
    System.out.println(cache.get(1));
    cache.put(3, 3);
    System.out.println();
    System.out.println(cache.get(2));
    cache.put(4, 4);
    System.out.println();
    System.out.println(cache.get(1));
    System.out.println(cache.get(3));
    System.out.println(cache.get(4));
  }


}
