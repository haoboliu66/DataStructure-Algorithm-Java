package src.main.java.advanced.c2.sortedMap;

import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeMap;

public class SkipList {

    // SkipList TreeNode
    public static class SkipListNode<K extends Comparable<K>, V> {
        public K key;
        public V val;
        public ArrayList<SkipListNode<K, V>> nextNodes;

        public SkipListNode(K k, V v) {
            key = k;
            val = v;
            nextNodes = new ArrayList<SkipListNode<K, V>>();
        }

        /**
         * determine if the current key is less than the otherKey,
         * the key of headNode, which is null is the minimum key in the skipList
         *
         * @param otherKey
         * @return
         */
        public boolean isKeyLess(K otherKey) {
            //  otherKey == null -> false
            return otherKey != null && (key == null || key.compareTo(otherKey) < 0);
        }

        /**
         * determine if the current key is equal to the otherKey,
         *
         * @param otherKey
         * @return
         */
        public boolean isKeyEqual(K otherKey) {
            return (key == null && otherKey == null)
                    || (key != null && otherKey != null && key.compareTo(otherKey) == 0);
        }
    }

    /**
     * skipList implementation
     *
     * @param <K>
     * @param <V>
     */
    public static class SkipListMap<K extends Comparable<K>, V> {
        private static final double PROBABILITY = 0.5; // probability for determining level in a node
        private SkipListNode<K, V> head;  // headNode of skipList
        private int size;
        private int maxLevel; // maximum level of skipList

        public SkipListMap() {
            head = new SkipListNode<>(null, null);
            head.nextNodes.add(null);
            size = 0;
            maxLevel = 0;
        }

        /**
         * find the most right node that is less than the key at level 0
         *
         * @param key
         * @return
         */
        private SkipListNode<K, V> mostRightLessNodeInTree(K key) {
            if (key == null) {
                return null;
            }
            int level = maxLevel;
            SkipListNode<K, V> cur = head;
            while (level >= 0) { // keep going down to the next lower level
                //  cur  level  -> level - 1
                cur = mostRightLessNodeInLevel(key, cur, level--);
            }
            return cur;
        }

        /**
         * find the most right node with key that is less than key at level
         *
         * @param key
         * @param cur
         * @param level
         * @return
         */
        private SkipListNode<K, V> mostRightLessNodeInLevel(K key, SkipListNode<K, V> cur, int level) {
            SkipListNode<K, V> next = cur.nextNodes.get(level);
            while (next != null && next.isKeyLess(key)) {
                cur = next;
                next = cur.nextNodes.get(level);
            }
            return cur;
        }

        /**
         * check if skipList contains such a key
         *
         * @param key
         * @return
         */
        public boolean containsKey(K key) {
            if (key == null) {
                return false;
            }
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null && next.isKeyEqual(key);
        }

        /**
         * Return the size of the skipList
         *
         * @return
         */
        public int size() {
            return size;
        }

        /**
         * put new key-value pair or modify existing key-value pair in the skipList
         *
         * @param key
         * @param value
         */
        public void put(K key, V value) {
            if (key == null) {
                return;
            }
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> find = less.nextNodes.get(0);
            // key already exists
            if (find != null && find.isKeyEqual(key)) {
                find.val = value;
            } else {
                size++;
                int newNodeLevel = 0;
                // keep generating level if Math.random() is less than 0.5
                while (Math.random() < PROBABILITY) {
                    newNodeLevel++;
                }
                // newNodeLevel
                while (newNodeLevel > maxLevel) {
                    head.nextNodes.add(null);
                    maxLevel++;
                }
                SkipListNode<K, V> newNode = new SkipListNode<>(key, value);
                for (int i = 0; i <= newNodeLevel; i++) {
                    newNode.nextNodes.add(null);
                }
                int level = maxLevel;
                SkipListNode<K, V> pre = head;
                while (level >= 0) {
                    pre = mostRightLessNodeInLevel(key, pre, level);
                    if (level <= newNodeLevel) {
                        newNode.nextNodes.set(level, pre.nextNodes.get(level));
                        pre.nextNodes.set(level, newNode);
                    }
                    level--;
                }
            }
        }

