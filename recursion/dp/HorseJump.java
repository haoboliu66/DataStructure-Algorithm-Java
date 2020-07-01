package recursion.dp;

/**
 * @author andy-liu
 * @date 2020/5/27 - 8:40 PM
 */
public class HorseJump {

    // 10*9
    // 0~9 x
    // 0~8 y
    public static int ways(int x, int y, int k) {
        if (k == 0) {
            return x == 0 && y == 0 ? 1 : 0 ;
        }
        if (x < 0 || x > 9 || y < 0 || y > 8) {
            return 0;
        }
        return ways(x - 2, y - 1, k - 1)
                + ways(x - 2, y + 1, k - 1)
                + ways(x - 1, y + 2, k - 1)
                + ways(x + 1, y + 2, k - 1)

                + ways(x + 2, y - 1, k - 1)
                + ways(x + 2, y + 1, k - 1)

                + ways(x - 1, y - 2, k - 1)
                + ways(x + 1, y - 2, k - 1);
    }

    public static int waysdp(int a, int b, int s) {
        // (i,j,0~ step)
        int[][][] dp = new int[10][9][s+1];
        dp[a][b][0] = 1;
        for(int step = 1 ; step <= s;step++ ) { // 按层来
            for(int i = 0 ; i < 10;i++) {
                for(int j = 0 ; j < 9; j++) {
                    dp[i][j][step] = getValue(dp,i - 2, j + 1, step - 1)
                            + getValue(dp,i - 1, j + 2, step - 1)
                            + getValue(dp,i + 1, j + 2, step - 1)
                            + getValue(dp,i + 2, j + 1, step - 1)
                            + getValue(dp,i + 2, j - 1, step - 1)
                            + getValue(dp,i + 1, j - 2, step - 1)
                            + getValue(dp,i - 1, j - 2, step - 1)
                            + getValue(dp,i - 2, j - 1, step - 1);
                }
            }
        }
        return dp[0][0][s];
    }

    // 在dp表中，得到dp[i][j][step]的值，但如果(i，j)位置越界的话，返回0；
    public static int getValue(int[][][] dp, int i, int j, int step) {
        if (i < 0 || i > 9 || j < 0 || j > 8) {
            return 0;
        }
        return dp[i][j][step];
    }

    public static void main(String[] args) {
        int x = 7;
        int y = 7;
        int step = 10;
        int testTimes = 1000;
        for(int i=0;i<testTimes;i++){
            x = (int)(Math.random() * 10);
            y = (int)(Math.random() * 9);
            step = (int)(Math.random() * 10);
            if(ways(x, y, step) != waysdp(x, y, step)){
                System.out.println("Oops");
                break;
            }
        }
        System.out.println("Finished");
    }
}

