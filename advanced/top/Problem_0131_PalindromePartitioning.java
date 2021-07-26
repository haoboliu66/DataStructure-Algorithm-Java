package advanced.top;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
    Input: s = "aab"
    Output: [["a","a","b"],["aa","b"]]
 */
public class Problem_0131_PalindromePartitioning {

    public static void main(String[] args) {
        String s = "abbab";
        List<List<String>> res = partition2(s);
        System.out.println(res);
    }

    // Solution1 - Brute Force
    public static List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        LinkedList<String> path = new LinkedList<>();
        process(s, 0, path, ans);
        return ans;
    }

    // 0...index已经处理好, 处理 index...N 的分割情况
    public static void process(String s, int index, LinkedList<String> path, List<List<String>> ans) {
        if (index == s.length()) {
            ArrayList<String> list = new ArrayList<>(path);
            ans.add(list);
            return;
        }
        // 从index...N-1, 分别取1,2,3,4.... N-index个字符
        for (int i = 1; i <= s.length() - index; i++) {
            String before = s.substring(index, index + i);
            if (isPalindrome(before)) {
                path.addLast(before);
                process(s, index + i, path, ans);
                path.pollLast();
            }
        }
    }

    public static boolean isPalindrome(String s) {
        if (s.length() == 1) return true;
        int left = 0, right = s.length() - 1;
        char[] str = s.toCharArray();
        while (left < right) {
            if (str[left] != str[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /* --------------------------- */
    // Solution2 - 优化回文判断的过程
    public static List<List<String>> partition2(String s) {
        List<List<String>> ans = new ArrayList<>();
        char[] str = s.toCharArray();
        int n = str.length;
        boolean[][] isPalindrome = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            isPalindrome[i][i] = true;
        }
        for (int i = 0; i < n - 1; i++) {
            isPalindrome[i][i + 1] = str[i] == str[i + 1];
        }
        for (int j = 2; j < n; j++) {
            for (int i = 0; i < n - j; i++) {
                if (str[i] == str[i + j]) {
                    isPalindrome[i][i + j] = isPalindrome[i + 1][i + j - 1];
                }
            }
        }
        LinkedList<String> path = new LinkedList<>();
        process2(s, 0, path, ans, isPalindrome);
        return ans;
    }

    // 0...index已经处理好, 处理 index...N 的分割情况
    public static void process2(String s, int index, LinkedList<String> path, List<List<String>> ans, boolean[][] isPalindrome) {
        if (index == s.length()) {
            ArrayList<String> list = new ArrayList<>(path);
            ans.add(list);
            return;
        }
        // 从index...N-1, 分别取1,2,3,4.... N-index个字符
        for (int i = 1; i <= s.length() - index; i++) {
            boolean beforeIsPalindrome = isPalindrome[index][index + i - 1];
            if (beforeIsPalindrome) {
                path.addLast(s.substring(index, index + i));
                process2(s, index + i, path, ans, isPalindrome);
                path.pollLast();
            }
        }
    }


}
