package fundamental.sorting.heap;

import java.util.PriorityQueue;

public class SortArrayDistanceLessK {

    public static void sortedArrayDistanceLessK(int[] arr, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index = 0;
        // 先把数组中前k + 1个数(下标是到k)组织成小根堆, 因为第k + 1往前移动k步都满足要求
        for (; index <= Math.min(k, arr.length); index++) {
            heap.add(arr[index]);
        }
        int i = 0;
        // index继续向后走, 开始从小根堆中弹出值并赋值给arr, 弹出的永远是剩下的值中最小的
        for (; index < arr.length; i++, index++) {
            heap.add(arr[index]);
            arr[i] = heap.poll();
        }
        //剩下的值依次弹出
        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }

    }

}
