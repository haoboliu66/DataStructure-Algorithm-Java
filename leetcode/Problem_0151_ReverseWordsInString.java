package leetcode;

public class Problem_0151_ReverseWordsInString {

    public String reverseWords(String s) {
        if (s == null) return null;

        char[] a = s.toCharArray();
        int n = a.length;

        // step 1. reverse the whole string
        reverse(a, 0, n - 1);
        // step 2. reverse each word
        reverseWords(a, n);
        // step 3. clean up spaces
        return cleanSpaces(a, n);
    }

    void reverseWords(char[] a, int n) {
        int i = 0, j = 0;

        while (i < n) {
            while (i < j || i < n && a[i] == ' ') i++; // skip spaces
            while (j < i || j < n && a[j] != ' ') j++; // skip non spaces
            reverse(a, i, j - 1);                      // reverse the word
        }
    }

    // trim leading, trailing and multiple spaces
    String cleanSpaces(char[] a, int n) {
        int i = 0, j = 0;

        while (j < n) {
            while (j < n && a[j] == ' ') j++;             // skip spaces
            while (j < n && a[j] != ' ') a[i++] = a[j++]; // keep non spaces
            while (j < n && a[j] == ' ') j++;             // skip spaces
            if (j < n) a[i++] = ' ';                      // keep only one space
        }

        return new String(a).substring(0, i);
    }

    // reverse a[] from a[i] to a[j]
    private void reverse(char[] a, int i, int j) {
        while (i < j) {
            char t = a[i];
            a[i++] = a[j];
            a[j--] = t;
        }
    }
    /* ------------------------------------------------------------- */

    public static String reverseWords2(String s) {

        String[] strs = process(s);
        int left = 0, right = strs.length - 1;
        while(left <= right){
            swap(strs,left++, right--);
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < strs.length; i++){
            sb.append(strs[i]).append(i != strs.length - 1? " ": "");
        }
        return sb.toString();
    }

    public static String[] process(String s) {
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (i == 0 || i == chars.length - 1) {
                if(chars[i] == ' ') continue;
                sb.append(chars[i]);
            }else{
                // i != 0 && i != s.length() - 1
                if(chars[i] == ' ' && (sb.length() > 0 && sb.charAt(sb.length() - 1) == ' ')){
                    continue;
                }
                sb.append(chars[i]);
            }
        }
        return sb.toString().trim().split(" ");
    }

    public static void swap(String[] strs, int i, int j){
        String tmp = strs[i];
        strs[i] = strs[j];
        strs[j] = tmp;
    }


}
