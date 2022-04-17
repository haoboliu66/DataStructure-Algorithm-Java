package src.main.java.advanced.top;


public class Problem_0041_FirstMissingPositive {

    // 268. Missing Number 类似题

    /*
    O(1) extra space
     */
    public int firstMissingPositive2(int[] arr) {
        int l = 0;
        int r = arr.length;

        while (l < r) {
            if (arr[l] == l + 1) {
                l++;
            } else if (arr[l] <= l || arr[l] > r || arr[arr[l] - 1] == arr[l]) {
                swap(arr, l, --r);
            } else {
                swap(arr, l, arr[l] - 1);
            }
        }
        return l + 1;
    }

    public void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    /*
    O(N) extra space
     */
    public int firstMissingPositive1(int[] nums) {
        int[] arr = new int[nums.length];
        int res = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0 && nums[i] <= arr.length) {
                arr[nums[i] - 1] = nums[i];
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                res = i + 1;
                break;
            }
        }

        return res != -1 ? res : arr.length + 1;
    }

}
