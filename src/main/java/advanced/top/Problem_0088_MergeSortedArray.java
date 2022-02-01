package src.main.java.advanced.top;

import java.util.Arrays;

public class Problem_0088_MergeSortedArray {

    public static void merge(int[] nums1, int m, int[] nums2, int n) {

        int index = m + n - 1;
        int i, j;
        for (i = m - 1, j = n - 1; i >= 0 && j >= 0; ) {
            nums1[index--] = nums1[i] >= nums2[j] ? nums1[i--] : nums2[j--];
        }

        while(i >= 0){
            nums1[index--] = nums1[i--];
        }

        while(j >= 0){
            nums1[index--] = nums2[j--];
        }

    }

    public static void main(String[] args) {
        int[] nums1 = {0};
        int[] nums2 = {1};
        merge(nums1, 0, nums2, 1);
        System.out.println(Arrays.toString(nums1));
    }


}
