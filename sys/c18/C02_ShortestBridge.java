package sys.c18;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class C02_ShortestBridge {

    // https://leetcode.com/problems/shortest-bridge/

    public static void main(String[] args) {
        int[][] grid = {{1, 1, 1, 1, 1}, {1, 0, 0, 0, 1}, {1, 0, 1, 0, 1}, {1, 0, 0, 0, 1}, {1, 1, 1, 1, 1}};
        shortestBridge(grid);
    }

    public static int shortestBridge(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        // (i,j) => index
        int targetVal = 2;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    infect(grid, i, j, targetVal);
                    targetVal++;
                }

            }
        }
//        printMatrix(grid);
        Queue<Integer> q = new LinkedList<>();



        return 0;
    }

    private static void infect(int[][] grid, int i, int j, int targetVal) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid.length || grid[i][j] == 0
                || grid[i][j] == targetVal) {

            return;
        }
        grid[i][j] = targetVal;

        infect(grid, i + 1, j, targetVal);
        infect(grid, i - 1, j, targetVal);
        infect(grid, i, j + 1, targetVal);
        infect(grid, i, j - 1, targetVal);
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static class UnionFind {
        Map<Integer, Integer> parentMap;
        Map<Integer, Integer> sizeMap;

        public UnionFind() {
            parentMap = new HashMap<>();
            sizeMap = new HashMap<>();
        }

        public boolean isSameSet(int a, int b) {
            return false;
        }


    }


}
