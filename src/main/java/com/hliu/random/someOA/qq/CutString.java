package com.hliu.random.someOA.qq;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CutString {

    public static void main(String[] args) {
        String s = "11111111";
        int[] res = cutString0(s);
        System.out.println(Arrays.toString(res));
    }

    public static int[] cutString0(String s) {
        char[] str = s.toCharArray();
        int n = str.length;
        int[] zeros = new int[n];
        int[] ones = new int[n];
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            if (str[i] == '0') {
                zeros[i] = i == 0 ? 1 : 1 + zeros[i - 1];
                ones[i] = i == 0 ? 0 : ones[i - 1];
            } else {
                ones[i] = i == 0 ? 1 : 1 + ones[i - 1];
                zeros[i] = i == 0 ? 0 : zeros[i - 1];
            }
        }

        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        // 0 -> {1, 5}
        for (int i = 0; i < n; i++) {
            int zeroCount = zeros[i];
            int oneCount = ones[i];
            if (zeroCount != 0 || oneCount != 0) {
                int gcd = gcd(zeroCount, oneCount);
                zeroCount /= gcd;
                oneCount /= gcd;
            }
            res[i] = map.containsKey(zeroCount) && map.get(zeroCount).containsKey(oneCount) ? map.get(zeroCount).get(oneCount) + 1 : 1;
            addToMap(zeroCount, oneCount, map);
        }
        return res;
    }

    private static void addToMap(int zeroCount, int oneCount, Map<Integer, Map<Integer, Integer>> map) {
        if (!map.containsKey(zeroCount)) {
            map.put(zeroCount, new HashMap<>());
        }
        map.get(zeroCount).put(oneCount, 1 + map.get(zeroCount).getOrDefault(oneCount, 0));
    }

    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }


}
