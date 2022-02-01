package src.main.java.advanced.c4.class4;

import java.util.Arrays;

public class C02_KthMinInTwoSortedArray {

    /*
    4. Median of Two Sorted Arrays
    算法原型: 假设两个有序数组,且长度相等, 求两个数组排序后的上中位数(奇数偶数情况不同)
    */

    //两个有序数组, 长度M和N, 找到第K小的数字
    public static int findKthNum(int[] arr1, int[] arr2, int k) {
        if (arr1 == null || arr2 == null) {
            return -1;
        }
        if (k < 1 || k > arr1.length + arr2.length) {
            return -1;
        }
        int[] longs = arr1.length >= arr2.length ? arr1 : arr2;
        int[] shorts = longs == arr1 ? arr2 : arr1;
        int l = longs.length;  //长数组的长度
        int s = shorts.length; //短数组的长度

        //所求的k的范围, 三种情况
        if (k <= s) {
            return getUpMedian(shorts, 0, k - 1, longs, 0, k - 1);
        }

        if (k > l) {
            if (longs[k - s - 1] >= shorts[s - 1]) {
                return longs[k - s - 1];
            }
            if (shorts[k - l - 1] >= longs[l - 1]) {
                return shorts[k - l - 1];
            }
            return getUpMedian(shorts, k - l, s - 1, longs, k - s, l - 1);
        }

        //   s < k <= l
        if (longs[k - s - 1] >= shorts[s - 1]) {
            return longs[k - s - 1];
        }
        return getUpMedian(shorts, 0, s - 1, longs, k - s, k - 1);
    }


    /*
    算法原型: 假设两个有序数组,且长度相等, 求两个数组排序后的上中位数(奇数偶数情况不同)
     */
    public static int getUpMedian(int[] arr1, int L1, int R1, int[] arr2, int L2, int R2) {
        int mid1;
        int mid2;
        while (L1 < R1) {
            mid1 = ((L1 + R1) >> 1);
            mid2 = ((L2 + R2) >> 1);
            if (arr1[mid1] == arr2[mid2]) {
                return arr1[mid1];
            }

            if (((R1 - L1 + 1) & 1) == 0) { //even length
                if (arr1[mid1] > arr2[mid2]) {
                    R1 = mid1;
                    L2 = mid2 + 1;
                } else {
                    L1 = mid1 + 1;
                    R2 = mid2;
                }

            } else {    // odd length
                if (arr1[mid1] > arr2[mid2]) {
                    if (arr2[mid2] > arr1[mid1 - 1]) { //奇数需要额外一步手动验证
                        return arr2[mid2];
                    } else {
                        R1 = mid1 - 1;
                        L2 = mid2 + 1;
                    }

                } else {
                    if (arr1[mid1] > arr2[mid2 - 1]) {
                        return arr1[mid1];
                    } else {
                        L1 = mid1 + 1;
                        R2 = mid2 - 1;
                    }
                }
            }
        }
        //如果while过程中没有返回,
        // 说明到了base case: arr1和arr2中都只剩一个数字了, 返回其中小的

        return Math.min(arr1[R1], arr2[R2]);
    }


    public static int findKthMin2(int[] arr1, int[] arr2, int K) {

        int ATest = process(arr1, arr2, 0, arr1.length - 1, K);
        return ATest == 0 ? process(arr2, arr1, 0, arr2.length - 1, K) : ATest;
    }

    public static int process(int[] arr1, int[] arr2, int L, int R, int K) {
        if (L >= R) {
            return 0;
        }
        int mid1 = arr1[(R - L - 1) / 2]; //arr1中点
        int leftNums = (R - L - 1) / 2;  // mid1的左侧有多少个数字, 不包含mid1
        int index = findNumInArray(arr2, mid1);
        if (index == -1) {
            return arr1[K - 1];
        }
        if (index + leftNums == K - 1) {
            return mid1;
        } else if (index + leftNums > K - 1) {
            return process(arr1, arr2, L, mid1 - 1, K);
        } else {
            return process(arr1, arr2, mid1 + 1, R, K);
        }
    }

    private static int findNumInArray(int[] arr, int target) {
        int L = 0;
        int R = arr.length - 1;
        int mid = L;
        while (L <= R) {
            mid = L + ((R - L) >> 1);
            if (arr[mid] <= target) {
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }

        return R < 0 ? -1 : R;
    }


    /*
    两个有序数组中, 找到合并后第K小的数字
     */
    public static int findKthMin1(int[] arr1, int[] arr2, int K) {
        int L1 = 0, L2 = 0;
        int time = 0;
        while (time < K - 1) {
            if (arr1[L1] < arr2[L2]) {
                L1++;
            } else {
                L2++;
            }
            time++;
        }
        return Math.min(arr1[L1], arr2[L2]);
    }


    // for test ↓
    public static int right(int[] arr1, int[] arr2, int K) {
        int[] help = new int[arr1.length + arr2.length];
        int index = 0;
        int L1 = 0;
        int L2 = 0;
        // O(M + N)
        while (L1 < arr1.length && L2 < arr2.length) {
            if (arr1[L1] < arr2[L2]) {
                help[index++] = arr1[L1++];
            } else {
                help[index++] = arr2[L2++];
            }
        }
        while (L1 < arr1.length) {
            help[index++] = arr1[L1++];
        }
        while (L2 < arr2.length) {
            help[index++] = arr2[L2++];
        }
        return help[K - 1];
    }

    public static int[] generateSortedArray(int len, int maxValue) {
        int[] res = new int[len];
        for (int i = 0; i != len; i++) {
            res[i] = (int) (Math.random() * (maxValue + 1));
        }
        Arrays.sort(res);
        return res;
    }


    public static void main(String[] args) {
        int len1 = 10;
        int len2 = 23;
        int maxValue1 = 20;
        int maxValue2 = 100;
        int times = 100000000;
        int[] arr1 = generateSortedArray(len1, maxValue1);
        int[] arr2 = generateSortedArray(len2, maxValue2);
        int K = 6;
        for (int i = 0; i < times; i++) {
            if (findKthMin2(arr1, arr2, K) != right(arr1, arr2, K)) {
                System.out.println("Oops");
                break;
            }
        }
        System.out.println("Finished");

    }

}
