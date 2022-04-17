package src.main.java.sys.c9;

public class C02_1_MinimumRemoveMakeValidParentheses {

    public static void main(String[] args) {
        String s = "))((";
        String s1 = "a)b(c)d";
        String s2 = "(a(b(c)d)";
        System.out.println(minRemoveToMakeValid(s2));
    }

    public static String minRemoveToMakeValid(String s) {

        for (int i = 0; i < s.length(); i++) {

        }


        return "";

    }

    public static String process0(String s, int index, char[] p) {
        int count = 0;
        String res = "";
        for (int i = index; i < s.length(); i++) {
            if (s.charAt(i) == p[0]) {
                count++;
            }
            if (s.charAt(i) == p[1]) {
                count--;
            }
            //  ....).....
            if (count < 0) {
                if (isValid(s.substring(0, i), p)) {
                    res = s.substring(0, i) + process0(s.substring(i + 1), 0, p);
                    break;
                } else {
                    res = "" + process0(s.substring(i + 1), 0, p);
                }
            }
        }
        String tmp = s;
        if (!isValid(s, p)) {
            tmp = "";
        }
        return "".equals(res) ? tmp : res;
    }

    private static boolean isValid(String s, char[] p) {
        char[] str = s.toCharArray();
        int count = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == p[0]) {
                count++;
            }
            if (str[i] == p[1]) {
                count--;
            }
            if (count < 0) return false;
        }
        return count == 0;
    }


}
