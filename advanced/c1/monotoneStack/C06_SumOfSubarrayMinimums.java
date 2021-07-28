package advanced.c1.monotoneStack;

import java.util.Arrays;
import java.util.Stack;

// https://leetcode.com/problems/sum-of-subarray-minimums/
//Similiar: https://leetcode.com/problems/number-of-valid-subarrays/

public class C06_SumOfSubarrayMinimums {


    // for AC
    public static int sumSubarrayMinsMod(int[] arr) {
        int mod = 1000000007;
        long sum = 0;
        int[] right = rightLessOrEqual(arr);
        int[] left = leftLess(arr);
        for (int i = 0; i < arr.length; i++) {
            long leftIndex = left[i];   // has to be long
            long rightIndex = right[i];  // has to be long
            sum = (sum + arr[i] * ((i - leftIndex) * (rightIndex - i))) % mod;
        }
        return (int) (sum % mod);
    }


    public static int sumSubarrayMins(int[] arr) {
        int sum = 0;
        int[] right = rightLessOrEqual(arr);
        int[] left = leftLess(arr);
        for (int i = 0; i < arr.length; i++) {
            int leftIndex = left[i];
            int rightIndex = right[i];
            sum += arr[i] * ((i - leftIndex) * (rightIndex - i));
        }
        return sum;
    }

    public static int[] rightLessOrEqual(int[] arr) {
        int[] right = new int[arr.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[i] <= arr[stack.peek()]) {
                int popIndex = stack.pop();
                right[popIndex] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            right[stack.pop()] = arr.length;
        }
        return right;
    }

    public static int[] leftLess(int[] arr) {
        int[] left = new int[arr.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[i] < arr[stack.peek()]) {
                int popIndex = stack.pop();
                left[popIndex] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            left[stack.pop()] = -1;
        }
        return left;
    }


    public static int CorrectsumSubarrayMins(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int minInRange = findMin(arr, i, j);
                sum += minInRange;
            }
        }
        return sum;
    }

    private static int findMin(int[] arr, int L, int R) {
        int min = Integer.MAX_VALUE;
        for (int i = L; i <= R; i++) {
            min = Math.min(min, arr[i]);
        }
        return min;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * maxSize) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr;
        int times = 100000, size = 50, value = 500;
        System.out.println("Go");
        for (int i = 0; i < times; i++) {
            arr = generateRandomArray(size, value);
            int right = CorrectsumSubarrayMins(arr);
            int res1 = sumSubarrayMins(arr);
            if (right != res1) {
                System.out.println("Oops");
                System.out.println(right);
                System.out.println(res1);
                System.out.println(Arrays.toString(arr));
                break;
            }
        }
        System.out.println("Done");
    }

}
