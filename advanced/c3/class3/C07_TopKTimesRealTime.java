package advanced.c3.class3;

import java.util.HashMap;

/**
 * @author andy-liu
 * @date 2020/6/30 - 8:12 AM
 */
public class C07_TopKTimesRealTime {

    public static class Node {
        public String str;
        public int times;

        public Node(String s, int t) {
            str = s;
            times = t;
        }
    }

    public static class Heap {
        int[] heap;
        int size;
        HashMap<Integer, Integer> indexMap;

        public Heap(int size) {
            this.size = size;
            heap = new int[size];
            indexMap = new HashMap<>();
        }


        public void heapInsert(int val) {
            heap[size] = val;
            int parent = (size - 1) / 2;
            if (val > parent) {
                swap(heap, size++, parent);
            }
        }

        public int pop() {
            int res = heap[0];
            swap(heap, 0, size);
            return res;
        }



        public void swap(int[] arr, int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }


    }


}
