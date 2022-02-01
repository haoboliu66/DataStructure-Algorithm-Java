package src.main.java.advanced.c3.class3;

import java.util.*;


// 相似题  lintCode测试链接: https://www.lintcode.com/problem/top-k-frequent-words-ii/
/*
    请实现如下结构：
    TopRecord{
    public TopRecord(int K)  :  构造时事先指定好K的大小，构造后就固定不变了
    public  void add(String str)  :   向该结构中加入一个字符串，可以重复加入
    public  List<String> src.main.java.advanced.top() : 返回之前加入的所有字符串中，词频最大的K个
    }
    要求：
    add方法，复杂度O(log K);
    top方法，复杂度O(K)
 */
public class C07_TopKTimesRealTime {

    public static class Node {
        public String str;
        public int times;

        public Node(String str, int times) {
            this.str = str;
            this.times = times;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "str='" + str + '\'' +
                    ", times=" + times +
                    '}';
        }

//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//
//            TreeNode node = (TreeNode) o;
//
//            if (times != node.times) return false;
//            return str != null ? str.equals(node.str) : node.str == null;
//        }
//
//        @Override
//        public int hashCode() {
//            int result = str != null ? str.hashCode() : 0;
//            result = 31 * result + times;
//            return result;
//        }
    }

    private static class NodeComparator implements Comparator<Node> {
        public int compare(Node n1, Node n2) {
            return n2.times - n1.times;
        }
    }

    public static class TopKRecordHeap {
        Node[] heap;
        int heapSize;
        int index;
        Map<Node, Integer> heapIndexMap;
        Map<String, Node> stringNodeMap;
        Comparator<Node> comparator;

        public TopKRecordHeap(int k, Comparator<Node> comp) {
            index = 0;
            heapSize = k;
            heap = new Node[heapSize];
            stringNodeMap = new HashMap<>();
            heapIndexMap = new HashMap<>();
            comparator = comp;
        }

        public boolean isFull() {
            return index == heapSize;
        }

        public boolean isEmpty() {
            return index == 0;
        }

        public void add(String s) {
            // new str comes
            if (!stringNodeMap.containsKey(s)) {
                if (isFull()) {
                    throw new RuntimeException("Heap is Full");
                }
                Node newNode = new Node(s, 1);
                heap[index] = newNode;
                stringNodeMap.put(s, newNode);
                heapIndexMap.put(newNode, index);
                heapInsert(heap, index++);
            } else {
                // old str comes
                Node curNode = stringNodeMap.get(s);
                curNode.times++;
                Integer curIndex = heapIndexMap.get(curNode);
                // curNode will possibly move up
                heapInsert(heap, curIndex);
            }
        }

        public List<String> top() {
            List<String> res = new ArrayList<>();
            for (int i = 0; i < index; i++) {
                res.add(heap[i].str);
            }
            return res;
        }

        private Node pop() {
            if (isEmpty()) throw new RuntimeException("Heap is Empty");
            Node res = heap[0];
            swap(heap, 0, --index);
            heapify(heap, 0);
            return res;
        }

        private void heapify(Node[] heap, int i) {
            int leftChild = i * 2 + 1;
            int greater = leftChild + 1 < heapSize &&
                    comparator.compare(heap[leftChild], heap[leftChild + 1]) > 0 ? leftChild + 1 : leftChild;
            while (leftChild < heapSize && comparator.compare(heap[i], heap[greater]) > 0) {
                swap(heap, i, greater);
                i = greater;
                if (i * 2 >= heapSize) break;
                greater = leftChild + 1 < heapSize &&
                        comparator.compare(heap[leftChild], heap[leftChild + 1]) < 0 ? leftChild + 1 : leftChild;
            }
        }

        private void heapInsert(Node[] heap, int index) {
            int parent = (index - 1) / 2;
            // [index] > [parent]
            while (comparator.compare(heap[parent], heap[index]) > 0) {
                swap(heap, index, parent);
                index = parent;
                parent = (index - 1) / 2;
            }
        }

        private void swap(Node[] heap, int index1, int index2) {
            Node node1 = heap[index1];
            Node node2 = heap[index2];
            // swap index record in Map
            heapIndexMap.put(node2, index1);
            heapIndexMap.put(node1, index2);
            // swap node in heap[]
            heap[index1] = node2;
            heap[index2] = node1;
        }

        public String toString() {
            return Arrays.toString(heap);
        }

    }


