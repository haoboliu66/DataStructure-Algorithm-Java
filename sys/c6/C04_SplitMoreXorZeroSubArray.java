package sys.c6;

public class C04_SplitMoreXorZeroSubArray {

    public static int split(int[] arr) {
        int n = arr.length;
        int count;
        for (int i = 0; i < n; i++) {
            // 第一份怎么切
            count = 0;
            // 从i..j切成第一份, 然后j+1 ...n-1继续处理
            for (int j = i; j < n; j++) {
                if (isXorZero(arr, i, j)) {
                    count++;
                }

            }

        }
        return 0;

    }

    // index....可以分出的最多份
    public static int process(int[] arr, int index, int count) {
        if (index == arr.length) {
            return count;
        }
        for (int i = index; i < arr.length; i++) {
            if (isXorZero(arr, index, i)) {
                count++;

            }
            process(arr, i + 1, count);
        }

        return 0;
    }

    public static boolean isXorZero(int[] arr, int i, int j) {
        return true;
    }

}
