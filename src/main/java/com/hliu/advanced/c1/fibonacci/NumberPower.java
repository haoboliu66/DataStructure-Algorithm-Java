package src.main.java.advanced.c1.fibonacci;


 // 543, 501

public class NumberPower {

    // 如何计算一个数字的N次方尽量快, 2分法

    //  10 ^ 75    75 ==> 1 0 0 1 0 1 1
    public static double pow(int base, int p) {
        double res = 1;
        double t = base;
        while (p != 0) {
            boolean lastDigitIsZero = (p & 1) != 0;
            if (lastDigitIsZero) {
                res *= t;
            }
            t *= t; // 每次t和自己相乘   1次方--2次方--4次方--8次方--16次方的增长
            p >>= 1; // get rid of the last bit
        }

        return res;
    }

    public static void main(String[] args) {
        int base = 2;
        int p = 3;
        for (int i = 0; i < 1000000; i++) {
            base = (int) (Math.random() * 10);
            p = (int) (Math.random() * 10);
            if (pow(base, p) != Math.pow(base, p)) {
                System.out.println("Oops");
                System.out.println(pow(base, p));
                System.out.println(Math.pow(base, p));
                break;
            }
        }
        System.out.println("done");
    }


}
