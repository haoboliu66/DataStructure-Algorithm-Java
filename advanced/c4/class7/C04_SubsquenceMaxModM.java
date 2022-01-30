package advanced.c4.class7;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*  给定一个非负数组arr, 一个正数m, 返回arr所有子序列中累加和 % m 后的最大值  */
public class C04_SubsquenceMaxModM {

    @Test
    public void test() {
        int a, b, m;
        int testTime = 20000;
        System.out.println("Started");
        for (int i = 0; i < testTime; i++) {
            a = (int) (Math.random() * 10000);
            b = (int) (Math.random() * 10000);
            m = (int) ((Math.random() + 1) * 10000);
            int aModm = a % m;
            int bModm = b % m;
            while(bModm > aModm){
                b = (int) (Math.random() * 10000);
                bModm = b % m;
            }
            int aPlusbModm = (a + b) % m;
            if(aModm + bModm != aPlusbModm){
                System.out.println("Oops");
                break;
            }
        }
        System.out.println("Done");
    }


    public static void main(String[] args) {
        int maxVal = 1000, maxLen = 20;
        int testTime = 500;
        int[] arr;
        int m;

        System.out.println("Started");
        for (int i = 0; i < testTime; i++) {
            arr = generateRandomArray(maxVal, maxLen);
            m = (int) ((Math.random() + 1) * maxLen);
            if (maxByCollectingAllSubSequences(arr, m) != max1(arr, m)) {
                System.out.println("Oops");
                break;
            }
        }

        System.out.println("Done");
    }

    /*
    Solution1:
    arr中的数值都不大, 但是m很大

    背包解法: dp[i][j] => arr中 [0...i]范围内数字自由组合, 形成累加和为 j
    j的上界就是arr的整体累加和
     */
    public static int max1(int[] arr, int m) {
        int sum = 0, n = arr.length;
        for (int num : arr) {
            sum += num;
        }
        int max = 0;
        boolean[][] dp = new boolean[n][sum + 1];
        for (int i = 0; i < n; i++) {
            dp[i][0] = arr[i] == 0;
        }
        for (int j = 0; j <= sum; j++) {
            dp[0][j] = arr[0] == j;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (arr[i] == j || dp[i - 1][j]) {
                    dp[i][j] = true;
                }
                if (j - arr[i] >= 0 && dp[i - 1][j - arr[i]]) {
                    dp[i][j] = true;
                }
            }
        }
        for (int j = 0; j <= sum; j++) {
            if (dp[n - 1][j]) {
                max = Math.max(max, j % m);
            }
        }
        return max;
    }


    /*
    Solution2:
    arr中的数值都很大, 但是m不大 (数值很大会导致dp表的sum上界范围过大)

    dp[i][j] => arr中 [0...i]范围内数字自由组合, 形成的累加和 % m 的结果是j
    j的范围 [0...m-1]
     */


    /*
    Solution3:
    arr中的数值都很大, m也很大, 但是arr的长度很小 (eg. 30以内)
    收集所有的子序列, 然后求 % m 的值

    此方法暴力展开所有可能性, 数组每个位置有2种可能性, 所以2^30 => 10^9 超过了 10^8

    分治优化: 先将数组一分为二, 一侧长度为15, 分别先求暴力展开求两侧的答案,
    然后再寻找两侧整合的逻辑

     */
    public static int maxByCollectingAllSubSequences(int[] arr, int m) {
        LinkedList<Integer> path = new LinkedList<>();
        List<Integer> ans = new ArrayList<>();
        process(arr, 0, m, path, ans);
        int max = 0;
        for (Integer each : ans) {
            max = Math.max(each, max);
        }
        return max;
    }

    public static void process(int[] arr, int index, int m, LinkedList<Integer> path, List<Integer> ans) {
        if (index == arr.length) {
            int sum = 0;
            for (Integer i : path) {
                sum += i;
            }
            ans.add(sum % m);
            return;
        }

        process(arr, index + 1, m, path, ans);

        path.addLast(arr[index]);
        process(arr, index + 1, m, path, ans);
        path.pollLast();
    }


    // For test
    private static int[] generateRandomArray(int maxVal, int maxLen) {
        int size = (int) (Math.random() * maxLen) + 1;
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = (int) (Math.random() * maxVal);
        }
        return res;
    }


}
