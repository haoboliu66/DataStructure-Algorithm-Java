package com.hliu.advanced.c3.class1;

import org.junit.Test;

public class C05_MaxOneBorderSize {
    /*
    lc 85. Maximal Rectangle
    */

  // Brute Force O(N^5)
  public static int maximalRectangle(char[][] matrix) {
    if (matrix == null || matrix.length == 0) {
      return 0;
    }

    int M = matrix.length;
    int N = matrix[0].length;

    int[][] right = new int[M][N];
    int[][] down = new int[M][N];

    int rectangleArea = 0;
    for (int i = 0; i < M; i++) {
      for (int j = 0; j < N; j++) {
        if (matrix[i][j] != '1') {
          continue;
        }

        for (int len = 1; len <= N - j; len++) {
          // if end point of length != '1'
          if (matrix[i][j + len - 1] != '1') {
            continue;
          }

          for (int width = 1; width <= M - i; width++) {
            // if end point of width != '1'
            if (matrix[i + width - 1][j + len - 1] != '1') {
              continue;
            }
            if (validateRectangle(matrix, i, j, len, width)) {
              rectangleArea = Math.max(rectangleArea, len * width);
            }
          }
        }
      }
    }
    return rectangleArea;
  }

  /*
  validate rectangle starting from (a,b) with sides len and width
   */
  public static boolean validateRectangle(char[][] matrix, int a, int b, int len, int width) {
    int endRow = a + width - 1;
    int endCol = b + len - 1;

    for (int i = a; i <= endRow; i++) {
      if (matrix[i][b] != '1') {
        return false;
      }
    }
    for (int i = a; i <= endRow; i++) {
      if (matrix[i][endCol] != '1') {
        return false;
      }
    }
    for (int j = b; j <= endCol; j++) {
      if (matrix[a][j] != '1') {
        return false;
      }
    }
    for (int j = b; j <= endCol; j++) {
      if (matrix[endRow][j] != '1') {
        return false;
      }
    }
    return true;
  }

  // O(N^4)
  public static int maximalRectangle2(char[][] matrix) {

    if (matrix == null || matrix.length == 0) {
      return 0;
    }

    int M = matrix.length;
    int N = matrix[0].length;

    int[][] lenMatrix = new int[M][N];
    int[][] widthMatrix = new int[M][N];

    fillHelpRectangeMatrix(matrix, lenMatrix, widthMatrix);

    int rectangleArea = 0;
    for (int i = 0; i < M; i++) {
      for (int j = 0; j < N; j++) {
        if (matrix[i][j] != '1') {
          continue;
        }

        for (int len = 1; len <= N - j; len++) {
          // if end point of length != '1'
          if (matrix[i][j + len - 1] != '1') {
            continue;
          }

          for (int width = 1; width <= M - i; width++) {
            // if end point of width != '1'
            if (matrix[i + width - 1][j + len - 1] != '1') {
              continue;
            }

            if (validateRectangleWithHelpMatrix(matrix, i, j, len, width, lenMatrix, widthMatrix)) {
              rectangleArea = Math.max(rectangleArea, len * width);
            } else {
              break;
            }
          }
        }
      }
    }
    return rectangleArea;
  }

  // fill the help matrices, one for horizontal length, one for vertical width
  public static void fillHelpRectangeMatrix(char[][] matrix, int[][] lenMatrix, int[][] widthMatrix) {
    int M = matrix.length;
    int N = matrix[0].length;

    // most right column
    for (int i = 0; i < M; i++) {
      lenMatrix[i][N - 1] = matrix[i][N - 1] == '1' ? 1 : 0;
    }

    // bottom
    for (int j = 0; j < N; j++) {
      widthMatrix[M - 1][j] = matrix[M - 1][j] == '1' ? 1 : 0;
    }

    // left <== right   for len
    for (int j = N - 2; j >= 0; j--) {
      for (int i = M - 1; i >= 0; i--) {
        lenMatrix[i][j] = matrix[i][j] == '1' ? lenMatrix[i][j + 1] > 0 ? 1 + lenMatrix[i][j + 1] : 1 : 0;
      }
    }

    // bottom ==> up   for width
    for (int i = M - 2; i >= 0; i--) {
      for (int j = N - 1; j >= 0; j--) {
        widthMatrix[i][j] = matrix[i][j] == '1' ? widthMatrix[i + 1][j] > 0 ? 1 + widthMatrix[i + 1][j] : 1 : 0;
      }
    }

    System.out.println("----lenMatrix----");
    printMatrix(lenMatrix);
    System.out.println("----widthMatrix----");
    printMatrix(widthMatrix);
  }

