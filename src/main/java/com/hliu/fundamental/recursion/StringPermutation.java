package com.hliu.fundamental.recursion;

import java.util.*;

public class StringPermutation {

    public static List<String> permutation(String str) {
        ArrayList<String> ans = new ArrayList<>();
        process(str.toCharArray(), 0, ans);
        return ans;
    }

    // abc
    private static void process(char[] str, int i, List<String> res) {
        if (i == str.length) {
            res.add(new String(str));
            return;
        }
        for (int j = i; j < str.length; j++) {
            swap(str, i, j);
            process(str, i + 1, res);
            swap(str, i, j);
        }

    }

    // no repeat
    public static ArrayList<String> permutationNoRepeat(String str) {
        ArrayList<String> ans = new ArrayList<>();
        process2(str.toCharArray(), 0, ans);
        return ans;
    }


    private static void process2(char[] str, int i, List<String> res) {
        if (i == str.length) {
            res.add(new String(str));
            return;
        }
        Map<Character, Integer> charMap = new HashMap<>();//Map不是全局共享, 只在某一层上使用
        for (int j = i; j < str.length; j++) {
            if (!charMap.containsKey(str[j])) {
                charMap.put(str[j], 1);
                swap(str, i, j);
                process2(str, i + 1, res);
                swap(str, i, j);
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<String> v2 = permutationNoRepeat("abc");
        List<String> v1 = permutation("abc");
        System.out.println(v1);
        System.out.println(v2);
    }


    private static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


}
