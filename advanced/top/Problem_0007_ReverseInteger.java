package advanced.top;

public class Problem_0007_ReverseInteger {

    /*
    技巧: 负数的绝对值域比正数的大1个, 不是所有的负数都能转成正数操作, 所以把正数统一转成负数来操作
     */

    public int reverse(int x) {
        boolean isPositive = ((x >>> 31) & 1) == 0;

        int num = isPositive ? ((~x) + 1) : x; // num < 0

        int res = 0;
        while (num != 0) {
            if (res < Integer.MIN_VALUE / 10
                    || res == Integer.MIN_VALUE / 10 && num < Integer.MIN_VALUE % 10) {
                return 0;
            }
            res = res * 10 + num % 10;
            num /= 10;
        }

        return isPositive ? -res : res;
    }

}
