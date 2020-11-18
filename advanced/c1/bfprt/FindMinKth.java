package advanced.c1.bfprt;

import java.util.PriorityQueue;

public class FindMinKth {

    /*
    找到一个数组中第k小的数
     */

    /*
    方法一:
        Heap
     */
    public static int minKth1(int[] nums, int k) {

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);

        for (int i = 0; i < k; i++) {
            maxHeap.add(nums[i]);
        }
        for (int i = k; i < nums.length; i++) {
            if (nums[i] < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.add(nums[i]);
            }
        }
        return maxHeap.peek();
    }

    /*
    方法二:
      partition O(N)
     */
    public static int minKth2(int[] nums, int k) {
        int[] arr = copyArray(nums);
        return process2(arr, 0, arr.length - 1, k - 1);
    }

    // 在L...R范围上, 如果排好序的话, 找到下标为index的值(index是指定的位置参数)
    // index一定在L...R范围上
    private static int process2(int[] arr, int L, int R, int index) {
        if (L == R) {
            return arr[L];
        }
        int pivot = arr[L + (int) (Math.random() * (R - L + 1))];
        int[] range = partition(arr, L, R, pivot);
        if (index >= range[0] && index <= range[1]) {
            return arr[index];

        } else if (index < range[0]) {
            return process2(arr, L, range[0] - 1, index);

        } else {
            return process2(arr, range[1] + 1, R, index);
        }
    }

    //iterative
//    private static int process2(int[] arr, int L, int R, int index) {
//        while (L < R) {
//            int pivot = arr[L + (int) (Math.random() * (R - L + 1))];
//            int[] range = partition(arr, L, R, pivot);
//            if (index >= range[0] && index <= range[1]) {
//                return arr[index];
//            } else if (index < range[0]) {
//                R = range[0] - 1;
//            } else {
//                L = range[1] + 1;
//            }
//        }
//        return arr[L];
//    }

    private static int[] partition(int[] arr, int L, int R, int num) {
        int less = L - 1;
        int more = R + 1;
        int i = L;
        while (i < more) {
            if (arr[i] == num) {
                i++;
            } else if (arr[i] < num) {
                swap(arr, i++, ++less);
            } else {
                swap(arr, i, --more);
            }
        }
        return new int[]{less + 1, more - 1};
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int[] copyArray(int[] arr) {
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    /*
    方法三:
    bfprt
     */
    public static int minKth3(int[] nums, int k) {
        int[] arr = copyArray(nums);
        return bfprt(arr, 0, arr.length - 1, k - 1);
    }


    // 在arr中, L...R范围获取位于index位置的数
    public static int bfprt(int[] arr, int L, int R, int index) {
        if (L == R) {
            return arr[L];
        }
        int pivot = medianOfMedians(arr, L, R);

        int[] range = partition(arr, L, R, pivot);
        if (index >= range[0] && index <= range[1]) {
            return arr[index];
        } else if (index < range[0]) {
            return bfprt(arr, L, range[0] - 1, index);
        } else {
            return bfprt(arr, range[1] + 1, R, index);
        }
    }

    private static int medianOfMedians(int[] arr, int L, int R) {
        //分组
        int size = R - L + 1; // L...R的size
        int offset = size % 5 == 0 ? 0 : 1; // 总的size以5位单位划分有没有剩余
        // 总组数: size /5 + offset
        int[] mArr = new int[size / 5 + offset]; // mArr[]中位数数组

        //arr中, 获取每个组的中位数, 并赋值给mArr
        for (int team = 0; team < mArr.length; team++) {
            int teamFirst = L + team * 5;
            mArr[team] = getMedian(arr, teamFirst, Math.min(R, teamFirst + 4));
        }
        //mArr是无序的, 要获取mArr的中位数,需要递归调用bfprt
        return bfprt(mArr, 0, mArr.length - 1, mArr.length / 2);
    }

    // 数组长度为5, 复杂度O(1)
    public static int getMedian(int[] arr, int L, int R) {
        insertionSort(arr, L, R);
        return arr[(L + R) / 2]; //取中位数,奇数个取中点, 偶数个取上中点
    }

    public static void insertionSort(int[] arr, int L, int R) {
        for (int i = L + 1; i <= R; i++) {
            for (int j = i - 1; j >= L && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * maxSize) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTime = 1000000;
        int maxSize = 100;
        int maxValue = 200;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int k = (int) (Math.random() * arr.length) + 1;
            int ans1 = minKth1(arr, k);
            int ans2 = minKth2(arr, k);
            int ans3 = minKth3(arr, k);
            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }

}
