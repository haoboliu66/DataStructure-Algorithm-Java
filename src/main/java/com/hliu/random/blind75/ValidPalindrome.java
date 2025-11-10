package com.hliu.random.blind75;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

public class ValidPalindrome {

  /*

  // 00000000000000000000000000001011

  // 00000000000000000000000000001010

  // 00000000000000000000000000001010
  // 00000000000000000000000000001001
  // 00000000000000000000000000001000

   // 计算一个n的二进制表示中有多少个1
   public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            n &= n - 1;
            res++;
        }
        return res;
    }
   */


  /*
  3[a] = aaa
  3[a2[b]] = abbabbabb
  2[cd] = cdcd
   */
  public String extend(String s) {

    StringBuilder res = new StringBuilder();
    Stack<Character> stack = new Stack<>();
    char[] str = s.toCharArray();

    for (char c : str) {

      if (isNumber(c)) {
        stack.push(c);
        continue;
      }
      if (c == '[') {
        stack.push(c);
        continue;
      }
      if (isLetter(c)) {
        stack.push(c);
        continue;
      }

      if (c == ']') {
        StringBuilder repeatStr = new StringBuilder();
        while (stack.peek() != '[') {
          repeatStr.insert(0, stack.pop());
        }
        stack.pop();

        StringBuilder repeatCount = new StringBuilder();
        while (!stack.isEmpty() && isNumber(stack.peek())) {
          repeatCount.insert(0, stack.pop());
        }
        int count = Integer.parseInt(repeatCount.toString());
        for (int i = 0; i < count; i++) {
          res.append(repeatStr);
        }
      }
    }

    return res.toString();
  }

  private boolean isNumber(char c) {
    return c >= '0' && c <= '9';
  }

  private boolean isLetter(char c) {
    return c >= 'a' && c <= 'z';
  }


  public static void main(String[] args) {
    ValidPalindrome vp = new ValidPalindrome();

    String res = vp.extend("3[a2[c]]");
    System.out.println(res);
  }

  private static class Node {

    int num;
    int count;

    public Node(int num, int count) {
      this.num = num;
      this.count = count;
    }
  }

  public int[] topKFrequent(int[] nums, int k) {
    Map<Integer, Integer> countMap = new HashMap<>();
    for (int x : nums) {
      countMap.put(x, countMap.getOrDefault(x, 0) + 1);
    }
    int[] res = new int[k];
    Map<Integer, Integer> map = new HashMap<>();

    PriorityQueue<Node> maxHeap = new PriorityQueue<>((a, b) -> b.count - a.count);
    for (Map.Entry<Integer, Integer> countEntry : countMap.entrySet()) {
      maxHeap.offer(new Node(countEntry.getKey(), countEntry.getValue()));
    }
    for (int i = 0; i < k; i++) {
      res[i] = maxHeap.poll().num;
    }
    return res;
  }

  public int[] topKFrequent1(int[] nums, int k) {
    Map<Integer, Integer> countMap = new HashMap<>();
    for (int x : nums) {
      countMap.put(x, countMap.getOrDefault(x, 0) + 1);
    }
    List<Integer>[] freqBucket = new List[nums.length + 1];
    for (int i = 0; i < freqBucket.length; i++) {
      freqBucket[i] = new ArrayList<>();
    }

    for (Map.Entry<Integer, Integer> countEntry : countMap.entrySet()) {
      int number = countEntry.getKey();
      int freq = countEntry.getValue();
      freqBucket[freq].add(number);
    }
    int index = 0;
    int[] res = new int[k];
    for (int i = freqBucket.length - 1; i >= 0; i++) {
      for (Integer x : freqBucket[i]) {
        res[index++] = x;
        if (index == k) {
          return res;
        }
      }
    }

    return res;
  }

  public int reverseBits(int n) {
    int res = 0;
    int right = 0;

    while (right <= 31) {
      int leftBitCount = 31 - right;
      int bit = (n >> right) & 1;

      res |= (bit << leftBitCount);
      right++;
    }
    return res;
  }


  public boolean isPalindrome(String s) {
    if (s == null || s.length() <= 1) {
      return true;
    }
    char[] str = s.toCharArray();
    int left = 0, right = str.length - 1;

    while (left < right) {

      if (!validChar(str[left])) {
        left++;
        continue;
      }
      if (!validChar(str[right])) {
        right--;
        continue;
      }

      if (!equalChar(str[left++], str[right--])) {
        return false;
      }
    }
    return true;
  }

  private boolean equalChar(char x, char y) {
    return x == y || (validLetter(x) && validLetter(y) && Math.abs(x - y) == Math.abs('a' - 'A'));
  }

  private boolean validNumber(char c) {
    return (c >= '0' && c <= '9');
  }

  private boolean validLetter(char c) {
    return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
  }

  private boolean validChar(char c) {
    return validNumber(c) || validLetter(c);
  }
}
