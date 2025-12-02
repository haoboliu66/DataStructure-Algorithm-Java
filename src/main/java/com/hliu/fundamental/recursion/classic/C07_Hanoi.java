package com.hliu.fundamental.recursion.classic;

public class C07_Hanoi {

  public static void hanoi(int n, String from, String to, String other) {
    if (n > 0) {
      fun(n, from, to, other);
    }
  }

  public static void fun(int n, String from, String to, String other) {
    if (n == 1) {
      System.out.println("Move " + n + " From " + from + " To " + to);
      return;
    }
    fun(n - 1, from, other, to);
    System.out.println("Move " + n + " From " + from + " To " + to);
    fun(n - 1, other, to, from);
  }

  public static void main(String[] args) {
    hanoi(5, "left", "right", "mid");
  }
}
