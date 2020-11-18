package advanced.c2.sortedMap.skipList;

import java.util.ArrayList;
import java.util.List;


public class SkipList {

    public static class SkipListNode<K extends Comparable<K>, V> {
        public K key;
        public V value;
        public ArrayList<SkipListNode<K, V>> nextNodes;

        public SkipListNode(K key, V value) {
            this.key = key;
            this.value = value;
            nextNodes = new ArrayList<>();
        }

        //判断this.key是否小于otherKey
        public boolean isKeyLess(K otherKey) {
            // key == null的情况是考虑全局最左侧节点的key都是null, 为全局最小
            return otherKey != null && (key == null || key.compareTo(otherKey) < 0);
        }

        public boolean isKeyEqual(K otherKey) {
            return otherKey != null && (key.compareTo(otherKey) == 0);
        }
    }


    public static class SkipListMap<K extends Comparable<K>, V> {
        private static final double PROBABILITY = 0.5;
        private SkipListNode<K, V> head;
        private int size;
        private int maxLevel;

        public SkipListMap() {
            head = new SkipListNode<K, V>(null, null);
            head.nextNodes.add(null); // 最底层是0层
            size = 0;
            maxLevel = 0;
        }

        // 从maxLevel开始，一路找下去，
        // 找到第0层的小于key的最右节点
        private SkipListNode<K, V> mostRightLessNodeInTree(K key) {
            if (key == null) {
                return null;
            }
            SkipListNode<K, V> cur = head;
            int level = maxLevel;
            while (level >= 0) {
                //层数逐渐递减, 在每层中取到当前层的比key小的最右节点, 赋值给cur, 循环重新调用继续向下层走
                cur = mostRightLessNodeInLevel(key, cur, level--);
                System.out.println(cur.key + " " + cur.value);
//                level--;
            }
            //出循环, cur已经拿到了0层的小于key的最右节点
            return cur;
        }

        //现在来到的节点是cur，来到了cur的level层, 返回level层上比key小的最右节点
        private SkipListNode<K, V> mostRightLessNodeInLevel(K key, SkipListNode<K, V> cur, int level) {
            SkipListNode<K, V> next = cur.nextNodes.get(level); //level层上cur的下一个节点为next
            //当next没到最后, 且next的值比key小, 在当前层就还能向右走
            while (next != null && next.isKeyLess(key)) { //在一层上的本质就是单链表遍历
                cur = next;
                next = cur.nextNodes.get(level); // next在当前level向右走
            }
            //出循环后, cur的值就是当前level层中比key小的最右节点
            return cur;
        }

        public boolean containsKey(K key) {
            if (key == null) {
                throw new RuntimeException("invalid paramter");
            }
            //less是0层的小于key的最右节点
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            //取到小于key的最右节点的下一个, 跟key比较
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null && next.isKeyEqual(key);
        }

        public V get(K key) {
            if (key == null) {
                return null;
            }
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null && next.isKeyEqual(key) ? next.value : null;
        }


        public void put(K key, V value) {
            if (key == null) {
                return;
            }
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            if (next != null && next.isKeyEqual(key)) {
                // 找到了对应的key, 直接修改value
                next.value = value;
            } else {
                //没找到对应的key, 要增加节点
                size++;
                /*
                1.新建节点
                2.随机层数, 并添加相应层数的指针
                3.看是否需要更新head节点的指针
                4.将新节点有高层到底层插入相应的位置
                 */
                SkipListNode<K, V> newNode = new SkipListNode<>(key, value);
                int newNodeLevel = 0;
                while (Math.random() < PROBABILITY) {
                    newNodeLevel++;
                    newNode.nextNodes.add(null);
                    if (newNodeLevel > maxLevel) {
                        maxLevel++;
                        head.nextNodes.add(null);
                    }
                }
                //开始由高到低进行newNode插入
                int level = maxLevel;
                SkipListNode<K, V> pre = head;
                while (level >= 0) {
                    pre = mostRightLessNodeInLevel(key, pre, level);
                    if (level <= newNodeLevel) {
                        //只有当前层数是newNode有的层数时, 才把newNode插入到该层
                        newNode.nextNodes.set(level, pre.nextNodes.get(level));
                        pre.nextNodes.set(level, newNode);
                    }
                    level--;
                }
            }
        }

        public void remove(K key) {
            if (!containsKey(key)) {
                throw new RuntimeException("key not exist");
            }
            size--;
            int level = maxLevel;
            SkipListNode<K, V> pre = head;
            while (level >= 0) {
                pre = mostRightLessNodeInLevel(key, pre, level);
                SkipListNode<K, V> next = pre.nextNodes.get(level);
                //如果level层除了head以外只有一个节点, next是可能为null的(特别是在高层)
                if (next != null && next.isKeyEqual(key)) {
                    pre.nextNodes.set(level, next.nextNodes.get(level));
                }
                //考虑缩层问题: 当level层仅剩下最左侧初始节点(可能是唯一一个节点被删除了), 该层可以进行缩减
                if (level != 0 && pre == head && pre.nextNodes.get(level) == null) {
                    head.nextNodes.remove(level); //移除当前level的指针
                    maxLevel--;
                }
                level--;
            }
        }

        public K firstKey() {
            return head.nextNodes.get(0) != null ? head.nextNodes.get(0).key : null;
        }


    }


    public static void main(String[] args) {
        SkipListMap<Integer, Integer> skipList = new SkipListMap<>();
        skipList.put(5, 1);
        skipList.put(8, 2);
        skipList.put(3, 3);
        System.out.println(skipList.get(5));

    }


}
