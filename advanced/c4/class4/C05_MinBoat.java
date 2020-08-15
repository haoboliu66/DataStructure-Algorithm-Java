package advanced.c4.class4;

import java.util.Arrays;

/**
 * @author andy-liu
 * @date 2020/7/24 - 11:20 AM
 */
public class C05_MinBoat {

    /*
    881. Boats to Save People
     */

    // 指针从中间向两头走
    public static int numRescueBoats1(int[] weight, int limit) {
        if (weight == null || weight.length == 0) {
            return 0;
        }
        Arrays.sort(weight);
        int N = weight.length;
        if (weight[N - 1] > limit) {//如果最后一个人的重量超过limit, 没法运
            return -1;
        }

        int mid = -1;
        for (int i = N - 1; i >= 0; i--) {
            if (weight[i] <= limit / 2) {
                mid = i;
                break;
            }
        }

        //找到第一个<=limit/2重量的位置, 如果mid是-1, 说明每个人的重量都超过了limit/2
        if (mid == -1) {
            return N;
        }

        int L = mid;
        int R = mid + 1;
        int leftMissed = 0; //左侧 画 ❌ 的位置
        while (L >= 0) {
            int rightSolved = 0;
            while (R < N && weight[L] + weight[R] <= limit) {
                //当前的L, 让R一直向右走
                R++;
                rightSolved++;
            }
            if (rightSolved == 0) { //如果R没动, 继续试下一个L
                L--;
                leftMissed++;
            } else {// 如果左侧先耗尽, L会取到-1, 直接会导致外面的循环结束
                L = Math.max(-1, L - rightSolved);
            }
        }
        int leftAll = mid + 1; //左半区的总数量
        int used = leftAll - leftMissed;// 左半区的✅数量
        int rightUnsolved = N - leftAll - used;//右半区的❌数量
        return used + ((leftMissed + 1) >> 1) + rightUnsolved;
    }

    // 指针从两头向中间走
    public static int numRescueBoats2(int[] weight, int limit) {

        Arrays.sort(weight);
        int i, j;
        for(i = 0, j = weight.length - 1; i <= j; j--){
            if(weight[i] + weight[j] <= limit) {
                i++;
            }
        }
        return weight.length - 1 - j;  // j走过的位置包含✅和❌, 每个✅是一条船, 每个❌也是一条船
    }


    public static void main(String[] args) {
        int[] people = {5, 1, 4, 2};
        int limit = 6;
        System.out.println(numRescueBoats1(people, limit));
        System.out.println(numRescueBoats2(people, limit));
    }
}
