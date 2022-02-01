package src.main.java.advanced.c3.class5;

import java.util.*;

public class C01_DeleteMinCost {

    /*
    Q: 给定s1和s2, 问s2最少删多少个字符能变成s1的子串
     */
    // 方法一: 找到所有的s2的子序列, 按长度由大到小排序, 依次去和s1做KMP
    public static int minCost1(String s1, String s2) {
        ArrayList<String> subStrings = new ArrayList<>();
        process(s2.toCharArray(), 0, "", subStrings);
        subStrings.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.length() - o1.length();
            }
        });

        for (String sub : subStrings) {
            if (s1.indexOf(sub) != -1) {
                return s2.length() - sub.length();
            }
        }
        return s2.length();
    }

    public static void process(char[] str2, int index, String path, ArrayList<String> list) {
        if (index == str2.length) {
            list.add(path);
            return;
        }
        process(str2, index + 1, path, list);
        process(str2, index + 1, path + str2[index], list);
    }


    public static int KMP(String s1, String s2) {
        if (s1.length() < s2.length()) {
            return -1;
        }
        char[] str1 = s1.toCharArray();
        char[] match = s2.toCharArray();
        int[] next = getNextArray(match);
        int x = 0;
        int y = 0;
        while (x < str1.length && y < match.length) {

            if (str1[x] == match[y]) {
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

    //方法二: 生成s1的所有子串, 找到子串和s2的最小编辑距离
    public static int minCost2(String s1, String s2) {
        int ans = Integer.MAX_VALUE;
        char[] str2 = s2.toCharArray();
        for (int start = 0; start < s1.length(); start++) {
            for (int end = start + 1; end <= s1.length(); end++) {
                // 因为substring方法右侧是开区间, 所以end最后要取到s1.length()
                char[] sub = s1.substring(start, end).toCharArray();
                ans = Math.min(ans, distance(str2, sub));
            }
        }
        return ans;
    }

    // edit str2 to s1Sub, only by deletion
    public static int distance(char[] str2, char[] s1Sub) {
        int row = str2.length;
        int col = s1Sub.length;

        int[][] dp = new int[row][col];
        dp[0][0] = str2[0] == s1Sub[0] ? 0 : Integer.MAX_VALUE;

        for (int i = 1; i < col; i++) {
            dp[0][i] = Integer.MAX_VALUE;
        }
        for (int j = 1; j < row; j++) {
            dp[j][0] = (dp[j - 1][0] != Integer.MAX_VALUE || str2[j] == s1Sub[0]) ? j : Integer.MAX_VALUE;
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Integer.MAX_VALUE;

                if (dp[i - 1][j] != Integer.MAX_VALUE) {
                    dp[i][j] = dp[i - 1][j] + 1;
                }
                if (str2[i] == s1Sub[j] && dp[i - 1][j - 1] != Integer.MAX_VALUE) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
                }

            }
        }

        return dp[row - 1][col - 1];
    }


    //方法三: 方法二的优化
    public static int minCost3(String s1, String s2) {

        return 1;
    }


    // 以下的代码仅为了测试使用
    // 先生成一个随机字符串，比如 123abcd456
    // 再生根据这个字符串的随机某部分比如abcd，随机添加字符之后生成abckd
    // 生成的123abcd456和abckd都返回，这个方法就是本题的随机样本发生器
    public static String[] generateTwoStrings() {
        int len = (int) (Math.random() * 20) + 5;
        int adds = (int) (Math.random() * 5);
        char[] chas = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
                'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D',
                'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
                'Z'};
        int N = chas.length;
        char[] str1 = new char[len];
        for (int i = 0; i < str1.length; i++) {
            str1[i] = chas[(int) (Math.random() * N)];
        }
        int a = (int) (Math.random() * (str1.length));
        int b = (int) (Math.random() * (str1.length));
        int left = Math.min(a, b);
        int right = Math.max(a, b) + 1;
        char[] part = String.valueOf(str1).substring(left, right).toCharArray();
        char[] str2 = new char[part.length + adds];
        int count = 0;
        while (count != adds) {
            int i = (int) (Math.random() * str2.length);
            if (str2[i] == 0) {
                str2[i] = chas[(int) (Math.random() * N)];
                count++;
            }
        }
        int index = 0;
        for (int i = 0; i < str2.length; i++) {
            if (str2[i] == 0) {
                str2[i] = part[index++];
            }
        }
        return new String[]{String.valueOf(str1), String.valueOf(str2)};
    }


    public static void main(String[] args) {
        int testTime = 100;
        boolean pass = true;
        for (int i = 0; i < testTime; i++) {
            String[] test = generateTwoStrings();
            if (minCost1(test[0], test[1]) != minCost2(test[0], test[1])) {
                pass = false;
                System.out.println(test[0]);
                System.out.println(test[1]);
                System.out.println(minCost1(test[0], test[1]));
                System.out.println(minCost2(test[0], test[1]));
                break;
            }
        }
        System.out.println("test pass : " + pass);
    }

}
