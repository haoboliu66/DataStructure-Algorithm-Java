package sorting.heap;

import java.util.Arrays;

/**
 * @author andy-liu
 * @date 2020/5/11 - 3:23 PM
 */
public class MaxHeap {

    public static void main(String[] args) {
        MaxHeap heap = new MaxHeap();
        heap.insertHeap(5);
        heap.insertHeap(7);
        heap.insertHeap(8);
        heap.insertHeap(6);
        heap.insertHeap(3);
        System.out.println(heap);

    }

    private int[] heap;
    private int heapSize;
    private int limit;
    private final int DEFAULT_SIZE_LIMIT = 20;

    public MaxHeap() {
       this.heap = new int[DEFAULT_SIZE_LIMIT];
        heapSize = 0;
    }

    public MaxHeap(int limit) {
        heap =  new int[limit];
        heapSize = 0;
    }

    public boolean isEmpty(){
        return heapSize == 0;
    }

    public int size(){
        return heapSize;
    }

    public void insertHeap(int val){
        heap[heapSize] = val;
        int parentIndex = (heapSize - 1) / 2;
        while(val > heap[parentIndex]){
            swap(heap,heapSize, parentIndex);
            parentIndex = (parentIndex - 1) / 2;
        }
        heapSize++;
    }

    private void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public int getMax(){
        int max = heap[0];
//        heapify();

        return max;
    }

    private void heapify(int[] arr, int index, int heapSize){

    }


    @Override
    public String toString() {
        return "MaxHeap{" +
                "heap=" + Arrays.toString(heap) +
                '}';
    }
}
