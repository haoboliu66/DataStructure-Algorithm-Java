package advanced.c2.arraysum;


/**
 * @author andy-liu
 * @date 2020/6/10 - 11:51 PM
 */
public class LongestSumSubArrayLengthInPositiveArray {
    // 类似问题 leetcode 209;
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
        int left = 0;
        int right = 0;
        while (right < arr.length) {

            if (windowSum == K) {
                len = Math.max(len, right - left + 1);
                windowSum -= arr[left++];  //先移动L或R都可以

            } else if (windowSum < K) {
                right++;
                if (right == arr.length) {
                    break;
                }
                windowSum += arr[right];

            } else { // windowSum > K
                windowSum -= arr[left++];
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
