package src.main.java.advanced.c1.manacher;

public class ManacherTest {

    public static int manacher(String s) {
        char[] str = manacherString(s);
        int[] pArr = new int[str.length];
        int C = -1, R = -1, l, r;
        int max = 1;
        for (int i = 0; i < str.length; i++) {
            if (i > R) {  // i在R外, 暴力扩展
                pArr[i] = 1;
                l = i - 1;
                r = i + 1;
                while (l >= 0 && r <= str.length - 1) {
                    if (str[l] == str[r]) {
                        l--;
                        r++;
                        pArr[i]++;
                    } else {
                        break;
                    }
                }
            } else {   // i在R内: i<=R
                int i1 = 2 * C - i;  // i的对称点i1
                // 5 [6 7 8 9 10
                int L1 = i1 - pArr[i1] + 1;  // i1的左边界
                int L = 2 * C - R;
                if (L1 > L) { // L1在L内部
                    pArr[i] = pArr[i1];
                } else if (L1 < L) { // L1在L之外
                    pArr[i] = (R - i + 1);
                } else {  // L1压线L
                    pArr[i] = (R - i + 1);
                    l = i - pArr[i];
                    r = R + 1;
                    while (l >= 0 && r <= str.length - 1) {
                        if (str[l] == str[r]) {
                            pArr[i]++;
                            l--;
                            r++;
                        } else {
                            break;
                        }
                    }
                }
            }
            if (i + pArr[i] - 1 > R) {
                R = i + pArr[i] - 1;
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
            if ((i & 1) == 0) {
                res[i] = '#';
            } else {
                res[i] = str[index++];
            }
        }
        return res;
    }

    // for test
    public static int manacherBruteForce(String s) {
        char[] str = manacherString(s);
        int l, r, cur = 1, max = Integer.MIN_VALUE;
        for (int i = 1; i < str.length; i++) {
            l = i - 1;
            r = i + 1;
            cur = 1;
            while (l >= 0 && r <= str.length - 1) {
                if (str[l] == str[r]) {
                    cur++;
                    l--;
                    r++;
                } else {
                    break;
                }
            }
            max = Math.max(max, cur);
        }
        return max - 1;
    }


    private static String generateRandomString(int len) {
        int n = (int) (Math.random() + 1) * len;
        char[] str = new char[n];
        for (int i = 0; i < n; i++) {
            // [0, 25]
            str[i] = (char) ((int) (Math.random() * 5) + 'a');
        }
        return new String(str);
    }

    public static void main(String[] args) {
        int len = 200, times = 200000;
        String s;
        System.out.println("Go");
        for (int i = 0; i < times; i++) {
            s = generateRandomString(len);
            int ans1 = manacher(s);
            int ans2 = Manacher.manacher(s);
            if (ans1 != ans2) {
                System.out.println(s);
                System.out.println("Oops");
                break;
            }
        }
        System.out.println("Done");
    }


}