    public static class TopKRecord {
        private Node[] heap;
        private int heapSize;
        // string -> TreeNode(times)
        private HashMap<String, Node> strNodeMap;
        private HashMap<Node, Integer> nodeIndexMap;

        public TopKRecord(int K) {
            heap = new Node[K];
            heapSize = 0;
            strNodeMap = new HashMap<String, Node>();
            nodeIndexMap = new HashMap<Node, Integer>();
        }

        // str用户现在给我的
        public void add(String str) {
            Node curNode = null;
            int preIndex = -1; // str之前在堆上的位置
            // 查词频表，看看有没有关于这个str的记录
            if (!strNodeMap.containsKey(str)) { // str之前没进来过
                curNode = new Node(str, 1);
                strNodeMap.put(str, curNode);
                nodeIndexMap.put(curNode, -1);
            } else { // str之前进来过
                curNode = strNodeMap.get(str);
                curNode.times++;
                preIndex = nodeIndexMap.get(curNode);
            }

            // 词频表修改完毕，
            if (preIndex == -1) { // 不在堆上
                if (heapSize == heap.length) { // 堆满了
                    if (heap[0].times < curNode.times) {
                        nodeIndexMap.put(heap[0], -1);
                        nodeIndexMap.put(curNode, 0);
                        heap[0] = curNode;
                        heapify(0, heapSize);
                    }
                } else {// 堆没有满
                    nodeIndexMap.put(curNode, heapSize);
                    heap[heapSize] = curNode;
                    heapInsert(heapSize++);
                }
            } else { // str已经在堆上了
                heapify(preIndex, heapSize);
            }
        }

        public void printTopK() {
            System.out.println("TOP: ");
            for (int i = 0; i != heap.length; i++) {
                if (heap[i] == null) {
                    break;
                }
                System.out.print("Str: " + heap[i].str);
                System.out.println(" Times: " + heap[i].times);
            }
        }

        private void heapInsert(int index) {
            while (index != 0) {
                int parent = (index - 1) / 2;
                if (heap[index].times < heap[parent].times) {
                    swap(parent, index);
                    index = parent;
                } else {
                    break;
                }
            }
        }

        private void heapify(int index, int heapSize) {
            int l = index * 2 + 1;
            int r = index * 2 + 2;
            int smallest = index;
            while (l < heapSize) {
                if (heap[l].times < heap[index].times) {
                    smallest = l;
                }
                if (r < heapSize && heap[r].times < heap[smallest].times) {
                    smallest = r;
                }
                if (smallest != index) {
                    swap(smallest, index);
                } else {
                    break;
                }
                index = smallest;
                l = index * 2 + 1;
                r = index * 2 + 2;
            }
        }

        private void swap(int index1, int index2) {
            nodeIndexMap.put(heap[index1], index2);
            nodeIndexMap.put(heap[index2], index1);
            Node tmp = heap[index1];
            heap[index1] = heap[index2];
            heap[index2] = tmp;
        }

    }

    // For Test
    public static String[] generateInputString(int maxSize) {
        int size = maxSize;
        String[] res = new String[size];
        for (int i = 0; i < size; i++) {
            res[i] = randomLetter();
        }
        return res;
    }

    public static String randomLetter() {
        //97 - 122
        // [0,1) => [0,26) + 97 => [97,123)
        int rand = (int) (Math.random() * 5 + 97);
        return String.valueOf((char) rand);
    }

    public static List<Node> getTopTimes(String[] input) {
        Map<String, Integer> timesMap = new HashMap<>();
        for (int i = 0; i < input.length; i++) {
            if (!timesMap.containsKey(input[i])) {
                timesMap.put(input[i], 1);
            } else {
                timesMap.put(input[i], timesMap.get(input[i]) + 1);
            }
        }
        List<Node> top = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : timesMap.entrySet()) {
            String str = entry.getKey();
            Integer times = entry.getValue();
            top.add(new Node(str, times));
        }
        Collections.sort(top, new NodeComparator());
        return top;
    }

    public static void main(String[] args) {
        int maxSize = 100;
        TopKRecordHeap maxHeap = new TopKRecordHeap(maxSize, new NodeComparator());
        TopKRecord topHeap = new TopKRecord(maxSize);
        String[] input = generateInputString(maxSize);
        for (int i = 0; i < input.length; i++) {
            maxHeap.add(input[i]);
            topHeap.add(input[i]);
        }
        System.out.println(maxHeap);
        List<String> res = maxHeap.top();
        System.out.println(res);
        topHeap.printTopK();

    }


}
