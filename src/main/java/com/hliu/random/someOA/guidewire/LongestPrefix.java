package com.hliu.random.someOA.guidewire;

public class LongestPrefix {

    public static void main(String[] args) {
        int[] arr = {6, 42, 11, 7, 1, 42};
        int X = 7, Y = 42;
        System.out.println(solution(X, Y, arr));
    }

    public static int solution(int X, int Y, int[] arr) {
        int n = arr.length;
        int XCount = 0, YCount = 0;
        int position = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == X) {
                XCount++;
            }
            if (arr[i] == Y) {
                YCount++;
            }
            if (XCount == YCount) {
                position = Math.max(position, i);
            }
        }
        return position;
    }


}

