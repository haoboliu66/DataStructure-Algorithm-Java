package com.hliu.fundamental.recursion.classic;

public class PlayCard {

    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int first = first(arr, 0, arr.length - 1);
        int second = second(arr, 0, arr.length - 1);
        if (first > second) {
            System.out.println("A wins the game");
        } else {
            System.out.println("B wins the game");
        }
        return Math.max(first, second);
    }

    public static int first(int[] arr, int left, int right) {
        if (left == right) {
            return arr[left];
        }
        int getLeft = arr[left] + second(arr, left + 1, right); //先手拿L + (L + 1)...R后手
        int getRight = arr[right] + second(arr, left, right - 1);//先手拿R + L...(R-1)后手
        return Math.max(getLeft, getRight);
    }

    public static int second(int[] arr, int left, int right) {
        if (left == right) {
            return 0;
        }
        int leftGone = first(arr, left + 1, right);
        int rightGone = first(arr, left, right - 1);
        return Math.min(leftGone, rightGone);
    }

    /* DP solution  */
    public static int win2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] f = new int[N][N];
        int[][] s = new int[N][N];
        for (int i = 0; i < N; i++) { //填第一条对角线
            f[i][i] = arr[i];
        }
        for (int i = 1; i < N; i++) { //填后面的对角线
            int L = 0;
            int R = i;
            while (L < N && R < N) {
                f[L][R] = Math.max(arr[L] + s[L + 1][R], arr[R] + s[L][R - 1]);
                s[L][R] = Math.min(f[L + 1][R], f[L][R - 1]);

                L++;
                R++;
            }
        }
        if (f[0][arr.length - 1] > s[0][arr.length - 1]) {
            System.out.println("A wins the game");
        } else {
            System.out.println("B wins the game");
        }
        return Math.max(f[0][arr.length - 1], s[0][arr.length - 1]);
    }

    public static void main(String[] args) {
        int[] arr = {1, 300, 100, 101,301};
        int r1 = win1(arr);
        System.out.println(r1);
        int r2 = win2(arr);
        System.out.println(r2);
    }


}
