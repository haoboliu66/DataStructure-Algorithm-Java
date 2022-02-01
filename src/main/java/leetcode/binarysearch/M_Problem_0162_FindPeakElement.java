package src.main.java.leetcode.binarysearch;

public class M_Problem_0162_FindPeakElement {

    public static int findPeakElement(int[] arr) {
        if (arr.length == 1) return 0;
        int n = arr.length;
        int left = 0, right = n - 1, mid;
        while (left < right) {
            mid = left + ((right - left) >> 1);

            if (mid > 0 && arr[mid] > arr[mid - 1] && mid < n - 1 && arr[mid] > arr[mid + 1]) {
                return mid;
            }
            if (arr[mid] < arr[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public static int findPeakElement2(int[] arr) {
        if (arr.length == 1) return 0;
        int n = arr.length;
        if (arr[0] > arr[1]) return 0;
        if (arr[n - 1] > arr[n - 2]) return n - 1;

        int left = 1, right = n - 2, mid;
        while (left < right) {
            mid = left + ((right - left) >> 1);

            if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
                return mid;
            }
            if (arr[mid] < arr[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
