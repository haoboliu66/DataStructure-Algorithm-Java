package fundamental.bitwise;

public class Arithmetic {

    public static void main(String[] args) {
        short s = 10;
        s += 1;
        System.out.println(s);
//        int a = 10, b = 123;
//        System.out.println(a + b);
//        System.out.println(add(a, b));
    }

    public static int add(int a, int b) {
        int carry = ((a & b) << 1);
        while (carry != 0) {
            int tmp = a;
            a = a ^ b;
            b = tmp & b;
        }
        return a ^ b;
    }


}
