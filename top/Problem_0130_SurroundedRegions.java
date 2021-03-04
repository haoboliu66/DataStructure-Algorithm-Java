package top;

public class Problem_0130_SurroundedRegions {

    public void solve(char[][] board) {

    }

    public void process(char[][] board, int i, int j) {


        process(board, i + 1, j);
        process(board, i - 1, j);
        process(board, i, j + 1);
        process(board, i, j - 1);
    }

    public void solve1(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        int[][] dp = new int[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (infect(board, i, j, dp)) {
                    board[i][j] = 'T';
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'T') {
                    board[i][j] = 'X';
                }
            }
        }
    }


    public boolean infect(char[][] board, int i, int j, int[][] dp) {
        if (i < 0 || i == board.length || j < 0 || j == board[i].length) {
            return false;
        }
        if (dp[i][j] != 0) {
            return dp[i][j] == 1;
        }
        boolean res = true;
        if (board[i][j] == 'O') {
            board[i][j] = 'T';
            res = infect(board, i + 1, j, dp) &&
                    infect(board, i - 1, j, dp) &&
                    infect(board, i, j + 1, dp) &&
                    infect(board, i, j - 1, dp);

            board[i][j] = 'O';
        }
        dp[i][j] = res ? 1 : -1;
        return res;
    }


    public static void solve2(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        int row = board.length;
        int col = board[0].length;
        for (int i = 0; i < row; i++) {
            if (board[i][0] == 'O') {
                infect2(board, i, 0);
                board[i][0] = 'O';
            }
        }
        for (int j = 0; j < col; j++) {
            if (board[row - 1][j] == 'O') {
                infect2(board, row - 1, j);
                board[row - 1][j] = 'O';
            }
        }
        for (int j = 0; j < col; j++) {
            if (board[0][j] == 'O') {
                infect2(board, 0, j);
                board[0][j] = 'O';
            }
        }
        for (int i = 0; i < row; i++) {
            if (board[i][col - 1] == 'O') {
                infect2(board, i, col - 1);
                board[i][col - 1] = 'O';
            }
        }

        for (int i = 1; i < row - 1; i++) {
            for (int j = 1; j < col - 1; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }


    public static void infect2(char[][] board, int i, int j) {
        if (i < 0 || i == board.length || j < 0 || j == board[i].length) {
            return;
        }
        if (board[i][j] == 'O') {
            board[i][j] = 'F';
            infect2(board, i + 1, j);
            infect2(board, i - 1, j);
            infect2(board, i, j + 1);
            infect2(board, i, j - 1);
        }
    }


    public static void main(String[] args) {

        /*
        char型变量中能不能存贮一个中文汉字?为什么?
能够定义成为一个中文的，因为java中以unicode编码，一个char占2个字节，所以放一个中文是没问题的
         */
        String[][] b = {{"X", "O", "O", "X", "X", "X", "O", "X", "O", "O"},
                {"X", "O", "X", "X", "X", "X", "X", "X", "X", "X"},
                {"X", "X", "X", "X", "O", "X", "X", "X", "X", "X"},
                {"X", "O", "X", "X", "X", "O", "X", "X", "X", "O"},
                {"O", "X", "X", "X", "O", "X", "O", "X", "O", "X"},
                {"X", "X", "O", "X", "X", "O", "O", "X", "X", "X"},
                {"O", "X", "X", "O", "O", "X", "O", "X", "X", "O"},
                {"O", "X", "X", "X", "X", "X", "O", "X", "X", "X"},
                {"X", "O", "O", "X", "X", "O", "X", "X", "O", "O"},
                {"X", "X", "X", "O", "O", "X", "O", "X", "X", "O"}};

        char[][] board = new char[b.length][b[0].length];
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                board[i][j] = b[i][j].charAt(0);
            }
        }

        char[][] board1 = {{'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}};

        solve2(board1);

        printBoard(board1);
    }

    public static void printBoard(char[][] b) {
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                System.out.print(b[i][j] + " ");
            }
            System.out.println();
        }
    }


}
