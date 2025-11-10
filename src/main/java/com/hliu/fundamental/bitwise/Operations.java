package com.hliu.fundamental.bitwise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Operations {

  public boolean isPowerOfTwo(int n) {
    return (n & -n) == n;
  }

  // 已知n是正数
  // 返回大于等于，且最接近n的，2的某次方的值
  public static int tableSizeFor(int n) {
    n--;
    n |= n >>> 1;
    n |= n >>> 2;
    n |= n >>> 4;
    n |= n >>> 8;
    n |= n >>> 16;
    return (n < 0) ? 1 : n + 1;
  }

  // https://leetcode.com/problems/bitwise-and-of-numbers-range/
  public int rangeBitwiseAnd(int left, int right) {
    /*
    如果right > left, 说明在&操作时right一定会失去最右侧的1, 因为 right - 1 一定会把这个1清掉
    所以不断地把right的最右侧的1清掉，直到 right <= left 为止
     */
    while (left < right) {
      right -= (right & -right);
    }
    return right;
  }

  // https://leetcode.com/problems/reverse-bits/
  public int reverseBits(int n) {
    int res = 0;
    for (int i = 0; i < 32; i++) {
      res |= ((n >>> i) & 1) << (31 - i);
    }
    return res;
  }

  public int reverseBits1(int n) {
    /*
     脑洞极大, 先1位交换，再2位交换，再4位, 8位, 16位依次交换...
     abcd efgh => badc fghe  (1位交换)
     */

    return n;
  }

  // https://leetcode.com/problems/hamming-distance/
  public int hammingDistance(int x, int y) {
    int xor = x ^ y;
    return 0;
  }

  private int countOneBits(int n) {
    return 0;
  }

  // num : 31.....i 这些状态保留，剩下右侧全变成0
  public static int wipeLowBits(int num) {
    int i = 3;
    num = (num >> i) << i;   //  0001[101] => 0000001 => 0001000
    return num;
  }

  /* 把一个int二进制最右侧的1提取出来 */
  public static int getRightMostOne(int num) {
    return num & (~num + 1);   // num & (-num)
  }

  public int smallestNumber(int n) {
    int zeros = Integer.numberOfLeadingZeros(n);
    int msbOne = (32 - 1 - zeros); // highest bit 1
    // 0001000
    n = (n >>> msbOne) << (msbOne + 1); // 只保留最高位
    return n - 1;

    //  return  (1 << (msbOne + 1) ) - 1;
  }

  public static void main(String[] args) {
    String paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.";
    String res = mostCommonWord(paragraph, new String[]{"hit"});
    System.out.println(res);
  }

  public static String shortestCompletingWord(String licensePlate, String[] words) {
    char[] str = licensePlate.toLowerCase()
                             .toCharArray();
    int[] map = new int[128];
    int total = 0;
    for (char c : str) {
      if (Character.isLetter(c)) {
        map[Character.toLowerCase(c)]++;
        total++;
      }
    }
    int minLen = Integer.MAX_VALUE;
    boolean[] valid = new boolean[words.length];
    for (int i = 0; i < words.length; i++) {
      String word = words[i];
      char[] s = word.toCharArray();
      int[] tempMap = map.clone();
      int x = total;
      for (char c : s) {
        if (tempMap[c] > 0) {
          tempMap[c]--;
          x--;
        }
      }
      if (x == 0 && word.length() <= minLen) {
        minLen = word.length();
        valid[i] = true;
      }
    }
    for (int i = 0; i < valid.length; i++) {
      if (valid[i] && words[i].length() == minLen) {
        return words[i];
      }
    }
    return null;
  }

  public static String mostCommonWord(String paragraph, String[] banned) {
    Set<String> bannedSet = new HashSet<>();
    for (String ban : banned) {
      bannedSet.add(ban.toLowerCase());
    }
    String[] words = paragraph.replaceAll("\\p{Punct}", " ")
                              .split(" ");
    Map<String, Integer> countMap = new HashMap<>();
    for (String word : words) {
      word = word.toLowerCase();
      if (!bannedSet.contains(word)) {
        countMap.put(word, countMap.getOrDefault(word, 0) + 1);
      }
    }
    List<String> res = countMap.entrySet()
                               .stream()
                               .sorted((e1, e2) -> e2.getValue() - e1.getValue())
                               .map(e -> e.getKey())
                               .collect(Collectors.toList());

    return res.get(0);
  }


  private static class TrieNode {

    TrieNode[] nexts;
    int end;

    public TrieNode() {
      this.nexts = new TrieNode[26];
      this.end = 0;
    }
  }

  private static class Trie {

    TrieNode root;

    public Trie() {
      this.root = new TrieNode();
    }

    public void insert(String word) {
      TrieNode cur = root;
      char[] str = word.toCharArray();
      for (int i = 0; i < str.length; i++) {
        if (cur.nexts[str[i] - 'a'] == null) {
          cur.nexts[str[i] - 'a'] = new TrieNode();
        }
        cur = cur.nexts[str[i] - 'a'];
      }
      cur.end++;
    }

    public boolean prefixExist(String prefix) {
      TrieNode cur = root;
      char[] str = prefix.toCharArray();
      for (int i = 0; i < str.length; i++) {
        if (cur.nexts[str[i] - 'a'] == null) {
          return false;
        }
        cur = cur.nexts[str[i] - 'a'];
      }
      return true;
    }

    private void dfs(TrieNode node, StringBuilder path, List<String> res) {
      if (res.size() == 3) {
        return;
      }
      if (node.end > 0) {
        res.add(path.toString());
      }
      for (int i = 0; i < 26; i++) {
        if (node.nexts[i] != null) {
          path.append((char) (i + 'a'));
          dfs(node.nexts[i], path, res);
          path.deleteCharAt(path.length() - 1);
        }
      }
    }

    public List<String> find(String prefix) {
      List<String> res = new ArrayList<>();

      TrieNode cur = root;
      char[] str = prefix.toCharArray();
      for (int i = 0; i < str.length; i++) {
        cur = cur.nexts[str[i] - 'a'];
      }
      // finishes the prefix part, now do DFS to find up to 3 words
      dfs(cur, new StringBuilder(prefix), res);
      return res;
    }
  }

  public List<List<String>> suggestedProducts(String[] products, String searchWord) {
    Trie trie = new Trie();
    for (String product : products) {
      trie.insert(product);
    }
    List<List<String>> result = new ArrayList<>();
    for (int i = 0; i < searchWord.length(); i++) {
      String prefix = searchWord.substring(0, i);
      if (trie.prefixExist(prefix)) {
        List<String> suggestions = trie.find(prefix);
        result.add(suggestions);
      } else {
        result.add(new ArrayList<>());
      }
    }
    return result;
  }


}
