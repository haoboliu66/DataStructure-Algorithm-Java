package com.hliu.big.c1;

public class C04_MinSwap {

    public static void main(String[] args) {
        String s = "BBGGBGBG";
        System.out.println(minSwap(s));
    }

    // 贪心
    public static int minSwap(String s) {
        char[] str = s.toCharArray();
        int L = 0, index = 0;
        int step = 0;
        for (; index < str.length; ) {
            if (str[index] != 'G') index++;
            else {
                step += (index - L);
                L++;
                index++;
            }
        }
        return step;
    }

}
