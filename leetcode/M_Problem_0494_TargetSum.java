package leetcode;

public class M_Problem_0494_TargetSum {


    // brute force
    public int findTargetSumWays(int[] nums, int S) {
        return process(nums, 0, S);
    }


    public int process(int[] arr, int index, int rest) {
        if (index == arr.length) return rest == 0 ? 1 : 0;


        int p1 = process(arr, index + 1, rest - arr[index]);
        int p2 = process(arr, index + 1, rest + arr[index]);

        return p1 + p2;
    }

    public int findTargetSumWays2(int[] nums, int S) {
        int sum = 0;
        for(int i : nums){
            sum += i;
        }
        int[][] dp = new int[nums.length][sum + 1];
        return process2(nums, 0, S, dp);
    }


    public int process2(int[] arr, int index, int rest, int[][] dp) {
        if(dp[index][rest] != 0){
            return dp[index][rest];
        }

        if (index == arr.length) {
            dp[index][rest] = rest == 0 ? 1 : 0;
            return dp[index][rest];
        }


        int p1 = process2(arr, index + 1, rest - arr[index],dp);
        int p2 = process2(arr, index + 1, rest + arr[index],dp);

        dp[index][rest] = p1 + p2;
        return dp[index][rest];
    }

}
