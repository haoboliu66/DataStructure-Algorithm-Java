package fundamental.bitwise;


public class Swap {

    public static int numberOfTrailingZeros(int i) {

        int z = i & ((~i) + 1);

        int count = 0;
        while (z != 0) {
            z = z >> 1;
            count++;
        }
        return count - 1;
    }

    public static void main(String[] args) {

//        int range = 50000;
//        int times = 1000000;
//        int a;
//        System.out.println("begin");
//        for (int i = 0; i < times; i++) {
//            a = (int) ((Math.random() + 1) * range) * (Math.random() > 0.5 ? 1 : -1);
////            System.out.println(a);
//            if (Integer.numberOfTrailingZeros(a) != numberOfTrailingZeros(a)) {
//                System.out.println("Oops");
//                System.out.println("a == " + a);
//                break;
//            }
//        }
//        System.out.println("end");

//
//        int a = (int) (Math.random() * 50);
//        int b = (int) (Math.random() * 50);
//        System.out.println("a == " + a + " b == " + b);
//
//        a = a + b;
//        b = a - b;
//        a = a - b;
//
//        System.out.println("a == " + a + " b == " + b);

    }
}
