package src.main.java.advanced.top;

public class Problem_0152_MaximumProductSubarray {

    public static int maxProduct(int[] arr) {
        int N = arr.length;
        int[] dpMax = new int[N];
        int[] dpMin = new int[N];
        dpMax[0] = arr[0];
        dpMin[0] = arr[0];
        int max = arr[0];
        // dpMax[i]: 0...i max Product
        // dpMin[i]: 0...i min Product
        for (int i = 1; i < N; i++) {
            // {2, 3, -2, 4}
            dpMax[i] = Math.max(Math.max(arr[i], arr[i] * dpMax[i - 1]), dpMin[i - 1] * arr[i]);
            dpMin[i] = Math.min(Math.min(arr[i], arr[i] * dpMax[i - 1]), dpMin[i - 1] * arr[i]);
            max = Math.max(max, dpMax[i]);
        }

        return max;
    }




    public static int maxProduct2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return Integer.MIN_VALUE;
        }

        int max = arr[0];
        int min = arr[0];
        int res = max;
        for (int i = 1; i < arr.length; i++) {
            int curMax = Math.max(arr[i], Math.max(arr[i] * max, arr[i] * min));
            int curMin = Math.min(arr[i], Math.min(arr[i] * max, arr[i] * min));
            res = Math.max(res, curMax);

            max = curMax;
            min = curMin;
        }

        return res;
    }

}
