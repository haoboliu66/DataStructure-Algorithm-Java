package src.main.java.leetcode.bfs_dfs;

import java.util.*;

public class M_Problem_0417_PacificAtlanticWaterFlow {

    private class Point {
        int x;
        int y;

        public Point(int i, int j) {
            x = i;
            y = j;
        }

    }

    /*  [i][j] hasPathTo Top,Left edge  &&  [i][j] hasPathTo Bottom,Right edge    */
    //  O(M^2 * N^2)
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> ans = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return ans;

        int m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (hasPathToDestination(matrix, i, j, 0, 0) && hasPathToDestination(matrix, i, j, m - 1, n - 1)) {
                    ans.add(Arrays.asList(i, j));
                }
            }
        }
        return ans;
    }

    /* bfs */
    public boolean hasPathToDestination(int[][] matrix, int startX, int startY, int endX, int endY) {
        int m = matrix.length, n = matrix[0].length;

        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(startX, startY));
        boolean[][] visited = new boolean[m][n];
        int count = 0;

        while (!q.isEmpty()) {
            Point cur = q.poll();
            int x = cur.x, y = cur.y;

            if (x == endX || y == endY) {
                return true;
            }

            if (x > 0 && matrix[x][y] >= matrix[x - 1][y] && !visited[x - 1][y]) {
                visited[x - 1][y] = true;
                q.offer(new Point(x - 1, y));
            }
            if (x < m - 1 && matrix[x][y] >= matrix[x + 1][y] && !visited[x + 1][y]) {
                visited[x + 1][y] = true;
                q.offer(new Point(x + 1, y));
            }
            if (y > 0 && matrix[x][y] >= matrix[x][y - 1] && !visited[x][y - 1]) {
                visited[x][y - 1] = true;
                q.offer(new Point(x, y - 1));
            }
            if (y < n - 1 && matrix[x][y] >= matrix[x][y + 1] && !visited[x][y + 1]) {
                visited[x][y + 1] = true;
                q.offer(new Point(x, y + 1));
            }
        }
        return false;
    }

    // not finished
    /* dfs  */
    public List<List<Integer>> pacificAtlantic2(int[][] matrix) {
        List<List<Integer>> ans = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return ans;

        int m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(matrix, i, j, false)) {
                    ans.add(Arrays.asList(i, j));
                }
            }
        }
        return ans;
    }

    public boolean dfs(int[][] matrix, int i, int j, boolean goDownRight) {
        if (!goDownRight && (i == 0 || j == 0)) {
            return true;
        }
        if (goDownRight && (i == matrix.length - 1 || j == matrix[0].length - 1)) {
            return true;
        }
        if (matrix[i][j] == -1) {
            return false;
        }
        int tmp = matrix[i][j];
        matrix[i][j] = -1;
        boolean top = false, left = false, down = false, right = false;
        if (i > 0 && matrix[i][j] >= matrix[i - 1][j]) {
            top |= dfs(matrix, i - 1, j, false);
        }
        if (j > 0 && matrix[i][j] >= matrix[i][j - 1]) {
            left |= dfs(matrix, i, j - 1, false);
        }
        if (i < matrix.length - 1 && matrix[i][j] >= matrix[i + 1][j]) {
            down |= dfs(matrix, i + 1, j, true);
        }
        if (j < matrix[0].length - 1 && matrix[i][j] >= matrix[i][j + 1]) {
            right |= dfs(matrix, i, j + 1, true);
        }

        matrix[i][j] = tmp;

        return (top || left) && (down || right);
    }


}
