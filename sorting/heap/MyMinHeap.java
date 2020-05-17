package sorting.heap;

import java.util.Arrays;

/**
 * @author andy-liu
 * @date 2020/5/11 - 5:27 PM
 */
public class MyMinHeap {

    public static void main(String[] args) {
        MinHeap heap = new MinHeap(6);
        heap.push(5);
        heap.push(7);
        heap.push(8);
        heap.push(6);
        heap.push(3);
        System.out.println(heap.pop());
        System.out.println(heap);
        System.out.println(heap.pop());
        System.out.println(heap);
        System.out.println(heap.size());
    }

    public static class MinHeap {

        private int[] heap;
        private int heapSize;
        private int limit;


        public MinHeap(int limit) {
            this.limit = limit;
            heap = new int[limit];
            heapSize = 0;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean isFull(){
            return heapSize == limit;
        }

        public int size() {
            return heapSize;
        }

        public void push(int val) {
            if (isFull()) {
                throw new RuntimeException("HeapSize exceeds limit");
            }
            heap[heapSize] = val;
            heapInsert(heap, heapSize++);
        }

        private void heapInsert(int[] arr, int index) {
            while (arr[index] < arr[(index - 1) / 2]) {
                swap(heap, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        public int pop() {
            if (isEmpty()) {
                throw new RuntimeException("Heap is Empty");
            }
            int min = heap[0];
            swap(heap,0, --heapSize); // remove the smallest number out of heap
            heapify(heap, 0, heapSize);
            return min;
        }

        private void heapify(int[] arr, int index, int heapSize) {
            int left = index * 2 + 1;
            while(left < heapSize){
                int smallest = (left + 1 < heapSize && arr[left + 1] < arr[left])? left + 1: left;
                smallest = arr[index] < arr[smallest] ? index: smallest;
                if(index == smallest){
                    break;
                }
                swap(arr,index, smallest);
                index = smallest;
                left = 2 * index + 1;
            }
        }

        @Override
        public String toString() {
            return "MinHeap{" +
                    "heap=" + Arrays.toString(heap) +
                    '}';
        }
    }

}
