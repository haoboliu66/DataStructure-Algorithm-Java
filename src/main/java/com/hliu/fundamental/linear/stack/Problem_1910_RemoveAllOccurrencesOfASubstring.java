package com.hliu.fundamental.linear.stack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Problem_1910_RemoveAllOccurrencesOfASubstring {

  TrieNode head = new TrieNode();

  public String encode(List<String> strs) {
    TrieNode curr = head;
    for (String s : strs) {
      TrieNode next = new TrieNode(s, new HashMap<>());
      curr.nexts.put(s, next);
      curr = next;
    }
    return null;
  }

  public List<String> decode(String str) {
    return null;
  }

  private static class TrieNode {

    String word;
    Map<String, TrieNode> nexts;

    public TrieNode() {
      word = null;
      nexts = new HashMap<>();
    }

    public TrieNode(String word, Map<String, TrieNode> nexts) {
      this.word = word;
      this.nexts = nexts;
    }
  }

  public String removeOccurrences(String s, String part) {
    Stack<Character> stack = new Stack<>();
    int partLen = part.length();
    for (char c : s.toCharArray()) {

    }

    return null;
  }

}
