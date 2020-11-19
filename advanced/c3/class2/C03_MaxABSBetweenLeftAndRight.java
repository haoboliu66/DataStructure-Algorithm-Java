package advanced.c3.class2;

public class C03_MaxABSBetweenLeftAndRight {
    /*
    Q: 给定一个arr长度为N, 可以把任意长度大于0且小于N的前缀作为左部分, 剩下的作为右部分,
    每种划分下都有左边的最大值和右边的最大值, 请返回最大的, 左max - 右max的绝对值
     */

    // 可能性的舍弃, 单调性(划分范围变大, max只可能变大)
    public static int maxAbsoluteDifference(int[] arr) {
        int max = Integer.MIN_VALUE;
        int N = arr.length;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(arr[i], max);
        }
        return max - Math.min(arr[0], arr[N - 1]);
    }
}
