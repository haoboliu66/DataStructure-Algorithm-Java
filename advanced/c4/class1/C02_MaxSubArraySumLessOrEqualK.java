package advanced.c4.class1;

import java.util.*;

/**
 * @author andy-liu
 * @date 2020/7/19 - 4:26 PM
 */
public class C02_MaxSubArraySumLessOrEqualK {
    /*
    给定一个数组arr, 一个K值, 返回累加和小于等于K,但是离K最近的子数组累加和
     */

    public static int getMaxLessOrEqualK(int[] arr, int K) {

        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            //子数组累加和<=K, 前缀和 >=K
            if (set.ceiling(sum - K) != null) {
                max = Math.max(max, sum - set.ceiling(sum - K));
            }
            set.add(sum);

        }
        return max;
    }

    public static void main(String[] args) {
        Integer[] array = {6, 4, 1, 2, 3, 5};
        int[] arr = {6, 4, 1, 2, 3, 5};
        TreeSet<Integer> set = new TreeSet();
        List<Integer> list = Arrays.asList(array);
        set.addAll(list);
        System.out.println(set);

        System.out.println(getMaxLessOrEqualK(arr, 8));
    }
}
