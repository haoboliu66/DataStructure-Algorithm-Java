package advanced.c1.slidingwindow;

import java.util.LinkedList;

/**
 * @author andy-liu
 * @date 2020/5/27 - 11:46 AM
 */
public class MaxMinusMin {

    /**
     * 给定一个整数数组arr, 和一个整数num
     * 某个arr中的子数组sub, 如果想达标, 必须满足:
     * sub中的最大值 - sub中的最小值 <= num
     * 返回arr中达标的子数组数量
     */

    public static int subArrayNum(int[] arr, int num) {
        LinkedList<Integer> qmax = new LinkedList<>(); //双向队列保存最大值
        LinkedList<Integer> qmin = new LinkedList<>(); //双向队列保存最小值
        int res = 0;
        int L = 0;
        int R = 0;

        while (L < arr.length) { //尝试每一个L

            while (R < arr.length) {
                //更新最大值
                while (!qmax.isEmpty() && arr[R] >= arr[qmax.peekLast()]) {
                    qmax.pollLast();
                }
                qmax.addLast(R);

                //更新最小值
                while (!qmin.isEmpty() && arr[R] <= arr[qmin.peekLast()]) {
                    qmin.pollLast();
                }
                qmin.addLast(R);
                //取max和min检验
                if (arr[qmax.getFirst()] - arr[qmin.getFirst()] > num) {
                    break;
                }
                R++;
            }
            // R已经越界 或者 触发了break, R已经来到了第一个违规的位置,
            // 所以不能包含R的位置subArray总个数是R - L
            res += R - L;

            // 检验最大值和最小值是否会在窗口左移后过期, 如果会, 就直接弹出
            if(qmax.peekFirst() == L){
                qmax.pollFirst();
            }
            if(qmin.peekFirst() == L){
                qmin.pollFirst();
            }
            L++;
        }
        return res;
    }



    // for test
    public static int[] getRandomArray(int len) {
        if (len < 0) {
            return null;
        }
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * 10);
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        int[] arr = getRandomArray(30);
//        int[] arr = {4, 1, 6, 6, 7, 8, 1, 2, 9, 5};
        int num = 5;
        printArray(arr);
        System.out.println(subArrayNum(arr, num));

    }
}
