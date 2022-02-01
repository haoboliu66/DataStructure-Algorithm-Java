package src.main.java;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MyHeapCode {

    static class MyHeapSort {

        public void heapSort(int[] arr) {
            for (int i = 0; i < arr.length; i++) {
                heapInsert(arr, i);
            }
            int heapSize = arr.length;
            while(heapSize > 0){
                swap(arr,0, --heapSize);
                heapify(arr,0,heapSize);
            }
        }

        public void heapInsert(int[] arr, int index) {
            int parent;
            while (arr[index] > arr[parent = (index - 1) / 2]) {
                swap(arr, index, parent);
                index = parent;
            }
        }

        private void swap(int[] arr, int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }

        public void heapify(int[] arr, int index, int heapSize) {
            int left, larger;
            while ((left = 2 * index + 1) < heapSize) {
                larger = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;

                if (arr[index] <= arr[larger]) {
                    break;
                }

                swap(arr, larger, index);
                index = larger;
            }
        }


    }


    public static void main(String[] args) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        MyHeap my = new MyHeap(1000);
        for (int i = 0; i < 1000; i++) {
            int n = (int) (Math.random() * 10000);
            heap.add(n);
            my.add(n);
        }

        for (int i = 0; i < 1000; i++) {
            if (heap.poll() != my.poll()) {
                System.out.println("Oops");
                break;
            }
        }
        System.out.println(heap.size() + " , " + my.size());

    }

    public static class MyHeap {
        public int[] heap;
        int limit;
        int heapSize;

        // 0,1,2,3,4,5   n  2n+1   2n+2

        public MyHeap(int l) {
            heap = new int[l];
            limit = l;
            heapSize = 0;
        }


        public void add(int n) {
            if (isFull()) return;
            heap[heapSize] = n;
            heapInsert(heapSize++);
        }

        public int poll() {
            if (isEmpty()) {
                throw new RuntimeException("empty heap");
            }
            int min = heap[0];
            swap(heap, 0, --heapSize);
            heapify(0);
            return min;
        }

        public void heapify(int curIndex) {
            int leftChild = 2 * curIndex + 1;
            int smaller;
            while (leftChild < heapSize) {
                smaller = leftChild + 1 < heapSize && heap[leftChild] > heap[leftChild + 1] ? leftChild + 1 : leftChild;
                smaller = heap[curIndex] < heap[smaller] ? curIndex : smaller;

                if (curIndex == smaller) break;

                swap(heap, curIndex, smaller);
                curIndex = smaller;

                leftChild = 2 * curIndex + 1;
            }
        }


        public void heapInsert(int index) {
            int parent;
            while (heap[index] < heap[parent = (index - 1) / 2]) {
                swap(heap, parent, index);
                index = parent;
            }
        }

        private void swap(int[] heap, int i, int j) {
            int tmp = heap[i];
            heap[i] = heap[j];
            heap[j] = tmp;
        }

        public int size() {
            return heapSize;
        }

        public boolean isEmpty() {
            return limit == 0;
        }

        public boolean isFull() {
            return limit == heapSize;
        }

        public String toString() {
            return Arrays.toString(heap);
        }
    }


}
