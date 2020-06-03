package recursion.dp;

/**
 * @author andy-liu
 * @date 2020/5/27 - 8:40 PM
 */
public class ChessBoard {


    public static int ways(int x, int y, int k) {
        if (k == 0) {
            return x == 0 && y == 0 ? 1 : 0;
        }
        if (x < 0 || x > 9 || y < 0 || y > 10) {
            return 0;
        }
        return ways(x - 2, y - 1, k - 1)
                + ways(x - 2, y + 1, k - 1)
                + ways(x - 1, y + 2, k - 1)
                + ways(x + 1, y + 2, k - 1)

                + ways(x + 2, y - 1, k - 1)
                + ways(x + 2, y + 1, k - 1)

                + ways(x - 1, y - 2, k - 1)
                + ways(x + 1, y - 2, k - 1);
    }

    public static void main(String[] args){
        int ways = ways(2, 1, 1);
        System.out.println(ways);
    }
}

