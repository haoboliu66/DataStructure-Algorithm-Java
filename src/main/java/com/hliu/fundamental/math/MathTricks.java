package com.hliu.fundamental.math;

public class MathTricks {

  // 最大公约数Greatest Common Divisor, 辗转相除法
  public static int gcd(int a, int b) {
    return b == 0 ? a : gcd(b, a % b);
  }

  /*
  在数字 1 -> N 之间, 能被 a 或 b 整除的数的个数为:
  N / a + N / b - N / lcm(a, b)  => 荣斥原理(Rouché's Theorem)

  其中 lcm(a, b) 为 a 和 b 的最小公倍数, 也即会被 a 和 b 同时整除的数, 相当于从 N / a + N / b 中减去重复计算的部分
   */

  // Least Common Multiple 最小公倍数
  public static int lcm(int a, int b) {
    return a * b / gcd(a, b);
  }

  // https://leetcode.com/problems/nth-magical-number




  // 同余原理



}