        /**
         * get value by key in the skipList
         *
         * @param key
         * @return
         */
        public V get(K key) {
            if (key == null) {
                return null;
            }
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null && next.isKeyEqual(key) ? next.val : null;
        }

        /**
         * remove a key-value pair from the skipList
         *
         * @param key
         */
        public void remove(K key) {
            if (containsKey(key)) {
                size--;
                int level = maxLevel;
                SkipListNode<K, V> pre = head;
                while (level >= 0) {
                    pre = mostRightLessNodeInLevel(key, pre, level);
                    SkipListNode<K, V> next = pre.nextNodes.get(level);

                    if (next != null && next.isKeyEqual(key)) {
                        // level : pre -> next(key) -> ...
                        pre.nextNodes.set(level, next.nextNodes.get(level));
                    }
                    // only one node remaining at level, need to shrink the level of the skipList
                    if (level != 0 && pre == head && pre.nextNodes.get(level) == null) {
                        head.nextNodes.remove(level);
                        maxLevel--;
                    }
                    level--;
                }
            }
        }

        /**
         * Returns the first key(minimum key) in the skipList
         *
         * @return
         */
        public K firstKey() {
            return head.nextNodes.get(0) != null ? head.nextNodes.get(0).key : null;
        }

        /**
         * Return the last key(maximum key) in the skipList
         *
         * @return
         */
        public K lastKey() {
            int level = maxLevel;
            SkipListNode<K, V> cur = head;
            while (level >= 0) {
                SkipListNode<K, V> next = cur.nextNodes.get(level);
                while (next != null) {
                    cur = next;
                    next = cur.nextNodes.get(level);
                }
                level--;
            }
            return cur.key;
        }

        public K ceillingKey(K key) {
            if (key == null) {
                return null;
            }
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null ? next.key : null;
        }

        public K floorKey(K key) {
            if (key == null) {
                return null;
            }
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null && next.isKeyEqual(key) ? next.key : less.key;
        }

    }

    // for test
    public static void printAll(SkipListMap<Integer, Integer> obj) {
        for (int i = obj.maxLevel; i >= 0; i--) {
            System.out.print("Level " + i + " : ");
            SkipListNode<Integer, Integer> cur = obj.head;
            while (cur.nextNodes.get(i) != null) {
                SkipListNode<Integer, Integer> next = cur.nextNodes.get(i);
                System.out.print("(" + next.key + " , " + next.val + ") ");
                cur = next;
            }
            System.out.println();
        }
    }



    @Test
    public void testTreeMap() {
        TreeMap<Integer, Integer> skipList = new TreeMap<>();
        int[] nodes = {200_000, 300_000, 400_000, 500_000, 600_000, 700_000, 800_000, 900_000, 1_000_000};
        int[][] arrs = new int[nodes.length][];
        for (int i = 0; i < arrs.length; i++) {
            arrs[i] = generateRandomArray(nodes[i], nodes[i] * 2);
        }

        String[][] times = new String[3][];
        for (int i = 0; i < times.length; i++) {
            times[i] = new String[nodes.length];
        }
        TreeMap<Integer, Integer>[] treeMaps = new TreeMap[nodes.length];
        for (int i = 0; i < treeMaps.length; i++) {
            treeMaps[i] = new TreeMap<>();
            TreeMap<Integer, Integer> each = treeMaps[i];
            int[] nodeArray = arrs[i];
            for (int j = 0; j < nodeArray.length; j++) {
                each.put(nodeArray[j], j);
            }
        }
        // test get
        String[] get = times[0];
        for (int i = 0; i < treeMaps.length; i++) {
            TreeMap<Integer, Integer> each = treeMaps[i];
            int withinGroupIndex = (int) (Math.random() * arrs[i].length);
            // time of get
            long start = System.nanoTime();
            for (int r = 0; r < 10000; r++) {
                withinGroupIndex = (int) (Math.random() * arrs[i].length);
                each.get(arrs[i][withinGroupIndex]);
            }
            long end = System.nanoTime();
            String res = "size: " + each.size() + " time:" + (end - start);
            writeRecord("get_skiplist", res);
            get[i] = String.valueOf(end - start);
        }
        System.out.println(Arrays.toString(get));
        /* ------------------------------------------------------------   */
        // test put
        String[] put = times[1];
        for (int i = 0; i < treeMaps.length; i++) {
            TreeMap<Integer, Integer> each = treeMaps[i];
            int withinGroupIndex = (int) (Math.random() * arrs[i].length);
            // time of get
            long start = System.nanoTime();
            for (int r = 0; r < 10000; r++) {
                withinGroupIndex = (int) (Math.random() * arrs[i].length);
                each.put(arrs[i][withinGroupIndex], i);
            }
            long end = System.nanoTime();
            String res = "size: " + each.size() + " time:" + String.valueOf(end - start);
            writeRecord("put_skiplist.txt", res);
            put[i] = String.valueOf(end - start);
        }
        System.out.println(Arrays.toString(put));
        /* ------------------------------------------------------------   */
        // test remove
        String[] remove = times[2];
        for (int i = 0; i < treeMaps.length; i++) {
            TreeMap<Integer, Integer> each = treeMaps[i];
            int withinGroupIndex = (int) (Math.random() * arrs[i].length);
            // time of get
            long start = System.nanoTime();
            for (int r = 0; r < 10000; r++) {
                withinGroupIndex = (int) (Math.random() * arrs[i].length);
                each.remove(arrs[i][withinGroupIndex], i);
            }
            long end = System.nanoTime();
            String res = "size: " + each.size() + " time:" + String.valueOf(end - start);
            writeRecord("remove_skiplist", res);
            remove[i] = String.valueOf(end - start);
        }
        System.out.println(Arrays.toString(remove));
    }


