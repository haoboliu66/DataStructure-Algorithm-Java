package sorting.heap;

import java.util.Arrays;

/**
 * @author andy-liu
 * @date 2020/5/11 - 3:23 PM
 */
public class MaxHeap {

    public static void main(String[] args) {
        MaxHeap heap = new MaxHeap();
        heap.push(5);
        heap.push(7);
        heap.push(8);
        heap.push(6);
        heap.push(3);
        System.out.println(heap);
        System.out.println(heap.size());
        int i = heap.pop();
        System.out.println(heap);
        System.out.println(heap.size());
        heap.push(12);
        System.out.println(heap);
        System.out.println(heap.size());

    }

    private int[] heap;
    private int heapSize;
    private int limit;
    private final int DEFAULT_SIZE_LIMIT = 20;

    public MaxHeap() {
        limit = DEFAULT_SIZE_LIMIT;
        heap = new int[limit];
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

    public void push(int val){
        heapInsert(val);
    }

    /** insert a new number into heap   */
    private void heapInsert(int val){
        if(heapSize > limit){
            throw new RuntimeException("HeapSize reaches the limit");
        }
        heap[heapSize] = val;
        int index = heapSize;
        int parentIndex = (index - 1) / 2;
        while(val > heap[parentIndex]){
            swap(heap, index, parentIndex);
            index = parentIndex;
            parentIndex = (parentIndex - 1) / 2;
        }
        heapSize++;
    }

    private void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public int pop(){
        int max = heap[0];
        heapify(heap, 0, heapSize);
        return max;
    }

    /**  rearrange heap array into Max-heap  */
    private void heapify(int[] arr, int index, int heapSize){
        swap(arr,0,heapSize - 1); // remove the largest element out of heap
        heapSize--;
        int largest = (arr[index * 2 + 1] >= arr[index * 2 + 2])? index * 2 + 1: index * 2 + 2;
        while(largest < heapSize && arr[index] < arr[largest]){
            swap(arr,index,largest);
            index = largest;
            if(index * 2 + 1 > heapSize){
                break;
            }
            largest = (arr[index * 2 + 1] >= arr[index * 2 + 2])? index * 2 + 1: index * 2 + 2;
        }
    }

    @Override
    public String toString() {
        return "MaxHeap{" +
                "heap=" + Arrays.toString(heap) +
                '}';
    }



}
