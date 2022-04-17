package src.main.java.someOA.amazon;

import java.util.Arrays;

public class CellCompete {

    // 0和n-1两侧默认都是inactive

    public static void main(String[] args) {
        int[] arr = {0, 1, 0, 1, 1, 0, 0, 0, 1};
        // [1, 0, 0, 1, 1, 1, 0, 1, 0]  d1
        // [0, 1, 1, 1, 0, 1, 0, 0, 1]  d2
        // [1, 1, 0, 1, 0, 0, 1, 1, 0]  d3
        int[] res = cellCompete(arr, 3);
        System.out.println(Arrays.toString(res));
    }


    public static int[] cellCompete(int[] arr, int days) {
        if (arr == null || arr.length == 0 || days == 0) return arr;
        int n = arr.length;
        int[] cur = arr;
        int[] next = new int[n];
        for (int j = 1; j <= days; j++) {
            for (int i = 0; i < n; i++) {
                if (i == 0) {
                    next[i] = cur[i + 1] == 1 ? 1 : 0;
                } else if (i == n - 1) {
                    next[i] = cur[i - 1] == 1 ? 1 : 0;
                } else {
                    next[i] = (cur[i + 1] ^ cur[i - 1]) == 1 ? 1 : 0;
                }
            }
            cur = next;
            next = new int[n];
        }
        return cur;
    }

}
