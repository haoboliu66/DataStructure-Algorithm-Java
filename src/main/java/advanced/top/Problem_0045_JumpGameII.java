package src.main.java.advanced.top;


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


    // 1306. Jump Game III

    /*
    Given an array of non-negative integers arr,
    you are initially positioned at start index of the array.
    When you are at index i, you can jump to i + arr[i] or i - arr[i],
    check if you can reach to any index with value 0.
     */

    public static boolean canReach(int[] arr, int i) {

        return process(arr, i, i + arr[i], 0) || process(arr, i, i - arr[i], 0);
    }


    // 从start位置, 跳到end位置, 这个步数k不会超过arr.length - 1
    public static boolean process(int[] arr, int start, int end, int k) {

        if (start < 0 || start > arr.length - 1 || end < 0 || end > arr.length - 1) {
            return false;
        }

        if (arr[end] == 0) {
            return true;
        }

        if (k > arr.length - 1) {
            return false;
        }

        return process(arr, end, end + arr[end], k + 1) || process(arr, end, end - arr[end], k + 1);
    }

    // by Lee
    public boolean canReach2(int[] A, int i) {
        return 0 <= i && i < A.length && A[i] >= 0 &&
                ((A[i] = -A[i]) == 0 || canReach(A, i + A[i]) || canReach(A, i - A[i]));
    }

    public static void main(String[] args) {
        int[] arr = {3,0,2,1,2};
        int start = 2;
        System.out.println(canReach(arr, start));
    }



}
