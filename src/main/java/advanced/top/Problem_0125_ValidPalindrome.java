package src.main.java.advanced.top;

public class Problem_0125_ValidPalindrome {

    public static boolean isPalindrome(String s) {

        char[] str = s.toCharArray();
        int i = 0, j = str.length - 1;

        for (; i < j; ) {

            if (!valid(str[i])) {
                i++;
                continue;
            }

            if (!valid(str[j])) {
                j--;
                continue;
            }

            if (valid(str[i]) && valid(str[j]) &&

                    Character.toLowerCase(str[i]) == Character.toLowerCase(str[j])

            ) {
                i++;
                j--;
            } else {
                return false;
            }
        }

        return true;
    }


    public static boolean valid(char c) {
        return Character.isLetterOrDigit(c);
    }


    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        System.out.println(isPalindrome(s));

    }
}
