package advanced.c3.class6;

import java.util.HashMap;

public class C05_Split4Parts {

    /*
    Q: 给定一个正数数组arr, 返回该数组能不能分成4个部分, 并且每个部分的累加和相等, 切分位置的数字不要
    arr=[3,2,4,1,4,9,5,10,1,2,2], 返回true, 四个子数组[3,2],[1,4],[5],[1,2,2]

    548. Split Array with Equal Sum
     */

    public static boolean canSplits(int[] arr) {
        //首先数组的长度必须大于等于7, 4个部分加3刀 ===>  0 ↓ 0 ↓ 0 ↓ 0
        if (arr == null || arr.length < 7) {
            return false;
        }
        int N = arr.length;
        HashMap<Integer, Integer> sumMap = new HashMap<>();
        int sum = arr[0];
        for (int i = 1; i < N; i++) { //统计前缀和, 放入哈希表
            sumMap.put(sum, i);
            sum += arr[i];
        }
        System.out.println(sumMap);
        int leftSum = arr[0];
        for (int i = 1; i <= N - 6; i++) { //枚举第一刀的位置
            int s1 = sumMap.get(leftSum);  //第一刀的位置s1
            System.out.println("s1: " + s1);
            int checkSum = arr[s1] + 2 * leftSum; //第二刀的左侧应该有两个leftSum + 第一刀所在位置的值

            if (sumMap.containsKey(checkSum)) {
                int s2 = sumMap.get(checkSum); //第二刀位置
                System.out.println("s2: " + s2);
                checkSum += arr[s2] + leftSum;

                if (sumMap.containsKey(checkSum)) {
                    int s3 = sumMap.get(checkSum); //第三刀位置
                    System.out.println("s3: " + s3);
                    if (leftSum + arr[s3] + checkSum == sum) { //切完第三刀, 检验第四部分是不是等于leftSum
                        return true;
                    }
                }
            }
            leftSum += arr[i];
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 4, 1, 4, 9, 5, 10, 1, 2, 2};
        int[] arr1 = {3, 2, 1, 10, 3, 3, 5, 3, 3, 2, 3, 3, 0, 0};

        int[] arr2 = {-1, 0, -1, 0, -1, 0, -1};
        // 负数没有单调性, 会出现前缀和重复导致index被覆盖的情况
        System.out.println(canSplits(arr2));

    }

}

