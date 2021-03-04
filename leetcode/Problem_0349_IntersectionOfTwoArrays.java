package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Problem_0349_IntersectionOfTwoArrays {


    public static int[] intersection(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null) return null;

        if (arr1.length == 0 || arr2.length == 0) return new int[]{};

        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> intersect = new HashSet<>();
        for (int i = 0; i < arr1.length; i++) {
            set.add(arr1[i]);
        }

        for (int i = 0; i < arr2.length; i++) {
            if (set.contains(arr2[i])) {
                intersect.add(arr2[i]);
            }
        }
        int[] res = new int[intersect.size()];
        int index = 0;
        for (int k : intersect) {
            res[index++] = k;
        }

        return res;
    }

    // Stream
    public int[] intersectionWithStream(int[] nums1, int[] nums2) {
        Set<Integer> a = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
        Set<Integer> b = Arrays.stream(nums2).boxed().collect(Collectors.toSet());
        a.retainAll(b);
        return a.stream().mapToInt(Integer::intValue).toArray();
    }


    public static int[] intersection1(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null) return null;

        if (arr1.length == 0 || arr2.length == 0) return new int[]{};

        HashSet<Integer> set1 = new HashSet<>();
        for (int i = 0; i < arr1.length; i++) {
            set1.add(arr1[i]);
        }

        HashSet<Integer> set2 = new HashSet<>();
        for (int i = 0; i < arr2.length; i++) {
            set2.add(arr2[i]);
        }

        set1.retainAll(set2);
        Object[] arr = set1.toArray();
        int[] res = new int[arr.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = (int) arr[i];
        }
        return res;
    }

    public static int[] intersection2(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null) return null;

        if (arr1.length == 0 || arr2.length == 0) return new int[]{};

        HashMap<Integer, Integer> map1 = new HashMap<>();
        HashMap<Integer, Integer> map2 = new HashMap<>();

        int[] shortArr = arr1.length < arr2.length ? arr1 : arr2;
        int[] longArr = shortArr == arr1 ? arr2 : arr1;

        for (int i = 0; i < shortArr.length; i++) {
            if (map1.containsKey(shortArr[i])) continue;
            map1.put(shortArr[i], i);
        }
        for (int i = 0; i < longArr.length; i++) {
            if (map2.containsKey(longArr[i])) continue;
            map2.put(longArr[i], i);
        }

        Set<Integer> set1 = map1.keySet();
        Set<Integer> set2 = map2.keySet();
        set1.retainAll(set2);

        int[] res = new int[set1.size()];
        int index = 0;
        for (int k : set1) {
            res[index++] = k;
        }

        return res;
    }


    public static void main(String[] args) {
        int[] arr1 = {1, 2, 2, 1};
        int[] arr2 = {2, 2};
        int[] res = intersection2(arr1, arr2);
        System.out.println(Arrays.toString(res));

    }
}
