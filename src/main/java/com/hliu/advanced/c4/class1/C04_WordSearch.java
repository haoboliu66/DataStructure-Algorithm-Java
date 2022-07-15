package com.hliu.advanced.c4.class1;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class C04_WordSearch {

  /*
  79. Word Search (Medium)
  212. Word Search II (Hard)
   */
  private static class TrieNode {

    TrieNode[] nexts;
    int pass;
    int end;

    public TrieNode() {
      this.nexts = new TrieNode[26];
      this.pass = 0;
      this.end = 0;
    }
  }


  public static void fillWord(TrieNode head, String word) {
    head.pass++;
    char[] str = word.toCharArray();
    TrieNode cur = head;
    for (int i = 0; i < str.length; i++) {
      if (cur.nexts[str[i] - 'a'] == null) { //需要创建节点
        cur.nexts[str[i] - 'a'] = new TrieNode();
      }
      //创建完新节点或已有老节点, 往下移动
      cur = cur.nexts[str[i] - 'a'];
      cur.pass++;
    }
    cur.end++; //加完之后, 最后一个节点的end++
  }

  public static String generatePath(LinkedList<Character> path) {
    char[] res = new char[path.size()];
    int index = 0;
    for (Character c : path) {
      res[index++] = c;  //c.charValue();
    }
    return String.valueOf(res);
  }


  public static List<String> findWords(char[][] board, String[] words) {
    TrieNode head = new TrieNode();
    HashSet<String> set = new HashSet<>(); //set确保在Trie上不添加重复的word
    for (String str : words) {
      if (!set.contains(str)) {
        fillWord(head, str);
        set.add(str);
      }
    }
    List<String> res = new ArrayList<>();
    LinkedList<Character> path = new LinkedList<>(); //记录已走过的路径
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        //遍历每一个点, 从每一个点开始走
        process(board, i, j, path, head, res);
      }
    }

    return res;
  }

  public static int process(char[][] board, int row, int col, LinkedList<Character> path, TrieNode cur,
      List<String> res) {

    char cha = board[row][col];
    if (cha == 0) { //回头路, 直接返回
      return 0;
    }
    int index = cha - 'a'; //当前位置的字符的索引
    if (cur.nexts[index] == null || cur.nexts[index].pass == 0) {
      // 前缀树上没有路, 或者 当前节点后续的答案都收集过了, 直接返回
      return 0;
    }
    // 当前节点有效 且 能登上去(前缀树有这个节点)
    cur = cur.nexts[index];
    path.addLast(cha); //记录登上了的节点
    int fix = 0; //已解决的答案数量
    if (cur.end > 0) { //判断表明来到了一个字符串的结尾位置, 可以收集当前的答案path了
      // end只有1和0, 因为前缀树不加入重复字符串
      res.add(generatePath(path));
      cur.end--;
      fix++;
    }

    board[row][col] = 0;
    if (row - 1 >= 0) { //往上走, 搞定的数量
      fix += process(board, row - 1, col, path, cur, res);
    }
    if (row + 1 < board.length) { //往下走, 搞定的数量
      fix += process(board, row + 1, col, path, cur, res);
    }
    if (col - 1 >= 0) { //往左走, 搞定的数量
      fix += process(board, row, col - 1, path, cur, res);
    }
    if (col + 1 < board[0].length) { //往右走, 搞定的数量
      fix += process(board, row, col + 1, path, cur, res);
    }

    board[row][col] = cha;
    path.pollLast();
    cur.pass -= fix; //优化当前从当前节点开始搞定的数量

    return fix;
  }

  //----------------------------------------------------------------------------------------
  public static boolean exist(char[][] board, String word) {
    TrieNode head = new TrieNode();
    fillWord(head, word);
    boolean res = false;
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        res |= process2(board, i, j, head);
      }
    }
    return res;
  }

  public static boolean process2(char[][] board, int row, int col, TrieNode cur) {
    char cha = board[row][col];

    if (cur.nexts[cha - 'a'] == null) {
      return false;
    }
    cur = cur.nexts[cha - 'a'];
    if (cur.end == 1) {
      return true;
    }
    boolean res = false;
    if (row - 1 >= 0 && cur.nexts[board[row - 1][col] - 'a'] != null) {
      res |= process2(board, row - 1, col, cur);
    }
    if (row + 1 < board.length && cur.nexts[board[row + 1][col] - 'a'] != null) {
      res |= process2(board, row + 1, col, cur);
    }
    if (col - 1 >= 0 && cur.nexts[board[row][col - 1] - 'a'] != null) {
      res |= process2(board, row, col - 1, cur);
    }
    if (col + 1 < board[0].length && cur.nexts[board[row][col + 1] - 'a'] != null) {
      res |= process2(board, row, col + 1, cur);
    }
    return res;
  }


  /*
  79. Word Search
   */
  // Brute Force (Recursion)
  public static boolean exist3(char[][] board, String word) {

    int M = board.length, N = board[0].length;

    char[] str = word.toCharArray();

    boolean[][] visited = new boolean[M][N];
    for (int i = 0; i < M; i++) {
      for (int j = 0; j < N; j++) {
        if (process3(board, i, j, str, 0, visited)) {
          return true;
        }
      }
    }
    return false;
  }


  public static boolean process3(char[][] board, int i, int j, char[] str, int index, boolean[][] visited) {

    if (index == str.length) {
      return true;
    }

    if (i < 0 || j < 0 || i > board.length - 1 || j > board[0].length - 1) {
      return false;
    }

    // index < str.length
    if (visited[i][j]) {
      return false;
    }

    if (board[i][j] != str[index]) {
      return false;
    }

    visited[i][j] = true;

    boolean res =
        process3(board, i + 1, j, str, index + 1, visited) || process3(board, i - 1, j, str, index + 1, visited)
            || process3(board, i, j - 1, str, index + 1, visited) || process3(board, i, j + 1, str, index + 1, visited);

    visited[i][j] = false;

    return res;
  }


}
