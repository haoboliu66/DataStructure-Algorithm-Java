package leetcode;

public class M_Problem_0926_FlipStringToMonotoneIncreasing {


    public static int minFlipsMonoIncr(String s) {
        if (s == null || s.length() == 0) return -1;
        char[] str = s.toCharArray();
        int n = str.length;
        int[] oneAtLeft = new int[n];
        int[] zeroAtRight = new int[n];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            oneAtLeft[i] = (i == 0) ? (str[i] == '0' ? 1 : 0) : (oneAtLeft[i - 1] += (str[i] == '0' ? 1 : 0));
        }
        for (int i = n - 1; i >= 0; i--) {
            zeroAtRight[i] = (i == n - 1) ? (str[i] == '1' ? 1 : 0) : (zeroAtRight[i + 1] += (str[i] == '1' ? 1 : 0));
        }
        for (int i = 0; i < n; i++) {
            min = Math.min(min, Math.min(oneAtLeft[i],zeroAtRight[i]));
        }
        return min;
    }

    public static int minFlipsMonoIncr2(String s) {
        if (s == null || s.length() == 0) return -1;

        char[] str = s.toCharArray();

        int zeroCount = 0, oneCount = 0;

        int min = Integer.MAX_VALUE;
        int zeroPass = 0, onePass = 0;

        for (int i = 0; i < str.length; i++) {
            if (str[i] == '0') zeroCount++;
            else oneCount++;
        }
        int zeroAtRight = zeroCount, oneAtLeft = oneCount;
        min = Math.min(zeroCount, oneAtLeft);

        // fewer 1 to the left, less 0 to the right\
        // 左边最少的1，右边最少的0
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '0') {
                zeroPass++;
                zeroAtRight -= zeroPass;
            } else {
                onePass++;
                oneAtLeft -= onePass;
            }
            min = Math.min(zeroAtRight + oneAtLeft, min);

        }

        return min;
    }
}
