package advanced.top;

public class Problem_0011_ContainerWithMostWater {


    /*
    不是求每个位置的最优解, 而是看有没有可能让结果变更大 (可能性的舍弃)
     */

    public static int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int max = Integer.MIN_VALUE;
        while (left < right) {
            max = Math.max(max, Math.min(height[left], height[right]) * (right - left));
            if (height[left] < height[right]) {
                left++;
            } else {
                right++;
            }
        }
        return max;
    }


}
