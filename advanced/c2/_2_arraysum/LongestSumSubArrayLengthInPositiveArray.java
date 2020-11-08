package advanced.c2._2_arraysum;


import java.util.Arrays;

public class LongestSumSubArrayLengthInPositiveArray {

    // 类似问题 lc 209. Minimum Size Subarray Sum
    private static class minSizeSubArray {

        public static void main(String[] args) {
            int[] arr1 = {2, 3, 1, 2, 4, 3};
            int k1 = 7;
            int[] arr2 = {1, 2, 3, 4, 5};
            int k2 = 1;

        }

        // brute force
        public int minSubArrayLen1(int s, int[] nums) {
            if (nums == null || nums.length == 0) return 0;
            int sum = 0;
            for (int n : nums) {
                sum += n;
            }
            if (sum < s) return 0;

            int len = Integer.MAX_VALUE;
            for (int i = 0; i < nums.length; i++) {
                sum = 0;
                for (int j = i; j < nums.length; j++) {
                    sum += nums[j];
                    if (sum >= s) {
                        len = Math.min(len, j - i + 1);
                        break;
                    }
                }
            }
            return len;
        }

        public int minSubArrayLen2(int s, int[] nums) {
            if (nums == null || nums.length == 0) return 0;
            int sum = 0;
            for (int n : nums) {
                sum += n;
            }
            if (sum < s) return 0;

            int L = 0;
            int R = 0;
            int len = Integer.MAX_VALUE;
            sum = nums[0];

            while (L < nums.length) {
                while (sum >= s) {
                    len = Math.min(len, R - L + 1);
                    if (len == 1) return 1;
                    sum -= nums[L++];
                }

                // sum < s
                R++;
                if (R == nums.length) break;
                sum += nums[R];
            }

            return len;
        }
    }


    /*
    Q:一个数组arr[] 都是正数, 一个累加和K, 求:累加和正好等于K的子数组最长是多长
    滑动窗口
     */
    public static int getMaxLength(int[] arr, int K) {
        if (arr == null || arr.length == 0 || K <= 0) {
            return 0;
        }
        int len = 0; //记录最大值, 返回的最终结果
        int windowSum = arr[0];
        int L = 0;
        int R = 0;
        while (R < arr.length) {
            if (windowSum == K) {
                len = Math.max(len, R - L + 1);
                windowSum -= arr[L++];  //先移动L或R都可以

            } else if (windowSum < K) {
                R++;
                if (R == arr.length) {
                    break;
                }
                windowSum += arr[R];

            } else { // windowSum > K
                windowSum -= arr[L++];
            }
        }

        return len;
    }

    public static int right(int[] arr, int K) {
        int len = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                if (sum == K) {
                    len = Math.max(len, j - i + 1);
                }
            }
            sum = 0;
        }
        return len;
    }


    // for test
    public static int[] generatePositiveArray(int size, int value) {
        int[] ans = new int[size];
        for (int i = 0; i != size; i++) {
            ans[i] = (int) (Math.random() * value) + 1;
        }
        return ans;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len = 50;
        int value = 1000;
        int testTime = 5000000;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generatePositiveArray(len, value);
            int K = (int) (Math.random() * value) + 1;
            int ans1 = getMaxLength(arr, K);
            int ans2 = right(arr, K);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println("K : " + K);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("test end");
    }
}