  /*
  validate rectangle starting from (a,b) with sides len and width
   */
  public static boolean validateRectangleWithHelpMatrix(char[][] matrix, int a, int b, int len, int width,
      int[][] lenMatrix, int[][] widthMatrix) {
    int endRow = a + width - 1;
    int endCol = b + len - 1;

    return lenMatrix[a][b] >= len && widthMatrix[a][b] >= width && lenMatrix[endRow][b] >= len
        && widthMatrix[a][endCol] >= width;
  }


  @Test
  public void testMaxRectangle() {
    char[][] matrix = {{'0', '1'}};
    char[][] matrix2 = {{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'},
        {'1', '0', '0', '1', '0'}};
    char[][] matrix3 =
        {{'1', '0', '1', '1', '0', '1'},
            {'1', '1', '1', '1', '1', '1'},
            {'0', '1', '1', '0', '1', '1'},
            {'1', '1', '1', '0', '1', '0'},
            {'0', '1', '1', '1', '1', '1'},
            {'1', '1', '0', '1', '1', '1'}};
//        System.out.println(maximalRectangle(matrix2));
    System.out.println(maximalRectangle2(matrix3));
  }

  /* ----------------------------------------------------------------------------------------------------------- */

    /*
    lc 221. Maximal Square
    */

  // brute force O(N^4)
  public static int maximalSquare(char[][] matrix) {
    if (matrix == null || matrix.length == 0) {
      return 0;
    }

    int M = matrix.length;
    int N = matrix[0].length;
    int limit = Math.min(M, N);

    int area = 0;
    for (int i = 0; i < M; i++) {
      for (int j = 0; j < N; j++) {
        if (matrix[i][j] != '1') {
          continue;
        }

        // side length of the square
        for (int len = 1; len <= limit; len++) {
                    /*
                    该层循环是以当前(i,j)为起点, 逐渐扩大square的边长,
                    一旦validate为true, 可以保证以当前(i,j)为起点, 边长为len的square内部也全都是1
                     */
          // 验证square四边是否都为'1'
          if (validate(matrix, i, j, len)) {  //O(N)
            area = Math.max(area, len * len);
          } else {
            break;
          }
        }
      }
    }
    return area;
  }

  // 验证: matrix里, [i][j]位置开始, 长度为len的正方形, 边上是否都为'1'
  public static boolean validate(char[][] matrix, int i, int j, int len) {
    int rA = i;
    int cA = j;
    int rB = i + len - 1;
    int cB = j + len - 1;

    if (rB >= matrix.length || cB >= matrix[0].length) {
      return false;
    }

    for (int r = rA; r <= rB; r++) {
      if (matrix[r][cA] != '1') {
        return false;
      }
    }
    for (int r = rA; r <= rB; r++) {
      if (matrix[r][cB] != '1') {
        return false;
      }
    }
    for (int c = cA; c <= cB; c++) {
      if (matrix[rA][c] != '1') {
        return false;
      }
    }
    for (int c = cA; c <= cB; c++) {
      if (matrix[rB][c] != '1') {
        return false;
      }
    }

    return true;
  }
  /* ------     ------------    ----------   ----------  */

  // O(N^3)
  public static int maximalSquare2(char[][] matrix) {
    if (matrix == null || matrix.length == 0) {
      return 0;
    }

    int M = matrix.length;
    int N = matrix[0].length;
    int limit = Math.min(M, N);

    int[][] right = new int[M][N]; // [i][j]位置右侧最多有几个'1'
    int[][] down = new int[M][N];  // [i][j]位置往下最多有几个'1'

    fillHelpMatrix(matrix, right, down);

    int area = 0;
    for (int i = 0; i < M; i++) {
      for (int j = 0; j < N; j++) {
        if (matrix[i][j] != '1') {
          continue;
        }

        // side length of the square
        for (int len = 1; len <= limit; len++) {
          // 验证square四边是否都为'1'
          if (validateWithHelpMatrix(matrix, i, j, len, right, down)) { //O(1)
            area = Math.max(area, len * len);
          } else {
                        /*
                         如果此时某个边上已经不都为1了, 在此(i,j)位置无需再扩边长了, 因为继续扩的话, 正方形内部就不都为1, 所以直接break即可

                         如果没有此break语句, 正方形会一直扩, 把0扩进内部, 但是可以保证找到边长上都为1的面积最大的正方形 ==> class1 - C05
                         */
            break;
          }
        }
      }
    }
    return area;
  }

  // 验证: matrix中, [i][j]位置,长度为len的正方形内, 边长是否有足够的'1'
  // 填辅助数组, O(N)
  private static void fillHelpMatrix(char[][] matrix, int[][] right, int[][] down) {
    int M = matrix.length;
    int N = matrix[0].length;
    // 填right的最右侧一列
    for (int i = 0; i < M; i++) {
      right[i][N - 1] = matrix[i][N - 1] == '1' ? 1 : 0;
    }
    // 从右-->左依次填right
    for (int i = 0; i < M; i++) {
      for (int j = N - 2; j >= 0; j--) {
        right[i][j] = matrix[i][j] == '0' ? 0 : 1 + right[i][j + 1];
      }
    }
    // 填down的最底下一行
    for (int j = 0; j < N; j++) {
      down[M - 1][j] = matrix[M - 1][j] == '1' ? 1 : 0;
    }
    // 从下-->上依次填down
    for (int j = 0; j < N; j++) {
      for (int i = M - 2; i >= 0; i--) {
        down[i][j] = matrix[i][j] == '0' ? 0 : 1 + down[i + 1][j];
      }
    }
  }

  private static boolean validateWithHelpMatrix(char[][] matrix, int i, int j, int len, int[][] right, int[][] down) {
    int rA = i;
    int cA = j;
    int rB = i + len - 1;
    int cB = j + len - 1;
    if (rB >= matrix.length || cB >= matrix[0].length) {
      return false;
    }

    return down[rA][cA] >= len && down[rA][cB] >= len
        && right[rA][cA] >= len && right[rB][cA] >= len;
  }

  // dp O(N^2)
  public static int maximalSquare3(char[][] matrix) {
    if (matrix == null || matrix.length == 0) {
      return 0;
    }

    int res = 0, M = matrix.length, N = matrix[0].length;
    int[][] dp = new int[M][N];

    for (int i = 0; i < M; i++) {
      dp[i][0] = matrix[i][0] == '1' ? 1 : 0;
      res = Math.max(res, dp[i][0]);
    }

    for (int j = 0; j < N; j++) {
      dp[0][j] = matrix[0][j] == '1' ? 1 : 0;
      res = Math.max(res, dp[0][j]);
    }

    for (int i = 1; i < M; i++) {
      for (int j = 1; j < N; j++) {
        if (matrix[i][j] == '1') {
          dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
          res = Math.max(dp[i][j], res);
        }
      }
    }
    return res * res;
  }


  // for test
  public static void printMatrix(char[][] matrix) {
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
    }
  }

