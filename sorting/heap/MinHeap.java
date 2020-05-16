package sorting.heap;

import java.util.Arrays;

/**
 * @author andy-liu
 * @date 2020/5/11 - 5:27 PM
 */
public class MinHeap {

    public static void main(String[] args) {
        MinHeap heap = new MinHeap();
        heap.push(5);
        heap.push(7);
        heap.push(8);
        heap.push(6);
        heap.push(3);
        System.out.println(heap);
        System.out.println(heap.size());
        System.out.println("**************");
        int i = heap.pop();
        System.out.println(heap);
        System.out.println(heap.size());
        System.out.println("**************");
        heap.push(12);
        System.out.println(heap);
        System.out.println(heap.size());

    }

    private int[] heap;
    private int heapSize;
    private int limit;
    private final int DEFAULT_SIZE_LIMIT = 20;

    public MinHeap() {
        limit = DEFAULT_SIZE_LIMIT;
        heap = new int[limit];
    }

    public MinHeap(int limit) {
        heap = new int[limit];
    }

    public boolean isEmpty(){
        return heapSize == 0;
    }

    public int size(){
        return heapSize;
    }

    public void push(int val){
        heapInsert(val);
    }

    private void heapInsert(int val){
        if(heapSize > limit){
            throw new RuntimeException("HeapSize exceeds limit");
        }
        heap[heapSize] = val;
        int index = heapSize;
        int parent = (index - 1) / 2;
        while(heap[index] < heap[parent]){
            swap(heap,index,parent);
            index = parent;
            parent = (parent - 1) / 2;
        }
        heapSize++;
    }

    private void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public int pop(){
        if(heapSize == 0){
            throw new RuntimeException("Heap is Empty");
        }
        int min = heap[0];
        heapify(heap,0, heapSize);
        heapSize--;
        return min;
    }

    private void heapify(int[] arr, int index, int heapSize){
        swap(arr,0,heapSize - 1); // remove the smallest element out of heap
        heapSize--;
        int smallest = (arr[index * 2 + 1] < arr[index * 2 + 2])? index * 2 + 1: index * 2 + 2;
        while(smallest < heapSize && arr[index] > arr[smallest]){
            swap(arr,index,smallest);
            index = smallest;
            if(index * 2 + 1 > heapSize){
                break;
            }
            smallest = (arr[index * 2 + 1] < arr[index * 2 + 2])? index * 2 + 1: index * 2 + 2;
        }
    }

    @Override
    public String toString() {
        return "MinHeap{" +
                "heap=" + Arrays.toString(heap) +
                '}';
    }
}
