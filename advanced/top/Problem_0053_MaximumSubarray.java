package advanced.top;

public class Problem_0053_MaximumSubarray {

    /*
    也可以用一维dp解
     */

    public int maxSubArray(int[] nums) {

        int cur = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            cur += nums[i];
            max = Math.max(cur, max);
            if (cur < 0) {
                cur = 0;
            }
        }
        return max;
    }

    /*
    follow-up
    在0...N-1上选组合, 不能选相邻的数字, 求可选的最大值
     */



}
