package advanced.c4.class3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author andy-liu
 * @date 2020/7/20 - 2:47 PM
 */
public class C02_WorldBreak {

    /*
    139. Word Break
    140. Word Break II
     */

    //暴力尝试
    public static int ways1(String str, List<String> words) {

        HashSet<String> set = new HashSet<>(words);
        return process(str, 0, set);
    }

    //最差情况下i位置后面所有都要枚举
    public static int process(String str, int i, HashSet<String> set) {
        if (i == str.length()) {
            return 1;
        }
        int ways = 0;
        //枚举从i开始的前缀串, 如果前缀串是某个贴纸, 才继续递归
        for (int end = i; end < str.length(); end++) {
            String prefix = str.substring(i, end + 1);
            if (set.contains(prefix)) {
                ways += process(str, end + 1, set);
            }
        }
        return ways;
    }

    /* --------------------------------------------------------------------------------- */
    // O(N^3)
    public static int ways1DP(String str, List<String> words) {
        HashSet<String> set = new HashSet<>(words);
        int N = str.length();
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int i = N - 1; i >= 0; i--) {
            for (int end = i; end < str.length(); end++) {
                String prefix = str.substring(i, end + 1);
                if (set.contains(prefix)) {
                    dp[i] += dp[end + 1];
                }
            }
        }
        return dp[0];
    }


    /* --------------------------------------------------------------------------------- */
    //前缀树优化,替代set
    private static class TrieNode {
        TrieNode[] nexts;
        boolean end;

        public TrieNode() {
            nexts = new TrieNode[26];
        }
    }

    public static void fillWord(TrieNode head, String word) {
        char[] str = word.toCharArray();
        int index = 0;
        TrieNode cur = head;
        for (int i = 0; i < str.length; i++) {
            index = str[i] - 'a';
            if (cur.nexts[index] == null) {
                cur.nexts[index] = new TrieNode();
            }
            cur = cur.nexts[index];
        }
        cur.end = true;
    }

    public static int ways2(String s, List<String> words) {
        TrieNode head = new TrieNode();
        for (String word : words) {
            fillWord(head, word);
        }
        char[] str = s.toCharArray();

        return process(str, head, 0);
    }

    public static int process(char[] str, TrieNode head, int index) {
        if (index == str.length) {
            return 1;
        }
        int ways = 0;
        TrieNode cur = head;
        for (int end = index; end < str.length; end++) {
            int path = str[end] - 'a';
            if (cur.nexts[path] == null) {
                break;
            }
            cur = cur.nexts[path];
            if (cur.end) {
                ways += process(str, head, end + 1);
            }

        }
        return ways;
    }


    public static int ways2DP(String s, List<String> words) {
        TrieNode head = new TrieNode();
        for (String word : words) {
            fillWord(head, word);
        }
        int N = s.length();
        char[] str = s.toCharArray();
        int[] dp = new int[N + 1];
        dp[N] = 1;

        for (int i = N - 1; i >= 0; i--) {
            TrieNode cur = head;
            for (int end = i; end < N; end++) {
                if (cur.nexts[str[end] - 'a'] == null) {
                    break;
                }
                cur = cur.nexts[str[end] - 'a'];
                if (cur.end) {
                    dp[i] += dp[end + 1];
                }
            }
        }
        return dp[0];


    }


    public static void main(String[] args) {
        String s = "catsanddog";
        List<String> words = new ArrayList<>();
        words.add("cat");
        words.add("cats");
        words.add("and");
        words.add("sand");
        words.add("dog");
        System.out.println(ways1(s, words));
        System.out.println(ways1DP(s, words));
    }
}
