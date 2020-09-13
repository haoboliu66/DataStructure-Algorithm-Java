package bitwise;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author andy-liu
 * @date 2020/7/4 - 12:32 PM
 */
public class BinarySearch {


    //有序数组arr, 找到左边第一个 >= target 的值
    public static int findLeftMostGreater(int[] arr, int target) {

        int left = 0;
        int right = arr.length - 1;
        int index = -1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] >= target) {
                index = mid; // possible answer
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return index;
    }

    public static int findLeftMostGreater1(int[] arr, int target) {

        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
                /*
                 left每次新到的位置都可能是答案所在的位置, 如果是正确的位置, 以后只会有right不断左移
                 */
            }
        }
        return left > arr.length - 1 ? -1 : left;
    }

    @Test
    public void test() {
        int maxSize = 100;
        int maxValue = 3000;
        int[] arr = generateRandomArray(maxSize, maxValue);
        int target = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        int r1 = findLeftMostGreater(arr, target);
        int r2 = findLeftMostGreater1(arr, target);
        System.out.println(r1 == r2);
    }


    public static int right1(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= target) {
                return i;
            }
        }
        return -1;
    }

    //有序数组arr, 找到右边第一个 <= target 的值
    public static int findRightMostSmaller(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right < 0 ? -1 : right;
    }

    public static int right2(int[] arr, int value) {
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] <= value) {
                return i;
            }
        }
        return -1;
    }


    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }


    public static void main(String[] args) {
        int maxSize = 100;
        int maxValue = 3000;
        int testTimes = 1000000;
        System.out.println("Started");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            Arrays.sort(arr);
            int target = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
            int res1 = findLeftMostGreater1(arr, target);
            int res2 = right1(arr, target);
            if (res1 != res2) {
                System.out.println(res1 + " ,,, " + res2);
                System.out.println("Oops!!!");
            }

            int res3 = findRightMostSmaller(arr, target);
            int res4 = right2(arr, target);
            if (res3 != res4) {
                System.out.println(res3 + " ,,, " + res4);
                System.out.println("Oops!!!~~~");
            }

        }
        System.out.println("Finished");
    }


}
