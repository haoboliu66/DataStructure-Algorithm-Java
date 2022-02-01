package src.main.java.advanced.c5.class1;

import java.util.HashMap;

public class C01_MinimumWindowSubstring {

    /*
    76. Minimum Window Substring
     */

    public static String minWindow(String s, String t) {

        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        int all = 0;
        for (int i = 0; i < str2.length; i++) {
            if (map.containsKey(str2[i])) {
                map.put(str2[i], map.get(str2[i]) + 1);
            } else {
                map.put(str2[i], 1);
            }
            all++;
        }

        int L = 0;
        int R = 0;
        int len = Integer.MAX_VALUE;
        String res = "";
        // [L,R)
        while (R < str1.length) {
            //包含字符, 取出,词频--
            if (map.containsKey(str1[R])) {
                map.put(str1[R], map.get(str1[R]) - 1);
                if (map.get(str1[R]) >= 0) {  //有效还款
                    all--;   //总数--
                }
            }

            // all == 0时是有效的窗口范围
            // 1.要收集答案;  2. 要缩小窗口范围
            while (all == 0) {


                if (R - L + 1 < len) {
                    len = R - L + 1;
                    res = s.substring(L, R + 1);
                }

                if (map.containsKey(str1[L])) { // 窗口缩小
                    map.put(str1[L], map.get(str1[L]) + 1);
                    if (map.get(str1[L]) > 0) {
                        all++;
                    }
                }
                //如果当前字符不属于目标字符, 直接过
                L++;

            }

            R++;
        }

        return res;
    }

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(minWindow(s, t));
    }


    /*
    727. Minimum Window Subsequence
    不是正确答案
     */

    public String minWindowSubsequence(String s, String t) {

        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();

        HashMap<Character, Integer> map = new HashMap<>();
        int all = 0;
        for (int i = 0; i < str2.length; i++) {
            if (map.containsKey(str2[i])) {
                map.put(str2[i], map.get(str2[i]) + 1);
            } else {
                map.put(str2[i], 1);
            }
            all++;
        }
        int resL = 0;
        int resR = 0;
        int len = Integer.MAX_VALUE;
        int L = 0;
        int R = 0;
        while (R < str1.length) {
            if (map.containsKey(str1[R])) {
                map.put(str1[R], map.get(str1[R]) - 1);
                if (map.get(str1[R]) >= 0) {
                    all--;
                }
            }
            while (all == 0) {
                if (R - L + 1 < len) {
                    len = R - L + 1;
                    resL = L;
                    resR = R;
                }
                if (map.containsKey(str1[L])) {
                    map.put(str1[L], map.get(str1[L]) + 1);
                    if (map.get(str1[L]) > 0) {
                        all++;
                    }
                }
                L++;
            }


            R++;
        }

        return s.substring(resL, resR + 1);
    }


}
