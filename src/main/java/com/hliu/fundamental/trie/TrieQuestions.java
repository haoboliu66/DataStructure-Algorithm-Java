package com.hliu.fundamental.trie;

/*

  https://leetcode.com/problems/implement-trie-prefix-tree/
  https://leetcode.com/problems/design-add-and-search-words-data-structure/
  https://leetcode.com/problems/implement-magic-dictionary/

  https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/
  https://leetcode.com/problems/maximum-xor-with-an-element-from-array/

 */
public class TrieQuestions {

  class TrieNode {

    int end;
    public TrieNode[] nexts;

    public TrieNode() {
      this.end = 0;
      this.nexts = new TrieNode[128];
    }
  }

  // https://leetcode.com/problems/implement-trie-prefix-tree/
  class Trie {

    TrieNode head;

    public Trie() {
      head = new TrieNode();
    }

    public void insert(String word) {
      char[] str = word.toCharArray();
      TrieNode cur = head;
      for (char ch : str) {
        int path = ch - 'a';
        if (cur.nexts[path] == null) {
          cur.nexts[path] = new TrieNode();
        }
        cur = cur.nexts[path];
      }
      cur.end++;
    }

    public boolean search(String word) {
      char[] str = word.toCharArray();
      TrieNode cur = head;
      for (char ch : str) {
        int path = ch - 'a';
        if (cur.nexts[path] == null) {
          return false;
        }
        cur = cur.nexts[path];
      }
      return cur.end > 0;
    }

    public boolean startsWith(String prefix) {
      char[] str = prefix.toCharArray();
      TrieNode cur = head;
      for (char ch : str) {
        int path = ch - 'a';
        if (cur.nexts[path] == null) {
          return false;
        }
        cur = cur.nexts[path];
      }
      return true;
    }
  }

  // https://leetcode.com/problems/design-add-and-search-words-data-structure/
  class WordDictionary {

    TrieNode head;

    public WordDictionary() {
      head = new TrieNode();
    }

    public void addWord(String word) {
      char[] str = word.toCharArray();
      TrieNode cur = head;
      for (int i = 0; i < str.length; i++) {
        char c = str[i];
        if (cur.nexts[c] == null) {
          cur.nexts[c] = new TrieNode();
        }
        cur = cur.nexts[c];
      }
      cur.end++;
    }

    public boolean search(String word) {
      char[] str = word.toCharArray();
      return dfs(str, 0, head);
    }

    private boolean dfs(char[] str, int index, TrieNode cur) {
      if (index == str.length) {
        return cur.end > 0;
      }
      char c = str[index];
      if (cur.nexts[c] != null) {
        return dfs(str, index + 1, cur.nexts[c]);
      }
      // no path to c
      if (c != '.') {
        return false;
      }
      boolean found = false;
      // c == '.'
      for (char ch = 'a'; ch <= 'z'; ch++) {
        if (cur.nexts[ch] != null) {
          found |= dfs(str, index + 1, cur.nexts[ch]);
        }
      }
      return found;
    }

    class TrieNode {

      TrieNode[] nexts;
      int end;

      public TrieNode() {
        nexts = new TrieNode[128];
      }
    }
  }

  // https://leetcode.com/problems/implement-magic-dictionary/
  class MagicDictionary {

    TrieNode head;

    public MagicDictionary() {
      head = new TrieNode();
    }

    public void buildDict(String[] dictionary) {
      for (String word : dictionary) {
        insert(word);
      }
    }

    private void insert(String word) {
      char[] str = word.toCharArray();
      TrieNode cur = head;
      for (char ch : str) {
        if (cur.nexts[ch] == null) {
          cur.nexts[ch] = new TrieNode();
        }
        cur = cur.nexts[ch];
      }
      cur.end++;
    }

    public boolean search(String searchWord) {
      char[] str = searchWord.toCharArray();
      return dfs(str, 0, head, 0);
    }

    private boolean dfs(char[] str, int index, TrieNode cur, int modCount) {
      if (cur == null) {
        return false;
      }
      if (index == str.length) {
        return cur.end > 0 && modCount == 1; // exactly one modification
      }
      char ch = str[index];
      boolean result = false;
      // not change current c
      result |= dfs(str, index + 1, cur.nexts[ch], modCount);

      // change current c
      for (char newChar = 'a'; newChar <= 'z'; newChar++) {
        if (newChar != ch && cur.nexts[newChar] != null && modCount == 0) {
          result |= dfs(str, index + 1, cur.nexts[newChar], 1);
        }
      }
      return result;
    }
  }

  public static void main(String[] args) {
    TrieQuestions questions = new TrieQuestions();
    TrieQuestions.MagicDictionary dict = questions.new MagicDictionary();
    String[] dictionary = {"hello", "leetcode"};
    dict.buildDict(dictionary);
    System.out.println(dict.search("hello"));
    System.out.println(dict.search("hhllo"));
    System.out.println(dict.search("hell"));
    System.out.println(dict.search("leetcoded"));
  }

  public int[] xorQueries(int[] arr, int[][] queries) {
    int[] res = new int[queries.length];
    int[] preXor = new int[arr.length];
    preXor[0] = arr[0];
    for (int i = 1; i < arr.length; i++) {
      preXor[i] = preXor[i - 1] ^ arr[i];
    }
    // preXor[i]: arr[0] ^ arr[1] ^ ... ^ arr[i-1]

    //  [1...4]   arr[1] ^ arr[2] ^ arr[3] ^ arr[4] ^ (arr[0])

    // [0...3]  arr[0] ^ arr[1] ^ arr[2] ^ arr[3] ^ 0

    for (int i = 0; i < queries.length; i++) {
      int[] q = queries[i];
      int left = q[0];
      int right = q[1];
      res[i] = left == 0 ? preXor[right] : preXor[right] ^ preXor[left - 1];
    }

    return res;
  }

}
