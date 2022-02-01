package src.main.java.someOA.IMC;

import java.util.LinkedList;
import java.util.Queue;

public class KnightPath {


    public static int moves0(int n, int startRow, int startCol, int endRow, int endCol, int bishopRow, int bishopCol) {
        if (startRow < 0 || startCol < 0 || endRow < 0 || endCol < 0 || startRow >= n || startCol >= n || endRow >= n || endCol >= n) {
            return -1;
        }
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];
        boolean immuned = false;
        int step = 0;
        queue.offer(new Point(startRow, startCol, immuned));
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Point cur = queue.poll();
                if (cur.x == bishopRow && cur.y == bishopCol) {
                    immuned = true;
                }
                if (cur.x == endRow && cur.y == endCol) {
                    return step;
                }

                if (cur.x + 2 < n && cur.y + 1 < n && !visited[cur.x + 2][cur.y + 1]) {

                    if (valid(cur.x + 2, cur.y + 1, bishopRow, bishopCol, immuned)) {
                        visited[cur.x + 2][cur.y + 1] = true;
                        queue.offer(new Point(cur.x + 2, cur.y + 1, immuned));
                    }

                }
                if (cur.x + 2 < n && cur.y - 1 >= 0 && !visited[cur.x + 2][cur.y - 1]) {

                    if (valid(cur.x + 2, cur.y - 1, bishopRow, bishopCol, immuned)) {
                        visited[cur.x + 2][cur.y - 1] = true;
                        queue.offer(new Point(cur.x + 2, cur.y - 1, immuned));
                    }
                }

                if (cur.x - 2 >= 0 && cur.y + 1 < n && !visited[cur.x - 2][cur.y + 1]) {

                    if (valid(cur.x - 2, cur.y + 1, bishopRow, bishopCol, immuned)) {
                        visited[cur.x - 2][cur.y + 1] = true;
                        queue.offer(new Point(cur.x + 2, cur.y + 1, immuned));
                    }

                }
                if (cur.x - 2 >= 0 && cur.y - 1 >= 0 && !visited[cur.x - 2][cur.y - 1]) {

                    if (valid(cur.x - 2, cur.y - 1, bishopRow, bishopCol, immuned)) {
                        visited[cur.x - 2][cur.y - 1] = true;
                        queue.offer(new Point(cur.x - 2, cur.y - 1, immuned));
                    }
                }


                if (cur.x - 1 >= 0 && cur.y + 2 < n && !visited[cur.x - 1][cur.y + 2]) {

                    if (valid(cur.x - 1, cur.y + 2, bishopRow, bishopCol, immuned)) {
                        visited[cur.x - 1][cur.y + 2] = true;
                        queue.offer(new Point(cur.x - 1, cur.y + 2, immuned));
                    }

                }
                if (cur.x - 1 >= 0 && cur.y - 2 >= 0 && !visited[cur.x - 1][cur.y - 2]) {

                    if (valid(cur.x - 1, cur.y - 2, bishopRow, bishopCol, immuned)) {
                        visited[cur.x - 1][cur.y - 2] = true;
                        queue.offer(new Point(cur.x - 1, cur.y - 2, immuned));
                    }
                }

                if (cur.x + 1 < n && cur.y + 2 < n && !visited[cur.x + 1][cur.y + 2]) {

                    if (valid(cur.x + 1, cur.y + 2, bishopRow, bishopCol, immuned)) {
                        visited[cur.x + 1][cur.y + 2] = true;
                        queue.offer(new Point(cur.x + 1, cur.y + 2, immuned));
                    }

                }
                if (cur.x + 1 < n && cur.y - 2 >= 0 && !visited[cur.x + 1][cur.y - 2]) {
                    if (valid(cur.x + 1, cur.y - 2, bishopRow, bishopCol, immuned)) {
                        visited[cur.x + 1][cur.y - 2] = true;
                        queue.offer(new Point(cur.x + 1, cur.y - 2, immuned));
                    }
                }
            }
            step++;
        }
        return -1;
    }

    public static boolean valid(int i, int j, int bishopRow, int bishopCol, boolean immuned) {
        if (immuned) {
            return true;
        }
        return !(Math.abs(i - j) == Math.abs(bishopRow - bishopCol));
    }

    private static class Point {
        int x;
        int y;
        boolean immuned;

        public Point(int x, int y, boolean immuned) {
            this.x = x;
            this.y = y;
            this.immuned = immuned;
        }
    }


    public static int moves(int n, int startRow, int startCol, int endRow, int endCol, int bishopRow, int bishopCol) {

        return process(n, startRow, startCol, endRow, endCol, bishopRow, bishopCol, false);
    }


    public static int process(int n, int i, int j, int endRow, int endCol, int bishopRow, int bishopCol, boolean killed) {
        if (i < 0 || j < 0 || i >= n || j >= n) {
            return -1;
        }
        if (valid(i, j, bishopRow, bishopCol, killed)) {
            return -1;
        }
        if (i == bishopRow && j == bishopCol) {
            killed = true;
        }
        int path = Integer.MAX_VALUE;
        int p1 = process(n, i + 1, j + 2, endRow, endCol, bishopRow, bishopCol, killed);
        int p2 = process(n, i - 2, j + 1, endRow, endCol, bishopRow, bishopCol, killed);
        int p3 = process(n, i - 2, j - 1, endRow, endCol, bishopRow, bishopCol, killed);
        int p4 = process(n, i + 1, j - 2, endRow, endCol, bishopRow, bishopCol, killed);
        if (p1 != -1) {
            path = Math.min(path, p1);
        }
        if (p2 != -1) {
            path = Math.min(path, p2);
        }
        if (p3 != -1) {
            path = Math.min(path, p3);
        }
        if (p4 != -1) {
            path = Math.min(path, p4);
        }

        return path == Integer.MAX_VALUE ? -1 : path;
    }


}
