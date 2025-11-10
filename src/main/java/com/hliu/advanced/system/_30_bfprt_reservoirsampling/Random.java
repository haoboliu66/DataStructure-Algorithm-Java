package com.hliu.advanced.system._30_bfprt_reservoirsampling;

public class Random {

  // Rejection Sampling

  // 已知一个随机函数f等概率生成1-7, 编写一个函数随机生成1-10
  private static class Rand10 {

    // [0,9] + 1
    public int rand10() {
      int bits = 4;
      int num = generateNum(bits);
      // [0...15]
      while (num >= 10 && num <= 15) {
        // 我需要 num 在 [0...9], 如果不是, 就重新生成
        num = generateNum(bits);
      }
      // [0...9]
      return num + 1;
    }

    // [0...15]
    // 等概率生成 0 - (2^bits-1)
    public int generateNum(int bits) {
      int times = bits;
      int res = 0;
      int base = 1;
      for (; times != 0; times--) {
        // 4,3,2,1
        res += base * getOneOrZero();
        base *= 2;  // 2 , 4,  8
      }
      return res;
    }

    // 等概率返回0和1
    public int getOneOrZero() {
      // [1,2,3],(4,5,6) ,7
      int res;
      while ((res = rand7()) == 7) {
        res = rand7();
      }
      return res <= 3 && res >= 1 ? 0 : 1;
    }

    public int rand7() {
      // [0, 1) => [0, 7) + 1 => [1,8)
      return (int) (Math.random() * 7) + 1;
    }

  }

  public static void main(String[] args) {
    int times = 200000;
    Rand10 r = new Rand10();
    System.out.println("Started");
    for (int i = 0; i < times; i++) {
      int num = r.generateNum(4);
      if (num > 15) {
        System.out.println(num);
        System.out.println("Oops");
        break;
      }
    }
    System.out.println("Done");
  }
}
