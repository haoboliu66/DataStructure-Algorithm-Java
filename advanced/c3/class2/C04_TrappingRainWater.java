package advanced.c3.class2;

/**
 * @author andy-liu
 * @date 2020/6/27 - 8:41 AM
 */
public class C04_TrappingRainWater {

    /*
    LeetCode 42 Trapping Rain Water
     */

    public static int trap(int[] arr) {
        if (arr == null || arr.length < 3) {
            return 0;
        }
        //int N = arr.length;
        int left = 1;
        int right = arr.length - 2;
        int leftMax = arr[0];
        int rightMax = arr[arr.length - 1];
        int water = 0;
        while (left <= right) {
            if (leftMax <= rightMax) {
                water += Math.max(leftMax - arr[left], 0);
                leftMax = Math.max(arr[left++], leftMax);
            } else {
                water += Math.max(rightMax - arr[right], 0);
                rightMax = Math.max(rightMax, arr[right--]);
            }
        }
        return water;
    }
}
