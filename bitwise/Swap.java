package bitwise;


public class Swap {


    public static void main(String[] args) {

        int a = (int) (Math.random() * 50);
        int b = (int) (Math.random() * 50);
        System.out.println("a == " + a + " b == " + b);

        int tmp = a ^ b;
        a = tmp ^ a;
        b = tmp ^ a;

        System.out.println("a == " + a + " b == " + b);

    }
}
