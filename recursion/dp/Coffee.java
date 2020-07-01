package recursion.dp;

/**
 * @author andy-liu
 * @date 2020/5/27 - 10:30 AM
 */
public class Coffee {
    // 题目
// 数组arr代表每一个咖啡机冲一杯咖啡的时间，每个咖啡机只能串行的制造咖啡。
// 现在有n个人需要喝咖啡，只能用咖啡机来制造咖啡。
// 认为每个人喝咖啡的时间非常短，冲好的时间即是喝完的时间。
// 每个人喝完之后咖啡杯可以选择洗或者自然挥发干净，只有一台洗咖啡杯的机器，只能串行的洗咖啡杯。
// 洗杯子的机器洗完一个杯子时间为a，任何一个杯子自然挥发干净的时间为b。
// 四个参数：arr, n, a, b
// 假设时间点从0开始，返回所有人喝完咖啡并洗完咖啡杯的全部过程结束后，至少来到什么时间点。


    public static int wash(int[] drinks, int a, int b) {
        if (a >= b) {
            return drinks[drinks.length - 1] + b;
        }
        return process(drinks, a, b, 0, 0);

    }

    // 主函数调用process(drinks, 3, 10, 0, 0)
    // a 洗一杯的时间 固定变量
    // b 自己挥发干净的时间 固定变量
    // drinks 每一个员工喝完的时间 固定变量
    // drinks[0..index-1]都已经干净了，不用你操心了
    // drinks[index...]都想变干净，这是我操心的，machineAvailableTime表示洗的机器何时可用
    // drinks[index...]变干净，最少的时间点返回
    private static int process(int[] drinks, int a, int b, int index, int machineAvailableTime) {
        if (index == drinks.length - 1) {
            // 1.挥发:  drinks[index] + b  ===> 喝完的时间点 加上 挥发时间b
            // 2.机洗:  机器能用的时间和喝完的时间取max, 这是能开始洗的时间, 再加上洗的时间a
            return Math.min(drinks[index] + b, Math.max(machineAvailableTime, drinks[index]) + a);
        }

        int wash = Math.max(machineAvailableTime, drinks[index]) + a; //洗完当前index杯子的时间
        int next1 = process(drinks, a, b, index + 1, wash); //后面只有等index洗完机器才可用
        int p1 = Math.max(wash, next1);

        int dry = drinks[index] + b;//当前index杯子挥发的时间
        int next2 = process(drinks, a, b, index + 1, machineAvailableTime);
        int p2 = Math.max(dry, next2);

        return Math.min(p1, p2);
    }

    // 可变参数machineAvailableTime范围无法确定, 可从题目中推出
    public static int dp(int[] drinks, int a, int b) {
        if (a >= b) {   //如果 a>=b, 那就全去挥发, 最后一杯挥发结束就是结束时间
            return drinks[drinks.length - 1] + b;
        }
        // a < b
        int limit = 0;
        for (int i = 0; i < drinks.length; i++) {
            limit = Math.max(drinks[i], limit) + a; //机器可用时间和喝完时间max 加a
        }
        int N = drinks.length;
        int[][] dp = new int[N][limit + 1];
        //从递归函数中判断, index依赖index + 1, 所以可以从下往上, 从左往右填表
        for (int washLine = 0; washLine <= limit; washLine++) {
            // 根据base case, 填N - 1行
            dp[N - 1][washLine] = Math.min(drinks[N - 1] + b, Math.max(washLine, drinks[N - 1]) + a);
        }
        for (int index = N - 2; index >= 0; index--) {
            for (int washLine = 0; washLine <= limit; washLine++) {

                int p1 = Integer.MAX_VALUE;
                int wash = Math.max(washLine, drinks[index] + a);
                if (wash <= limit) {  // 因为wash不断增大, 要判断不越界
                    p1 = Math.max(wash, dp[index + 1][wash]);
                }

                int dry = drinks[index] + b;
                int p2 = Math.max(dry, dp[index + 1][washLine]);// p2情况不会越界

                dp[index][washLine] = Math.min(p1, p2);
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 6, 8, 12, 20, 35};
        int a = 3;
        int b = 10;
        int w1 = wash(arr, a, b);
        int w2 = dp(arr, a, b);
        System.out.println(w1);
        System.out.println(w2);

    }


}
