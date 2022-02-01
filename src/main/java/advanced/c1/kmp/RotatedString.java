package src.main.java.advanced.c1.kmp;

public class RotatedString {

    public static void main(String[] args) {
        String s = "abcd123";
        String m = "123abcd";
//        String m1 = "asbcd123a";
//        System.out.println(s + s);
//        System.out.println(isRotatedString(s, m));
//        System.out.println(isRotatedString(s, m1));
        System.out.println(isRotated(s, m));
    }

    public static boolean isRotated(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int index1 = 0;
        int index2 = 0;
        for (; index2 < str2.length; ) {
            while (str1[index1] != str2[index2]) {
                index1 = next(str1, index1);
            }
            // [index1] == [index2]
            while (index2 < str2.length && str1[index1] == str2[index2]) {
                index1 = next(str1, index1);
                index2++;
            }
            if (index2 != str2.length) return false;
        }
        return true;
    }

    public static int next(char[] str, int index) {
        return index == str.length - 1 ? 0 : index + 1;
    }

    public static boolean isRotatedString(String s, String m) {
        String s1 = s + s;
        System.out.println(getIndexOf(s1, m));
        return getIndexOf(s1, m) != -1;
    }

    public static int getIndexOf(String s, String m) {
        if (s.length() < m.length()) {
            return -1;
        }
        char[] str = s.toCharArray();
        char[] match = m.toCharArray();
        int[] next = getNextArray(match);
        int x = 0;
        int y = 0;
        while (x < str.length && y < match.length) {
            if (str[x] == match[y]) {
                x++;
                y++;
            } else if (y != 0) {
                y = next[y];
            } else {
                x++;
            }
        }
        return y == match.length ? x - y : -1;
    }


    public static int[] getNextArray(char[] match) {
        if (match.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[match.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0;
        while (i < match.length) {
            if (match[i - 1] == match[cn]) {
                next[i] = cn + 1;
                i++;
                cn++;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }


}
