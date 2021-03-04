package top;

public class Problem_0079_WordSearch {

    /*
    DFS, 增加现场, 避免走回头路
     */

    public boolean exist(char[][] board, String word) {

        char[] str = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {

                if (process(board, i, j, str, 0)) {
                    return true;
                }
            }
        }

        return false;
    }


    public boolean process(char[][] board, int i, int j, char[] str, int k) {

        // 走过str最后一个字符了, 说明都找到了
        if (k == str.length) {
            return true;
        }

        // 越界检查
        if (i < 0 || i > board.length - 1 || j < 0 || j > board[i].length - 1) {
            return false;
        }

        if (board[i][j] != str[k]) {
            return false;
        }

        // board[i][j] == str[k]; 需要继续向其他位置走
        char tmp = board[i][j];
        board[i][j] = 0;

        boolean res = process(board, i + 1, j, str, k + 1) ||
                process(board, i - 1, j, str, k + 1) ||
                process(board, i, j + 1, str, k + 1) ||
                process(board, i, j - 1, str, k + 1);

        board[i][j] = tmp;

        return res;
    }
}
