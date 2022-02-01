package src.main.java.sys.c16;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class C01_BackPack {


    public static boolean sumDP(int[] num, int k) {
//        if(k == 0) return true;
        int[] arr = Arrays.copyOfRange(num, 0, num.length);
        int min = Integer.MAX_VALUE;
        int sum = 0, n = arr.length;
        for (int i : arr) {
            min = Math.min(i, min);
        }
        for (int i = 0; i < n; i++) {
            arr[i] += (min < 0 ? (-min) : 0);
            sum += arr[i];
        }
        int target = (k + (min < 0 ? (-min) : 0));

        if (target > sum || target < 0) return false;

        boolean[][] dp = new boolean[n][sum + 1];
        dp[0][0] = arr[0] == 0;
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] || arr[i] == 0;
        }
        for (int j = 1; j <= sum; j++) {
            dp[0][j] = dp[0][j - 1] || arr[0] == j;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= sum; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - arr[i] >= 0) {
                    dp[i][j] |= dp[i - 1][j - arr[i]];
                }
            }
        }

        return dp[n - 1][target];
    }

    public static boolean sumRecursion(int[] arr, int k) {
        if (k == 0) return true;
        return process(arr, 0, k);
    }

    private static boolean process(int[] arr, int index, int rest) {
//        if (index == arr.length) {
//            return rest == 0;
//        }
        if (rest == 0) return true;
        if (index >= arr.length) return false;
        return process(arr, index + 1, rest) ||
                process(arr, index + 1, rest - arr[index]);
    }


    public static boolean sum2(int[] arr, int k) {
        if (k == 0) return true;
        Map<Integer, Map<Integer, Boolean>> dp = new HashMap<>();
        return processWithMap(arr, 0, k, dp);
    }

    private static boolean processWithMap(int[] arr, int index, int rest, Map<Integer, Map<Integer, Boolean>> dp) {
        if (dp.containsKey(index) && dp.get(index).containsKey(rest)) {
            return dp.get(index).get(rest);
        }
        boolean res;
        if (rest == 0) {
            res = true;
        } else if (index >= arr.length) {
            res = false;
        } else {
            res = processWithMap(arr, index + 1, rest, dp) ||
                    processWithMap(arr, index + 1, rest - arr[index], dp);
        }

        if (!dp.containsKey(index)) {
            dp.put(index, new HashMap<>());
        }
        Map<Integer, Boolean> map = dp.get(index);
        map.put(rest, res);
        dp.put(index, map);
        return res;
    }


    // for test
    public static int[] randomArray(int len, int max) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * ((max << 1) + 1)) - max;
        }
        return arr;
    }

    public static boolean isSum2(int[] arr, int sum) {
        if (sum == 0) {
            return true;
        }
        if (arr == null || arr.length == 0) {
            return false;
        }
        return process2(arr, arr.length - 1, sum, new HashMap<>());
    }

    public static boolean process2(int[] arr, int i, int sum, HashMap<Integer, HashMap<Integer, Boolean>> dp) {
        if (dp.containsKey(i) && dp.get(i).containsKey(sum)) {
            return dp.get(i).get(sum);
        }
        boolean ans = false;
        if (sum == 0) {
            ans = true;
        } else if (i != -1) {
            ans = process2(arr, i - 1, sum, dp) || process2(arr, i - 1, sum - arr[i], dp);
        }
        if (!dp.containsKey(i)) {
            dp.put(i, new HashMap<>());
        }
        dp.get(i).put(sum, ans);
        return ans;
    }


    // 对数器验证所有方法
    public static void main(String[] args) {
        int N = 30;
        int M = 100;
        int testTime = 20000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int size = (int) (Math.random() * (N + 1));
            int[] arr = randomArray(size, M);
            int sum = (int) (Math.random() * ((M << 1) + 1)) - M;
//            boolean ans1 = sum(arr, sum);
            boolean ans2 = sum2(arr, sum);
            boolean ans3 = isSum2(arr, sum);
//            boolean ans4 = isSum4(arr, sum);
            if (ans3 != ans2 /*|| ans3 ^ ans4 || ans1 ^ ans3*/) {
                System.out.println("出错了！");
                System.out.print("arr : ");
                for (int num : arr) {
                    System.out.print(num + " ");
                }
//                System.out.println();
                System.out.println("sum : " + sum);
//                System.out.println("方法一答案 : " + ans1);
                System.out.println("方法二答案 : " + ans2);
                System.out.println("方法三答案 : " + ans3);
//                System.out.println("方法四答案 : " + ans4);
                break;
            }
        }
        System.out.println("测试结束");
    }


}
