package advanced.top;

public class Problem_0050_Pow {

    /*
    特别注意的一种情况是: 当power为Integer.MIN_VALUE时, ~power+1 还是它本身,
    所以在进行power右移过程中要采用无符号右移, 否则>>导致左侧一直补1, 会进入死循环
     */
    public static double myPow(double x, int n) {
        if (x == 1.0D || n == 0) {
            return 1;
        }
        int power = n > 0 ? n : ~n + 1;
        double base = x, res = 1;
        while (power != 0) {
            boolean lastDigitOne = (power & 1) == 1;
            if (lastDigitOne) {
                res *= base;
            }
            base *= base;
            power >>>= 1;
        }
        return n > 0 ? res : 1 / res;
    }

}
