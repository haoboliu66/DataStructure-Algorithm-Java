package com.hliu.random.algoexpert.medium;

import java.util.HashMap;
import java.util.Map;

public class P54_SuffixTrieConstruction {

  public static void main(String[] args) {

    SuffixTrie trie = new SuffixTrie("babc");

    System.out.println(trie.contains("abc"));

  }

  static class TrieNode {
    Map<Character, TrieNode> children = new HashMap<Character, TrieNode>();
  }

  static class SuffixTrie {
    TrieNode root = new TrieNode();
    char endSymbol = '*';

    public SuffixTrie(String str) {
      populateSuffixTrieFrom(str);
    }

    public void populateSuffixTrieFromIndex(int start, String s) {
      char[] str = s.toCharArray();
      TrieNode cur = root;
      for (int i = start; i < str.length; i++) {
        if(!cur.children.containsKey(str[i])){
          cur.children.put(str[i], new TrieNode());
        }
        cur = cur.children.get(str[i]);
      }
      cur.children.put(endSymbol, new TrieNode());
    }

    public void populateSuffixTrieFrom(String s) {
      for(int i = 0;  i < s.length(); i++){
        populateSuffixTrieFromIndex(i, s);
      }
    }

    public boolean contains(String s) {
      char[] str = s.toCharArray();
      TrieNode cur = root;
      for (char c : str) {
        if(!cur.children.containsKey(c)){
          return false;
        }
        cur = cur.children.get(c);
      }
      return cur.children.containsKey(endSymbol);
    }
  }

}
