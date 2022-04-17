package src.main.java.leetcode;

import java.util.HashMap;

public class Problem_0220_ContainsDuplicateIII {

    public static boolean containsNearbyAlmostDuplicate(int[] arr, int k, int t) {

        if (arr == null || arr.length == 0) return false;


        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (Math.abs(arr[j] - arr[i]) <= t && Math.abs(i - j) <= k) return true;
            }

        }

        return false;
    }


    public static boolean containsNearbyAlmostDuplicate2(int[] arr, int k, int t) {

        if (arr == null || arr.length == 0) return false;

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            for (int value = 0; value <= Math.abs(i - t); value++) {
                if (map.containsKey(value) && Math.abs(i - map.get(value)) <= k) return true;
            }
            map.put(arr[i], i);
        }

        return false;
    }

}
