package com.hliu.advanced.c3.class7;


import java.util.HashMap;
import java.util.Map;

public class C06_LongestNoRepeatSubstring {

  /*
  LC 3 Longest Substring Without Repeating Characters
   */
  public static int maxUnique(String s) {
    if (s == null || s.length() == 0) {
      return 0;
    }
    char[] str = s.toCharArray();
    int N = str.length;
    int[] map = new int[256];
    for (int i = 0; i < 256; i++) {
      map[i] = -1;
    }

    int len = 0; //收集最终答案
    int pre = -1; // i - 1位置的左边界, 0位置起始pre就是-1
    int cur = 0;
    for (int i = 0; i < N; i++) {
      pre = Math.max(pre, map[str[i]]); //前一个字符的左边界, 和当前字符的左边界(上次出现的位置)
      cur = i - pre;
      len = Math.max(len, cur);
      map[str[i]] = i;
    }

    return len;
  }


  public static int maxUnique1(String s) {
    if (s == null || "".equals(s)) {
      return 0;
    }
    char[] str = s.toCharArray();
    HashMap<Character, Integer> map = new HashMap<>();
    map.put(str[0], 0);
    int pre = -1;
    int len = 1;
    int cur = 0;
    for (int i = 1; i < str.length; i++) {
      if (map.containsKey(str[i])) {
        pre = Math.max(map.get(str[i]), pre);
      }
      cur = i - pre;
      len = Math.max(len, cur);
      map.put(str[i], i);
    }
    return len;
  }

  // my solution with Map
  public int lengthOfLongestSubstring(String s) {
    if (s == null || "".equals(s)) {
      return 0;
    }
    char[] str = s.toCharArray();
    int max = Integer.MIN_VALUE, pre = -1;
    Map<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < str.length; i++) {
      if (!map.containsKey(str[i])) {
        map.put(str[i], i);
        // 1.以当前字符为界
        // 2. 以i-1的字符为界
        // 当前字符首先的边界:
        // 10(lastIndex) 11 12 13(pre) 14 15 16 17
        max = Math.max(max, i - pre);
      } else {
        // 10 11 12 13 14 15 16 17
        // 1.以当前字符为界
        // 2. 以i-1的字符为界
        pre = Math.max(map.get(str[i]), pre);
        max = Math.max(max, i - pre);
        map.put(str[i], i);
      }
    }
    return max;
  }


  public static void main(String[] args) {
    String s = "abcabcbb";
    System.out.println(maxUnique(s));
    System.out.println(maxUnique1(s));
  }

}
