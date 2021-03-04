package advanced.c5.class1;

import java.util.HashMap;

public class C03_LFU {
    /*
    460. LFU Cache
     */

    //双向链表的Node
    private static class Node {
        int key;
        int value;
        int times;
        Node prev;
        Node next;

        public Node(int key, int value, int times) {
            this.key = key;
            this.value = value;
            this.times = times;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    ", times=" + times +
                    '}';
        }
    }

    //双向链表
    public static class NodeList {
        Node head;
        Node tail;
        NodeList last;
        NodeList next;

        public NodeList() {
        }

        public NodeList(Node node) {
            head = node;
            tail = node;
        }

        public boolean isEmpty() {
            return head == null;
        }

        public void addNodeFromTail(Node node) {
            if (isEmpty()) {
                head = node;
                tail = node;
                return;
            }

            tail.next = node;
            node.prev = tail;
            tail = node;
        }

        public void deleteNode(Node node) {
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                if (node == head) {
                    head = node.next;
                    head.prev = null;
                } else if (node == tail) {
                    tail = node.prev;
                    tail.next = null;
                } else {
                    node.prev.next = node.next;
                    node.next.prev = node.prev;
                }
            }
            node.prev = null;
            node.next = null;
        }
    }

    // 二维双向链表
    public static class LFUCache {
        private int capacity; // 缓存的大小限制，即K
        private int size; // 缓存目前有多少个节点
        private HashMap<Integer, Node> nodeMap;
        private HashMap<Node, NodeList> listMap;
        private NodeList headList; // 整个结构中位于最左的桶, times统计为1的Node


        @Override
        public String toString() {
            return "LFUCache{" +
                    "nodeMap=" + nodeMap +
                    '}';
        }

        public LFUCache(int capacity) {
            this.capacity = capacity;
            size = 0;
            nodeMap = new HashMap<>();
            listMap = new HashMap<>();
            headList = new NodeList();
        }


        public void put(int key, int value) {
            // 已存在的key
            if (nodeMap.containsKey(key)) {
                Node curNode = nodeMap.get(key);
                curNode.value = value;
                curNode.times++;

                //time++后要将当前Node向后面的桶移动
                NodeList curList = listMap.get(curNode);
                curList.deleteNode(curNode);

                if (curList.next == null) {
                    NodeList newList = new NodeList(curNode);
                    curList.next = newList;
                    newList.last = curList;
                } else { //判定桶所存放的次数是否等于当前的times
                    if (curList.next.head.times == curNode.times) {
                        //times次数对上了, 直接加入尾部
                        curList.next.addNodeFromTail(curNode);
                    } else {  //times必然是小于桶内的times值, 需要新建桶, 并连接
                        NodeList newNodeList = new NodeList(curNode);
                        newNodeList.next = curList.next;
                        newNodeList.last = curList;
                        curList.next.last = newNodeList;
                        curList.next = newNodeList;
                    }
                }
                return;
            }
            // 是新的key进来了
            if (size == capacity) {
                //检查容量满了, 执行LFU替换, 取出最左侧第二个链表桶中的第一个Node
                if (!headList.isEmpty()) {
                    Node targetNode = headList.head;
                    headList.deleteNode(targetNode);
                    nodeMap.remove(targetNode.key);
                    listMap.remove(targetNode);
                } else {
                    NodeList targetList = headList.next;
                    Node removedNode = targetList.head;
                    targetList.deleteNode(removedNode);
                    nodeMap.remove(removedNode.key);
                    listMap.remove(removedNode);
                    //如果移除一个node之后链表边空了, 那就直接移除整个链表
                    if (targetList.isEmpty()) {
                        headList.next = targetList.next;
                        if (targetList.next != null) {
                            targetList.next.last = headList;
                        }
                        targetList.last = null;
                        targetList.next = null;
                    }
                }
                size--;
            }
            // 容量没满, 新增节点至最左侧链表桶headList
            Node node = new Node(key, value, 1);
            headList.addNodeFromTail(node);
            nodeMap.put(key, node);
            listMap.put(node, headList);
            size++;
        }

        public int get(int key) {
            int res = -1;
            if (nodeMap.containsKey(key)) {
                Node curNode = nodeMap.get(key);
                res = curNode.value;

                curNode.times++;

                //time++后要将当前Node向后面的桶移动
                NodeList curList = listMap.get(curNode);
                curList.deleteNode(curNode);

                if (curList.next == null) {
                    NodeList newList = new NodeList(curNode);
                    curList.next = newList;
                    newList.last = curList;
                } else { //判定桶所存放的次数是否等于当前的times
                    if (curList.next.head.times == curNode.times) {
                        //times次数对上了, 直接加入尾部
                        curList.next.addNodeFromTail(curNode);
                    } else {  //times必然是小于桶内的times值, 需要新建桶, 并连接
                        NodeList newNodeList = new NodeList(curNode);
                        newNodeList.next = curList.next;
                        newNodeList.last = curList;
                        curList.next.last = newNodeList;
                        curList.next = newNodeList;
                    }
                }

                //判断移除节点后是否为空
                if (curList != headList && curList.isEmpty()) {
                    curList.last.next = curList.last;
                    curList.next.last = curList.last;
                    curList.last = null;
                    curList.next = null;
                }
            }


            return res;
        }


    }

    public static void main(String[] args) {
        int capacity = 2;
        LFUCache cache = new LFUCache(capacity);
        cache.put(3, 1);
        System.out.println(cache);
        cache.put(2, 1);
        System.out.println(cache);
        cache.put(2, 2);
        System.out.println(cache);
        cache.put(4, 4);
        System.out.println(cache.get(2));

    }


}
