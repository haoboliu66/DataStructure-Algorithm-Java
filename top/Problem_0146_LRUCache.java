package top;

import advanced.c3.class5.C04_LRU;

import java.util.HashMap;

public class Problem_0146_LRUCache {

    MyLRUCache<Integer, Integer> cache;

    public Problem_0146_LRUCache(int capacity) {
        cache = new MyLRUCache<Integer, Integer>(capacity);
    }

    public int get(int key) {
        Integer res = cache.get(key);
        return res != null ? res : -1;
    }

    public void put(int key, int value) {
        cache.put(key, value);
    }


    public static class Node<K, V> {
        K key;
        V value;
        Node<K, V> before;
        Node<K, V> after;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    public static class NodeLinkedList<K, V> {
        Node<K, V> head;
        Node<K, V> tail;

        public void addNode(Node<K, V> node) {
            if (head == null) {
                head = node;
                tail = node;
            } else {
                tail.after = node;
                node.before = tail;
                tail = node;
            }
        }

        // remove node to the tail
        public void moveNodeToTail(Node<K, V> node) {
            if (node == tail) {
                return;
            }

            if (node == head) {
                head = node.after;
                head.before = null;
                node.after = null;
            } else {
                node.before.after = node.after;
                node.after.before = node.before;
                node.before = null;
                node.after = null;
            }
            tail.after = node;
            node.before = tail;
            tail = node;
        }


        // remove head when map.size() reach capacity
        public Node<K, V> removeHead() {
            if (head == null) {
                return null;
            }
            Node<K, V> res = head;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.after;
                head.before = null;
            }

            return res;
        }


    }


    public static class MyLRUCache<K, V> {
        HashMap<K, Node<K, V>> nodeMap;
        NodeLinkedList<K, V> list;
        final int capacity;

        public MyLRUCache(int cap) {
            nodeMap = new HashMap<>();
            list = new NodeLinkedList<>();
            capacity = cap;
        }

        public V get(K key) {
            if (!nodeMap.containsKey(key)) {
                return null;
            }
            // key exists
            Node<K, V> node = nodeMap.get(key);
            list.moveNodeToTail(node);
            System.out.println("tail ===   " + list.tail);
            return node.value;
        }

        public void put(K key, V value) {
            if (nodeMap.containsKey(key)) {
                Node<K, V> node = nodeMap.get(key);
                list.moveNodeToTail(node);
                node.value = value;
            } else {
                // size reaches capacity, remove head of list
                if (nodeMap.size() == capacity) {
                    Node<K, V> removed = list.removeHead();
                    nodeMap.remove(removed.key);
                }
                Node<K, V> newNode = new Node<>(key, value);
                list.addNode(newNode);
                nodeMap.put(key, newNode);
            }
        }

    }

    public static void main(String[] args) {
//        ["LRUCache","put","put","get","put","get","put","get","get","get"]

//         [[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]

        Problem_0146_LRUCache cache = new Problem_0146_LRUCache(2);
        cache.put(1, 1);
        System.out.println("--");
        cache.put(2, 2);
        System.out.println("--");
        System.out.println(cache.get(1));
        cache.put(3, 3);
        System.out.println("--");
        System.out.println(cache.get(2));
        cache.put(4, 4);
        System.out.println("--");
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));


    }


}
