package com.hliu.advanced.c3.catalan;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
/*
    96. Unique Binary Search Trees
 */
public class CatalanNumber {

  // n个节点有多少种二叉树组合
  public static long g(int n) {
    if (n == 0) {
      return 0;
    }
    if (n == 1) {
      return 1;
    }
    // n = 2, 2个节点的组合
    // 1,2   root是1,  root是2, root是...
    int total = 0;
    for (int i = 1; i <= n; i++) {
      total += f(i, n);
    }
    return total;
  }

  // 以i为root, 总可用节点为n, 有多少种组合
  public static long f(int i, int n) {
    if (i == 1) {
      return g(n - 1);
    }
    if (i == n) {
      return g(n - 1);
    }
    int leftNodeNum = i - 1;
    int rightNodeNum = n - i;
    return g(leftNodeNum) * g(rightNodeNum);
  }


  /* 如何实现C(m,n) */
  public static long combination(int m, int n) {
    if (n == 0 || n == m) {
      return 1;
    }
    BigInteger res = (factorial(m).divide(factorial(n).multiply(factorial(m - n))));
    return res.intValue();
  }

  public static BigInteger permutation(int m, int n) {
    return factorial(m).divide(factorial(m - n));
  }

  static Map<String, BigInteger> cache = new HashMap<>();

  public static BigInteger factorial(int n) {
    if (cache.containsKey(String.valueOf(n))) {
      return cache.get(String.valueOf(n));
    }
    if (n == 1) {
      cache.put("1", BigInteger.ONE);
      return BigInteger.ONE;
    }
    BigInteger f = new BigInteger(String.valueOf(n));
    f = f.multiply(factorial(n - 1));
    cache.put(String.valueOf(n), f);
    return f;
  }

  @Test
  public void testBigInteger() {
    BigInteger a = new BigInteger("100000");
    BigInteger b = new BigInteger("100000");
    System.out.println(a.toString());
    System.out.println(a.equals(b));
  }

  public static void main(String[] args) {
    System.out.println(g(11));
    int i = 11;
    System.out.println(factorial(22));
//        long res = combination(2 * i, i) - combination(2 * i, i - 1);
//        System.out.println(res);
    //System.out.println("Started");
//        for (int i = 1; i <= 15; i++) {
//            long res1 = g(i);
//            long res2 = combination(2 * i, i) - combination(2 * i, i - 1);
//            if (res1 != res2) {
//                System.out.println("i: " + i);
//                System.out.println("res1: " + res1);
//                System.out.println("res2: " + res2);
//                System.out.println("Oops");
//                break;
//            }
//        }
    System.out.println("Done");
  }


}
