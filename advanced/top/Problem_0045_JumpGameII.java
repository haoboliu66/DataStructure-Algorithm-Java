package advanced.top;


public class Problem_0045_JumpGameII {

    public int jump(int[] nums) {

        int step = 0;
        int cur = 0;
        int next = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > cur) {
                step++;
                cur = next;
            }
            next = Math.max(next, i + nums[i]);
        }

        return step;
    }

}
