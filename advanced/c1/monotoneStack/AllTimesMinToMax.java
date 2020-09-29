package advanced.c1.monotoneStack;

import java.util.Stack;

public class AllTimesMinToMax {

    /*
    给定一个只包含正整数的数组arr, arr中任何一个子数组sub, 一定都可以算出(sub累加和) * (sub中的最小值)是什么
    求在所有子数组中, 这个值最大是多少
     */

    //暴力解法
    public static int max1(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int minNum = Integer.MAX_VALUE;
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                    minNum = Math.min(minNum, arr[k]);
                }
                max = Math.max(max, minNum * sum);
            }
        }
        return max;
    }


    // sum (L...R) == sum (0...R) - sum (0...L-1)
    public static int[] getPrefixSumArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[] prefixSum = new int[arr.length];
        prefixSum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            prefixSum[i] = arr[i] + prefixSum[i - 1];
        }
        return prefixSum;
    }

    public static int getMaxByMinMultiplySum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] sums = getPrefixSumArray(arr);
        int size = sums.length;
        Stack<Integer> incrStack = new Stack<>();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            while (!incrStack.isEmpty() && arr[i] <= arr[incrStack.peek()]) {
                int popIndex = incrStack.pop();
                //左边界如果没有比他小的, 那就是一直延伸到0
                //右边界就是 i - 1的位置, 即最近比他小的数字的前一个位置
                max = Math.max(max, (incrStack.isEmpty() ? sums[i - 1] : (sums[i - 1] - sums[incrStack.peek()])) * arr[popIndex]);
            }
            incrStack.push(i);
        }
        while (!incrStack.isEmpty()) {
            int popIndex = incrStack.pop();
            //左边界如果没有比他小的, 那就是一直延伸到0
            //此时在清空栈的时候, 每一个数字右侧都没有比他小的数字了, 所以有边界一直延伸到arr.length - 1
            max = Math.max(max, (incrStack.isEmpty() ? sums[size - 1] : (sums[size - 1] - sums[incrStack.peek()])) * arr[popIndex]);
        }
        return max;
    }


    //单调栈
    public static int max2(int[] arr) {
        int size = arr.length;
        int[] sums = new int[size];
        sums[0] = arr[0];
        for (int i = 1; i < size; i++) {
            sums[i] = sums[i - 1] + arr[i];
        }
        int max = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < size; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                int j = stack.pop();
                max = Math.max(max, (stack.isEmpty() ? sums[i - 1] : (sums[i - 1] - sums[stack.peek()])) * arr[j]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int j = stack.pop();
            max = Math.max(max, (stack.isEmpty() ? sums[size - 1] : (sums[size - 1] - sums[stack.peek()])) * arr[j]);
        }
        return max;
    }


    public static int[] gerenareRondomArray() {
        int[] arr = new int[(int) (Math.random() * 20) + 10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 101);
        }
        return arr;
    }


    public static void main(String[] args) {
        int testTimes = 2000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = gerenareRondomArray();
            if (getMaxByMinMultiplySum(arr) != max2(arr)) {
                System.out.println("FUCK!");
                System.out.println(getMaxByMinMultiplySum(arr));
                System.out.println(max2(arr));
                break;
            }
        }
        System.out.println("test finish");

    }

}
