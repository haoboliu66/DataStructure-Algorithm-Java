package advanced.c3.class5;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class C04_LRU {

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

            if (this.map.containsKey(key)) { //包含key,修改值, 移动Node到链表尾
                Node<K, V> node = map.get(key);
                node.value = value;
                //修改值后把node移到末尾
                linkedList.moveNodeToTail(node);

            } else {  //不包含key, 新添加节点到尾部
                //检查是否超量
                Node<K, V> newNode = new Node<>(key, value);
                if (map.size() == this.capacity) {
                    Node<K, V> oldHead = linkedList.removeHead();
                    map.remove(oldHead.key);
                }
                map.put(key, newNode);
                linkedList.add(newNode);
            }
        }
    }

    public static class Node<K, V> {

        public K key;
        public V value;
        public Node<K, V> last;
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
            node.last = tail;
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
                head.last = null;
                cur.next = null;
            } else {
                cur.last.next = cur.next;
                cur.next.last = cur.last;
            }
            tail.next = cur;
            cur.last = tail;
            tail = cur;
        }


        public Node<K, V> removeHead() {
            if (head == null) {
                return null;
            }
            Node<K, V> cur = head;
            head = head.next;
            head.last = null;
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
