package advanced;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PlanetMap {

    public static int transmissionLength(List<List<Character>> map) {
        char[][] matrix = getMatrix(map);
        return process(matrix, 0, 0);
    }

    public static int process(char[][] matrix, int i, int j) {
        int m = matrix.length, n = matrix[0].length;
//        if (i < 0 || i == m || j < 0 || j == n) return -1;
        if (matrix[i][j] == 'Z' || matrix[i][j] == 'E') return -1;
        if (i == m - 1 && j == n - 1) return 1;

        char tmp = matrix[i][j];
        matrix[i][j] = 'Z';
        int up = i - 1 >= 0 ? process(matrix, i - 1, j) : -1;
        int down = i + 1 < m ? process(matrix, i + 1, j) : -1;
        int left = j - 1 >= 0 ? process(matrix, i, j - 1) : -1;
        int right = j + 1 < n ? process(matrix, i, j + 1) : -1;
        int res = Integer.MAX_VALUE;
        if (up > 0) {
            res = Math.min(up, res);
        }
        if (down > 0) {
            res = Math.min(up, res);
        }
        if (left > 0) {
            res = Math.min(up, res);
        }
        if (right > 0) {
            res = Math.min(up, res);
        }
        if (res == Integer.MAX_VALUE) {
            return -1;
        }
        matrix[i][j] = tmp;
        return res + 1;
    }

    public static char[][] getMatrix(List<List<Character>> map) {
        int M = map.size();
        int N = map.get(0).size();
        char[][] matrix = new char[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = map.get(i).get(j);
            }
        }
        return matrix;
    }


    class Pair {
        int x;
        int y;
        int count;

        Pair(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }

    public int shortestPathBinaryMatrix(List<List<Character>> map) {
        char[][] matrix = getMatrix(map);
        return BFS(matrix, 0, 0, matrix.length - 1, matrix[0].length - 1);
    }

    public int BFS(char grid[][], int start_x, int start_y, int target_x, int target_y) {
        Queue<Pair> q = new LinkedList<>();

        q.add(new Pair(start_x, start_y, 1));

        while (q.size() > 0) {

            Pair cur = q.remove();
            int x = cur.x;
            int y = cur.y;
            int count = cur.count;

            if (x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && grid[x][y] != 'E') {

                grid[x][y] = 'E';

                if (x == target_x && y == target_y) {
                    return cur.count;
                }

                q.add(new Pair(x - 1, y, count + 1));
                q.add(new Pair(x, y + 1, count + 1));
                q.add(new Pair(x + 1, y, count + 1));
                q.add(new Pair(x, y - 1, count + 1));
            }
        }
        return -1;
    }


}
