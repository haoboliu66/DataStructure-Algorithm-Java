package src.main.java.advanced.c4.class1;

import java.util.TreeSet;

public class C02_MaxSubArraySumLessOrEqualK {
    /*
    给定一个数组arr, 一个K值, 返回累加和小于等于K,但是离K最近的子数组累加和
     */

    public static int getSubArrayK(int[] arr, int K) {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        int sum = 0, max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            // <=80 -> K [0....i] 100
            // >=20
            Integer ceilingKey = set.ceiling(sum - K);
            if (ceilingKey != null) {
                max = Math.max(sum - ceilingKey, max);
            }
            set.add(sum);
        }
        return max;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * maxSize) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
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
            int ans1 = getSubArrayK(arr, k);
            int ans2 = getMaxLessOrEqualK(arr, k);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("test finish");
    }


    public static int getMaxLessOrEqualK(int[] arr, int K) {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            //子数组累加和<=K, 前缀和 >=K
            if (set.ceiling(sum - K) != null) {
                max = Math.max(max, sum - set.ceiling(sum - K));
            }
            set.add(sum);
        }
        return max;
    }


    // similar problem
    /*  1099. Two Sum Less Than K  */
    // https://leetcode.com/problems/two-sum-less-than-k/
    public int twoSumLessThanK(int[] arr, int k) {
        if (arr.length < 2 || k <= 0) {
            return -1;
        }
        TreeSet<Integer> set = new TreeSet<>();
        int max = -1;
        for (int i = 0; i < arr.length; i++) {
            // 60  arr[i] = 40
            // < 20
            Integer floorKey = set.lower(k - arr[i]);
            if (floorKey != null) {
                max = Math.max(max, arr[i] + floorKey);
            }
            set.add(arr[i]);
        }

        return max;
    }


}
