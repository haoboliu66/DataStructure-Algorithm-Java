package advanced.c3.class4;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author andy-liu
 * @date 2020/7/4 - 10:25 AM
 */
public class C04_LIS {

/*
Longest Increasing Sequence
LeetCode 300
 */

    //  O(N^2)
    public static int longestIncreasingSequence1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return 1;
        }

        int[] dp = new int[arr.length];
        dp[0] = 1;
        dp[1] = arr[1] > arr[0] ? 2 : 1;
        int max = Math.max(dp[0], dp[1]);
        for (int i = 2; i < arr.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }

        System.out.println(Arrays.toString(dp));
        return max;
    }


    public static int[] getdp1(int[] arr) {
        int[] dp = new int[arr.length];
        dp[0] = 1;
        for (int i = 1; i < arr.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return dp;
    }

    //生成对应的LIS
    public static int[] getLIS(int[] arr, int[] dp) {
        int len = 0;  //LIS的长度
        int index = 0; //LIS的结尾index
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > len) {
                len = dp[i];
                index = i;
            }
        }
        int[] lis = new int[len];
        lis[--len] = arr[index];
        for (int i = index - 1; i >= 0; i--) {
            if (arr[i] < arr[index] && dp[i] == dp[index] - 1) {//i位置的数字比index小, 且dp的值刚好小1
                lis[--len] = arr[i];
                index = i; // index跳到LIS的前一个位置
            }
        }
        return lis;
    }


    // O(N*logN)
    public static int longestIncreasingSequence2(int[] arr) {
        int[] dp = new int[arr.length];
        int[] ends = new int[arr.length];  //ends[i]表示找到的所有长度为i+1的递增子序列中, 最小结尾的值,
        int max = Integer.MIN_VALUE;
        ends[0] = arr[0];
        dp[0] = 1;
        int right = 0; //0 ... right 是有效区
        int l = 0;
        int r = 0;
        int m = 0;
        for (int i = 1; i < arr.length; i++) {
            l = 0;
            r = right;
            // binary search, 在ends数字中找到leftMostGreater, arr[i]是target
            while (l <= r) {
                m = l + ((r - l) >> 1);
                if (ends[m] >= arr[i]) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
            //二分最后的l就是要找的位置, l是可能会越过right这个右边界的, 所以要取值判断
            //如果越界了, 说明要扩充有效区
            right = Math.max(l, right);
            ends[l] = arr[i];
            dp[i] = l + 1;
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
        return max;
    }

    public static int[] getdp2(int[] arr) {

        int[] dp = new int[arr.length];
        int[] ends = new int[arr.length];
        dp[0] = 1;
        ends[0] = arr[0];
        int right = 0;

        int l = 0;
        int r = 0;
        int m = 0;
        for (int i = 1; i < arr.length; i++) {
            l = 0;
            r = right;
            while (l <= r) {
                m = l + ((r - l) >> 1);
                if (ends[m] >= arr[i]) {
                    r = m - 1;
                } else {
                    r = m + 1;
                }
                right = Math.max(right, l);
                ends[l] = arr[i];
                dp[i] = l + 1;
            }
        }
        return dp;
    }


    public static void main(String[] args) {
        int[] arr = {10, 9, 2, 5, 3, 7, 101, 18};
        int[] arr1 = {-2, -1};
        int[] arr2 = {1, 3, 6, 7, 9, 4, 10, 5, 6};
        int len1 = longestIncreasingSequence1(arr2);
        int len2 = longestIncreasingSequence2(arr2);
        System.out.println(len1 == len2);

    }

}
