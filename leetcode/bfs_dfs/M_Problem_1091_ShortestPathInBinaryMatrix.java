package leetcode.bfs_dfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class M_Problem_1091_ShortestPathInBinaryMatrix {
    /*  lattice fundamental.graph */


    /* To find the shortest path in a fundamental.graph, use BFS ! */

    private static class Pair {
        int x;
        int y;
        int count;

        public Pair(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.count = val;
        }
    }

    /* exceed limited time */
    public static int shortestPathBinaryMatrixBFS(int[][] grid) {
        return bfs(grid);
    }

    /*  problem with overwriting input
            =>  1. multithreading environment;
                2. input needs to be reused later
     */
    public static int bfs(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) return -1;

        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(0, 0, 1));
        grid[0][0] = 1;

        while (!queue.isEmpty()) {
            Pair cur = queue.poll();
            int x = cur.x, y = cur.y, count = cur.count;

            if (x == n - 1 && y == n - 1) {
                return count;
            }

            if (x - 1 >= 0 && grid[x - 1][y] == 0) {
                queue.offer(new Pair(x - 1, y, count + 1));
                grid[x - 1][y] = 1;
            }
            if (x + 1 < n && grid[x + 1][y] == 0) {
                queue.offer(new Pair(x + 1, y, count + 1));
                grid[x + 1][y] = 1;
            }
            if (y - 1 >= 0 && grid[x][y - 1] == 0) {
                queue.offer(new Pair(x, y - 1, count + 1));
                grid[x][y - 1] = 1;
            }
            if (y + 1 < n && grid[x][y + 1] == 0) {
                queue.offer(new Pair(x, y + 1, count + 1));
                grid[x][y + 1] = 1;
            }
            if (x - 1 >= 0 && y - 1 >= 0 && grid[x - 1][y - 1] == 0) {
                queue.offer(new Pair(x - 1, y - 1, count + 1));
                grid[x - 1][y - 1] = 1;
            }
            if (x - 1 >= 0 && y + 1 < n && grid[x - 1][y + 1] == 0) {
                queue.offer(new Pair(x - 1, y + 1, count + 1));
                grid[x - 1][y + 1] = 1;
            }
            if (x + 1 < n && y - 1 >= 0 && grid[x + 1][y - 1] == 0) {
                queue.offer(new Pair(x + 1, y - 1, count + 1));
                grid[x + 1][y - 1] = 1;
            }
            if (x + 1 < n && y + 1 < n && grid[x + 1][y + 1] == 0) {
                queue.offer(new Pair(x + 1, y + 1, count + 1));
                grid[x + 1][y + 1] = 1;
            }
        }
        return -1;
    }

    public static int bfs2(int[][] grid) {
        int n = grid.length;
        boolean[][] visited = new boolean[n][n];
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) return -1;

        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(0, 0, 1));
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Pair cur = queue.poll();
            int x = cur.x, y = cur.y, count = cur.count;

            if (x == n - 1 && y == n - 1) {
                return count;
            }

            if (x - 1 >= 0 && !visited[x - 1][y] && grid[x - 1][y] == 0) {
                queue.offer(new Pair(x - 1, y, count + 1));
                visited[x - 1][y] = true;
            }
            if (x + 1 < n && !visited[x + 1][y] && grid[x + 1][y] == 0) {
                queue.offer(new Pair(x + 1, y, count + 1));
                visited[x + 1][y] = true;
            }
            if (y - 1 >= 0 && !visited[x][y - 1] && grid[x][y - 1] == 0) {
                queue.offer(new Pair(x, y - 1, count + 1));
                visited[x][y - 1] = true;
            }
            if (y + 1 < n && !visited[x][y + 1] && grid[x][y + 1] == 0) {
                queue.offer(new Pair(x, y + 1, count + 1));
                visited[x][y + 1] = true;
            }
            if (x - 1 >= 0 && y - 1 >= 0 && !visited[x - 1][y - 1] && grid[x - 1][y - 1] == 0) {
                queue.offer(new Pair(x - 1, y - 1, count + 1));
                visited[x - 1][y - 1] = true;
            }
            if (x - 1 >= 0 && y + 1 < n && !visited[x - 1][y + 1] && grid[x - 1][y + 1] == 0) {
                queue.offer(new Pair(x - 1, y + 1, count + 1));
                visited[x - 1][y + 1] = true;
            }
            if (x + 1 < n && y - 1 >= 0 && !visited[x + 1][y - 1] && grid[x + 1][y - 1] == 0) {
                queue.offer(new Pair(x + 1, y - 1, count + 1));
                visited[x + 1][y - 1] = true;
            }
            if (x + 1 < n && y + 1 < n && !visited[x + 1][y + 1] && grid[x + 1][y + 1] == 0) {
                queue.offer(new Pair(x + 1, y + 1, count + 1));
                visited[x + 1][y + 1] = true;
            }
        }
        return -1;
    }


    // dfs ? ? ?
    public static int shortestPathBinaryMatrix(int[][] grid) {
        if (grid[0][0] == 1) return -1;
        int res = process(grid, 0, 0);
        return res;
    }

    public static int process(int[][] grid, int i, int j) {
        int n = grid.length;
        if (i < 0 || i >= n || j == n || j < 0) return -1;

        if (grid[i][j] == 1 || grid[i][j] == -1) return -1;

        if (i == n - 1 && j == n - 1) {
            return grid[n - 1][n - 1] == 0 ? 1 : -1;
        }

        int tmp = grid[i][j];
        grid[i][j] = -1;

        int up = process(grid, i - 1, j);
        int rightUp = process(grid, i - 1, j + 1);
        int right = process(grid, i, j + 1);
        int rightDown = process(grid, i + 1, j + 1);
        int down = process(grid, i + 1, j);
        int leftDown = process(grid, i + 1, j - 1);
        int left = process(grid, i, j - 1);
        int leftUp = process(grid, i - 1, j - 1);

        int res = Integer.MAX_VALUE;
        if (up > 0) {
            res = Math.min(up, res);
        }
        if (rightUp > 0) {
            res = Math.min(rightUp, res);
        }
        if (right > 0) {
            res = Math.min(right, res);
        }
        if (rightDown > 0) {
            res = Math.min(rightDown, res);
        }
        if (down > 0) {
            res = Math.min(down, res);
        }
        if (leftDown > 0) {
            res = Math.min(leftDown, res);
        }
        if (left > 0) {
            res = Math.min(left, res);
        }
        if (leftUp > 0) {
            res = Math.min(leftUp, res);
        }

        grid[i][j] = tmp;
        if (res == Integer.MAX_VALUE) {
            return -1;
        }
        return 1 + res;
    }

    public static int shortestPathBinaryMatrix2(int[][] grid) {
        if (grid[0][0] == 1) return -1;
        int n = grid.length;
        int[][] dp = new int[n][n];
        int res = process(grid, 0, 0, dp);
        System.out.println(Arrays.deepToString(dp));
        return res;
    }

    public static int process(int[][] grid, int i, int j, int[][] dp) {
        int n = grid.length;

        if (grid[i][j] == -1) {
            dp[i][j] = -1;
            return dp[i][j];
        }
        if (grid[i][j] == 1) {
            dp[i][j] = -1;
            return dp[i][j];
        }

        if (i == n - 1 && j == n - 1) {
            dp[i][j] = grid[n - 1][n - 1] == 0 ? 1 : -1;
            return dp[i][j];
        }

        int tmp = grid[i][j];
        grid[i][j] = -1;

        int up = i - 1 >= 0 ? process(grid, i - 1, j, dp) : -1;
        int rightUp = i - 1 >= 0 && j + 1 < n ? process(grid, i - 1, j + 1, dp) : -1;
        int right = j + 1 < n ? process(grid, i, j + 1, dp) : -1;
        int rightDown = i + 1 < n && j + 1 < n ? process(grid, i + 1, j + 1, dp) : -1;
        int down = i + 1 < n ? process(grid, i + 1, j, dp) : -1;
        int leftDown = i + 1 < n && j - 1 >= 0 ? process(grid, i + 1, j - 1, dp) : -1;
        int left = j - 1 >= 0 ? process(grid, i, j - 1, dp) : -1;
        int leftUp = i - 1 >= 0 && j - 1 >= 0 ? process(grid, i - 1, j - 1, dp) : -1;

        int res = Integer.MAX_VALUE;
        if (up > 0) {
            res = Math.min(up, res);
        }
        if (rightUp > 0) {
            res = Math.min(rightUp, res);
        }
        if (right > 0) {
            res = Math.min(right, res);
        }
        if (rightDown > 0) {
            res = Math.min(rightDown, res);
        }
        if (down > 0) {
            res = Math.min(down, res);
        }
        if (leftDown > 0) {
            res = Math.min(leftDown, res);
        }
        if (left > 0) {
            res = Math.min(left, res);
        }
        if (leftUp > 0) {
            res = Math.min(leftUp, res);
        }

        grid[i][j] = tmp;

        if (res == Integer.MAX_VALUE) {
            dp[i][j] = -1;
            return dp[i][j];
        }
        dp[i][j] = Math.min(1 + res, dp[i][j]);
        System.out.printf("Return %d with i: %d, j: %d\n", dp[i][j], i, j);
        return dp[i][j];
    }


    public static void main(String[] args) {
        int[][] grid =
                {{0, 0, 1, 0, 1}, {0, 0, 0, 1, 0}, {1, 0, 0, 1, 1}, {0, 0, 0, 1, 1}, {1, 0, 0, 0, 0}};
        int[][] grid2 = {{0, 0, 0, 0}, {1, 0, 0, 1}, {0, 1, 0, 0}, {0, 0, 0, 0}};


    }


}
