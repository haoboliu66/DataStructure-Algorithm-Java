package advanced.c4.class3;

import org.junit.Test;

public class C04_KTimesOneTime {

    public static int onceNum(int[] arr, int k) {

        int[] eO = new int[32];
        for (int i = 0; i < arr.length; i++) {
            //把arr[i]变成k进制的形式, 每一位累加到数组eo上
            setExclusiveOr(eO, arr[i], k);
        }
        // kBase数转成Decimal
        int res = getDecimalFromKBase(eO, k);
        return res;
    }

    public static void setExclusiveOr(int[] eO, int num, int k) {
        int[] kBase = getKBaseFromDecimal(num, k); //把num由decimal转成k进制

        for (int i = 0; i < eO.length; i++) {
            eO[i] = (eO[i] + kBase[i]) % k;  //提前%k防止溢出
        }
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
//        for (int i = 0; i < kBase.length; i++) {
//            res += kBase[i] * Math.pow(k, i);
//        }
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
        int[] test1 = { 1, 1, 1, 2, 6, 6, 2, 2, 10, 10, 10, 12, 12, 12, 6, 9 };
        System.out.println(onceNum(test1, 3));

        int[] test2 = { -1, -1, -1, -1, -1, 2, 2, 2, 4, 2, 2 };
        System.out.println(onceNum(test2, 5));

    }


}
