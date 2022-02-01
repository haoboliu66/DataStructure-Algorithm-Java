package src.main.java.fundamental.sorting.heap;

import static src.main.java.fundamental.sorting.BubbleSort.*;


public class HeapSort {

    public static class MyHeapSort {

        public static void heapSort(int[] arr) {
            if (arr == null || arr.length < 2) {
                return;
            }

            /* construct Max-Heap, Method1   */
//            for(int i=0; i<arr.length; i++){ // O(N)
//             保证 i 之前的部分都已经构成大根堆(每向右遍历一个数看这个新数字要不要上升)
//                heapInsert(arr,i);    // O(logN)
//            }

            int heapSize = arr.length;

            /* construct Max-Heap, Method2  */
            for (int i = arr.length - 1; i >= 0; i--) {
                // 保证 i 之后的部分都已经构成大根堆(每向左遍历一个数看这个新数字要不要下沉)
                heapify(arr, i, heapSize);
            }

            while (heapSize > 0) {    // O(N)
                swap(arr, 0, --heapSize);  // O(1)
                heapify(arr, 0, heapSize); // O(logN)
            }
        }

        private static void heapify(int[] arr, int index, int heapSize) {
            int left = index * 2 + 1;
            while (left < heapSize) {
                int largest = (left + 1 < heapSize && arr[left + 1] > arr[left]) ? left + 1 : left;
                largest = arr[largest] > arr[index] ? largest : index;
                if (index == largest) {
                    break;
                }
                swap(arr, largest, index);
                index = largest;
                left = index * 2 + 1;
            }
        }


        private static void heapInsert(int[] arr, int index) {
            while (arr[index] > arr[(index - 1) / 2]) {
                swap(arr, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private static void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        public static void main(String[] args) {
            int maxSize = 200;
            int maxValue = 50000;
            int times = 1000000;
            for (int i = 0; i < times; i++) {
                int[] arr = generateRandomArray(200, 50000);
                int[] copyArr = copyArray(arr);
                heapSort(arr);
                comparator(copyArr);
                boolean res = isEqual(arr, copyArr);
                if (!res) {
                    System.out.println("Oops");
                    break;
                }
            }
            System.out.println("Done");

        }

    }


}
