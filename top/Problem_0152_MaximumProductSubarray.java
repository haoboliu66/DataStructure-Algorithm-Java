package advanced.top;

public class Problem_0152_MaximumProductSubarray {

//    public int maxProduct(int[] nums) {
//
//        return process(nums, 0, 1);
//    }

    // arr中已经走到了i位置, [0...i-1]累积了product的值
//    public int process(int[] arr, int i, int p, int max) {
//        if (i == arr.length) {
//            return max;
//        }
//        // i <= arr.length - 1
//        if (arr[i] > 0) {
//            return process(arr, i + 1, p * arr[i], p * arr[i]);
//        } else if (arr[i] == 0) {
//            int m = Math.max(max, p);
//            return process(arr, i + 1)
//        }
//
//
//    }


}
