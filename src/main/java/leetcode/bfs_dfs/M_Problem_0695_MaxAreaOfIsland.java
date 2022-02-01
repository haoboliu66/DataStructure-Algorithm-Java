package src.main.java.leetcode.bfs_dfs;

import java.util.LinkedList;
import java.util.Queue;

public class M_Problem_0695_MaxAreaOfIsland {

    private class Point{
        int x;
        int y;

        public Point(int i, int j){
            x = i;
            y = j;
        }
    }

    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int max = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 1){
                    int areaFromHere = infect(grid, i, j);
                    max= Math.max(max, areaFromHere);
                }

            }
        }
        return max;
    }

    /*  DFS with infect, similar to problem <Number Of Island> */
    public int infect(int[][] grid, int x, int y){
        int m = grid.length, n = grid[0].length;
        if(x < 0 || y < 0 || x == m || y == n) return 0;
        if(grid[x][y] != 1) return 0;

        int area = 1;
        grid[x][y] = 2;

        area += infect(grid, x - 1, y);
        area += infect(grid, x, y - 1);
        area += infect(grid, x + 1, y );
        area +=  infect(grid, x, y + 1);

        return area;
    }


    /* basic BFS, less efficient than DFS above  */
    public int bfs(int[][] grid, int i, int j){
        int m = grid.length, n = grid[0].length;
        int area = 1;
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(i , j));
        grid[i][j] = 2;
        while(!q.isEmpty()){
            Point cur = q.poll();
            int x = cur.x, y = cur.y;

            if(x > 0 && grid[x - 1][y] == 1){
                grid[x-1][y] = 2;
                q.offer(new Point(x-1,y));
                area++;
            }

            if(y > 0 && grid[x][y - 1] == 1){
                grid[x][y-1] = 2;
                q.offer(new Point(x,y-1));
                area++;
            }

            if(x < m - 1 && grid[x + 1][y] == 1){
                grid[x+1][y] = 2;
                q.offer(new Point(x+1,y));
                area++;
            }

            if(y < n - 1 && grid[x][y+1] == 1){
                grid[x][y+1] = 2;
                q.offer(new Point(x,y+1));
                area++;
            }

        }
        return area;
    }
}
