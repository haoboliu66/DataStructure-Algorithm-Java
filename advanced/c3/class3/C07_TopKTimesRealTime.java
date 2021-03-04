package advanced.c3.class3;

import java.util.HashMap;
import java.util.Map;

public class C07_TopKTimesRealTime {

    public static class Node {
        public String str;
        public int times;

        public Node(String str, int times) {
            this.str = str;
            this.times = times;
        }
    }

    public static class Heap {
        int[] heap;
        int heapSize;
        Map<String, Node> StringNodeMap;
        Map<Node, Integer> nodeIndexMap;

        public Heap(int size) {
            this.heapSize = size;
            heap = new int[size];
            StringNodeMap = new HashMap<>();
            nodeIndexMap = new HashMap<>();
        }


        public void heapInsert(int val) {
            heap[heapSize] = val;
            int parent = (heapSize - 1) / 2;
            if (val > parent) {
                swap(heap, heapSize++, parent);
            }
        }

        public int pop() {
            int res = heap[0];
            swap(heap, 0, heapSize);
            return res;
        }


        public void swap(int[] arr, int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }


    }


}
