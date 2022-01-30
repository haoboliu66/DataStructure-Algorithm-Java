package someOA.guidewire;

public class G2 {

    public static void main(String[] args) {
        System.out.println(solution0("011100"));
        System.out.println(solution0("111"));
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 400000; i++) {
            sb.append(1);
        }
        System.out.println(solution0(sb.toString()));
    }
// 11个1,  3个0     3 +

    //   11110101011111
    //    1111010101111
    //     111101010111
    //      11110101011
    //       1111010101
    //        111101010  => 1
    //         11110101
    //          1111010  => 1
    //           111101
    //            11110  => 1
    //             1111
    //              111
    //               11
    //                1 => 1
    //
    public static int solution0(String s) {
        char[] str = s.toCharArray();
        int leftBoundary = -1;
        for (int i = 0; i < str.length; i++) {
            if (str[i] != '0') {
                leftBoundary = i;
                break;
            }
        }
        if (leftBoundary == -1) return 0;
        int steps = 0;
        for (int i = str.length - 1; i >= leftBoundary + 1; i--) {
            steps += (str[i] == '1' ? 2 : 1);
        }
        steps += str[leftBoundary] == '1' ? 1 : 0;
        return steps;
    }

    // 0000110 => 0000011


    public static int toDecimal(String s) {
        char[] str = s.toCharArray();
        int res = 0, power = 0;
        for (int i = str.length - 1; i >= 0; i--) {
            int cur = (int) (str[i] - '0');
            res += cur * Math.pow(2, power++);
        }

        return res;
    }

}
