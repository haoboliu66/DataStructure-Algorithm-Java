package advanced.c3.class2;

public class C03_MaxABSBetweenLeftAndRight {
    /*
    Q: 给定一个arr长度为N, 可以把任意长度大于0且小于N的前缀作为左部分, 剩下的作为右部分,
    每种划分下都有左边的最大值和右边的最大值, 请返回最大的, 左max - 右max的绝对值
     */
    public static int maxAbsoluteDifference2(int[] arr) {
        int N = arr.length;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N - 1; i++) {
            int leftMax = i == 0 ? arr[i] : getMaxInRange(arr, 0, i);
            int rightMax = i == N - 2 ? arr[N - 1] : getMaxInRange(arr, i + 1, N - 1);
            max = Math.max(max, Math.abs(leftMax - rightMax));
        }
        return max;
    }

    private static int getMaxInRange(int[] arr, int L, int R) {
        int max = arr[L];
        for (int i = L + 1; i <= R; i++) {
            max = Math.max(arr[i], max);
        }
        return max;
    }

    // 可能性的舍弃, 单调性(划分范围变大, max只可能变大)
    public static int maxAbsoluteDifference(int[] arr) {
        int max = Integer.MIN_VALUE;
        int N = arr.length;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(arr[i], max);
        }
        return max - Math.min(arr[0], arr[N - 1]);
    }

    private static int[] generateRandomArray(int maxLen, int maxVal) {
        int size = maxLen * (int) (Math.random() + 1);
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * maxVal);
        }
        return arr;
    }

    public static void main(String[] args) {
        int maxLen = 20;
        int maxVal = 1500;
        int times = 100000;
        int[] arr;
        System.out.println("Started");
        for (int i = 0; i < times; i++) {
            arr = generateRandomArray(maxLen, maxVal);
            if (maxAbsoluteDifference(arr) != maxAbsoluteDifference2(arr)) {
                System.out.println("Oops");
                break;
            }
        }

        System.out.println("Done");

    }

}
