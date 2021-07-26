package advanced.c2._2_arraysum;

public class Array3_LongestLessSumSubArrayLength {
    /*
    Q: 一个数组arr[] 有正数,有负数, 有0, 所有累加和小于等于K的子数组全达标, 求在这些子数组中最长的一个
    数组三连3 - 最难的一题
     */

    public static int getMaxLength(int[] arr, int K) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[] minSums = new int[N];
        int[] minSumEnds = new int[N];
        minSums[N - 1] = arr[N - 1];
        minSumEnds[N - 1] = N - 1;
        for (int i = N - 2; i >= 0; i--) {
            minSums[i] = Math.min(arr[i], minSums[i + 1] + arr[i]);
            minSumEnds[i] = minSums[i + 1] <= 0 ? minSumEnds[i + 1] : i;
        }

        int end = 0; // (i...)(...)(...)(end...) 从end开始扩不动了
        int sum = 0; // end之前的累加和
        int len = 0;
        // i 就是于类似窗口的左边界
        for (int i = 0; i < arr.length; i++) {
            //开始向右扩窗口, 找到end能达到的最远位置
            while (end < arr.length && sum + minSums[end] <= K) {
                sum += minSums[end]; //累加上当前块的sum
                end = minSumEnds[end] + 1; //end跳到当前块末尾的下一个位置
            }
            //窗口已形成 [i....] (end....
            len = Math.max(len, end - i);

            //开始缩窗口  sum -= arr[i];
            //但是考虑到窗口有可能直到缩没了 也不达标
            if (end > i) {
                sum -= arr[i]; //窗口中还有数字, 减掉左侧的值, 缩减窗口看能不能继续向右扩
            } else {
                end = i + 1; //窗口中没数字了, 没法再减去左侧的值了, 直接向右移
            }
        }

        return len;
    }


    /* 另一种解法  */
    // 维护一个只升不降的help数组, 便于查找 >= N 最早的位置, 可以二分查找
    // 复杂度O(N*logN)



}
