package leetcode.bfs_dfs;

import java.util.LinkedList;
import java.util.Queue;

public class M_Problem_0994_RottingOranges {

    /*  BFS  */

    private static class Point{
        int x;
        int y;

        public Point(int i, int j){
            x = i;
            y = j;
        }
    }

    public static int orangesRotting(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Queue<Point> q = new LinkedList<>();
        int numOfOne = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 2){
                    q.offer(new Point(i, j));
                }
                if(grid[i][j] == 1){
                    numOfOne++;
                }
            }
        }

        if(numOfOne == 0) return 0;   // no orange to be rotten
        if(q.size() == 0) return -1;  // no rotten orange exists, cannot spread

        int minute = 0;
        while(!q.isEmpty()){
            int size = q.size();
            // go through each Point at one layer
            for(int i = 0; i < size; i++){
                Point cur = q.poll();
                int x = cur.x, y = cur.y;

                if(x > 0 && grid[x-1][y] == 1){
                    grid[x-1][y] = 2;
                    q.offer(new Point(x - 1, y));
                    numOfOne--;

                }
                if(x < m - 1 && grid[x + 1][y] == 1){
                    grid[x+1][y] = 2;
                    q.offer(new Point(x + 1, y));
                    numOfOne--;

                }
                if(y > 0 && grid[x][y - 1] == 1){
                    grid[x][y - 1] = 2;
                    q.offer(new Point(x , y - 1));
                    numOfOne--;

                }
                if(y < n - 1 && grid[x][y + 1] == 1){
                    grid[x][y + 1] = 2;
                    q.offer(new Point(x , y + 1));
                    numOfOne--;
                }
            }
            // no neighbours at next layer
            if(q.size() > 0){
                minute++;
            }
        }

        return numOfOne != 0? -1: minute;
    }



}
