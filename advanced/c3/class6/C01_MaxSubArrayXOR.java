package advanced.c3.class6;

import java.util.Arrays;

public class C01_MaxEOR {

    /*
     子数组的最大异或和
     */

    // 传统思维: 以[i]结尾求最大的异或和
    public static int maxEor0(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int n = arr.length;
        int[] dp = new int[n];
        dp[0] = arr[0];
        int max = arr[0];
        for (int i = 1; i < n; i++) {
            dp[i] = arr[i];
            dp[i] = Math.max(dp[i], arr[i] ^ dp[i - 1]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }



    // for test
    public static int right(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int res = arr[i];
            max = Math.max(max, res);
            for (int j = i + 1; j < arr.length; j++) {
                res ^= arr[j];
                max = Math.max(res, max);
            }
        }

        return max;
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
        int len = 5, max = 10, times = 1000;
        int[] arr;
        System.out.println("Go");
        for (int i = 0; i < times; i++) {
            arr = generateRandomArray(len, max);
            int right = right(arr);
            int res1 = maxEor0(arr);
            if (right != res1) {
                System.out.println(Arrays.toString(arr));
                System.out.println(right);
                System.out.println(res1);
                System.out.println("Oops");
                break;
            }
        }
        System.out.println("Done");
    }

}
