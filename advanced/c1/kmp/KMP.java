package advanced.c1.kmp;


/**
 * @author andy-liu
 * @date 2020/5/31 - 5:28 PM
 */
public class KMP {

    public static void main(String[] args) {
        String s = "abcd123";
        String m = "123abc";
        String m1 = "12x";
        System.out.println(s + s);
        System.out.println(getIndexOfBruteForce(s + s, m));
        System.out.println(getIndexOfBruteForce(s + s, m1));
    }


    public static int getIndexOfBruteForce(String s, String m) {
        if (s.length() < m.length()) {
            return -1;
        }
        char[] str = s.toCharArray();
        char[] match = m.toCharArray();
        int x = 0;
        int y = 0;
        while (x < str.length && y < match.length) {
            if (str[x] == match[y]) {
                x++;
                y++;
            } else {
                y = 0;
                x++;
            }
        }
        return y == match.length ? x - y : -1;
    }

    public static int getIndexOf(String s, String m) {
        if (s.length() < m.length()) {
            return -1;
        }
        char[] str = s.toCharArray();
        char[] match = m.toCharArray();
        int x = 0;  //str中当前比对到的位置
        int y = 0;  //match中当前比对到的位置
        int[] next = getNextArray(match);
        while (x < str.length && y < match.length) {
            if (str[x] == match[y]) { //匹配相同, 继续向后比
                x++;
                y++;
            } else if (next[y] != -1) {
                // y还没来到了0位置 (到0位置意味着str当前位置匹配失败,需要str换一个位置继续比)
                //等效于 y != 0
                y = next[y];   // y往回跳, x不动
            } else {
                x++;  // y到了0位置, str要换下一个位置继续匹配
            }
        }
        //出了while
        // 如果y没越界, 那一定是没匹配上, 返回-1
        // 如果匹配上了, 就返回最后x的位置减去y的长度, 就是第一个index位置
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
        int cn = 0;  // cn的位置, 和i - 1位置比较
        while (i < match.length) {
            if (match[i - 1] == match[cn]) {
                next[i++] = ++cn;
                //如果匹配上了, next[i] = cn + 1;
                // i要后移  i++;
                // 因为匹配上了所以cn也要+1, 准备为下一个位置匹配计算用, cn++
            } else if (cn > 0) {
                cn = next[cn];  // cn往前跳, 去到当前cn位置的指标前缀的后一个位置, 数组中即为next[cn]
            } else { // cn = 0
                next[i++] = 0;  //cn跳到头了, 说明以当前i位置的字符没有相同前后缀, 指标为0
            }
        }
        return next;
    }


}
