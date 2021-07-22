package advanced.c4.class3;

import java.util.*;

public class C02_WorldBreak {

    public static boolean wordBreak0(String s, List<String> wordDict) {
        return process0(s, 0, new HashSet<>(wordDict));
    }

    public static boolean process0(String s, int index, Set<String> wordDict) {
        if (index == s.length()) {
            return true;
        }
        boolean ans = false;
        for (int end = index; end < s.length(); end++) {
            String prefix = s.substring(index, end + 1);
            if (wordDict.contains(prefix)) {
                ans |= process0(s, end + 1, wordDict);
            }
        }
        return ans;
    }

    public static boolean wordBreakMemo(String s, List<String> wordDict) {
        int[] dp = new int[s.length() + 1];
        Arrays.fill(dp, -1);
        return processMemo(s, 0, new HashSet<>(wordDict), dp);
    }

    public static boolean processMemo(String s, int index, Set<String> wordDict, int[] dp) {
        if (dp[index] != -1) {
            return dp[index] == 1;
        }
        if (index == s.length()) {
            dp[index] = 1;
            return true;
        }

        boolean ans = false;
        for (int end = index; end < s.length(); end++) {
            String prefix = s.substring(index, end + 1);
            if (wordDict.contains(prefix)) {
                ans |= processMemo(s, end + 1, wordDict, dp);
                if (ans) break;
            }
        }
        dp[index] = ans ? 1 : 0;
        return ans;
    }

    public static class Node {
        Node[] nexts = new Node[128];
        boolean end = false;
    }

    public static class Trie {
        Node head = new Node();

        public void add(String s) {
            char[] str = s.toCharArray();
            Node cur = head;
            for (int i = 0; i < str.length; i++) {
                if (cur.nexts[str[i]] == null) {
                    cur.nexts[str[i]] = new Node();
                }
                cur = cur.nexts[str[i]];
            }
            cur.end = true;
        }
    }

    public static boolean wordBreak1(String s, List<String> wordDict) {
        Trie trie = new Trie();
        Node head = trie.head;
        for (String word : wordDict) {
            trie.add(word);
        }
        return processWithTrie(s, 0, head);
    }

    public static boolean processWithTrie(String s, int index, Node head) {
        if (index == s.length()) {
            return true;
        }
        Node cur = head;
        boolean ans = false;

        // has path to c
        for (int end = index; end < s.length(); end++) {
            char c = s.charAt(end);
            if (cur.nexts[c] == null) break;
            cur = cur.nexts[c];
            if (cur.end) {
                ans |= processWithTrie(s, end + 1, head);
            }
        }
        return ans;
    }

    public static boolean wordBreakDP(String s, List<String> wordDict) {
        Trie trie = new Trie();
        Node head = trie.head;
        for (String word : wordDict) {
            trie.add(word);
        }

        int n = s.length();
        int[] dp = new int[n + 1];
        dp[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            Node cur = head;
            for (int end = i; end < n; end++) {
                char c = s.charAt(end);
                if (cur.nexts[c] == null) break;
                cur = cur.nexts[c];
                if (cur.end) {
                    dp[i] += (dp[end + 1]);
                }
            }
        }
        return dp[0] >= 1;
    }


    /* --------------------------------------------------*/
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
