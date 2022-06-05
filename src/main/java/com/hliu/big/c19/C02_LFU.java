package com.hliu.sys.c19;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class C02_LFU {

  // https://leetcode.com/problems/lfu-cache/

  /*
  ["LFUCache","put","put","get","put","get","get","put","get","get","get"]
  [[2],      [1,1],[2,2], [1],   [3,3],[2], [3],  [4,4], [1],  [3],  [4]]
   */
  @Test
  public void test1() {
    LFUCache cache = new LFUCache(2);
    cache.put(1, 1);
    cache.put(2, 2);
    cache.get(1);
    cache.put(3, 3);
    cache.get(2);
    cache.get(3);
    cache.put(4, 4);
    cache.get(1);
    cache.get(3);
    cache.get(4);
  }

  /*
  ["LFUCache","put","put","put","put","get"]
  [[2],       [3,1],[2,1],[2,2],[4,4],[2]]
   */
  @Test
  public void test2() {
    LFUCache cache = new LFUCache(2);
    cache.put(3, 1);
    cache.put(2, 1);
    cache.put(2, 2);
    cache.put(4, 4);
    cache.get(2);
  }

  /*
  ["LFUCache","put","get","put","get","get"]
  [[1],       [2,1], [2],  [3,2], [2], [3]]
   */
  @Test
  public void test3() {
    LFUCache cache = new LFUCache(1);
    cache.put(2, 1);
    cache.get(2);
    cache.put(3, 2);
    cache.get(2);
    cache.get(3);
  }


  /*
  ["LFUCache","put","put","get","put","put","get"]
  [[2],       [2,1],[2,2], [2], [1,1],[4,1], [2]]
   */
  @Test
  public void test4() {
    LFUCache cache = new LFUCache(2);
    cache.put(2, 1);
    cache.put(2, 2);
    cache.get(2);
    cache.put(1, 1);
    cache.put(4, 1);
    System.out.println(cache.get(2));
  }

  /*
  ["LFUCache","put","put","put","put","get","get","get","get","put","get","get","get","get","get"]
  [[3],[      1,1], [2,2],[3,3],[4,4], [4], [3],  [2],  [1], [5,5], [1],   [2],  [3], [4],  [5]]
   */
  @Test
  public void test5() {
    LFUCache cache = new LFUCache(3);
    cache.put(1, 1);
    cache.put(2, 2);
    cache.put(3, 3);
    cache.put(4, 4);
    System.out.println("bucket size: " + cache.bucketCountMap.size());
    System.out.println(cache.get(4));
    System.out.println(cache.get(3));
    System.out.println(cache.get(2));
    System.out.println(cache.get(1));
    System.out.println("bucket size: " + cache.bucketCountMap.size());
    cache.put(5, 5);
    System.out.println("bucket size: " + cache.bucketCountMap.size());
    System.out.println(cache.get(1));
    System.out.println(cache.get(2)); // error
    System.out.println(cache.get(3));
    System.out.println(cache.get(4));
    System.out.println(cache.get(5));
  }


  static class LFUCache {

    Bucket headBucket;
    Bucket tailBucket;
    Map<Integer, BucketNode> nodeMap;
    //        Map<BucketNode, Bucket> bucketMap;
    Map<Integer, Bucket> bucketCountMap;
    int cap;

    public LFUCache(int capacity) {
      cap = capacity;
      nodeMap = new HashMap<>();
//            bucketMap = new HashMap<>();
      bucketCountMap = new HashMap<>(); // 1 <-> Bucket
    }

    // bucketCountMap
    // nodeMap

    public int get(int key) {
      if (!nodeMap.containsKey(key)) {
        return -1;
      }
      BucketNode oldNode = nodeMap.get(key);
      int oldTimes = oldNode.time; // 老time
      Bucket oldBucket = bucketCountMap.get(oldTimes);
      int curTimes = ++oldNode.time; // 新time
      Bucket targetBucket = bucketCountMap.get(curTimes);
      // 如果我要去的目标桶是null
      if (targetBucket == null) {
        // 新建桶, 加记录
        targetBucket = new Bucket();
        bucketCountMap.put(curTimes, targetBucket);
        addBucket(targetBucket, curTimes);  //此处curTimes一定不是1
      }
      // 不管get操作是把这个节点移到一个新建的桶 还是已有的桶
      // 老的bucket中都要把他移除
      BucketNode removedNode = oldBucket.remove(oldNode); // 当前桶 删除这个词频变化的节点
      if (removedNode == null) {  // 新头是null, 说明oldBucket空了
        removeBucket(oldBucket);  // 从链表中移除oldBucket
        bucketCountMap.remove(oldTimes);  // 移除oldBucket代表的词频
      }
      // 如果不为null, 那么只删除桶内节点即可

      targetBucket.add(oldNode);  // 目标桶 加入对应词频的节点

      return oldNode.value;
    }

    public void put(int key, int value) {
      if (cap <= 0) {
        return;
      }
      if (nodeMap.containsKey(key)) {
        // 老key
        BucketNode oldNode = nodeMap.get(key);
        int oldTime = oldNode.time;
        oldNode.value = value;
        Bucket oldBucket = bucketCountMap.get(oldTime);
        int curTime = ++oldNode.time;
        // oldNode要迁移到下一个桶

        // 下一个桶有没有呢?
        Bucket targetBucket;
        if (bucketCountMap.containsKey(curTime)) {  // 有桶, 拿出来
          targetBucket = bucketCountMap.get(curTime);
        } else {  // 没桶, 新建
          targetBucket = new Bucket();
          bucketCountMap.put(curTime, targetBucket);
          addBucket(targetBucket, oldNode.time);
        }
        // 节点加入目标桶
        targetBucket.add(oldNode);

        // 节点从原桶移除
        oldBucket.remove(oldNode);
        // 如果移除后, 原桶的size变0了, 直接移除原桶
        if (oldBucket.size == 0) {
          removeBucket(oldBucket);
          bucketCountMap.remove(oldTime);
        }

      } else {
        // 新key哦
        if (nodeMap.size() == cap) { // 超限了
          // evict 头bucket里面的head节点
          Bucket oldHeadBucket = headBucket;
          BucketNode evictedNode = evict(headBucket);
          nodeMap.remove(evictedNode.key);
          if (oldHeadBucket.size == 0) {
            bucketCountMap.remove(evictedNode.time);
          }

        }
        // 新加入节点
        BucketNode newNode = new BucketNode(key, value);
        nodeMap.put(key, newNode);
        // 把新节点放入桶
        if (!bucketCountMap.containsKey(newNode.time)) {
          // 没有词频为1的桶, 新建桶
          Bucket newBucket = new Bucket();
          bucketCountMap.put(newNode.time, newBucket);
          // 连接新桶
//                    head.prev = newBucket;
//                    newBucket.next = head;
//                    head = newBucket;
          newBucket.add(newNode);
          addBucket(newBucket, 1);

        } else {
          // 有time为1的桶, 就是当前的headBucket
          headBucket.add(newNode);
        }
      }
    }

    // 删除头bucket中的head节点, 如果删除后, bucket是空, 移除bucket => headBucket指针后移
    private BucketNode evict(Bucket headBucket) {
      BucketNode evictedNode = headBucket.removeHead();
      if (headBucket.size == 0) {
        // 新头是null, 直接把这个bucket移除
        removeBucket(headBucket);
      }
      return evictedNode;
    }

    private void removeBucket(Bucket bucket) {
      if (headBucket == bucket) {
//                System.out.println("tailBucket: " + tailBucket);
//                System.out.println("headBucket: " + headBucket);
        headBucket = headBucket.next;
        if (headBucket == null) {
          return;
        }
//                System.out.println("headNextBucket: " + headBucket);
        headBucket.prev = null;
        bucket.next = null;
      } else {
//                System.out.println(bucket);
//                System.out.println(bucket.prev);
//                System.out.println(bucket.next);
        bucket.prev.next = bucket.next;
        bucket.next.prev = bucket.prev;
      }
    }

    private void addBucket(Bucket bucket, int curTime) {
      if (headBucket == null) {
        headBucket = bucket;
        tailBucket = bucket;
      } else {
        Bucket preBucket = bucketCountMap.get(curTime - 1);
        if (preBucket == null) {  // 说明现在要添加的桶是1号桶
          headBucket.prev = bucket;
          bucket.next = headBucket;
          headBucket = bucket;
          return;
        }
        Bucket nextBucket = preBucket.next;  // (pre) <-> (next)
        preBucket.next = bucket;
        bucket.prev = preBucket;    // (pre) <-> (bucket)

        bucket.next = nextBucket;  // (pre) <-> (bucket) <-> (next)
        if (nextBucket != null) {
          nextBucket.prev = bucket;
        }
        if (preBucket == tailBucket) {
          tailBucket = bucket;
        }

      }
    }
  }

  private static class Bucket {

    BucketNode head;
    BucketNode tail;
    Bucket prev;
    Bucket next;
    int size;

    // bucket中新增节点
    public void add(BucketNode node) {
      size++;
      if (head == null) {
        head = node;
        tail = node;
      } else {
        tail.next = node;
        node.prev = tail;
        tail = node;
      }
    }

    // bucket删除节点, 返回新的头BucketNode
    public BucketNode remove(BucketNode node) {
      size--;
      if (head == tail) {
        head = null;
        tail = null;
        return null;
      }
      if (node == head) {
        head = head.next;
        head.prev = null;
        node.next = null;
      } else if (node == tail) {
        tail = tail.prev;
        tail.next = null;
        node.prev = null;
      } else {
        // 普通中间节点
        node.prev.next = node.next;
        node.next.prev = node.prev;
      }
      return head;
    }

    //  删除头节点, 返回被删除的头
    public BucketNode removeHead() {
      size--;
      BucketNode tmp = head;
      if (head == tail) {
        head = null;
        tail = null;
      } else {
        head = head.next;
        head.prev = null;
        tmp.next = null;
      }
      return tmp;
    }


    @Override
    public String toString() {
      return "Bucket{" +
          "head=" + head +
          ", tail=" + tail +
          '}';
    }
  }


  private static class BucketNode {

    int key;
    int value;
    int time;
    BucketNode next;
    BucketNode prev;

    public BucketNode(int key, int value) {
      this.key = key;
      this.value = value;
      time = 1;
    }

    @Override
    public String toString() {
      return "BucketNode{" +
          "key=" + key +
          ", value=" + value +
          ", time=" + time +
          '}';
    }
  }


}
