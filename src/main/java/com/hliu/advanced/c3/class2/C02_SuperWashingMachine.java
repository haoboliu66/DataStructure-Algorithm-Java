package src.main.java.advanced.c3.class2;

public class C02_SuperWashingMachine {

    /*
    https://leetcode.com/problems/super-washing-machines/
     */

    //要特别记忆, 贪心算法
    public static int findMinMoves(int[] arr) {
        int sum = 0;
        int N = arr.length;
        for (int i : arr) {
            sum += i;
        }
        if (sum % N != 0) {
            return -1;
        }
        int avg = sum / N; //每个机器要均分的件数
        int ans = Integer.MIN_VALUE;
        int leftSum = 0;
        int rightSum = 0;
        for (int i = 0; i < N; i++) {
            rightSum = sum - leftSum - arr[i]; //右侧的现在总数
            int leftToHandle = leftSum - i * avg; //左侧需要处理的件数
            int rightToHandle = rightSum - (N - i - 1) * avg;//右侧需要处理的件数
            if (leftToHandle < 0 && rightToHandle < 0) {
                ans = Math.max(ans, Math.abs(leftToHandle) + Math.abs(rightToHandle));
            } else {
                ans = Math.max(ans, Math.max(Math.abs(leftToHandle), Math.abs(rightToHandle)));
            }
            leftSum += arr[i]; //左侧的现有总数
        }

        return ans;
    }
}
