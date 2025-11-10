package com.hliu.fundamental.trie;

import java.util.ArrayList;
import java.util.List;


public class Problem_1233_RemoveSubFoldersFromFilesystem {

  public List<String> removeSubfolders(String[] folder) {
    Node root = new Node();
    Node cur = root;
    for (String path : folder) {
      char[] str = path.toCharArray();
      for (char c : str) {
        if(c == '/') continue;
        if (cur.nexts[c] == null) {
          cur.nexts[c] = new Node();
        }
        cur = cur.nexts[c];
      }
    }
    List<String> res = new ArrayList<>();

    return res;
  }


  private static class Node {

    String value;
    Node[] nexts = new Node[128];

  }

}
