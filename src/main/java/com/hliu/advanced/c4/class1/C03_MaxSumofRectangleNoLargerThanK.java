package src.main.java.advanced.c4.class1;

import java.util.TreeSet;

public class C03_MaxSumofRectangleNoLargerThanK {

   //  https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/

    public static int maxSumSubmatrix(int[][] matrix, int k) {

        int[] arr;
        int max = Integer.MIN_VALUE, n = matrix[0].length;
        for (int i = 0; i < matrix.length; i++) {
            arr = matrix[i];
            max = Math.max(max, getMaxSumLessthanK(arr, k));
            for (int j = i + 1; j < matrix.length; j++) {
                for (int col = 0; col < n; col++) {
                    arr[col] += matrix[j][col];
                }
                max = Math.max(max, getMaxSumLessthanK(arr, k));
            }
        }
        return max;
    }

    public static int getMaxSumLessthanK(int[] arr, int k) {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            Integer preSum = set.ceiling(sum - k);
            if (preSum != null) {
                max = Math.max(max, sum - preSum);
            }
            set.add(sum);
        }
        return max;
    }

}
