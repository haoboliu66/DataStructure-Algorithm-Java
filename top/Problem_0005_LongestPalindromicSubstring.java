package top;

import java.util.Arrays;

public class Problem_0005_LongestPalindromicSubstring {

    /*
    Manacher
     */
    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        char[] str = manacherString(s);
        int[] pArr = new int[str.length];
        int R = -1;
        int C = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < str.length; i++) {
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
            while (i + pArr[i] < str.length && i - pArr[i] > -1) {
                if (str[i + pArr[i]] == str[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            if(i + pArr[i] > R){
                R = i + pArr[i];
                C = i;
            }
            max = Math.max(max, pArr[i]);
        }

        return null;
    }

    public static char[] manacherString(String s) {
        char[] str = s.toCharArray();
        int N = str.length;
        char[] res = new char[(N << 1) + 1];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = ((i & 1) == 0) ? '*' : str[index++];
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "abcd";
        char[] str = manacherString(s);
        System.out.println(Arrays.toString(str));
    }

}
