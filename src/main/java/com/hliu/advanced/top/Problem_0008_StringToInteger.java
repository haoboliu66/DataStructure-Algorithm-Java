package com.hliu.advanced.top;

import org.junit.Test;

public class Problem_0008_StringToInteger {

    @Test
    public void test() {
        String s = "    -42";
        System.out.println(myAtoi(s));
    }

    public int myAtoi(String s) {

        String s1 = removeWhitespace(s);
        if (!(s1.charAt(0) >= '0' && s1.charAt(0) <= '9') && s1.charAt(0) != '-') {
            return 0;
        }

        s = keepDigit(s1);

        boolean isNegative = false;
        if (s.charAt(0) == '-') {
            isNegative = true;
        }
        System.out.println(s);
        long res = 0;
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9')
                res = res * 10 + (c - '0');
        }

        if (res > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if (res < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }

        return isNegative ? (int) res * (-1) : (int) res;
    }

    public String keepDigit(String s) {
        char[] str = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : str) {
            if (c >= '0' && c <= '9' || c == '-') {
                sb.append(c);
            }
        }

        return sb.toString();
    }


    public String removeWhitespace(String s) {

        char[] str = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : str) {
            if (c != ' ') {
                sb.append(c);
            }
        }

        return sb.toString();
    }

}