    @Test
    public void testSkipList() {
//        SkipListMap<Integer, Integer> skipList = new SkipListMap<>();
        int[] nodes = {200_000, 300_000, 400_000, 500_000, 600_000, 700_000, 800_000, 900_000, 1_000_000};
        int[][] arrs = new int[nodes.length][];
        for (int i = 0; i < arrs.length; i++) {
            arrs[i] = generateRandomArray(nodes[i], nodes[i] * 2);
        }
        String[][] times = new String[3][];
        for (int i = 0; i < times.length; i++) {
            times[i] = new String[nodes.length];
        }
        SkipListMap<Integer, Integer>[] treeMaps = new SkipListMap[nodes.length];
        for (int i = 0; i < treeMaps.length; i++) {
            treeMaps[i] = new SkipListMap<>();
            SkipListMap<Integer, Integer> each = treeMaps[i];
            int[] nodeArray = arrs[i];
            for (int j = 0; j < nodeArray.length; j++) {
                each.put(nodeArray[j], j);
            }
        }

        String[] get = times[0];
        for (int i = 0; i < treeMaps.length; i++) {
            SkipListMap<Integer, Integer> each = treeMaps[i];
            int withinGroupIndex = (int) (Math.random() * arrs[i].length);
            // time of get
            long start = System.nanoTime();
            for (int r = 0; r < 10000; r++) {
                withinGroupIndex = (int) (Math.random() * arrs[i].length);
                each.get(arrs[i][withinGroupIndex]);
            }
            long end = System.nanoTime();
            String res = "size: " + each.size() + " time:" + (end - start);
            writeRecord("get_skiplist", res);
            get[i] = String.valueOf(end - start);
        }
        System.out.println(Arrays.toString(get));
        /* ------------------------------------------------------------   */
        String[] put = times[1];
        for (int i = 0; i < treeMaps.length; i++) {
            SkipListMap<Integer, Integer> each = treeMaps[i];
            int withinGroupIndex = (int) (Math.random() * arrs[i].length);
            // time of get
            long start = System.nanoTime();
            for (int r = 0; r < 10000; r++) {
                withinGroupIndex = (int) (Math.random() * arrs[i].length);
                each.put(arrs[i][withinGroupIndex], i);
            }
            long end = System.nanoTime();
            String res = "size: " + each.size() + " time:" + String.valueOf(end - start);
            writeRecord("put_skiplist.txt", res);
            put[i] = String.valueOf(end - start);
        }
        System.out.println(Arrays.toString(put));
        /* ------------------------------------------------------------   */
        String[] remove = times[2];
        for (int i = 0; i < treeMaps.length; i++) {
            SkipListMap<Integer, Integer> each = treeMaps[i];
            int withinGroupIndex = (int) (Math.random() * arrs[i].length);
            // time of get
            long start = System.nanoTime();
            for (int r = 0; r < 10000; r++) {
                withinGroupIndex = (int) (Math.random() * arrs[i].length);
                each.remove(arrs[i][withinGroupIndex]);
            }
            long end = System.nanoTime();
            String res = "size: " + each.size() + " time:" + String.valueOf(end - start);
            writeRecord("remove_skiplist", res);
            remove[i] = String.valueOf(end - start);
        }
        System.out.println(Arrays.toString(remove));
    }


