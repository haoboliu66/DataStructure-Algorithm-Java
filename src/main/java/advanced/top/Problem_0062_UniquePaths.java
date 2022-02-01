package src.main.java.advanced.top;

public class Problem_0062_UniquePaths {

    //dp解法, 最intuitive
    public int uniquePaths(int m, int n) {

        int[][] dp = new int[n][m];

        for(int i=0;i<m;i++){
            dp[0][i] = 1;
        }

        for(int j=0;j<n;j++){
            dp[j][0] = 1;
        }

        for(int i=1;i<n;i++){
            for(int j=1;j<m;j++){
                dp[i][j] = dp[i][j-1] + dp[i-1][j];
            }
        }

        return dp[n-1][m-1];
    }


    // m x n, 总共需要走 m + n - 2步, 其中向下走m - 1步, 其余的是向右走 n - 1, 所以结果是C(m - 1, m+n - 2)

    /*
    如何优雅的实现C(K,N)
     */
//    public int uniquePaths2(int m, int n) {
//
//    }



}
