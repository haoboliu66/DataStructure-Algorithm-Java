package com.hliu.fundamental.recursion;

public class RobotMove {

  public static int way1(int N, int M, int K, int P) {
    if (N < 2 || M < 1 || M > N || K < 1 || P < 1 || P > N) {
      return 0;
    }
    return walk1(N, M, K, P);
  }

  public static int walk1(int N, int cur, int rest, int P) {
    if (rest == 0) {
      return cur == P ? 1 : 0;
    }
    if (cur == 1) {
      return walk1(N, 2, rest - 1, P);
    }
    if (cur == N) {
      return walk1(N, N - 1, rest - 1, P);
    }
    return walk1(N, cur + 1, rest - 1, P) + walk1(N, cur - 1, rest - 1, P);
  }

  public static int way1Cached(int N, int M, int K, int P) {
    if (N < 2 || M < 1 || M > N || K < 1 || P < 1 || P > N) {
      return 0;
    }
    int[][] cache = new int[N + 1][K + 1];
    return walk1Cached(N, M, K, P, cache);
  }

  public static int walk1Cached(int N, int cur, int rest, int P, int[][] cache) {
    if (rest == 0) {
      cache[cur][rest] = cur == P ? 1 : 0;
      return cache[cur][rest];
    }
    if (cur == 1) {
      cache[cur][rest] = walk1Cached(N, 2, rest - 1, P, cache);
      return cache[cur][rest];
    }
    if (cur == N) {
      cache[cur][rest] = walk1Cached(N, N - 1, rest - 1, P, cache);
      return cache[cur][rest];
    }
    cache[cur][rest] = walk1Cached(N, cur + 1, rest - 1, P, cache) + walk1Cached(N, cur - 1, rest - 1, P, cache);
    return cache[cur][rest];
  }


  public static int walk2(int N, int M, int K, int P) {
    if (N < 2 || M < 1 || M > N || K < 1 || P < 1 || P > N) {
      return 0;
    }
        /*
        动态参数是cur和rest, 确定范围, 创建对应二维矩阵
        横轴是K的范围,纵轴是M的范围[1...N]
         */
    int[][] dp = new int[K + 1][N + 1];
    dp[0][P] = 1; //边界值, 当步数K为0时, 只有M == P的位置值才是1
    for (int i = 1; i <= K; i++) {
      for (int j = 1; j <= N; j++) {
        if (j == 1) {
          dp[i][j] = dp[i - 1][2]; // return walk1(N, 2, rest - 1, P);

        } else if (j == N) {
          dp[i][j] = dp[i - 1][j - 1]; // walk1Cached(N, N - 1, rest - 1, P, cache)

        } else {
          dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j
              + 1];//walk1Cached(N, cur + 1, rest - 1, P, cache) + walk1Cached(N, cur - 1, rest - 1, P, cache);
        }
      }
    }
//        for(int i=0;i<=K;i++){
//            for(int j=0;j<=N;j++){
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }
    return dp[K][M];
  }

  public static void main(String[] args) {
    int c = way1Cached(7, 2, 5, 3);
    int count = walk2(7, 2, 5, 3);
    System.out.println(c);
    System.out.println(count);
  }

}
