package advanced.c3.class1;

/**
 * @author andy-liu
 * @date 2020/6/25 - 7:27 PM
 */
public class C04_ColorLeftRight {
    /*
      左侧要最少的Green, 右侧要最少的Red
     */
    public static int minPaint(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int redAll = 0;
        //循环求出Red的总数, 即 后缀和
        for (int i = 0; i < N; i++) {
            redAll += str[i] == 'R' ? 1 : 0;
        }
        int greenAll = 0; // 前缀和
        int ans = redAll;  //如果整个数组都划分到右侧, 整个数组的Red都要变成Green, 结果就是redAll
        for (int i = 0; i < N - 1; i++) {
            greenAll += str[i] == 'G' ? 1 : 0; //前缀和统计
            redAll -= str[i] == 'R' ? 1 : 0; //后缀和统计

            ans = Math.min(ans, greenAll + redAll);
        }
        // 如果整个数组都划分到左侧
        ans = Math.min(ans, greenAll + str[N - 1] == 'G' ? 1 : 0);
        return ans;
    }
}
