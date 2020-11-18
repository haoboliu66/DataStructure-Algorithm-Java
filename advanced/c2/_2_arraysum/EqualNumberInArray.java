package advanced.c2._2_arraysum;

import java.util.HashMap;

public class EqualNumberInArray {

    /*
    Q: 一个数组arr[] 有正数,有负数, 有0, 求含有1和2的数量相同的最长子数组长度
     */
    public static int getMaxLength(int[] arr, int a, int b) {
        if (arr == null || arr.length == 0 || a == b) {
            return 0;
        }
        // a保持不变, b变为-a, 其余数字都变成0, 转换成求累加和为0的最长子数组
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] == b ? -a : arr[i] == a ? a : 0;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int K = 0;
        int len = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(sum - K)) {
                len = Math.max(len, i - map.get(sum - K));
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }

        return len;
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 3, 5, 6, 7, 8, 1, 1, 1, 1, 0, 2, 2, 2, 2};
        int[] arr1 = {1, 2};
        int[] arr2 = {5, 6, 8};
        int[] arr3 = {1, 1, 2};
        int[] arr4 = {1, 2, 2};
        int[] arr5 = {4, 5, 6, 7, 8, 9, 0, 3, 4, 5, 6, 7, 8, 9, 1, 2};
        int a = 1;
        int b = 2;
        int len = getMaxLength(arr, a, b);
        int len1 = getMaxLength(arr1, a, b);
        int len2 = getMaxLength(arr2, a, b);
        int len3 = getMaxLength(arr3, a, b);
        int len4 = getMaxLength(arr4, a, b);
        int len5 = getMaxLength(arr5, a, b);
        System.out.println(len);
        System.out.println(len1);
        System.out.println(len2);
        System.out.println(len3);
        System.out.println(len4);
        System.out.println(len5);

    }
}
