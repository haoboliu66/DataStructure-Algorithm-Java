package src.main.java.advanced.c4.class3;

import org.junit.Test;

import java.util.Arrays;

public class C04_AmongKTimesFindOneTime {

    public static int onceNum(int[] arr, int k) {

        int[] eO = new int[32];
        for (int i = 0; i < arr.length; i++) {
            //把arr[i]变成k进制的形式, 每一位累加到数组eo上
            accumulateToArray(eO, arr[i], k);
        }
        // kBase数转成Decimal
        int res = getDecimalFromKBase(eO, k);
        return res;
    }

    public static void accumulateToArray(int[] eO, int num, int k) {
        int[] kBase = getKBaseFromDecimal(num, k); //把num由decimal转成k进制

        for (int i = 0; i < eO.length; i++) {
            eO[i] = (eO[i] + kBase[i]) % k;  //提前%k防止溢出
        }
    }

    @Test
    public void transfer() {
        int num = 177, k = 8;
        int[] res = getKBaseFromDecimal(num, k);
        System.out.println(Arrays.toString(res));
        int n = getDecimalFromKBase(res, 8);
        // 0 0 0 0 2 3 3
        System.out.println(n);
    }

    // 把num由decimal转成k进制
    private static int[] getKBaseFromDecimal(int num, int k) {
        int[] kBase = new int[32];
        int index = 0;
        while (num != 0) {
            kBase[index++] = num % k;
            num = num / k;
        }
        return kBase;
    }

    //把一个k进制数转成10进制
    private static int getDecimalFromKBase(int[] kBase, int k) {
        int res = 0;

//        int pow = 1;
//        for (int i = 0; i < kBase.length; i++) {
//            res += kBase[i] * pow;
//            pow *= k;
//        }

        // 1 7 8
        // 8 + 7 * 10 + 1 * 10^2

        // 0 * 10 + 1 = 1
        // 1 * 10 + 7  = 17
        // 17 * 10 + 8 = 178

        // 1 6 2 0 0 0 0 0 0 0 0

        /*
        0 * 8 + 0 = 0
        ......
        0 * 8 + 2 = 2;
        2 * 8 + 6 = 22;
        22 * 8 + 1 = 177
         */

        // 0 0 0 0 ... 2 6 1
        /*
        1 * 8^0 = 1;
        6 * 8^1 = 48;
        2 * 8^2 = 128
         */

        for (int i = kBase.length - 1; i >= 0; i--) {
            res = res * k + kBase[i];
        }
        return res;
    }

//    private static int convert(int[] kBase, int k) {
//        int res = 0;
//        for (int i = 0; i < kBase.length; i++) {
//            res += kBase[i] * Math.pow(k, i);
//        }
//        return res;
//    }

    @Test
    public void test() {
        int[] arr = {1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1};
        int[] arr2 = {0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1};
        int res1 = getDecimalFromKBase(arr2, 2);
//        int res2 = convert(arr2, 2);
        System.out.println(res1);
//        System.out.println(res2);
    }

    public static void main(String[] args) {
        int[] test1 = {1, 1, 1, 2, 6, 6, 2, 2, 10, 10, 10, 12, 12, 12, 6, 9};
        System.out.println(onceNum(test1, 3));

        int[] test2 = {-1, -1, -1, -1, -1, 2, 2, 2, 4, 2, 2};
        System.out.println(onceNum(test2, 5));

    }


}
