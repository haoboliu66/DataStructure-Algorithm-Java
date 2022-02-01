package src.main.java.leetcode;

public class E_Problem_0680_ValidPalindromeII {

    /*
    Check from left and right at the same time until the first different pair.
    Now we have something like a****b, where a and b are different.
    We need to delete either a or b to make it a palindrome.
     */
    public static void main(String[] args) {
//        String s = "aguoke patgbnv fqmgml cupuu fxoohd fpgjdm ysgvhmvff cnqxj jxqnc ffvmhvgsy mdjgpf dhooxf uupuc u lmgmqf vnbgtap ekouga";
//        boolean res = validPalindrome(s);
//        System.out.println(res);
        int a, b, m;
        for (int i = 0; i < 100000; i++) {
            a = (int) (Math.random() * 1000);
            b = (int) (Math.random() * 1000);
            m = (int) (Math.random() * 1000);
            int res1 = a % m + b % m;
            int res2 = (a + b) % m;
            if (res1 != res2) {
                System.out.println("Oops");
                System.out.printf("a: %d, b: %d, m: %d, res1: %d, res2: %d", a, b, m, res1, res2);
                break;
            }
        }
    }

    public static boolean validPalindrome(String s) {
        char[] str = s.toCharArray();
        int left = 0, right = str.length - 1;
        int miss = 0;
        while (left < right) {
            if (str[left] != str[right]) {
                int ignoreLeftIndex = left + 1;
                int ignoreRightIndex = right - 1;
                boolean res1 = isPalindrome(str, ignoreLeftIndex, right);
                boolean res2 = isPalindrome(str, left, ignoreRightIndex);
                return res1 || res2;
            }
            left++;
            right--;
        }

        return true;
    }

    private static boolean isPalindrome(char[] str, int i, int j) {
        int left = i, right = j;
        while (left < right) {
            if (str[left] != str[right]) return false;
            left++;
            right--;
        }
        return true;
    }

}
