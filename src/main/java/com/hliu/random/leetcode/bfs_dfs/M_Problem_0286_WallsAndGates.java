package com.hliu.random.leetcode.bfs_dfs;

import java.util.LinkedList;
import java.util.Queue;

public class M_Problem_0286_WallsAndGates {

    private class Point {
        int x;
        int y;

        public Point(int i, int j) {
            x = i;
            y = j;
        }
    }

    /*  bfs with class Point  */
    public void wallsAndGates3(int[][] rooms) {
        int m = rooms.length, n = rooms[0].length;
        Queue<Point> q = new LinkedList<>();
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[i].length; j++) {
                if (rooms[i][j] == 0) {
                    q.offer(new Point(i, j));
                }
            }
        }
        while (!q.isEmpty()) {
            Point cur = q.poll();
            int x = cur.x, y = cur.y;

            if (x - 1 >= 0 && rooms[x - 1][y] == Integer.MAX_VALUE) {
                rooms[x - 1][y] = rooms[x][y] + 1;
                q.offer(new Point(x - 1, y));
            }
            if (x + 1 < m && rooms[x + 1][y] == Integer.MAX_VALUE) {
                rooms[x + 1][y] = rooms[x][y] + 1;
                q.offer(new Point(x + 1, y));
            }
            if (y - 1 >= 0 && rooms[x][y - 1] == Integer.MAX_VALUE) {
                q.offer(new Point(x, y - 1));
                rooms[x][y - 1] = rooms[x][y] + 1;
            }
            if (y + 1 < n && rooms[x][y + 1] == Integer.MAX_VALUE) {
                q.offer(new Point(x, y + 1));
                rooms[x][y + 1] = rooms[x][y] + 1;
            }
        }
    }


    public class Pair {
        int x;
        int y;
        int count;

        public Pair(int i, int j, int c) {
            x = i;
            y = j;
            count = c;
        }
    }

    /*  bfs with class Pair  */
    // correct but not efficient
    public void wallsAndGates2(int[][] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[i].length; j++) {
                if (rooms[i][j] == Integer.MAX_VALUE) {
                    rooms[i][j] = Math.min(rooms[i][j], bfs(rooms, i, j));
                }
            }
        }
    }

    public int bfs2(int[][] rooms, int i, int j) {
        int m = rooms.length, n = rooms[0].length;
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(i, j, 1));

        boolean[][] visited = new boolean[m][n];
        visited[i][j] = true;

        while (!q.isEmpty()) {
            Pair cur = q.poll();
            int x = cur.x, y = cur.y, count = cur.count;

            if (x > 0 && !visited[x - 1][y] && rooms[x - 1][y] != -1) {
                if (rooms[x - 1][y] == 0) return count;
                q.offer(new Pair(x - 1, y, count + 1));
                visited[x - 1][y] = true;
            }
            if (x < m - 1 && !visited[x + 1][y] && rooms[x + 1][y] != -1) {
                if (rooms[x + 1][y] == 0) return count;
                q.offer(new Pair(x + 1, y, count + 1));
                visited[x + 1][y] = true;
            }
            if (y > 0 && !visited[x][y - 1] && rooms[x][y - 1] != -1) {
                if (rooms[x][y - 1] == 0) return count;
                q.offer(new Pair(x, y - 1, count + 1));
                visited[x][y - 1] = true;
            }
            if (y < n - 1 && !visited[x][y + 1] && rooms[x][y + 1] != -1) {
                if (rooms[x][y + 1] == 0) return count;
                q.offer(new Pair(x, y + 1, count + 1));
                visited[x][y + 1] = true;
            }
        }
        return Integer.MAX_VALUE;
    }


    /*  wrong bfs  */
    public void wallsAndGates(int[][] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[i].length; j++) {
                if (rooms[i][j] == Integer.MAX_VALUE) {
                    rooms[i][j] = Math.min(rooms[i][j], bfs(rooms, i, j));
                }
            }
        }
    }
    /*
    1. room[i][j] no value assigned to start point
    2. could fill in all the empty rooms before the second gate starts
     */
    public int bfs(int[][] rooms, int i, int j) {
        int m = rooms.length, n = rooms[0].length;
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(i, j, 1));

        boolean[][] visited = new boolean[m][n];
        while (!q.isEmpty()) {
            Pair cur = q.poll();
            int x = cur.x, y = cur.y, count = cur.count;

            if (rooms[x][y] == 0) {
                return count;
            }
            if (x > 0 && !visited[x - 1][y] && rooms[x - 1][y] == Integer.MAX_VALUE) {
                q.offer(new Pair(x - 1, y, count + 1));
                visited[x - 1][y] = true;
            }
            if (x < m - 1 && !visited[x + 1][y] && rooms[x + 1][y] == Integer.MAX_VALUE) {
                q.offer(new Pair(x + 1, y, count + 1));
                visited[x + 1][y] = true;
            }
            if (y > 0 && !visited[x][y - 1] && rooms[x][y - 1] == Integer.MAX_VALUE) {
                q.offer(new Pair(x, y - 1, count + 1));
                visited[x][y - 1] = true;
            }
            if (y < n - 1 && !visited[x][y + 1] && rooms[x][y + 1] == Integer.MAX_VALUE) {
                q.offer(new Pair(x, y + 1, count + 1));
                visited[x][y + 1] = true;
            }

        }
        return Integer.MAX_VALUE;
    }

}
