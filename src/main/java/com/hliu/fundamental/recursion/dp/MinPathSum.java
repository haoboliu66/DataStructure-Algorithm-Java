package com.hliu.fundamental.recursion.dp;


public class MinPathSum {

    public static int ways(int[][] arr) {
        return process(arr, 0, 0);
    }

    public static int process(int[][] arr, int x, int y) {
        if (x > arr.length - 1 || y > arr.length - 1) {
            return 0;
        }
        if (x == arr.length - 1 && y == arr.length - 1) {
            return arr[x][y];
        }
//        int max = Integer.MIN_VALUE;
        int p1 = process(arr, x + 1, y);
        int p2 = process(arr, x, y + 1);
        int max = Math.max(p1, p2);

        return max;
    }

}
