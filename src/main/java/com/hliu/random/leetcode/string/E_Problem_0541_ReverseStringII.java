package com.hliu.random.leetcode.string;

public class E_Problem_0541_ReverseStringII {

    public static void main(String[] args) {
        String s = "abcdefg";
        int k = 2;

        String res = reverseStr(s, k);
        System.out.println(res);
    }

    public static String reverseStr(String s, int k) {

        int n = s.length();
        char[] str = s.toCharArray();
        int groupLen = 2 * k;  // 4

        int group = (n / groupLen) + ((n % groupLen) > 0 ? 1 : 0);

        for (int i = 1, start = 0; i <= group; i++) {

            int end = Math.min(start + k - 1, n - 1);

            reverse(str, start, end);

            start += groupLen;

        }
        return new String(str);
    }

    public static void reverse(char[] str, int left, int right) {
        for (int i = left, j = right; i < j; i++, j--) {
            swap(str, i, j);
        }
    }

    private static void swap(char[] str, int i, int j) {
        char tmp = str[i];
        str[i] = str[j];
        str[j] = tmp;
    }

}
