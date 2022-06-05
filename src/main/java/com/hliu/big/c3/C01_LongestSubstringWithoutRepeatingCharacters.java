package com.hliu.sys.c3;

import java.util.HashMap;
import java.util.Map;

public class C01_LongestSubstringWithoutRepeatingCharacters {

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || "".equals(s)) return 0;
        char[] str = s.toCharArray();
        int max = Integer.MIN_VALUE, pre = -1;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length; i++) {
            if (!map.containsKey(str[i])) {
                map.put(str[i], i);
                // 1.以当前字符为界
                int p1 = -1;
                // 2. 以i-1的字符为界
                int p2 = pre;
                // 当前字符首先的边界:
                // 10(lastIndex) 11 12 13(pre) 14 15 16 17
                pre = Math.max(p2, p1);
                max = Math.max(max, i - Math.max(p1, p2));
            } else {
                // 10 11 12 13 14 15 16 17
                // 1.以当前字符为界
                int p1 = map.get(str[i]);
                // 2. 以i-1的字符为界
                int p2 = pre;
                pre = Math.max(p2, p1);
                max = Math.max(max, i - Math.max(p1, p2));

                map.put(str[i], i);
            }
        }
        return max;
    }


    public static int lengthOfLongestSubstring1(String s) {
        if (s == null || "".equals(s)) return 0;
        char[] str = s.toCharArray();
        int max = Integer.MIN_VALUE, pre = -1;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length; i++) {
            if (!map.containsKey(str[i])) {
                map.put(str[i], i);
                // 1.以当前字符为界
                // 2. 以i-1的字符为界
                // 当前字符首先的边界:
                // 10(lastIndex) 11 12 13(pre) 14 15 16 17
                max = Math.max(max, i - pre);
            } else {
                // 10 11 12 13 14 15 16 17
                // 1.以当前字符为界
                // 2. 以i-1的字符为界
                pre = Math.max(map.get(str[i]), pre);
                max = Math.max(max, i - pre);

                map.put(str[i], i);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(s));
    }

}
