package recursion;

import java.util.Arrays;

/**
 * @author andy-liu
 * @date 2020/5/24 - 3:14 PM
 */
public class NQueen {

    public static void main(String[] args) {
        int n = NQueensProblem(8);
        System.out.println(n);
    }

    public static int NQueensProblem(int n) {
        int[] record = new int[n];
        return process(0, record, n);
    }

    private static int process(int i, int[] record, int n) {
        if (i == n) {
            System.out.println(Arrays.toString(record));
            return 1;
        }
        int res = 0;
        for (int j = 0; j < n; j++) { //在当前i行, 尝试每一个位置
            if (isValid(record, i, j)) {  // conflict check
                record[i] = j;
                res += process(i + 1, record, n);
                //因为每次进入判断后都是直接修改值, 所以不需要还原现场
            }
        }
        return res;
    }

    private static boolean isValid(int[] record, int i, int j) {
        for (int k = 0; k < i; k++) { // 与i行之前的皇后比较
            if (record[k] == j || Math.abs(i - k) == Math.abs(record[k] - j)) {
                return false;
            }
        }
        return true;
    }


}
