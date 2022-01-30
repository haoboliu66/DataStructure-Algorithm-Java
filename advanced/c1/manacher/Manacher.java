package advanced.c1.manacher;

// LPS - Longest Palindromic Substring
public class Manacher {

    public static int manacher(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        //把s处理成manacher串
        //123 --->  #1#2#3#
        char[] str = manacherString(s);
        int[] pArr = new int[str.length];
        int R = -1;
        int C = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < str.length; i++) {
            // i >= R, 表示i在R外(因为R是第一个违规的位置,即回文最右边界+1的位置),
            // 所以R > i表示i在R内           pArr[2 * C - i]对称点的回文半径
            // 每种情况都有一个至少不用验的区域
            // i在R外, 就只有他自己本身i,长度为1
            // i在R内:
            //	1) i'回文区域整个在L..R内(pArr[i'])
            //  2) i'回文区域部分在L..R内(R-i)
            //  3) i'回文区域左测压线L(R-i)
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
            while (i + pArr[i] < str.length && i - pArr[i] > -1) {
                if (str[i + pArr[i]] == str[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
            max = Math.max(max, pArr[i]);
        }
        return max - 1;
    }

    public static char[] manacherString(String s) {
        char[] str = s.toCharArray();
        char[] res = new char[str.length * 2 + 1];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            // 判断奇偶数:  (i & 1) == 0
            res[i] = (i & 1) == 0 ? '#' : str[index++];
        }
        return res;
    }


    // for test
    public static int right(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = manacherString(s);
        int max = 0;
        for (int i = 0; i < str.length; i++) {
            int L = i - 1;
            int R = i + 1;
            while (L >= 0 && R < str.length && str[L] == str[R]) {
                L--;
                R++;
            }
            max = Math.max(max, R - L - 1);
        }
        return max / 2;
    }

    // for test
    public static String getRandomString(int possibilities, int size) {
        char[] ans = new char[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        int possibilities = 5;
        int strSize = 20;
        int testTimes = 500000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            System.out.println(manacherString(str));
            if (manacher(str) != right(str)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }


}
