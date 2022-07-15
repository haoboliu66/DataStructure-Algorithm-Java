package com.hliu.advanced.c3.class1;

public class C04_ColorLeftRight {
    /*
      左侧要最少的Green, 右侧要最少的Red
     */

  // 2个辅助数组
  public static int minPaint1(String s) {
    if (s == null || s.length() < 2) {
      return 0;
    }
    char[] str = s.toCharArray();
    int N = str.length;
    int[] greens = new int[N]; // 0...i最少有几个'G'
    int[] reds = new int[N];  // i...N-1最少有几个'R'
    for (int i = 0; i < N; i++) {
      greens[i] = (i == 0) ? (str[i] == 'G' ? 1 : 0) : greens[i - 1] + (str[i] == 'G' ? 1 : 0);
    }
    for (int i = N - 1; i >= 0; i--) {
      reds[i] = (i == N - 1) ? (str[i] == 'R' ? 1 : 0) : reds[i + 1] + (str[i] == 'R' ? 1 : 0);
    }

    int ans = reds[0];  // 划分右侧部分为整个数组, 右侧所有的R都要变成G
    for (int i = 0; i < N - 1; i++) {
      ans = Math.min(ans, greens[i] + reds[i + 1]);
    }
    ans = Math.min(ans, greens[N - 1]); //划分左侧部分为整个数组, 左侧所有的G都变成R
    return ans;
  }

  // 1个辅助数组
  public static int minPaint1_1(String s) {
    if (s == null || s.length() < 2) {
      return 0;
    }
    char[] str = s.toCharArray();
    int N = str.length;
    int greens = 0;
    int[] reds = new int[N];  // i...N-1最少有几个'R'
    for (int i = N - 1; i >= 0; i--) {
      reds[i] = (i == N - 1) ? (str[i] == 'R' ? 1 : 0) : reds[i + 1] + (str[i] == 'R' ? 1 : 0);
    }

    int ans = reds[0];  // 划分右侧部分为整个数组, 右侧所有的R都要变成G
    for (int i = 0; i < N - 1; i++) {
      greens += str[i] == 'G' ? 1 : 0;
      ans = Math.min(ans, greens + reds[i + 1]);
    }
    ans = Math.min(ans, greens + (str[N - 1] == 'G' ? 1 : 0)); //划分左侧部分为整个数组, 左侧所有的G都变成R
    return ans;
  }

  // 0个辅助数组
  public static int minPaint2(String s) {
    if (s == null || s.length() < 2) {
      return 0;
    }
    char[] str = s.toCharArray();
    int N = str.length;
    int redAll = 0;
    //循环求出Red的总数, 即 后缀和
    for (int i = 0; i < N; i++) {
      redAll += str[i] == 'R' ? 1 : 0;
    }
    int greenAll = 0; // 前缀和
    int ans = redAll;  //如果整个数组都划分到右侧, 整个数组的Red都要变成Green, 结果就是redAll
    for (int i = 0; i < N - 1; i++) {
      greenAll += str[i] == 'G' ? 1 : 0; //前缀和统计
      redAll -= str[i] == 'R' ? 1 : 0; //后缀和统计

      ans = Math.min(ans, greenAll + redAll);
    }
    // 如果整个数组都划分到左侧
    ans = Math.min(ans, greenAll + (str[N - 1] == 'G' ? 1 : 0));

    return ans;
  }


  public static String generateArray(int len) {
    char[] str = new char[len];
    for (int i = 0; i < str.length; i++) {
      str[i] = Math.random() > 0.5 ? 'G' : 'R';
    }
    return new String(str);
  }

  public static void main(String[] args) {
//        String str = "RGRGG";
//        System.out.println(minPaint1(str));
    int len = 50;
    String s = generateArray(len);
    int times = 500000;
    for (int i = 0; i < times; i++) {
      s = generateArray(len);
      if (minPaint1(s) != minPaint2(s) || minPaint1_1(s) != minPaint2(s)) {
        System.out.println(s);
        System.out.println(minPaint1(s));
        System.out.println(minPaint2(s));
        System.out.println(minPaint1_1(s));
        System.out.println("Oops");
        break;
      }
    }
    System.out.println("done");

  }

}
