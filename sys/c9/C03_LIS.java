package sys.c9;

import java.util.Arrays;

public class C03_LIS {


    public static int getLIS(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }


    // not done
    public static int getLIS2(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        int[] end = new int[n + 1];
        // 长度为i+1的最长递增子序列的结尾是end[i];
        // 长度为1的最长递增子序列的结尾是end[1];
        dp[0] = 1;
        end[0] = Integer.MIN_VALUE;
        end[1] = arr[0];
        int max = 1;
        int valid = 1;
        int l, r, m;
        for (int i = 1; i < n; i++) {
            l = 1;
            r = valid;
            // 在l...r范围上找 <= arr[i]最右的位置
            while (l <= r) {
                m = (r - l) / 2;

                if (end[m] >= arr[i]) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
            // l结果就是 <= arr[i]最右的位置
            valid = Math.max(l, valid);
            end[l] = arr[i];
            dp[i] = l;
            max = Math.max(dp[i], max);
        }
        System.out.println(Arrays.toString(end));
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(getLIS2(arr));
    }

}
