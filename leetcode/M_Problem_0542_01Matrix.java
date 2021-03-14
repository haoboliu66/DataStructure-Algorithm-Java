package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class M_Problem_0542_01Matrix {

    public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] res = new int[m][n];
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited  = new boolean[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == 1){
                    q.offer(new int[]{i, j});
                }
            }
        }

        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; i++){
                int[] cur = q.poll();
                int x = cur[0], y = cur[1];
                if(x >  0 && !visited[x-1][y]  && matrix[x - 1][y] == 0){
                    visited[x-1][y] = true;
                    res[x - 1][y] = res[x][y] + 1;
                    // matrix[x][y] = 0;
                    q.offer(new int[]{x - 1, y});
                }
                if(x < m - 1 && !visited[x + 1][y] && matrix[x + 1][y] == 0){
                    visited[x + 1][y] = true;
                    res[x + 1][y] = res[x][y] + 1;
                    // matrix[x][y] = 0;
                    q.offer(new int[]{x + 1, y});
                }
                if(y > 0 && !visited[x][y-1]  && matrix[x][y-1] == 0){
                    visited[x][y-1] = true;
                    res[x][y-1] = res[x][y] + 1;
                    //  matrix[x][y] = 0;
                    q.offer(new int[]{x, y - 1});
                }
                if(y < n - 1 && !visited[x][y + 1]   && matrix[x][y + 1] == 0){
                    visited[x][y + 1] = true;
                    res[x][y + 1] = res[x][y] + 1;
                    // matrix[x][y] = 0;
                    q.offer(new int[]{x, y + 1});
                }
            }

        }

        return res;
    }
}
