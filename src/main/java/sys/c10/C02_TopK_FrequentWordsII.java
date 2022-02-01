package src.main.java.sys.c10;

import org.junit.Test;

import java.util.*;

public class C02_TopK_FrequentWordsII {

    //https://www.lintcode.com/problem/top-k-frequent-words-ii/

    /*
    堆内排序是一个比较器 => 词频相同,字典序小的保留在堆上(说明它的Node值大)
    输出结果是另一个比较器 => 词频相同, 字典序小的排前面(说明它的Node值小)
     */

    class Node {
        String str;
        int times;

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
    }

    class NodeComparator implements Comparator<Node> {
        public int compare(Node n1, Node n2) {
            // aa 10   (aa,10) > (ab,10)
            // ab 10
            return n1.times != n2.times ? n1.times - n2.times : n2.str.compareTo(n1.str);
        }
    }

    class NodeTreeSetComp implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.times != o2.times ? (o2.times - o1.times) : (o1.str.compareTo(o2.str));
        }

    }

    public class TopK {
        private Node[] heap; // minHeap
        private int heapSize;
        private Map<String, Node> nodeMap;
        private Map<Node, Integer> indexMap;
        private Comparator<Node> comparator;

        public TopK(int k) {
            heap = new Node[k];
            heapSize = 0;
            nodeMap = new HashMap<>();
            indexMap = new HashMap<>();
            comparator = new NodeComparator();
        }

        public boolean isFull() {
            return heapSize == heap.length;
        }

        public void add(String word) {
            if (heap.length == 0) {
                return;
            }
            if (!nodeMap.containsKey(word)) {
                Node newNode = new Node(word, 1);
                nodeMap.put(word, newNode);
                indexMap.put(newNode, -1);
                if (!isFull()) {
                    heap[heapSize] = newNode;
                    indexMap.put(newNode, heapSize);
                    heapInsert(heapSize++);
                } else {
                    Node minNode = heap[0];
                    if (comparator.compare(minNode, newNode) < 0) {
                        heap[0] = newNode;
                        indexMap.put(minNode, -1);
                        indexMap.put(newNode, 0);
                        heapify(0);
                    }
                }

            } else {
                // 已经是进来的节点
                Node oldNode = nodeMap.get(word);
                Integer oldNodeIndex = indexMap.get(oldNode);
                oldNode.times++;
                if (oldNodeIndex == -1) {
                    Node minNode = heap[0];
                    if (comparator.compare(minNode, oldNode) < 0) {
                        heap[0] = oldNode;
                        indexMap.put(oldNode, 0);
                        indexMap.put(minNode, -1);
                        heapify(0);
                    }
                } else {
                    // oldNode在堆上, oldNode的词频加1了, 从所在位置直接进行调整
                    heapify(oldNodeIndex);
                }
            }
        }

        /*
         * @return: the current src.main.java.advanced.top k frequent words.
         */
        public List<String> topk() {
            if (heap.length == 0) {
                return Collections.EMPTY_LIST;
            }
            List<Node> list = new ArrayList<>();
            for (int i = 0; i < heap.length; i++) {
                if (heap[i] != null) {
                    list.add(heap[i]);
                }
            }
            Collections.sort(list, new NodeTreeSetComp());
            List<String> res = new ArrayList<>();
            for (Node n : list) {
                res.add(n.str);
            }
            return res;
        }


        private void heapInsert(int index) {
            if (index < 0) return;
            // index < parent
            while (comparator.compare(heap[index], heap[(index - 1) / 2]) < 0) {
                swap(heap, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void heapify(int index) {
            if (index < 0) return;
            int leftChild = index * 2 + 1;
            int smaller =
                    (leftChild + 1 < heapSize && comparator.compare(heap[leftChild + 1], heap[leftChild]) < 0) ?
                            leftChild + 1 : leftChild;
            while (leftChild < heapSize && comparator.compare(heap[smaller], heap[index]) < 0) {
                swap(heap, smaller, index);
                index = smaller;
                if (index * 2 >= heapSize) {
                    break;
                }
                leftChild = index * 2 + 1;
                smaller =
                        (leftChild + 1 < heapSize && comparator.compare(heap[leftChild + 1], heap[leftChild]) < 0) ?
                                leftChild + 1 : leftChild;
            }
        }

        private void swap(Node[] heap, int i, int j) {
            Node n1 = heap[i];
            Node n2 = heap[j];
            heap[i] = n2;
            heap[j] = n1;
            indexMap.put(n1, j);
            indexMap.put(n2, i);
        }
    }


    @Test
    public void test4() {
        /*
        ["baby","code","yes"]
        ["code","lint","baby"]
         */
        TopK topK = new TopK(3);
        topK.add("yes");
        topK.add("lint");
        topK.add("code");
        topK.add("yes");
        topK.add("code");
        topK.add("baby");
        topK.add("you");
        topK.add("baby");
        topK.add("chrome");
        System.out.println(topK.topk());
        topK.add("safari");
        topK.add("lint");
        topK.add("code");
        topK.add("body");
        topK.add("lint");
        topK.add("code");
        System.out.println(topK.topk());
    }

    @Test
    public void test3() {
        TopK topK = new TopK(2);
        topK.add("lint");
        topK.add("code");
        topK.add("code");
        topK.add("coding");
        topK.add("coding");
        topK.add("coding");
        topK.add("lint");
        topK.add("lint");
        System.out.println(topK.topk());
    }
}

// zuo
class Code02_TopK {

    private Node[] heap;
    private int heapSize;
    // 词频表   key  abc   value  (abc,7)
    private HashMap<String, Node> strNodeMap;
    private HashMap<Node, Integer> nodeIndexMap;
    private NodeHeapComp comparator;
    private TreeSet<Node> treeSet;

    public Code02_TopK(int K) {
        heap = new Node[K];
        heapSize = 0;
        strNodeMap = new HashMap<String, Node>();
        nodeIndexMap = new HashMap<Node, Integer>();
        comparator = new NodeHeapComp();
        treeSet = new TreeSet<>(new NodeTreeSetComp());
    }

    public static class Node {
        public String str;
        public int times;

        public Node(String s, int t) {
            str = s;
            times = t;
        }
    }

    public static class NodeHeapComp implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.times != o2.times ? (o1.times - o2.times) : (o2.str.compareTo(o1.str));
        }
    }

    public static class NodeTreeSetComp implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.times != o2.times ? (o2.times - o1.times) : (o1.str.compareTo(o2.str));
        }
    }

    public void add(String str) {
        if (heap.length == 0) {
            return;
        }
        // str   找到对应节点  curNode
        Node curNode = null;
        // 对应节点  curNode  在堆上的位置
        int preIndex = -1;
        if (!strNodeMap.containsKey(str)) {
            curNode = new Node(str, 1);
            strNodeMap.put(str, curNode);
            nodeIndexMap.put(curNode, -1);
        } else {
            curNode = strNodeMap.get(str);
            // 要在time++之前，先在treeSet中删掉
            // 原因是因为一但times++，curNode在treeSet中的排序就失效了
            // 这种失效会导致整棵treeSet出现问题
            if (treeSet.contains(curNode)) {
                treeSet.remove(curNode);
            }
            curNode.times++;
            preIndex = nodeIndexMap.get(curNode);
        }
        if (preIndex == -1) {
            if (heapSize == heap.length) {
                if (comparator.compare(heap[0], curNode) < 0) {
                    treeSet.remove(heap[0]);
                    treeSet.add(curNode);
                    nodeIndexMap.put(heap[0], -1);
                    nodeIndexMap.put(curNode, 0);
                    heap[0] = curNode;
                    heapify(0, heapSize);
                }
            } else {
                treeSet.add(curNode);
                nodeIndexMap.put(curNode, heapSize);
                heap[heapSize] = curNode;
                heapInsert(heap, heapSize++);
            }
        } else {
            treeSet.add(curNode);
            heapify(preIndex, heapSize);
        }
    }

    public List<String> topk() {
        ArrayList<String> ans = new ArrayList<>();
        for (Node node : treeSet) {
            ans.add(node.str);
        }
        return ans;
    }

    private void heapInsert(Node[] heap, int index) {
        while (comparator.compare(heap[index], heap[(index - 1) / 2]) < 0) {
            swap(heap, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void heapify(int index, int heapSize) {
        int l = index * 2 + 1;
        int r = index * 2 + 2;
        int smallest = index;
        while (l < heapSize) {
            if (comparator.compare(heap[l], heap[index]) < 0) {
                smallest = l;
            }
            if (r < heapSize && comparator.compare(heap[r], heap[smallest]) < 0) {
                smallest = r;
            }
            if (smallest != index) {
                swap(heap, smallest, index);
            } else {
                break;
            }
            index = smallest;
            l = index * 2 + 1;
            r = index * 2 + 2;
        }
    }

    private void swap(Node[] heap, int index1, int index2) {
        nodeIndexMap.put(heap[index1], index2);
        nodeIndexMap.put(heap[index2], index1);
        Node tmp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = tmp;
    }

}

