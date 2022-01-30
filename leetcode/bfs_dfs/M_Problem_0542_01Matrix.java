package leetcode.bfs_dfs;

import java.util.LinkedList;
import java.util.Queue;

public class M_Problem_0542_01Matrix {

    // dp solution

    // go from advanced.top-left to bottom-right, then from bottom-right to advanced.top-left
    public int[][] updateMatrix2(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int t = m * n + 1; // distance cannot exceed m*n+1
        int[][] distance = new int[m][n];

        // from advanced.top-left to bottom-right
        distance[0][0] = matrix[0][0] == 0 ? 0 : t;
        for (int i = 1; i < m; i++) {
            if (matrix[i][0] == 0) {
                distance[i][0] = 0;
                continue;
            }
            distance[i][0] = distance[i - 1][0] != t ? 1 + distance[i - 1][0] : t;
        }
        for (int j = 1; j < n; j++) {
            if (matrix[0][j] == 0) {
                distance[0][j] = 0;
                continue;
            }
            distance[0][j] = distance[0][j - 1] != t ? 1 + distance[0][j - 1] : t;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    distance[i][j] = 0;
                    continue;
                }
                if (matrix[i][j] == 1) {
                    distance[i][j] = 1 + Math.min(distance[i - 1][j], distance[i][j - 1]);
                }
            }
        }

        // from bottom-right to advanced.top-left
        distance[m - 1][n - 1] = matrix[m - 1][n - 1] == 0 ? 0 : Math.min(t, distance[m - 1][n - 1]);
        for (int i = m - 2; i >= 0; i--) {
            if (matrix[i][n - 1] == 0) {
                distance[i][n - 1] = 0;
                continue;
            }
            distance[i][n - 1] = Math.min(distance[i][n - 1], distance[i + 1][n - 1] != t ? 1 + distance[i + 1][n - 1] : t);
        }
        for (int j = n - 2; j >= 0; j--) {
            if (matrix[m - 1][j] == 0) {
                distance[m - 1][j] = 0;
                continue;
            }
            distance[m - 1][j] = Math.min(distance[m - 1][j], distance[m - 1][j + 1] != t ? 1 + distance[m - 1][j + 1] : t);
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                if (matrix[i][j] == 0) {
                    distance[i][j] = 0;
                    continue;
                }
                if (matrix[i][j] == 1) {
                    distance[i][j] = Math.min(distance[i][j], 1 + Math.min(distance[i + 1][j], distance[i][j + 1]));
                }
            }
        }
        return distance;
    }

    // bfs Solution
    public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] distance = new int[m][n];

        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    q.offer(new int[]{i, j});
                } else {
                    distance[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                int x = cur[0], y = cur[1];

                if (x > 0 && distance[x - 1][y] == Integer.MAX_VALUE) {
                    distance[x - 1][y] = distance[x][y] + 1;
                    q.offer(new int[]{x - 1, y});
                }
                if (x < m - 1 && distance[x + 1][y] == Integer.MAX_VALUE) {
                    distance[x + 1][y] = distance[x][y] + 1;
                    q.offer(new int[]{x + 1, y});
                }
                if (y > 0 && distance[x][y - 1] == Integer.MAX_VALUE) {
                    distance[x][y - 1] = distance[x][y] + 1;
                    q.offer(new int[]{x, y - 1});
                }
                if (y < n - 1 && distance[x][y + 1] == Integer.MAX_VALUE) {
                    distance[x][y + 1] = distance[x][y] + 1;
                    q.offer(new int[]{x, y + 1});
                }
            }
        }
        return distance;
    }
}

