package com.hliu.advanced.system._26_monotonestack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// https://leetcode.com/problems/online-stock-span/
public class C08_OnlineStockSpan {

  public static void main(String[] args) {

    StockSpanner ss = new StockSpanner();

    System.out.println(ss.next(100));
    System.out.println(ss.next(80));
    System.out.println(ss.next(60));
    System.out.println(ss.next(70));
    System.out.println(ss.next(60));
    System.out.println(ss.next(75));
    System.out.println(ss.next(85));

  }

  static class StockSpanner {

    List<Integer> list;
    Stack<Integer> stack;
    List<Integer> buffer;

    public StockSpanner() {
      list = new ArrayList<>();
      stack = new Stack<>();
      buffer = new ArrayList<>();
    }

    public int next(int price) {
      list.add(price);
      int[] leftMore = getLeftMore(list.size() - 1);
      buffer.add(list.size() - 1 - leftMore[list.size() - 1]);
      if (buffer.get(list.size() - 1) == 1 && list.size() > 2 && price >= list.get(list.size() - 2)) {
        return buffer.get(list.size() - 1) + 1;
      }
      return list.size() - 1 - leftMore[list.size() - 1];
    }

    public int[] getLeftMore(int curIndex) {
      Stack<Integer> stack = new Stack<>();
      int[] res = new int[list.size()];

      for (int i = curIndex; i >= 0; i--) {
        while (!stack.isEmpty() && list.get(i) > list.get(stack.peek())) {
          res[stack.pop()] = i;
        }
        stack.push(i);
      }
      while (!stack.isEmpty()) {
        res[stack.pop()] = -1;
      }
      return res;
    }

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */
  }
}
