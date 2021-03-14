package leetcode;

public class M_Problem_0678_ValidParenthesisString {

    // incorrect solution
    public static boolean checkValidString(String s) {

        char[] str = s.toCharArray();
        int left = 0, right = 0, star = 0;
        if (str[0] == ')' || str[str.length - 1] == '(') return false;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '(') left++;
            if (str[i] == ')') left--;
            if (str[i] == '*') star++;
            if (left < 0) {
                if (star < -left) return false;
                else {
                    star += left;
                    left = 0;
                }
            }

        }
        return left == 0 || (left > 0 && star >= left) || (left < 0 && star >= -left);
    }

    // Lee solution
    public static boolean checkValidString2(String s) {
        char[] str = s.toCharArray();
        int cmin = 0; // 最少需要的 ）
        int cmax = 0; // 最多需要的 )
        for (char c : str) {
            if (c == '(') {
                cmin++;
                cmax++;
            } else if (c == ')') {
                cmax--;
                cmin = Math.max(cmin - 1, 0);
            } else {
                cmax++;
                cmin = Math.max(cmin - 1, 0);
            }
            // cmax < 0 说明 ) 先于 ( 出现
            if (cmax < 0) return false;
        }
        return cmin == 0;
    }


    public static void main(String[] args) {
        String p = "(((((*(()((((*((**(((()()*)()()()*((((**)())*)*)))))))(())(()))())((*()()(((()((()*(())*(()**)()(())";
        boolean res = checkValidString(p);
        System.out.println(res);

    }
}
