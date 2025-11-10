package com.hliu.fundamental.bitwise;

import java.util.HashMap;

public class SingleNumber {

  public int[] getSneakyNumbers(int[] arr) {

    int[] res = new int[2];
    int index = 0;
    boolean[] seen = new boolean[101];

    for (int num : arr) {
      if (seen[num]) {
        res[index++] = num;
        continue;
      }
      seen[num] = true;
    }

    return res;
  }

  /**
   * Given a non-empty array of integers, every element appears twice except for one. Find that single one. 一个数组中,
   * 所有数字出现偶数次, 只有一个数字出现了奇数次, 找到这个数字
   */
  public static int singleNumber(int[] arr) {
    int x = arr[0];
    for (int i = 1; i < arr.length; i++) {
      x ^= arr[i];
    }
    return x;
  }

  /*把一个int二进制最右侧的1提取出来 */
  public static int getRightMostOne(int num) {
    return num & (~num + 1);   // num & (-num)
  }

  /* Q:一个数组中, 两个数字出现了奇数次, 其余数字都出现偶数次, 找到这两个数字 */
  public static int[] getTwo(int[] arr) {
    int xor = 0;
    for (int i = 0; i < arr.length; i++) {
      xor ^= arr[i];
    }
    /*
     if these two numbers are a, b, then eor =  a ^ b
     且a和b不相等, 那么意味着a ^ b的结果里一定有1, 这个1假设在第10位,
     那么这个1一定来自第 a第10位上的0 和 b第10位上的的1    or vice versa

     */
    int one = getRightMostOne(xor); // 拿出最右侧的1, 该位, 假设第10位, 是1的的为一种数, 其余的为另一种数
    int oneKind = 0;
    for (int i = 0; i < arr.length; i++) {
      if ((arr[i] & one) == 0) { // 当前数字第10位是0, 那么就异或上这个数字
        oneKind ^= arr[i];
      }
    }
    /*
     遍历后, oneKind就是第10位是0的所有数字的异或和, 这里面除了我们想找的数字a, 其余都是偶数次数字, 所以异或结果为0
     那么oneKind就是我们要找的数字a
     */
    return new int[]{oneKind, xor ^ oneKind};
  }

  /* Q:数出int中二进制1的个数  */
  public static int countOne(int num) {
    int count = 0;
    while (num != 0) {
      if (getRightMostOne(num) > 0) {
        count++;
        num -= getRightMostOne(num);
      }
    }
    return count;
  }

  public static void main(String[] args) {
//    int[] arr = {1, 1, 3, 3, 4, 5, 4, 5, 6, 7};
//    int[] two = getTwo(arr);
//    System.out.println(two[0] + " and " + two[1]);

    HashMap<String, Integer> map = new HashMap<>(2);
    map.put("1", 1);
    map.put("2", 2);

//        int a = 131;
//        int count = countOne(a);
//        System.out.println(count);

  }


}
