package src.main.java.leetcode.string;

public class M_Problem_0186_ReverseWordsInAStringII {

    public void reverseWords(char[] s) {
        reverse(s);


    }

    private void reverseEachWord(char[] str){

    }

    private void reverse(char[] str) {
        for (int i = 0, j = str.length - 1; i < j; ) {
            char tmp = str[i];
            str[i++] = str[j];
            str[j--] = tmp;
        }
    }


}
