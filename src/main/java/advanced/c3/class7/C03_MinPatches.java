package src.main.java.advanced.c3.class7;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class C03_MinPatches {

    // https://leetcode.com/problems/patching-array/

    public static int getMinPatches(int[] arr, int n) {
        Arrays.sort(arr);
        int patches = 0;
        long range = 0;

        for (int i = 0; i < arr.length; i++) {
            while (range < arr[i] - 1) {
                range += (range + 1);
                patches++;
                if (range >= n) {
                    return patches;
                }
            }
            // range >= arr[i] - 1;
            // [1....10] 11
            range += arr[i];
            if (range >= n) return patches;
        }

        //range=100,  n = 500
        while (range < n - 1) {
            range += range + 1;
            patches++;
        }
        return patches;
    }


    public static int minPatchesZuo(int[] arr, int aim) {
        int patches = 0; // 缺多少个数字
        long range = 0; // 已经完成了1 ~ range的目标
        Arrays.sort(arr);
        for (int i = 0; i != arr.length; i++) {
            // 1~range
            // 1 ~ arr[i]-1
            while (arr[i] - 1 > range) { // arr[i] 1 ~ arr[i]-1
                range += range + 1; // range + 1 是缺的数字
                patches++;
                if (range >= aim) {
                    return patches;
                }
            }
            range += arr[i];
            if (range >= aim) {
                return patches;
            }
        }
        while (aim >= range + 1) {
            range += range + 1;
            patches++;
        }
        return patches;
    }

    // for test
    private static int[] generateRandomArray(int len, int val) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i != len; i++) {
            int cur = (int) (Math.random() * val) + 1;
            set.add(cur);
        }
        int[] res = new int[set.size()];
        int index = 0;
        for (int i : set) {
            res[index++] = i;
        }
        return res;
    }

    public static void main(String[] args) {
        int len = 50, max = 300, times = 200000;
        int[] arr;
        System.out.println("Go");
        for (int i = 0; i < times; i++) {
            arr = generateRandomArray(len, max);
            int aim = (int) ((Math.random() + 1) * max);
            int res1 = getMinPatches(arr, aim);
            int res2 = minPatchesZuo(arr, aim);
            if (res1 != res2) {
                System.out.println("Oops");
                System.out.println(res1);
                System.out.println(res2);
                System.out.println(Arrays.toString(arr));
                System.out.println("aim: " + aim);
                break;
            }
        }
        System.out.println("Done");
    }

}
