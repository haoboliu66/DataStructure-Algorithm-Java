package leetcode.binarysearch;

public class E_Problem_0278_FirstBadVersion {

    // provided method
    boolean isBadVersion(int version) {
        return false;
    }

    public int firstBadVersion(int n) {
        int left = 1, right = n, mid;
        while (left < right) {
            mid = left + ((right - left) >> 1);
            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }


}
