package com.hliu.advanced.c3.class8;

public class C04_MoneyGame {

  /*  数据量决定解法   */

  //方法1: 暴力递归
  public static long func1(int[] d, int[] p) {
    return process(d, p, 0, 0);
  }

  // int[] d d[i]：i号怪兽的武力
  // int[] p p[i]：i号怪兽要求的钱
  // ability 当前你所具有的能力
  // index 来到了第index个怪兽的面前
  //  返回所需最少的钱数
  public static long process(int[] d, int[] p, int ability, int index) {
    if (index == d.length) {
      return 0;
    }
    if (ability >= d[index]) {
      return Math.min(process(d, p, ability, index + 1),
          process(d, p, ability + d[index], index + 1) + p[index]);
    } else {
      return p[index] + process(d, p, ability += d[index], index + 1);
    }
  }


  // DP 第一种尝试
  //纵向: 0...i号怪兽
  //横向: 所有怪兽的总能力
  public static long func2(int[] d, int[] p) {
    int ability = 0;
    for (int i = 0; i < d.length; i++) {
      ability += d[i];
    }
    // dp[i][j] 来到i号怪兽, 当前能力为j, 需要花的钱最少是多少
    int[][] dp = new int[d.length + 1][ability + 1];

//        if (index == d.length) {
//            return 0;
//        }
        /*
        依赖下一行 (index + 1), 所以从下往上填
         */
    for (int i = d.length - 1; i >= 0; i--) {
      for (int ab = 0; ab <= ability; ab++) {

        if (ab + d[i] > ability) {
          continue;
        }

        if (ab >= d[i]) { //已有能力大于等于当前怪兽能力d[i]
          dp[i][ab] = Math.min(dp[i + 1][ab], p[i] + dp[i + 1][ab + d[i]]);
        } else {
          dp[i][ab] = p[i] + dp[i + 1][ab + d[i]];
        }

      }
    }
//        if (ability > d[index]) {
//            return Math.min(process(d, p, ability, index + 1),
//                    process(d, p, ability + d[index], index + 1) + p[index])
//        } else {
//            return p[index] + process(d, p, ability += d[index], index + 1);
//        }

    return dp[0][0];
  }

  public static long func3(int[] d, int[] p) {
    int sum = 0;  //总钱数
    for (int n : p) {
      sum += n;
    }

    int[][] dp = new int[d.length][sum + 1];
    //dp[i][j]含义: 通过0...i的怪兽, 花了钱数刚好为j, 获取的能力值是多少

    // 花0元不可能通过任何怪兽, 无法获得任何能力值, 标记-1
    for (int i = 0; i < d.length; i++) {
      dp[i][0] = -1;
    }

    //对于第一个怪兽, 只有当钱数等于他要求钱数p[0]时, 才能获得他的能力
    for (int j = 0; j <= sum; j++) {
      dp[0][j] = j == p[0] ? d[0] : -1;
    }

    for (int i = 1; i < d.length; i++) {
      for (int j = 0; j <= sum; j++) {
        dp[i][j] = -1;
        // 如果为当前怪兽花钱, 那么 0...i-1花的钱就是j-p[i],
        // 获取的能力值就是0...i-1获取的能力值加上当前这个怪兽的能力值d[i]
        if (j - p[i] >= 0 && dp[i - 1][j - p[i]] != -1) {
          dp[i][j] = dp[i - 1][j - p[i]] + d[i];
        }

        // 不为当前怪兽花钱的条件是: 0...i-1积攒的能力值>=当前怪兽的能力值d[i]
        if (dp[i - 1][j] >= d[i]) {
          dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
        }

      }
    }

    int res = 0;
    for (int j = 1; j <= sum; j++) {
      if (dp[d.length - 1][j] != -1) {
        res = j;
        break;
      }
    }
    return res;
  }


  public static int[][] generateTwoRandomArray(int len, int value) {
    int size = (int) (Math.random() * len) + 1;
    int[][] arrs = new int[2][size];
    for (int i = 0; i < size; i++) {
      arrs[0][i] = (int) (Math.random() * value) + 1;
      arrs[1][i] = (int) (Math.random() * value) + 1;
    }
    return arrs;
  }

  public static void main(String[] args) {
    int len = 10;
    int value = 20;
    int testTimes = 1000000;
    System.out.println("Started");
    for (int i = 0; i < testTimes; i++) {
      int[][] arrs = generateTwoRandomArray(len, value);
      int[] d = arrs[0];
      int[] p = arrs[1];
      long ans1 = func1(d, p);
      long ans2 = func2(d, p);
      long ans3 = func3(d, p);
      if (ans1 != ans2 || ans2 != ans3) {
        System.out.println("oops!");
      }
    }
    System.out.println("Finished");

  }


}
