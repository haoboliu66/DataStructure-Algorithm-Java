package src.main.java.someOA.codility;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Correct {

    private static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * maxSize) + 1];
        for (int i = 0; i < arr.length; i++) {
            int cur = (int) (Math.random() * (maxValue + 1));
            if (cur == 0) cur++;
            arr[i] = cur;
        }
        Arrays.sort(arr);
        return arr;
    }

    public static void main(String[] args) {

    }

    public static boolean right(int[] arr, int K) {
        int n = arr.length;
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= K; i++) {
            set.add(i);
        }
        for (int i = 0; i < n; i++) {
            if (!set.contains(arr[i])) {
                return false;
            }
        }
        return true;
    }

    public static boolean solution(int[] A, int K) {
        int n = A.length;
        for (int i = 0; i < n - 1; i++) {
            if (A[i] + 1 < A[i + 1])
                return false;
        }
        if (A[0] != 1 || A[n - 1] != K)
            return false;
        else
            return true;
    }
}

