package src.main.java.sys.c3;

import java.util.ArrayList;
import java.util.List;

public class C07_ClosestSubsequenceSum {

    /* 分治 */

    static List<Integer> list = new ArrayList<>();
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        int[] arr = {5, -7, 3, 5};
        int[] arr2 = {1, 2, 3};
        int[] arr3 = {7, -9, 15, -2};
        int goal = 6;
        int goal2 = -7;
        int goal3 = -5;
        int res = minAbsDifference(arr3, goal3);
        processWithList(arr3, 0, goal3, 0, list);
        //System.out.println(res);
           System.out.println(list);
    }

    public static int minAbsDifference(int[] arr, int goal) {
        return 0;
    }


    public static void processWithList(int[] arr, int i, int goal, int sum, List<Integer> res) {
        if (i == arr.length) {
            res.add(sum);
            return;
        }

        processWithList(arr, i + 1, goal, sum + arr[i], list);
        processWithList(arr, i + 1, goal, sum, list);
    }


}
