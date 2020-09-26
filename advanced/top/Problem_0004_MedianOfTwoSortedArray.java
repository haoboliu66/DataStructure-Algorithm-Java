package advanced.top;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Problem_0004_MedianOfTwoSortedArray {

    /*
    c4 - class4 - C02
     */

//    public static int

    // 2个有序等长数组, 求上中位数
    public static int getUpMedian(int[] arr1, int L1, int R1, int[] arr2, int L2, int R2) {

        int mid1;
        int mid2;

        while (L1 < R1) {
            mid1 = ((R1 + L1) >> 1);
            mid2 = ((R2 + L2) >> 1);

            if (arr1[mid1] == arr2[mid2]) {
                return arr1[mid1];
            }

            if (((R1 - L1 + 1) & 1) == 0) {  // even length
                if (arr1[mid1] > arr2[mid2]) {
                    R1 = mid1;
                    L2 = mid2 + 1;
                } else {
                    L1 = mid1 + 1;
                    R2 = mid2;
                }

            } else {  //odd length
                if (arr1[mid1] > arr2[mid2]) {
                    if (arr2[mid2] >= arr1[mid1 - 1]) {
                        return arr2[mid2];
                    }
                    R1 = mid1 - 1;
                    L2 = mid2 + 1;
                } else {
                    if (arr1[mid1] >= arr2[mid2 - 1]) {
                        return arr1[mid1];
                    }
                    L1 = mid1 + 1;
                    R2 = mid2 - 1;
                }
            }
        }

        return Math.min(arr1[R1], arr2[R2]);
    }

}
