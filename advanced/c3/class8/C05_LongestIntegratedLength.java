package advanced.c3.class8;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

public class C05_LongestIntegratedLength {

/*
    可整合数组定义: 如果一个数组在排序之后, 每相邻两个数差的绝对值都为1, 则该数组为可整合数组。
    例如[5,3,4,6,2]为可整合数组。
    给定一个int数组arr, 返回其中最大可整合子数组的长度。
    例如[5,5,3,2,6,4,3], 最大的子数组为[5,3,2,6,4],返回其长度5
 */

    /*
    枚举每一个子数组, 并把这部分子数组进行排序, 再检查是否为可整合数组
     */
    // O(N^3 * logN)
    public static int getLIL1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int len = 0;
        //O(N^2)
        for (int start = 0; start < arr.length; start++) {
            for (int end = start; end < arr.length; end++) {
                // O(N*logN)
                if (isIntegrated(arr, start, end)) {
                    len = Math.max(len, end - start + 1);
                }
            }
        }
        return len;
    }

    public static boolean isIntegrated(int[] arr, int left, int right) {
        int[] newArr = Arrays.copyOfRange(arr, left, right + 1);
        Arrays.sort(newArr);
        for (int i = 0; i < newArr.length - 1; i++) {
            if (newArr[i + 1] != newArr[i] + 1) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] res = Arrays.copyOfRange(arr, 1, 5);
        System.out.println(Arrays.toString(res)); // 2,3,4,5(index 1,2,3,4)
    }


    // O(N^2)
    /*
    改写可整合数组的定义: 1.数组中无重复值; 2.数组中Max-Min==数组长度-1
     */
    public static int getLIL2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = 0;
        int min = 0;
        int len = 0;
        HashSet<Integer> set = new HashSet<>();
        for (int start = 0; start < arr.length; start++) {
            set.clear();
            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;
            for (int end = start; end < arr.length; end++) {

                if (set.contains(arr[end])) {
                    break;
                } else {
                    set.add(arr[end]);
                }
                max = Math.max(arr[end], max);
                min = Math.min(arr[end], min);

                if (max - min == end - start) { //max - min == length - 1
                    len = Math.max(len, end - start + 1);
                }
            }
        }
        return len;
    }


    // for test
    public static int[] generateArray(int len, int max) {
        int[] ans = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * max);
        }
        return ans;
    }

    public static void main(String[] args) {
//        int[] arr = {5, 5, 3, 2, 6, 4, 3};
        int len = 20;
        int max = 1000;
        int times = 100000;
        int[] arr = generateArray(len, max);
        System.out.println("Started");
        for (int i = 0; i < times; i++) {
            if (getLIL1(arr) != getLIL2(arr)) {
                System.out.println("Oops");
                break;
            }
        }
        System.out.println("Finished");

    }


}