    // generate sorted random array without duplicate element
    public static int[] generateRandomArray(int maxLen, int maxVal) {
        HashSet<Integer> set = new HashSet<>();
        int[] arr = new int[maxLen];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * maxVal);
            while (set.contains(arr[i])) {
                arr[i] = (int) (Math.random() * maxVal);
            }
            set.add(arr[i]);
        }
        Arrays.sort(arr);
        return arr;
    }

    public void writeRecord(String fileName, String res) {
        res += "\n";
        File file = new File(fileName);
        System.out.println(file.exists());
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file, true);
            fos.write(res.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        SkipListMap<Integer, Integer> skipList = new SkipListMap<>();
//        int[] nodes = {20000, 40000, 60000, 80000, 100000, 120000};
        int[] nodes = {200_000, 300_000, 400_000, 500_000, 600_000, 700_000, 800_000, 900_000, 1_000_000};
        int[][] arrs = new int[nodes.length][];
        for (int i = 0; i < arrs.length; i++) {
            arrs[i] = generateRandomArray(nodes[i], nodes[i] * 2);
        }
        long[][] times = new long[3][];
        for (int i = 0; i < times.length; i++) {
            times[i] = new long[nodes.length];
        }
        SkipListMap<Integer, Integer>[] skipLists = new SkipListMap[nodes.length];
        for (int i = 0; i < skipLists.length; i++) {
            skipLists[i] = new SkipListMap<>();
            SkipListMap<Integer, Integer> each = skipLists[i];
            int[] nodeArray = arrs[i];
            for (int j = 0; j < nodeArray.length; j++) {
                each.put(nodeArray[j], j);
            }
        }

        // verify size of each skipList, should be {20000, 40000, 60000, 80000, 100000, 120000}
//        for(SkipListMap<Integer, Integer> s : skipLists){
//			System.out.println(s.size());
//		}

        for (int group = 0; group < times.length; ) {
            long[] get = times[group++];
            for (int i = 0; i < skipLists.length; i++) {
                SkipListMap<Integer, Integer> each = skipLists[i];
                int withinGroupIndex = (int) (Math.random() * arrs[i].length);
                // time of get
                long start = System.nanoTime();
                each.get(arrs[i][withinGroupIndex]);
                long end = System.nanoTime();
                get[i] = end - start;
            }
            long[] put = times[group++];
            for (int i = 0; i < skipLists.length; i++) {
                SkipListMap<Integer, Integer> each = skipLists[i];
                int withinGroupIndex = (int) (Math.random() * arrs[i].length);
                // time of put
                long start = System.nanoTime();
                each.get(arrs[i][withinGroupIndex]);
                long end = System.nanoTime();
                put[i] = end - start;
            }

            long[] remove = times[group++];
            for (int i = 0; i < skipLists.length; i++) {
                SkipListMap<Integer, Integer> each = skipLists[i];
                int withinGroupIndex = (int) (Math.random() * arrs[i].length);
                // time of remove
                long start = System.nanoTime();
                each.get(arrs[i][withinGroupIndex]);
                long end = System.nanoTime();
                remove[i] = end - start;
            }
        }

        // print time
        System.out.println("get time for each group: ");
        System.out.println(Arrays.toString(times[0]));
        System.out.println();
        System.out.println("put time for each group: ");
        System.out.println(Arrays.toString(times[1]));
        System.out.println();
        System.out.println("remove time for each group: ");
        System.out.println(Arrays.toString(times[2]));

    }

}
