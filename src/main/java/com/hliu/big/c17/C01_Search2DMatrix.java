package com.hliu.big.c17;

public class C01_Search2DMatrix {

  // 01011
  // 10101

  //    01011
  //   00000
  //  01011
  // 00000
  //01011
  private static int multiply(int a, int b) {
    //
    // 1 1 1 1
    int res = 0;
    int shift = 0;
    while (b != 0) {
      int tmp = (b & 1) != 0 ? a : 0;
      tmp <<= shift++;
      res += tmp;
      b >>>= 1;  // 如果 b>>=1, 当b是负数的时候, 左侧会一直补1, 导致循环无法退出
    }
    return res;
  }


  public static void main(String[] args) {
    System.out.println("Go");
    for (int i = 0; i < 10000000; i++) {
      int a = (int) (Math.random() * 10000 - Math.random() * 10000);
      int b = (int) (Math.random() * 10000 - Math.random() * 10000);
      int res1 = multiply(a, b);
      int res2 = a * b;
      if (res1 != res2) {
        System.out.println("Oops");
        break;
      }
    }
    System.out.println("Done");
//        byte b = -10;
//        // 11110110
//        System.out.println(-128 + 64 + 32 + 16 + 4 + 2);
//        System.out.print((b >> 7) & 1);
//        System.out.print((b >> 6) & 1);
//        System.out.print((b >> 5) & 1);
//        System.out.print((b >> 4) & 1);
//        System.out.print((b >> 3) & 1);
//        System.out.print((b >> 2) & 1);
//        System.out.print((b >> 1) & 1);
//        System.out.println((b >> 0) & 1);
  }
}
