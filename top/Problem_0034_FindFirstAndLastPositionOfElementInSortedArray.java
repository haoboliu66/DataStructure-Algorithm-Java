package advanced.top;

public class Problem_0034_FindFirstAndLastPositionOfElementInSortedArray {

    public int[] searchRange(int[] nums, int target) {

        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }

        int[] res = new int[2];
        int N = nums.length;

        int L = 0;
        int R = N - 1;
        int mid;
        int first;
        int second;

        // 左边第一个>=target的
        while (L <= R) {
            mid = L + ((R - L) >> 1);

            if (nums[mid] >= target) {
                R = mid - 1;
            } else if (nums[mid] < target) {
                L = mid + 1;
            }

        }
        first = L;

        L = first;  // or L = 0
        R = N - 1;
        // 右侧第一个 <=target的
        while (L <= R) {
            mid = L + ((R - L) >> 1);

            if (nums[mid] <= target) {
                L = mid + 1;
            } else if (nums[mid] > target) {
                R = mid - 1;
            }

        }
        second = R;

        return first <= N - 1 && second >= 0 && nums[first] == nums[second] ? new int[]{first, second} : new int[]{-1, -1};
    }
}
