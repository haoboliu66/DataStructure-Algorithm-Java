package com.hliu.random.leetcode;

import java.util.ArrayList;
import java.util.List;

public class M_Problem_0267_PalindromePermutationII {

    String s = "aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga";

    public static void main(String[] args) {
        String s = "aabbccddeeffgghh";
        List<String> res = generatePalindromes(s);
        System.out.println(res);
    }

    // 需要优化
    public static List<String> generatePalindromes(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        process(str, 0, ans);
        return ans;
    }

    public static boolean isP(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    public static void process(char[] str, int index, List<String> ans) {
        if (index == str.length) {
            String s = String.valueOf(str);
            if (isP(s)) {
                ans.add(s);
            }
            return;
        }
        boolean[] occurred = new boolean[128];
        for (int i = index; i < str.length; i++) {
            if (!occurred[str[i]]) {
                occurred[str[i]] = true;
                swap(str, index, i);
                process(str, index + 1, ans);
                swap(str, index, i);
            }
        }
    }

    public static void swap(char[] str, int i, int j) {
        char c = str[i];
        str[i] = str[j];
        str[j] = c;
    }



}
