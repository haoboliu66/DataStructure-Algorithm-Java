package advanced.c3.class1;

public class C06_MakeNoEqual {

    /*
    给定一个正整数M, 请构造出一个长度为M的数组arr,
    要求对于任意的三个位置: i<j<k, 都有arr[i]+arr[k] != 2*arr[j], 返回构造出的arr
     */
    public static int[] makeNo(int size) {
        if (size == 1) {
            return new int[]{1};
        }
        // M = 14 ---> 7
        // M = 15 ---> 8
        // M = 16 ---> 8
        int halfSize = (size + 1) / 2; // size长度需要的种子的大小
        int[] base = makeNo(halfSize); //递归求种子
        int[] ans = new int[size];
        int index = 0;
        //把种子的数字转换成对应的奇数和偶数依次填入新数组
        for (; index < halfSize; index++) {
            ans[index] = base[index] * 2 - 1; //种子的数字转换成对应的奇数
        }
        for (int i = 0; index < size; i++) {
            ans[index++] = base[i] * 2; //种子的数字转换成对应的偶数
        }

        /*
        因为ans数组有可能不是种子的2倍规模, 所以不能遍历两倍种子来填入数字(会越界),
        只能按照ans的size来遍历
         */

//        for (int i = 0; i < halfSize; i++) {
//            ans[index++] = base[i] * 2 - 1;
//        }
//
//        for (int i = 0; i < halfSize; i++) {
//            System.out.println("i== " + i + "," + "index== " + index);
//            ans[index++] = base[i] * 2;
//        }

        return ans;
    }

    public static boolean isValid(int[] arr) {
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


    public static void main(String[] args) {
        System.out.println("test begin");
        for (int N = 1; N < 1000; N++) {
            int[] arr = makeNo(N);
            if (!isValid(arr)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test end");

        System.out.println(isValid(makeNo(1042)));
        System.out.println(isValid(makeNo(2981)));

    }

}
