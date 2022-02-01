package src.main.java.leetcode.string;

public class E_Problem_0557_ReverseWordsInAStringIII {

    public String reverseWords(String s) {
        char[] str = s.toCharArray();
        int i = 0;
        int left, right = 0;
        while (i < str.length) {

            while (str[i] == ' ') i++;
            left = i;

            while (i < str.length && str[i] != ' ') i++;
            right = i - 1;

            reverse(str, left, right);
        }
        return new String(str);
    }

    private void reverse(char[] str, int left, int right) {
        for (int i = left, j = right; i < j; i++, j--) {
            swap(str, i, j);
        }
    }

    private void swap(char[] str, int i, int j) {
        char tmp = str[i];
        str[i] = str[j];
        str[j] = tmp;
    }

}
