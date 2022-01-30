package advanced.c4.class8;

public class C04_HanoiTrack {

    public static int step1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        return process(arr, arr.length - 1, 1, 2, 3);
    }


    // 目标是:  把0~i的圆盘，从from全部挪到to上
    // 返回，根据arr中的状态arr[0..i]，它是最优解的第几步？
    // f(i, 3 , 2, 1)    f(i, 1, 3, 2)    f(i, 3, 1, 2)
    // 复杂度O(N)
    public static int process(int[] arr, int i, int from, int other, int to) {
        if (i == -1) {
            return 0;
        }
        /*
        情况1: i号圆盘还在from上
        情况2: i号圆盘在to上
        情况3: i号圆盘在other上  ---> 不是最优解
         */

        //最优解不可能出现的情况是: 第i号圆盘移到了other上
        if (arr[i] != from && arr[i] != to) {
            return -1;
        }

        // i号圆盘还在from上, 说明当前已经走的步数是把i-1从from移动到other走过的步数
        if (arr[i] == from) {
            return process(arr, i - 1, from, to, other); //也有可能返回-1, 那就是走第一阶段的时候就违规了
        }

        // i号圆盘在to上, 说明当前已经走的步数处在第三阶段将i-1从other移动到to的步数
        int rest = 0;
        if (arr[i] == to) {

            //第三阶段完成的程度,记为rest
            rest = process(arr, i - 1, other, from, to);
            if (rest == -1) {
                return -1;
            }

        }
        // N层汉诺塔问题, 最优解步数是2^N - 1
        //第一阶段步数 2^i - 1, 第二阶段步数 1, 所以前2个阶段总步数 2^i(因为i从0开始)
        return (1 << i) + rest;
    }


    public static void main(String[] args) {

        int[] arr = {3, 3, 2, 1};
        System.out.println(step1(arr));
//        System.out.println(step2(arr));

    }
}
