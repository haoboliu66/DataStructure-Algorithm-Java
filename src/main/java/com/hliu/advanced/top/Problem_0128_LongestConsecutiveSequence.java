package src.main.java.advanced.top;

import java.util.HashMap;
import java.util.Map;

public class Problem_0128_LongestConsecutiveSequence {

    public static int longestConsecutive(int[] arr) {

        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length < 2) {
            return 1;
        }
        // arr.length >= 2
        Map<Integer, Integer> map = new HashMap<>();
        int len = 0;

        for (int num : arr) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
                int preLen = map.containsKey(num - 1) ? map.get(num - 1) : 0;
                int postLen = map.containsKey(num + 1) ? map.get(num + 1) : 0;
                int total = preLen + postLen + 1;

//                map.put(num, total);
                map.put(num - preLen, total);  // update the smallest reachable key
                map.put(num + postLen, total);  // update the largest reachable key

                len = Math.max(len, total);
            }
        }

        return len;
    }

    public static void main(String[] args) {
        int[] arr = {100, 1, 200, 3, 0, 4, 2};
//        int[] arr1 = {0, 0};
        System.out.println(longestConsecutive(arr));
    }

}
