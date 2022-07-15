package com.hliu.advanced.c3.class1;

import java.util.Arrays;

import org.junit.Test;

public class C01_CoverMaxPoint {

    /*
    Q: 给定一个有序数组arr, 从左到右依次表示X轴上从左往右点的位置, 给定一个正整数K, 返回如果有一根长度为K的绳子,
    最多能盖住几个点, 绳子的边缘碰到X轴上的点, 也算盖住
     */

  public static int maxP(int[] arr, int K) {
    int len = 1;
    int N = arr.length;
    for (int i = 0; i < N; i++) {
      int rightEnd = arr[i] + K;
      // 右侧第一个 <= end的位置, index是ri
      int ri = rightMostSmaller(arr, i, rightEnd);
      len = Math.max(len, ri - i + 1);
    }
    return len;
  }
    /*
    以每个[i]位置为头, 向后延伸K长度, 找到绳子末尾, 看绳子末尾能覆盖到什么位置 (二分查找)
     */

  public static int rightMostSmaller(int[] arr, int i, int m) {
    int L = i;
    int R = arr.length - 1;
    int mid;
    int target = L;
    while (L <= R) {
      mid = L + ((R - L) >> 1);
      if (arr[mid] <= m) {
        target = mid;
        L = mid + 1;
      } else {
        R = mid - 1;
      }
    }
    return target;
  }

  // test
  public static int rightMostSmaller1(int[] arr, int L, int R, int m) {
    int target = L;
    for (int i = R; i >= L; i--) {
      if (arr[i] <= m) {
        target = i;
        break;
      }
    }
    return target;
  }

  @Test
  public void test() {
    int[] testArr = {7, 9, 11, 11, 16};
//        System.out.println(rightMostSmaller(testArr, 0, 11)); // 2
    // 3   not 2 !!!

    int[] arr = generateArray(50, 500);
    for (int i = 0; i < 200000; i++) {
      int a = (int) ((Math.random() + 1) * 100);
      if (rightMostSmaller(arr, 0, a) != rightMostSmaller1(arr, 0, arr.length - 1, a)) {
        System.out.println("!!!");
        break;
      }
    }
    System.out.println("done");
  }

  /*
  方法一: O(N*log(N))
  以每一个位置为绳子末端, 找到绳子左端L, 对左边有序数组进行二分查找, 找到最左侧>=L的数的index
   */
  public static int maxPoint1(int[] arr, int K) {
    int res = 1;
    for (int i = 0; i < arr.length; i++) {
      int leftEnd = arr[i] - K;  //绳子的左端所代表的的值
      int nearest = nearestIndex(arr, i, leftEnd); //找到arr数组i位置最左侧大于等于leftEnd的index
      res = Math.max(res, i - nearest + 1);
    }
    return res;
  }

  // 找到arr中R左侧第一个大于target的数的index
  public static int nearestIndex(int[] arr, int R, int target) {
    int L = 0;  //都是从0开始查找
    int index = R;
    while (L <= R) {
      int mid = L + ((R - L) >> 1);  //正常二分流程
      if (arr[mid] >= target) {
        index = mid; // mid满足条件记录mid的值
        R = mid - 1;
      } else {
        L = mid + 1;
      }
    }
    return index;
  }

  /*
  方法二: O(N)
  滑动窗口 (单调性)
  以L为头, R扩到不能再扩, 记录点数, 然后缩减L
   */
  public static int maxPoint2(int[] arr, int K) {
    int left = 0;
    int right = 0;
    int N = arr.length;
    int max = 0;
    while (left < N) {
      while (right < N && (arr[right] - arr[left]) <= K) {
        right++;
      }
      max = Math.max(max, right - left);
      left++;
    }
    return max;
  }

  // for test
  public static int test(int[] arr, int L) {
    int max = 0;
    for (int i = 0; i < arr.length; i++) {
      int pre = i - 1;
      while (pre >= 0 && arr[i] - arr[pre] <= L) {
        pre--;
      }
      max = Math.max(max, i - pre);
    }
    return max;
  }

  // for test
  public static int[] generateArray(int len, int max) {
    int[] ans = new int[(int) (Math.random() * len) + 1];
    for (int i = 0; i < ans.length; i++) {
      ans[i] = (int) (Math.random() * max);
    }
    Arrays.sort(ans);
    return ans;
  }

  public static void main(String[] args) {
    int len = 100;
    int max = 1000;
    int testTime = 100000;
    System.out.println("begin");
    for (int i = 0; i < testTime; i++) {
      int L = (int) (Math.random() * max);
      int[] arr = generateArray(len, max);

      int ans1 = maxPoint1(arr, L);
      int ans2 = maxPoint2(arr, L);
      int ans3 = test(arr, L);
      if (ans1 != ans2 || ans2 != ans3) {
        System.out.println("ans1 == " + ans1 + " ,ans2 == " + ans2 + " ,ans3 == " + ans3);
        System.out.println(L + "  " + Arrays.toString(arr));
        System.out.println("oops!");
        break;
      }
    }
    System.out.println("done");

  }
}
