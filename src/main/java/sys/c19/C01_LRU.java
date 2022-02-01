package src.main.java.sys.c19;

import java.util.HashMap;
import java.util.Map;

public class C01_LRU {

    static class LRUCache {
        Map<Integer, CacheNode> map;
        LRULinkedList list;
        int cap;

        public LRUCache(int capacity) {
            map = new HashMap<>();
            list = new LRULinkedList();
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


    static class LRULinkedList {
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


}
