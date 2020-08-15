package advanced.c3.class6;

/**
 * @author andy-liu
 * @date 2020/7/9 - 11:34 AM
 */
public class C03_JumpGame {

    public static int jumpGame(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int step = 0; //当前走的步数
        int curR = 0; //当前走的步数能到达的最右位置
        int next = 0; //再下一步能达到的最右位置
        for (int i = 0; i < nums.length; i++) {
            if (i <= curR) {
                next = Math.max(nums[i] + i, next);
            } else {
                step++;
                curR = next;
            }
        }
        return step;
    }
}
