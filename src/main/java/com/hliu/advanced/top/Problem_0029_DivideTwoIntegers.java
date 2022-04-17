package src.main.java.advanced.top;

public class Problem_0029_DivideTwoIntegers {

    public static int divide(int dividend, int divisor) {

        return 0;
    }


    /**
     * add operation
     * @param a
     * @param b
     * @return
     */
    public static int add(int a, int b) {
        int sum = a;
        while (b != 0) {
            sum = a ^ b;
            b = (a & b) << 1;
            a = sum;
        }
        return sum;
    }

    /*
    add(a, -b)  ===> -b == add(~b, 1)
     */

    /**
     * subtract operation
     * @param a
     * @param b
     * @return
     */
    public static int minus(int a, int b) {
        return add(a, negNum(b));
    }

    public static int negNum(int b) {
        return add(~b, 1);
    }




    public static void main(String[] args) {

        int times = 1000000000;
        System.out.println("begin");
        for (int i = 0; i < times; i++) {
            int a = (int) (Math.random() * 500);
            int b = (int) (Math.random() * 500);

            int c = minus(a, b);
            int d = a - b;
            if (c != d) {
                System.out.println("Oops");
                break;
            }
        }
        System.out.println("done");


    }


}
