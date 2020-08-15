package advanced.c5.class2;

/**
 * @author andy-liu
 * @date 2020/8/13 - 7:41 PM
 */
public class C02_Candy {

    /*
    135. Candy
     */

    public static int candy(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[] left = new int[N];
        int[] right = new int[N];
        left[0] = 1;
        right[N - 1] = 1;
        for (int i = 1; i < N; i++) {
            left[i] = arr[i] > arr[i - 1] ? left[i - 1] + 1 : 1;
        }
        for (int i = N - 2; i >= 0; i--) {
            right[i] = arr[i] > arr[i + 1] ? right[i + 1] + 1 : 1;
        }
        int res = 0;
        for (int i = 0; i < N; i++) {
            res += Math.max(left[i], right[i]);
        }

        return res;
    }
}
