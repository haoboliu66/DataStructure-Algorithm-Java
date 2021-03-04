package advanced;

public class Move {

    public static int move(int n, int startRow, int startCol, int endRow, int endCol, int bishopRow, int bishopCol) {
        boolean[][] visited = new boolean[n][n];
        return process(startRow, startCol, endRow, endCol, n, bishopRow, bishopCol, visited, startRow, startCol);
    }

    // 目前位于i,j, 目标是m,n, border是len, 限制点是bx, by,
    // 从i,j走到m,n最少需要几步
    public static int process(int i, int j, int m, int n, int len, int bx, int by, boolean[][] visited, int startRow, int startCol) {
        if (i < 0 || j < 0 || i >= len || j >= len) {
            return Integer.MAX_VALUE;
        }
        // 当前位置走过了, 无效
        if (visited[i][j]) {
            return Integer.MAX_VALUE;
        }
        //踩到了bishop
        if (i == bx && j == by) {
            visited[bx][by] = true;
            return process(bx, by, m, n, len, bx, by, visited, startRow, startCol);
        }
        // 没踩到bishop的情况下,到了对角线, 无效
        if (!visited[bx][by] && isDiagonal(i, j, bx, by, startRow, startCol, m, n)) {
            return Integer.MAX_VALUE;
        }

        //如果当前位置来到了m,n
        if (i == m && j == n) {
            return 0;
        }

        int res = 1;
        visited[i][j] = true;
        int p1 = process(i - 2, j - 1, m, n, len, bx, by, visited, startRow, startCol);
        if (p1 < 0) {
            p1 = Integer.MAX_VALUE;
        }
        int p2 = process(i - 2, j + 1, m, n, len, bx, by, visited, startRow, startCol);
        if (p2 < 0) {
            p2 = Integer.MAX_VALUE;
        }
        int p3 = process(i - 1, j + 2, m, n, len, bx, by, visited, startRow, startCol);
        if (p3 < 0) {
            p3 = Integer.MAX_VALUE;
        }
        int p4 = process(i + 1, j + 2, m, n, len, bx, by, visited, startRow, startCol);
        if (p4 < 0) {
            p4 = Integer.MAX_VALUE;
        }
        int p5 = process(i + 2, j - 1, m, n, len, bx, by, visited, startRow, startCol);
        if (p5 < 0) {
            p5 = Integer.MAX_VALUE;
        }
        int p6 = process(i + 2, j + 1, m, n, len, bx, by, visited, startRow, startCol);
        if (p6 < 0) {
            p6 = Integer.MAX_VALUE;
        }
        int p7 = process(i - 1, j - 2, m, n, len, bx, by, visited, startRow, startCol);
        if (p7 < 0) {
            p7 = Integer.MAX_VALUE;
        }
        int p8 = process(i + 1, j - 2, m, n, len, bx, by, visited, startRow, startCol);
        if (p8 < 0) {
            p8 = Integer.MAX_VALUE;
        }
        visited[i][j] = false;
        int ans = res + Math.min(p1, Math.min(p2, Math.min(p3, Math.min(p4, Math.min(p5, Math.min(p6, Math.min(p7, p8)))))));
        if(ans < 0){
            ans = Integer.MAX_VALUE;
        }
        System.out.println(ans);
        return ans;
    }

    // i,j, bx, by是否共对角线
    public static boolean isDiagonal(int i, int j, int bx, int by, int startRow, int startCol, int endRow, int endCol) {
        if ((i == startRow && j == startCol) || (i == endRow && j == endCol)) {
            return false;
        }
        return Math.abs(i - bx) == Math.abs(j - by);
    }


    public static void main(String[] args) {
//        int len = 6;
//        int i = 0, j = 0, m = 0, n = 2, bx = 0, by = 1;
        // 2

//        int len = 6;
//        int i = 5, j = 1, m = 0, n = 5, bx = 2, by = 3;
        // 3

        int len = 7;
        int i = 6, j = 6, m = 0, n = 1, bx = 4, by = 4;
        int res = move(len, i, j, m, n, bx, by);
        //5
        System.out.println(res);
    }


}