  // for test
  public static void printMatrix(int[][] matrix) {
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
    }
  }

  // for test
  public static char[][] generateRandomMatrix(int M, int N) {
    char[][] matrix = new char[M][N];
    for (int i = 0; i < M; i++) {
      for (int j = 0; j < N; j++) {
        matrix[i][j] = Math.random() > 0.5 ? '1' : '0';
      }
    }
    return matrix;
  }

  // for test
  public static int[][] generateRandomIntegerMatrix(int M, int N) {
    int[][] matrix = new int[M][N];
    for (int i = 0; i < M; i++) {
      for (int j = 0; j < N; j++) {
        matrix[i][j] = Math.random() > 0.5 ? 1 : 0;
      }
    }
    return matrix;
  }

  /* --------------------------------↓ Class1 - C05 Code ↓--------------------------------------------- */

  // 1139. Largest 1-Bordered Square

  // zuo code
  public static void setBorderMap(int[][] m, int[][] right, int[][] down) {
    int r = m.length;
    int c = m[0].length;
    if (m[r - 1][c - 1] == 1) {
      right[r - 1][c - 1] = 1;
      down[r - 1][c - 1] = 1;
    }
    for (int i = r - 2; i != -1; i--) {
      if (m[i][c - 1] == 1) {
        right[i][c - 1] = 1;
        down[i][c - 1] = down[i + 1][c - 1] + 1;
      }
    }
    for (int i = c - 2; i != -1; i--) {
      if (m[r - 1][i] == 1) {
        right[r - 1][i] = right[r - 1][i + 1] + 1;
        down[r - 1][i] = 1;
      }
    }
    for (int i = r - 2; i != -1; i--) {
      for (int j = c - 2; j != -1; j--) {
        if (m[i][j] == 1) {
          right[i][j] = right[i][j + 1] + 1;
          down[i][j] = down[i + 1][j] + 1;
        }
      }
    }
  }

  public static int getMaxSize(int[][] m) {
    int[][] right = new int[m.length][m[0].length];
    int[][] down = new int[m.length][m[0].length];
    setBorderMap(m, right, down); // O(N^2); +

    for (int size = Math.min(m.length, m[0].length); size != 0; size--) {
      if (hasSizeOfBorder(size, right, down)) {
        return size * size;
      }
    }
    return 0;
  }

  public static boolean hasSizeOfBorder(int size, int[][] right, int[][] down) {
    for (int i = 0; i != right.length - size + 1; i++) {
      for (int j = 0; j != right[0].length - size + 1; j++) {
        if (right[i][j] >= size && down[i][j] >= size
            && right[i + size - 1][j] >= size
            && down[i][j + size - 1] >= size) {
          return true;
        }
      }
    }
    return false;
  }


  // O(N^3) for class1 - C05
  public static int maximalSquareZuo(int[][] matrix) {
    if (matrix == null || matrix.length == 0) {
      return 0;
    }

    int M = matrix.length;
    int N = matrix[0].length;
    int limit = Math.min(M, N);

    int[][] right = new int[M][N]; // [i][j]位置右侧最多有几个'1'
    int[][] down = new int[M][N];  // [i][j]位置往下最多有几个'1'

    fillHelpMatrix(matrix, right, down);

    int area = 0;
    for (int i = 0; i < M; i++) {
      for (int j = 0; j < N; j++) {
        if (matrix[i][j] != 1) {
          continue;
        }

        // side length of the square
        for (int len = 1; len <= limit; len++) {
          // 验证square四边是否都为'1'
          if (validateWithHelpMatrix(matrix, i, j, len, right, down)) { //O(1)
            area = Math.max(area, len * len);
          } else {
                         /*
                         如果此时某个边上已经不都为1了, 在此(i,j)位置无需再扩边长了, 因为继续扩的话, 正方形内部就不都为1, 所以直接break即可

                         如果没有此break语句, 正方形会一直扩, 把0扩进内部, 但是可以保证找到边长上都为1的面积最大的正方形 ==> class1 - C05
                         */
//                        break;
          }
        }
      }
    }
    return area;
  }

  // 验证: matrix中, [i][j]位置,长度为len的正方形内, 边长是否有足够的'1'
  // 填辅助数组, O(N)
  private static void fillHelpMatrix(int[][] matrix, int[][] right, int[][] down) {
    int M = matrix.length;
    int N = matrix[0].length;
    // 填right的最右侧一列
    for (int i = 0; i < M; i++) {
      right[i][N - 1] = matrix[i][N - 1] == 1 ? 1 : 0;
    }
    // 从右-->左依次填right
    for (int i = 0; i < M; i++) {
      for (int j = N - 2; j >= 0; j--) {
        right[i][j] = matrix[i][j] == 0 ? 0 : 1 + right[i][j + 1];
      }
    }
    // 填down的最底下一行
    for (int j = 0; j < N; j++) {
      down[M - 1][j] = matrix[M - 1][j] == 1 ? 1 : 0;
    }
    // 从下-->上依次填down
    for (int j = 0; j < N; j++) {
      for (int i = M - 2; i >= 0; i--) {
        down[i][j] = matrix[i][j] == 0 ? 0 : 1 + down[i + 1][j];
      }
    }
  }

  private static boolean validateWithHelpMatrix(int[][] matrix, int i, int j, int len, int[][] right, int[][] down) {
    int rA = i;
    int cA = j;
    int rB = i + len - 1;
    int cB = j + len - 1;
    if (rB >= matrix.length || cB >= matrix[0].length) {
      return false;
    }

    return down[rA][cA] >= len && down[rA][cB] >= len
        && right[rA][cA] >= len && right[rB][cA] >= len;
  }


  @Test
  public void testMaxSquare() {
    char[][] matrix = {{'0', '1'}};
    char[][] matrix2 = {{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'},
        {'1', '0', '0', '1', '0'}};
    char[][] matrix3 = {{'1', '1', '1', '1', '1'}, {'1', '1', '1', '0', '1'}, {'1', '0', '0', '0', '1'},
        {'1', '0', '0', '0', '1'}, {'1', '1', '1', '1', '1'}};

    char[][] matrixZuo = {{'0', '1', '1', '1', '1'}, {'0', '1', '0', '0', '1'}, {'0', '1', '0', '0', '1'},
        {'0', '1', '1', '1', '1'}, {'0', '1', '0', '1', '1'}};

//        System.out.println(maximalSquare(matrix2));
//        System.out.println(maximalSquare2(matrix2));
//        System.out.println(maximalSquare3(matrixZuo));

    int times = 100000;
    int M, N;
    int[][] randomMatrix;
    for (int i = 0; i < times; i++) {
      M = (int) ((Math.random() + 1) * 20);
      N = (int) ((Math.random() + 1) * 20);
      randomMatrix = generateRandomIntegerMatrix(M, N);
      int my = maximalSquareZuo(randomMatrix);
      int zuo = getMaxSize(randomMatrix);
      if (my != zuo) {
        System.out.println("Oops");
        break;
      }
    }

  }


}
