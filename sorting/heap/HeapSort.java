package sorting.heap;

import java.util.Arrays;

/**
 * @author andy-liu
 * @date 2020/5/16 - 10:40 PM
 */
public class HeapSort {

    static int[] arr;
    static int[] heap;
    static int heapSize = 0;

    public static void main(String[] args) {
        arr = new int[]{5,12,3,7,1,8,9,0,-6};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));

    }

    public static void heapSort(int[] arr){
        if(arr == null || arr.length < 1){
            return;
        }
        process(arr);
    }

    private static void heapify(int[] arr, int index, int heapSize){
        if(heapSize == 0){
            return;
        }
       swap(arr,0,heapSize - 1);
       int largest = arr[index * 2 + 1] > arr[index * 2 + 2]? index * 2 + 1: index * 2 + 2;
       while(largest < heapSize && arr[index] < arr[largest]){
           swap(arr,index,largest);
           index = largest;
           if(index * 2 + 1 > heapSize){
               break;
           }
           largest = arr[index * 2 + 1] > arr[index * 2 + 2]? index * 2 + 1: index * 2 + 2;
       }
    }

    private static void process(int[] arr){
        heap = new int[arr.length];

        for(int i=0; i<arr.length ;i++){
            maxHeapInsert(arr[i]);
        }
        System.out.println(Arrays.toString(heap));
        while(heapSize > 0){
            System.out.println(heapSize);
            swap(heap,0,--heapSize);
            heapify(heap,0, heapSize);
        }

        for(int i=0; i<arr.length; i++){
            arr[i] = heap[i];
        }
    }

    private static void maxHeapInsert(int val){
        if(heapSize > arr.length){
            return;
        }
        heap[heapSize] = val;
        int index = heapSize;
        int parent = (index - 1) / 2;
        while(val > heap[parent]){
            swap(heap,index,parent);
            index = parent;
            parent = (parent - 1) / 2;
        }
        heapSize++;
    }


    private static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
