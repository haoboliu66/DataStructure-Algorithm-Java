package src.main.java.sys.c4;

import org.junit.Test;

import java.util.Arrays;

public class C06_MakesNoEqual {


    @Test
    public void test() {
        int N = 2500;
        System.out.println("Test begin");
        for (int i = 3; i < N; i++) {
            int[] res = generateArray(i);
            if (!isValid0(res, i)) {
                System.out.println("Oops");
                System.out.println("N: " + i);
                System.out.println("arr: " + Arrays.toString(res));
                break;
            }
        }
        System.out.println("Test end");
    }

    public static boolean isValid0(int[] arr, int len) {
        if (arr.length != len) {
            return false;
        }
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            for (int k = i + 1; k < N; k++) {
                for (int j = k + 1; j < N; j++) {
                    if (arr[i] + arr[j] == 2 * arr[k]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isValid(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int k = i + 1; k < arr.length; k++) {
                for (int j = k + 1; j < arr.length; j++) {
                    if (arr[i] + arr[j] == arr[k] * 2) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public int[] generateArray(int N) {
        if (N < 3) {
            return null;
        }
        // 1 + 4 =>
        // 11 -> 12,
        int len = N % 2 == 0 ? N : N + 1;
        int[] baseArray = getBase(len);
        int[] res = new int[N];
        System.arraycopy(baseArray, 0, res, 0, N);

        return res;
    }

    public int[] getBase(int n) {
        if (n == 1) {
            return new int[]{1};
        }
        n = n % 2 == 0 ? n : n + 1;
        int[] res = new int[n];
        int[] base = getBase(n / 2);
        for (int i1 = 0, i2 = n / 2; i2 < n; i1++, i2++) {
            res[i1] = 2 * base[i1];
            res[i2] = 2 * base[i1] + 1;
        }
        return res;
    }


}
