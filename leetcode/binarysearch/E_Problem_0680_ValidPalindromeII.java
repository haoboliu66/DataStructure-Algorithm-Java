package leetcode.binarysearch;

public class E_Problem_0680_ValidPalindromeII {

    public static boolean validPalindrome(String s) {
        char[] str = s.toCharArray();
        int n = str.length;
        int left = 0, right = n - 1;
        for (; left != right - 1; left++, right--) {
            if (str[left] != str[right]) {
            }

        }

        return false;
    }
}
