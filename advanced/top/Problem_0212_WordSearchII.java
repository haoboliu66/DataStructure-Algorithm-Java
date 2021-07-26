package advanced.top;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Problem_0212_WordSearchII {

    // BFS - Brute Force
    public List<String> findWords(char[][] board, String[] words) {
        HashSet<String> ans = new HashSet<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                for (String s : words) {
                    if (process(board, i, j, s.toCharArray(), 0)) {
                        ans.add(s);
                    }
                }
            }
        }
        return new ArrayList(ans);
    }

    public boolean process(char[][] board, int i, int j, char[] str, int index) {
        if (index == str.length) return true;
        if (i < 0 || j < 0 || i > board.length - 1 || j > board[0].length - 1 || board[i][j] == 0) {
            return false;
        }
        if (str[index] != board[i][j]) return false;

        char tmp = board[i][j];
        board[i][j] = 0;
        boolean res = process(board, i + 1, j, str, index + 1) ||
                process(board, i - 1, j, str, index + 1) ||
                process(board, i, j + 1, str, index + 1) ||
                process(board, i, j - 1, str, index + 1);
        board[i][j] = tmp;
        return res;
    }

}
