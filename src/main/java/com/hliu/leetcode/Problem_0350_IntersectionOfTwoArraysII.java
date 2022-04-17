package src.main.java.leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Problem_0350_IntersectionOfTwoArraysII {

    public static int[] intersect(int[] arr1, int[] arr2) {

        if (arr1 == null || arr2 == null) return null;

        if (arr1.length == 0 || arr2.length == 0) return new int[]{};

        int[] shortArr = arr1.length < arr2.length? arr1: arr2;
        int[] longArr = shortArr == arr1? arr2: arr1;

        List<Integer> list1 = Arrays.stream(shortArr).boxed().collect(Collectors.toList());
        List<Integer> list2 = Arrays.stream(longArr).boxed().collect(Collectors.toList());

        list1.retainAll(list2);

        System.out.println("list1: " + list1);
        int[] res = new int[list1.size()];
        int index = 0;
        for (int n : list1) {
            res[index++] = n;
        }

        return res;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 2, 1};
        int[] arr2 = {2};
        int[] res = intersect(arr1, arr2);
        System.out.println(Arrays.toString(res));
    }

}
