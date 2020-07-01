package advanced.c1.fibonacci;

/**
 * @author andy-liu
 * @date 2020/5/27 - 8:36 PM
 */
public class Fibonacci {

    public static int fibonacci1(int N) {
        if(N < 1){
            return 0;
        }
        if (N == 1 || N == 2) {
            return 1;
        }
        return fibonacci1(N - 1) + fibonacci1(N - 2);
    }

    public static int fibonacci2(int N) {
        if(N < 1){
            return 0;
        }
        if (N == 1 || N == 2) {
            return 1;
        }
        int[] dp = new int[N + 1];
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[N];
    }

    public static int fibonacci3(int N) {
        int[][] matrix = {
                {1, 1},
                {1, 0}
        };
        return 0;
    }

    private static int[][] matrixPowerTwo(int[][] matrix1, int[][] matrix2) {
        int[][] res = new int[matrix1.length][matrix2[0].length];

        return res;
    }


    public static void main(String[] args) {
        int testTime = 1000;
        for(int i=0; i<testTime;i++){
            int N = (int)(Math.random() * 50);
            if(fibonacci1(N) != fibonacci2(N)){
                System.out.println("Oops");
                break;
            }
        }
        System.out.println("Finished");
    }
}
