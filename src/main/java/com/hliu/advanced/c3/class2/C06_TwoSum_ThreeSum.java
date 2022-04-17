package src.main.java.advanced.c3.class2;

import java.util.ArrayList;
import java.util.List;


public class C06_TwoSum_ThreeSum {
    /*  3Sum   */

    /*
    Q: 给定一个有序数组和arr, 一个正数aim
    (1)返回所有累加和为aim的不同二元组
    (2)返回所有累加和为aim的不同三元组
     */
    public static List<List<Integer>> printUniquePair(int[] arr, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        if (arr == null || arr.length < 2) {
            return ans;
        }
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            if (arr[left] + arr[right] < k) {
                left++;
            } else if (arr[left] + arr[right] > k) {
                right--;
            } else {  // arr[left] + arr[right] == k) 准备收集答案
                if (left == 0 || arr[left] != arr[left - 1]) {// 避免收集重复答案, 要在满足该条件下才收集
                    List<Integer> res = new ArrayList<>();
                    res.add(arr[left]);
                    res.add(arr[right]);
                    ans.add(res);
                }
                left++;
                right--;
            }
        }
        return ans;
    }

    /*
    求三元组
     */
    public static List<List<Integer>> printUniquePair(int[] arr, int k, int start, int left, int right) {
        List<List<Integer>> ans = new ArrayList<>();

        while (left < right) {
            if (arr[left] + arr[right] < k) {
                left++;
            } else if (arr[left] + arr[right] > k) {
                right--;
            } else {  // arr[left] + arr[right] == k) 准备收集答案
                if (left == start + 1 || arr[left] != arr[left - 1]) {// 避免收集重复答案
                    // left是起始位置的下一个或者left和left前一个不相等
                    List<Integer> res = new ArrayList<>();
                    res.add(arr[left]);
                    res.add(arr[right]);
                    res.add(arr[start]);
                    ans.add(res);
                }
                left++;
                right--;
            }
        }
        return ans;
    }

    // O(N^2)
    public static List<List<Integer>> printUniqueTriad(int[] arr, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        if (arr == null || arr.length < 3) {
            return ans;
        }
        for (int i = 0; i < arr.length - 2; i++) {
            if (i == 0 || arr[i] != arr[i - 1]) {
                int rest = k - arr[i];
                List<List<Integer>> res = printUniquePair(arr, rest, i, i + 1, arr.length - 1);
                ans.addAll(res);
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        int sum = 10;
        int[] arr1 = {-8, -4, -3, 0, 1, 2, 4, 5, 8, 9};
        //求三元组
        List<List<Integer>> lists = printUniqueTriad(arr1, sum);
        lists.forEach(System.out::println);
        System.out.println("==============");
        //求二元组
        List<List<Integer>> res = printUniquePair(arr1, sum);
        for (List<Integer> list : res) {
            System.out.println(list);
        }

    }
}
