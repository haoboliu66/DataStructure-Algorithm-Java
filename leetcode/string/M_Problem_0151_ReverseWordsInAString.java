package leetcode.string;

public class M_Problem_0151_ReverseWordsInAString {

    /*
    Input: s = "the sky is blue"
    Output: "blue is sky the"

    Input: s = "  hello world  "
    Output: "world hello"
    Explanation: Your reversed string should not contain leading or trailing spaces.

    Input: s = "a good   example"
    Output: "example good a"
    Explanation: You need to reduce multiple spaces
    between two words to a single space in the reversed string.
     */

    public static void main(String[] args) {
        String s = "a good   example";
        System.out.println(reverseWords(s));
    }

    public static String reverseWords(String s) {

        char[] str = s.toCharArray();
        int n = str.length;
        reverse(str, 0, n - 1);
        reverseSeparateWords(str);

        return cleanSpace(str);
    }

    public static String cleanSpace(char[] str) {
        int left = 0, right = str.length - 1;
        while (left < right) {
            if (str[left] != ' ' && str[right] != ' ') break;
            if (str[left] == ' ') {
                left++;
            }
            if (str[right] == ' ') {
                right--;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = left; i <= right; ) {
            while (i <= right && str[i] != ' ') {
                sb.append(str[i++]);
            }
            while (i < right && str[i + 1] == ' ') {
                i++;
            }
            if (i <= right) { // i == 6
                sb.append(str[i++]);
            }
        }

        return sb.toString();
    }


    private static void reverseSeparateWords(char[] str) {
        int i = 0;
        int left, right = 0;
        while (i < str.length) {
            while (i < str.length && str[i] == ' ') {
                i++;
            }
            left = i;
            while (i < str.length && str[i] != ' ') {
                i++;
            }
            right = i - 1;

            reverse(str, left, right);
        }
    }

    private static void reverse(char[] str, int left, int right) {
        for (int i = left, j = right; i < j; ) {
            char tmp = str[i];
            str[i++] = str[j];
            str[j--] = tmp;
        }
    }


    public static String reverseWords2(String s) {

        String[] strs = process(s);
        int left = 0, right = strs.length - 1;

        while (left <= right) {
            swap(strs, left++, right--);
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < strs.length; i++) {
            sb.append(strs[i]).append(i != strs.length - 1 ? " " : "");
        }
        return sb.toString();
    }


    public static void swap(String[] strs, int i, int j) {
        String tmp = strs[i];
        strs[i] = strs[j];
        strs[j] = tmp;
    }


    public static String[] process(String s) {
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (i == 0 || i == chars.length - 1) {
                if (chars[i] == ' ') continue;
                sb.append(chars[i]);
            } else {
                // i != 0 && i != s.length() - 1
                if (chars[i] == ' ' && (sb.length() > 0 && sb.charAt(sb.length() - 1) == ' ')) {
                    continue;
                }
                sb.append(chars[i]);
            }
        }
        return sb.toString().trim().split(" ");
    }


}
