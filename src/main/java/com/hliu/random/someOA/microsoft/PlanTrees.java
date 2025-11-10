package com.hliu.random.someOA.microsoft;

import java.util.Arrays;

public class PlanTrees {

  /*
  We are provided with a plan of an industrial village, represented by an array A consisting of N integers. The K-th value (for K within the range O...N-1) represents a field which may contain:
a forest consisting of A[K] trees (if A[K] is positive); or
an industrial building (if A[K] is non-positive), producing -A[K] units of pollution.
One tree is able to neutralize one unit of pollution. Our goal is to make every neighborhood sustainable, i.e. for every field, the sum of its value and the values of its neighbors (adjacent fields to the left and right) should be greater than or equal to zero. To achieve this goal, we can plant additional trees in any chosen field (note that we can plant trees in fields containing industrial buildings).

For example, given A = [1, -3, 2], there is one tree in the field
number 0, an industrial building producing 3 units of pollution in field number 1 and two trees in field number 2. The sums of
values of the fields and their neighbors are 1 + (-3) = -2 for
field number 0, 1 + (-3) + 2 = 0 for field number 1, and (-3) + 2
= -1 for field number 2. The neighborhoods of fields 0 and 2 are not sustainable, as their sums are negative. After planting
two trees in field 1, we obtain A = [1, -1, 2]. In the new array,
the sums are respectively 0, 2, and 1, which makes every neighborhood sustainable.
What is the minimum number of trees we have to plant in order to make every field's neighborhood sustainable?
   */

  public static void main(String[] args) {
    int[] arr = {-3, 2, 4, -5, 3};  // 3
    int[] arr1 = {1, -3, 2};  // 2

    int res = solution1(arr1);
    int res1 = solution2(arr1);

    if(res != res1){
      System.out.println("res: " + res + " res1: " + res1);
    }

  }

  public static int solution1(int[] A) {
    int n = A.length;
    int[] arr = new int[n + 2];  // 加入2个dummy数据避免越界
    arr[0] = 0;
    arr[arr.length - 1] = 0;
    for(int i = 1; i < arr.length - 1; i++){
      arr[i] = A[i - 1];
    }

    n = arr.length;
    int plant = 0;
    for (int i = 1; i < n - 1; i++) {
      int sum =  arr[i - 1] + arr[i] + arr[i + 1];
      if (sum < 0) {
        arr[i + 1] += Math.abs(sum);
        /*
         此时sum<0, 意味着我要种树, 那么arr[i-1], arr[i], arr[i+1]往哪里种效果最好, 那肯定是种在arr[i+1], 这样既解决了当前的问题, 又为下一组做了贡献
         所以arr[i+1]上要累加这个欠缺的个数;
         */
        plant += Math.abs(sum);  // 收集要种的数量
      }
    }
    return plant;
  }


  public static int solution2(int[] arr) {
    int n = arr.length;
    int plant = 0;
    for (int i = 0; i < n; i++) {

      if (i == 0) {
        if (arr[i] + arr[i + 1] < 0) {
          plant += Math.abs(arr[i] + arr[i + 1]);
          arr[i + 1] += Math.abs(arr[i] + arr[i + 1]);
        }

      } else if (i == n - 1) {
        if (arr[i] + arr[i - 1] < 0) {
          plant += Math.abs(arr[i] + arr[i - 1]);
        }
        // 已经是最后一个index了, 意味着种哪里都可以, 不需要再greedy为后续的计算做贡献了
      } else {
        int sum = arr[i] + arr[i - 1] + arr[i + 1];
        if (sum < 0) {
          arr[i + 1] += Math.abs(sum);
          plant += Math.abs(sum);
        }

      }
    }
    return plant;
  }

  public static int solution(int[] arr) {
    // -3 2 4 -5 3  => 3
    System.out.println(Arrays.toString(arr));
    int n = arr.length;
    boolean[] need = new boolean[n];
    int[] plant = new int[n];
    for (int i = 0; i < n; i++) {
      if (i == 0 && arr[i] + arr[i + 1] < 0) {
        need[i] = true;
        plant[i] = -(arr[i] + arr[i + 1]);
      } else if (i == n - 1 && arr[i] + arr[i - 1] < 0) {
        need[i] = true;
        plant[i] = -(arr[i] + arr[i - 1]);
      } else {
        if (arr[i] + arr[i - 1] + arr[i + 1] < 0) {
          need[i] = true;
          plant[i] = -(arr[i] + arr[i - 1] + arr[i + 1]);
        }
      }
    }
    System.out.println(Arrays.toString(need));
    System.out.println(Arrays.toString(plant));

    return 0;
  }

}
