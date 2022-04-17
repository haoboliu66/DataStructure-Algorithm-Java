package java.someOA;

public class Problem_0307_RangeSumQueryMutable {

    // segment tree

    public static void main(String[] args) {
        int[] arr = {9,-8};
        NumArray na = new NumArray(arr);
        na.update(0,3);
        System.out.println(na.sumRange(1, 1));
        System.out.println(na.sumRange(0, 1));
        na.update(1,-3);
        System.out.println(na.sumRange(0, 1));
    }

    static class NumArray {

        int[] sum;
        int[] copy;
        int diff = 0;
        int pos = -1;

        public NumArray(int[] arr) {
            copy = new int[arr.length];
            sum = new int[arr.length + 1];
            // [1,2] => [0,3] - [0,1]
            for (int i = 0; i < arr.length; i++) {
                copy[i] = arr[i];
            }
            for (int i = 1; i < sum.length; i++) {
                sum[i] = sum[i - 1] + arr[i - 1];
            }
        }

        public void update(int index, int val) {
            int old = copy[index];
            copy[index] = val;
            diff = val - old;
            pos = index;
        }

        public int sumRange(int left, int right) {
            if (left == right) {
                return copy[left];
            }
            if (right < pos) {
                return sum[right + 1] - sum[left];
            }
            if (left >= pos) {
                return sum[right + 1] - sum[left] + diff + diff;
            }
            System.out.println("----");
            return sum[right + 1] - sum[left] + diff;
        }

    }


}
