package src.main.java.leetcode.bfs_dfs;

import java.util.LinkedList;
import java.util.Queue;

public class M_Problem_1730_ShortestPathToGetFood {

    /*

    '*' is your location. There is exactly one '*' cell.
    '#' is a food cell. There may be multiple food cells.
    'O' is free space, and you can travel through these cells.
    'X' is an obstacle, and you cannot travel through these cells.

     */

    public int getFood(char[][] grid) {

        int m = grid.length, n = grid[0].length;

        Queue<int[]> q = new LinkedList<>();
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == '*'){
                    grid[i][j] = 'X';
                    q.offer(new int[]{i,j});
                }
            }
        }
        int dis = 0;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; i++){

                int[] cur = q.poll();
                int x = cur[0], y = cur[1];

                if(x > 0 && grid[x-1][y] != 'X'){
                    if(grid[x-1][y] == '#') return dis+1;
                    q.offer(new int[]{x-1,y});
                    grid[x-1][y] = 'X';
                }
                if(x < m - 1 && grid[x+1][y] != 'X'){
                    if(grid[x+1][y] == '#') return dis+1;
                    q.offer(new int[]{x+1,y});
                    grid[x+1][y] = 'X';
                }
                if(y > 0 && grid[x][y-1] != 'X'){
                    if(grid[x][y-1] == '#') return dis+1;
                    q.offer(new int[]{x,y-1});
                    grid[x][y-1] = 'X';
                }

                if(y < n - 1 && grid[x][y+1] != 'X'){
                    if(grid[x][y+1] == '#') return dis+1;
                    q.offer(new int[]{x,y+1});
                    grid[x][y+1] = 'X';
                }
            }
            if(q.size() > 0) dis++;


        }

        return -1;
    }
}
