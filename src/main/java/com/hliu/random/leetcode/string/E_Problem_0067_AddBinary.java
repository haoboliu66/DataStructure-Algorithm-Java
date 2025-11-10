package com.hliu.random.leetcode.string;

public class E_Problem_0067_AddBinary {

    public static String addBinary(String a, String b) {
        char[] str1 = a.toCharArray(), str2 = b.toCharArray();
        int m = str1.length, n = str2.length, index1 = m - 1, index2 = n - 1;
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        char cur;
        for (; index1 >= 0 && index2 >= 0; index1--, index2--) {

            if (str1[index1] == '1' && str2[index2] == '1') {
                cur = (carry == 0) ? '0' : '1';
                sb.insert(0, cur);
                carry = 1;

            } else if (str1[index1] == '1' && str2[index2] == '0' || str1[index1] == '0' && str2[index2] == '1') {
                if (carry == 0) {
                    cur = '1';
                } else {
                    cur = '0';
                    carry = 1;
                }
                sb.insert(0, cur);

            } else {
                if (carry == 0) {
                    cur = '0';
                } else {
                    cur = '1';
                    carry = 0;
                }
                sb.insert(0, cur);
            }
        }
        while (index1 >= 0) {
            cur = '0';
            if (str1[index1] == '1') {
                cur = '1';
            }
            if (carry == 0) {
                sb.insert(0, cur);
            } else {
                if (cur == '1') {
                    sb.insert(0, 0);
                    carry = 1;
                } else {
                    sb.insert(0, 1);
                    carry = 0;
                }
            }
            index1--;
        }
        while (index2 >= 0) {
            cur = '0';
            if (str2[index2] == '1') {
                cur = '1';
            }
            if (carry == 0) {
                sb.insert(0, cur);
            } else {
                if (cur == '1') {
                    sb.insert(0, 0);
                    carry = 1;
                } else {
                    sb.insert(0, 1);
                    carry = 0;
                }

            }
            index2--;
        }
        if (carry == 1) {
            sb.insert(0, 1);
        }
        return sb.toString();
    }

}
