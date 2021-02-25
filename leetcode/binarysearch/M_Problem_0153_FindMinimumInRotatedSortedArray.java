package leetcode.binarysearch;

public class M_Problem_0153_FindMinimumInRotatedSortedArray {

    public static int findMin(int[] arr) {
        if (arr.length == 1) return arr[0];

        int n = arr.length;
        int lo = 0, hi = n - 1, mid;
        while (lo <= hi) {
            mid = lo + ((hi - lo) >> 1);

            if ((mid == 0 || arr[mid] > arr[mid - 1]) && (mid == n - 1 || arr[mid] > arr[mid + 1])) {
                return mid == 0 ? Math.min(arr[mid], arr[mid + 1]) : (mid == n - 1) ? Math.min(arr[mid], arr[mid - 1]) : Math.min(arr[mid - 1], arr[mid + 1]);
            }

            if ((mid == 0 || arr[mid] < arr[mid - 1]) && (mid == n - 1 || arr[mid] < arr[mid + 1])) {
                return mid == 0 ? Math.min(arr[mid], arr[mid + 1]) : (mid == n - 1) ? Math.min(arr[mid], arr[mid - 1]) : arr[mid];
            }

            if (arr[mid] >= arr[lo] && arr[mid] >= arr[hi]) {
                if (arr[lo] > arr[hi]) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }

            } else {
                hi = mid - 1;
            }
        }

        return arr[lo];
    }

    // Led by Discuss
    public static int findMin2(int[] arr) {
        int n = arr.length;
        int left = 0, right = n - 1, mid;

        while (left < right) {  // 只有1个元素时退出循环
            /*
            对于test case[1]，如果使用left<=right就会进入死循环
            对于test case[2,1]，如果使用left<=right就会进入死循环
             */
            mid = left + ((right - left) >> 1);

            if (arr[left] < arr[right]) return arr[left];

            if (arr[mid] > arr[right]) {
                // 当只有2个元素时，mid + 1就是right，即left和right重合
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return arr[left];
    }

    public int findMin3(int[] nums) {
        int left = 0, right = nums.length - 1, mid;
        /*
        left < right - 1 helps avoid edge cases with 1 or 2 numbers
        https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/discuss/48589/9-line-java-code-beats-95.14-run-times
         */
        while (left < right - 1) {  // while (left < right-1) is a useful technique
            mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return Math.min(nums[left], nums[right]);

    }

    public static void main(String[] args) {
        int[] arr = {2, 1};
        int res = findMin2(arr);
        System.out.println(res);


    }
}
