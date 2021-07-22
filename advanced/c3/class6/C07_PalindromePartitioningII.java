package advanced.c3.class6;


public class C07_PMinParts {
    /*
    LC 131. Palindrome Partitioning
    LC 132. Palindrome Partitioning II
    */

    /*
    LC 647. Palindromic Substrings  => 判断String [i...j] 上是不是回文
     */
    // 范围上尝试模型
    public static boolean[][] isPalindrome(String s) {

        char[] str = s.toCharArray();
        int N = str.length;
        boolean[][] dp = new boolean[N][N];
        // dp[i][j] 表示字符串从i...j是不是回文, 所以表格没有下半区
        for (int i = 0; i < N; i++) { //对角线, 一个字符一定是true
            dp[i][i] = true;
        }
        for (int i = 0; i < N - 1; i++) { //第二条对角线, 两个字符相等即为true
            dp[i][i + 1] = str[i] == str[i + 1];
        }

        for (int row = N - 3; row >= 0; row--) {
            for (int col = row + 2; col < N; col++) {
                //从i...j是回文, 那么一定有str[i]==str[j], 然后就依赖于i+1...j-1
                if (str[row] == str[col]) {
                    dp[row][col] = dp[row + 1][col - 1];
                }
            }
        }
        return dp;
    }

    //范围上尝试模型 + 从左到右的尝试模型
    public static int minCut2(String s) {
        if (s == null || s.length() == 0 || s.length() == 1) {
            return 0;
        }

        char[] str = s.toCharArray();
        int N = str.length;
        boolean[][] isPalin = new boolean[N][N];
        // dp[i][j] 表示字符串从i...j是不是回文, 所以表格没有下半区
        for (int i = 0; i < N; i++) { //对角线, 一个字符一定是true
            isPalin[i][i] = true;
        }

        for (int i = 0; i < N - 1; i++) { //第二条对角线, 两个字符相等即为true
            isPalin[i][i + 1] = str[i] == str[i + 1];
        }

        for (int row = N - 3; row >= 0; row--) {
            for (int col = row + 2; col < N; col++) {
                //从i...j是回文, 那么一定有str[i]==str[j], 然后就dp[i][j]依赖于i+1...j-1
                if (str[row] == str[col]) {
                    isPalin[row][col] = isPalin[row + 1][col - 1];
                }
            }
        }

        int[] dp = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[N] = 0;
        for (int i = N - 1; i >= 0; i--) {
            for (int end = i; end < N; end++) {
                if (isPalin[i][end]) {
                    dp[i] = Math.min(dp[i], 1 + dp[end + 1]);
                }
            }
        }
        return dp[0] - 1;
    }

    //暴力尝试O(N^3)
    public static int minCut1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        //因为递归过程求的是最少分割成多少份, 所以切几下就是递归结果减去1
        return process(str, 0) - 1;
    }


    public static int process(char[] str, int index) {
        if (index == str.length) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for (int end = index; end < str.length; end++) {
            if (isPalindrome(str, index, end)) {
                ans = Math.min(ans, 1 + process(str, end + 1));
            }
        }
        //求的是最少切割成多少份
        return ans;
    }


    public static boolean isPalindrome(char[] str, int L, int R) {
        if (L == R) {
            return true;
        }

        int left = L;
        int right = R;
        while (left <= right) {
            if (str[left] != str[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }


    public static void main(String[] args) {
        String s = "aabasdeqwe12412321faaaabbcccwqsdb";
        System.out.println(minCut2(s));
        System.out.println(minCut1(s));
    }


}
